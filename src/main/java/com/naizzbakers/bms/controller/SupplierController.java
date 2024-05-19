package com.naizzbakers.bms.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naizzbakers.bms.dto.SupplierDto;
import com.naizzbakers.bms.model.Supplier;
import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.service.SupplierService;
import com.naizzbakers.bms.service.UserService;

@Controller
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/listSuppliers")
	public ModelAndView listSuppliers() {
		System.out.println("listSuppliers");
	    ModelAndView mav = new ModelAndView("admin/supplier/supplier-list");
		
		List<Supplier> listSuppliers = supplierService.get();
	    mav.addObject("listSuppliers", listSuppliers);
		
	    return mav;
	}
	
	@RequestMapping("/supplierDetails")
	public ModelAndView supplierDetails(@RequestParam Long supplierId, Authentication authentication) {
		System.out.println("supplierDetails " + supplierId);
		ModelAndView mav = new ModelAndView("admin/supplier/supplier-details");
		
		User user = userService.findByUsername(authentication.getName());
		Supplier supplier = supplierService.get(supplierId); 	
				
		mav.addObject("user", user);
		mav.addObject("supplier", supplier);
	    
	    return mav;
	}
	
	@GetMapping("/addSupplier")
	public ModelAndView addSupplier(Authentication authentication) {
		System.out.println("addSupplier");
		ModelAndView mav = new ModelAndView("admin/supplier/supplier-details");
		
		Supplier supplier = new Supplier();
		
		User user = userService.findByUsername(authentication.getName());
		mav.addObject("user", user);
		
		supplier.setCreateBy(user);
		supplier.setCreateDate(LocalDateTime.now());
		supplier.setUpdateBy(user);
		supplier.setUpdateDate(LocalDateTime.now());
		supplier.setStatus(Supplier.STATUS.DEACTIVE);
		mav.addObject("supplier", supplier);
				
		return mav;
	}
	
	@RequestMapping("/secure/saveSupplier")
	public ResponseEntity<Long> saveSupplier(@ModelAttribute SupplierDto supplierDto, HttpSession session) {
		System.out.println("saveSupplier " + supplierDto);
		boolean isDupplicate = false;
		
		Supplier supplier;
		
		User user = (User)session.getAttribute("user");
        
		List<Supplier> listSuppliers = supplierService.get();
		for (Supplier i : listSuppliers) 
		{
			if(((i.getSupplierId()) == (supplierDto.getSupplierId()))) {
				isDupplicate = true;
				break;
			}
		}
		
		if(!isDupplicate) {
			supplier = new Supplier();
			supplier.setSupplierSerial(supplierDto.getSupplierSerial());
			supplier.setSupplierName(supplierDto.getSupplierName());
			supplier.setSpecialNote(supplierDto.getSpecialNote());
			supplier.setImage(supplierDto.getImage());
			supplier.setAddress(supplierDto.getAddress());
			supplier.setContactPerson(supplierDto.getContactPerson());
			supplier.setEmail(supplierDto.getEmail());
			supplier.setMobile1(supplierDto.getMobile1());
			supplier.setMobile2(supplierDto.getMobile2());
			supplier.setCreateBy(user);
			supplier.setCreateDate(LocalDateTime.now());
			supplier.setUpdateBy(user);
			supplier.setUpdateDate(LocalDateTime.now());
			supplier.setStatus(Supplier.STATUS.DEACTIVE);
						
			supplierService.save(supplier);
			
			return new ResponseEntity<>(supplier.getSupplierId(), HttpStatus.OK);
		}else {				
			supplier = supplierService.get(supplierDto.getSupplierId());
			supplier.setSupplierSerial(supplierDto.getSupplierSerial());
			supplier.setSupplierName(supplierDto.getSupplierName());
			supplier.setSpecialNote(supplierDto.getSpecialNote());
			supplier.setImage(supplierDto.getImage());
			supplier.setAddress(supplierDto.getAddress());
			supplier.setContactPerson(supplierDto.getContactPerson());
			supplier.setEmail(supplierDto.getEmail());
			supplier.setMobile1(supplierDto.getMobile1());
			supplier.setMobile2(supplierDto.getMobile2());
			supplier.setUpdateBy(user);
			supplier.setUpdateDate(LocalDateTime.now());
			supplier.setStatus(Supplier.STATUS.valueOf(supplierDto.getStatus()));
			supplierService.save(supplier);
			
			return new ResponseEntity<>(supplier.getSupplierId(), HttpStatus.OK);
		}
	}
		
	@GetMapping("/deleteSupplier")
	public ResponseEntity<String> deleteSupplier(@RequestParam Long supplierId) {
		System.out.println("deleteSupplier " + supplierId);
		
		try {
			supplierService.delete(supplierId);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			String message = "Supplier cannot be deleted. Already linked with another data.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/changeSupplierStatus")
	public ResponseEntity<String> changeSupplierStatus(@RequestParam Long supplierId,@RequestParam String sts, Authentication authentication) {
		System.out.println("changeInventoryStatus");
		String message = "";
		User user = userService.findByUsername(authentication.getName());
		
		try {
			Supplier supplier = supplierService.get(supplierId);
			supplier.setStatus(Supplier.STATUS.valueOf(sts));
			supplier.setUpdateBy(user);
			supplier.setUpdateDate(LocalDateTime.now());
			supplierService.save(supplier);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			message = "Supplier status cannot be change. Something went wrong.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
}
