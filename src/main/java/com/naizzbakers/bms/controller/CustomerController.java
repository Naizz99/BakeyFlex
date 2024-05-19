package com.naizzbakers.bms.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naizzbakers.bms.dto.CustomerDto;
import com.naizzbakers.bms.model.Customer;
import com.naizzbakers.bms.model.Password;
import com.naizzbakers.bms.model.Profile;
import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.service.CustomerOrderService;
import com.naizzbakers.bms.service.CustomerService;
import com.naizzbakers.bms.service.PasswordService;
import com.naizzbakers.bms.service.ProfileService;
import com.naizzbakers.bms.service.RoleService;
import com.naizzbakers.bms.service.UserService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/listCustomers")
	public ModelAndView listCustomers() {
		System.out.println("listCustomers");
	    ModelAndView mav = new ModelAndView("admin/customer/customer-list");
		
		List<Customer> listCustomers = customerService.get();
	    mav.addObject("listCustomers", listCustomers);
		
	    return mav;
	}
	
	@RequestMapping("/customerDetails")
	public ModelAndView customerDetails(@RequestParam Long customerId, Authentication authentication) {
		System.out.println("customerDetails " + customerId);
		ModelAndView mav = new ModelAndView("admin/customer/customer-details");
		
		User user = userService.findByUsername(authentication.getName());
		Customer customer = customerService.get(customerId); 	
				
		mav.addObject("user", user);
		mav.addObject("customer", customer);
		mav.addObject("customerUser", customer.getUserId());
		mav.addObject("orders", customerOrderService.getByCustomerId(customerId));
		mav.addObject("customerProfile", profileService.getByUserId(customer.getUserId()));
		mav.addObject("customerPassword", passwordService.getByUserId(customer.getUserId()));
		
	    return mav;
	}
	
	@GetMapping("/addCustomer")
	public ModelAndView addCustomer(Authentication authentication) {
		System.out.println("addCustomer");
		ModelAndView mav = new ModelAndView("admin/customer/customer-details");
				
		User user = userService.findByUsername(authentication.getName());
		mav.addObject("user", user);
		
		Customer customer = new Customer();
		mav.addObject("customer", customer);
		
		User customerUser = new User();
		customerUser.setCreateBy(user);
		customerUser.setCreateDate(LocalDateTime.now());
		customerUser.setUpdateBy(user);
		customerUser.setUpdateDate(LocalDateTime.now());
		mav.addObject("customerUser", customerUser);
		
		Password customerPassword = new Password();
		mav.addObject("customerPassword", customerPassword);
		
		Profile customerProfile = new Profile();
		mav.addObject("customerProfile", customerProfile);

		return mav;
	}
	
	@RequestMapping("/secure/saveCustomer")
	public ResponseEntity<Object> saveCustomer(@ModelAttribute CustomerDto customerDto, HttpSession session) {
		System.out.println("saveCustomer " + customerDto);
		boolean isDupplicate = false;
		
		Customer customer;
		
		User user = (User)session.getAttribute("user");
        		
		List<Customer> listCustomers = customerService.get();
		for (Customer i : listCustomers) 
		{
			if(((i.getCustomerId()) == (customerDto.getCustomerId()))) {
				isDupplicate = true;
				break;
			}
		}
		
		if(!isDupplicate) {
			
			List<User> listUsers = userService.get();
			for (User i : listUsers) 
			{
				if((i.getUsername()).equals(customerDto.getUsername())) {
					String message = "Cannot save customer due to dupplicate username";
					return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
				}
			}
			
			User customerUser = new User();
			customerUser.setFirstName(customerDto.getFirstName());
			customerUser.setMiddleName(null);
			customerUser.setLastName(customerDto.getLastName());
			customerUser.setBirthDay(customerDto.getBirthDay());
			customerUser.setEmail(customerDto.getEmail());
			customerUser.setGender(customerDto.getGender());
			customerUser.setMobile(customerDto.getMobile());
			customerUser.setNic(null);
			customerUser.setLogged(false);
			customerUser.setUsername(customerDto.getUsername());
			customerUser.setRole(roleService.getByRole("CUSTOMER"));
//			customerUser.setCreateBy(user);
			customerUser.setCreateDate(LocalDateTime.now());
//			customerUser.setUpdateBy(user);
			customerUser.setUpdateDate(LocalDateTime.now());
			customerUser.setStatus(User.STATUS.ACTIVE);
			userService.save(customerUser);
			
			Password customerPassword = new Password();
			customerPassword.setUserId(customerUser);
			customerPassword.setPassword(passwordEncoder.encode(customerDto.getPassword()));
			customerPassword.setLastUpdated(LocalDateTime.now());
			passwordService.save(customerPassword);
			
			Profile customerProfile = new Profile();
			customerProfile.setUserId(customerUser);
			customerProfile.setProfilePic(customerDto.getImage());
			customerProfile.setCoverImage(null);
			profileService.save(customerProfile);
			
			customer = new Customer();
			customer.setUserId(customerUser);
			customer.setAddressLine1(customerDto.getAddressLine1());
			customer.setAddressLine2(customerDto.getAddressLine2());
			customer.setAddressLine3(customerDto.getAddressLine3());
			customer.setAddressLine4(customerDto.getAddressLine4());
			customer.setCustomerCode(customerDto.getCustomerCode());
			customerService.save(customer);
			
			return new ResponseEntity<>(customer.getCustomerId(), HttpStatus.OK);
		}else {				
			customer = customerService.get(customerDto.getCustomerId());
			customer.setAddressLine1(customerDto.getAddressLine1());
			customer.setAddressLine2(customerDto.getAddressLine2());
			customer.setAddressLine3(customerDto.getAddressLine3());
			customer.setAddressLine4(customerDto.getAddressLine4());
			customer.setCustomerCode(customerDto.getCustomerCode());
			customerService.save(customer);
			
			User customerUser = customer.getUserId();
			customerUser.setFirstName(customerDto.getFirstName());
			customerUser.setMiddleName(null);
			customerUser.setLastName(customerDto.getLastName());
			customerUser.setBirthDay(customerDto.getBirthDay());
			customerUser.setEmail(customerDto.getEmail());
			customerUser.setGender(customerDto.getGender());
			customerUser.setMobile(customerDto.getMobile());
			customerUser.setNic(null);
//			customerUser.setUpdateBy(user);
			customerUser.setUpdateDate(LocalDateTime.now());
//			customerUser.setStatus(User.STATUS.valueOf(customerDto.getStatus()));
			userService.save(customerUser);
			
			Password customerPassword = passwordService.getByUserId(customerUser);
			customerPassword.setPassword(passwordEncoder.encode(customerDto.getPassword()));
			customerPassword.setLastUpdated(LocalDateTime.now());
			passwordService.save(customerPassword);
			
			Profile customerProfile = profileService.getByUserId(customerUser);
			customerProfile.setProfilePic(customerDto.getImage());
			customerProfile.setCoverImage(null);
			profileService.save(customerProfile);
			
			return new ResponseEntity<>(customer.getCustomerId(), HttpStatus.OK);
		}
	}
		
	@GetMapping("/deleteCustomer")
	public ResponseEntity<String> deleteCustomer(@RequestParam Long customerId) {
		System.out.println("deleteCustomer " + customerId);
		
		try {
			User tempId = customerService.get(customerId).getUserId();
			customerService.delete(customerId);
			passwordService.deleteByUserId(tempId);
			userService.delete(tempId.getUserId());			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			String message = "Customer cannot be deleted. Already linked with another data.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/changeCustomerStatus")
	public ResponseEntity<String> changeCustomerStatus(@RequestParam Long customerId,@RequestParam String sts, Authentication authentication) {
		System.out.println("changeInventoryStatus");
		String message = "";
		User user = userService.findByUsername(authentication.getName());
		
		try {
			Customer customer = customerService.get(customerId);
			User customerUser = userService.get(customer.getUserId().getUserId());
//			customerUser.setStatus(User.STATUS.valueOf(sts));
//			customerUser.setUpdateBy(user);
			customerUser.setUpdateDate(LocalDateTime.now());
			userService.save(customerUser);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			message = "Customer status cannot be change. Something went wrong.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
}
