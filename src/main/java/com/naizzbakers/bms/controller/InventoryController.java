package com.naizzbakers.bms.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import com.naizzbakers.bms.dto.InventoryDto;
import com.naizzbakers.bms.dto.InventoryRequestDto;
import com.naizzbakers.bms.dto.InventorySupplierLinkDto;
import com.naizzbakers.bms.model.Inventory;
import com.naizzbakers.bms.model.InventoryRequest;
import com.naizzbakers.bms.model.InventorySupplierLink;
import com.naizzbakers.bms.model.InventoryType;
import com.naizzbakers.bms.model.Measurement;
import com.naizzbakers.bms.model.Supplier;
import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.service.InventoryRequestService;
import com.naizzbakers.bms.service.InventoryService;
import com.naizzbakers.bms.service.InventorySupplierLinkService;
import com.naizzbakers.bms.service.InventoryTypeService;
import com.naizzbakers.bms.service.MeasurementService;
import com.naizzbakers.bms.service.SupplierService;
import com.naizzbakers.bms.service.UserService;

@Controller
//@RequestMapping("/secure")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private InventoryTypeService inventoryTypeService;
	
	@Autowired
	private InventoryRequestService inventoryRequestService;
	
	@Autowired
	private InventorySupplierLinkService inventorySupplierLinkService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private MeasurementService measurementService;
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/listInventories")
	public ModelAndView listInventories() {
		System.out.println("listInventories");
	    ModelAndView mav = new ModelAndView("admin/inventory/inventory-list");
		
		List<Inventory> listInventories = inventoryService.get();
	    mav.addObject("listInventories", listInventories);
		
	    return mav;
	}
	
	@RequestMapping("/inventoryDetails")
	public ModelAndView inventoryDetails(@RequestParam Long inventoryId, Authentication authentication) {
		System.out.println("inventoryDetails " + inventoryId);
		ModelAndView mav = new ModelAndView("admin/inventory/inventory-details");
		
		User user = userService.findByUsername(authentication.getName());
		Inventory inventory = inventoryService.get(inventoryId); 	
		
		int finalInventoryAmount = 0;
		for(InventorySupplierLink is : inventorySupplierLinkService.getByInventoryId(inventory)) {
			if(is.getStatus() == InventorySupplierLink.STATUS.AVAILABLE) {
				finalInventoryAmount += is.getAvailableAmount();
			}
		}
		
		if(inventory.getAvailableAmount() != finalInventoryAmount) {
			inventory.setAvailableAmount(finalInventoryAmount);
			inventory.setUpdateBy(user);
			inventory.setUpdateDate(LocalDateTime.now());
			inventoryService.save(inventory);
		}
		
		mav.addObject("user", user);
		mav.addObject("inventory", inventory);
	    mav.addObject("inventoryRequests", inventoryRequestService.getByInventoryIdAndStatus(inventoryService.get(inventoryId), InventoryRequest.STATUS.PENDING));
		mav.addObject("inventorySupplierLinks", inventorySupplierLinkService.getByInventoryId(inventoryService.get(inventoryId)));
		mav.addObject("inventoryTypeList", inventoryTypeService.getByStatus(InventoryType.STATUS.ACTIVE));
		mav.addObject("listSuppliers", supplierService.getByStatus(Supplier.STATUS.ACTIVE));
	    mav.addObject("unitList", measurementService.getByStatus(Measurement.STATUS.ACTIVE));
	    
	    return mav;
	}
	
	@GetMapping("/addInventory")
	public ModelAndView addInventory(Authentication authentication) {
		System.out.println("addInventory " + " authentication " + authentication);
		ModelAndView mav = new ModelAndView("admin/inventory/inventory-details");
		
		Inventory inventory = new Inventory();
		
		User user = userService.findByUsername(authentication.getName());
		
		inventory.setCreateBy(user);
		inventory.setCreateDate(LocalDateTime.now());
		inventory.setUpdateBy(user);
		inventory.setUpdateDate(LocalDateTime.now());
		inventory.setStatus(Inventory.STATUS.DISABLE);
		
		mav.addObject("inventory", inventory);
	    mav.addObject("InventoryRequests", new ArrayList<InventoryRequest>());
		mav.addObject("InventorySupplierLinks", new ArrayList<InventorySupplierLink>());
		mav.addObject("inventoryTypeList", inventoryTypeService.getByStatus(InventoryType.STATUS.ACTIVE));
		mav.addObject("listSuppliers", supplierService.getByStatus(Supplier.STATUS.ACTIVE));
	    mav.addObject("unitList", measurementService.getByStatus(Measurement.STATUS.ACTIVE));
	    
		return mav;
	}
	
	@RequestMapping("/secure/saveInventory")
	public ResponseEntity<Long> saveInventory(@ModelAttribute InventoryDto inventoryDto, HttpSession session) {
		System.out.println("saveInventory " + inventoryDto);
		boolean isDupplicate = false;
		
		Inventory inventory;
		
		User user = (User)session.getAttribute("user");
        
		List<Inventory> listInventories = inventoryService.get();
		for (Inventory i : listInventories) 
		{
			if(((i.getInventoryId()) == (inventoryDto.getInventoryId()))) {
				isDupplicate = true;
				break;
			}
		}
				
		if(!isDupplicate) {
			inventory = new Inventory();
			inventory.setInventorySerial(inventoryDto.getInventorySerial());
			inventory.setInventoryType(inventoryTypeService.get(inventoryDto.getInventoryType()));
			inventory.setInventoryName(inventoryDto.getInventoryName());
			inventory.setDescription(inventoryDto.getDescription());
			inventory.setSpecialNote(inventoryDto.getSpecialNote());
			inventory.setImage(inventoryDto.getImage());
			inventory.setUnitId(measurementService.get(Long.parseLong(inventoryDto.getUnitId())));
			inventory.setAvailableAmount(inventoryDto.getAvailableAmount());
			inventory.setUnitPrice(inventoryDto.getUnitPrice());
			inventory.setFirstExDate(inventoryDto.getFirstExDate());
			inventory.setCreateBy(user);
			inventory.setCreateDate(LocalDateTime.now());
			inventory.setUpdateBy(user);
			inventory.setUpdateDate(LocalDateTime.now());
			inventory.setStatus(Inventory.STATUS.DISABLE);
			
			inventoryService.save(inventory);
			
			return new ResponseEntity<Long>(inventory.getInventoryId(), HttpStatus.OK);
		}else {				
			inventory = inventoryService.get(inventoryDto.getInventoryId());
			inventory.setInventorySerial(inventoryDto.getInventorySerial());
			inventory.setInventoryType(inventoryTypeService.get(inventoryDto.getInventoryType()));
			inventory.setInventoryName(inventoryDto.getInventoryName());
			inventory.setDescription(inventoryDto.getDescription());
			inventory.setSpecialNote(inventoryDto.getSpecialNote());
			inventory.setImage(inventoryDto.getImage());
			inventory.setUnitId(measurementService.get(Long.parseLong(inventoryDto.getUnitId())));
			inventory.setAvailableAmount(inventoryDto.getAvailableAmount());
			inventory.setUnitPrice(inventoryDto.getUnitPrice());
			inventory.setFirstExDate(inventoryDto.getFirstExDate());
			inventory.setCreateBy(inventory.getCreateBy());
			inventory.setCreateDate(LocalDateTime.now());
			inventory.setUpdateBy(user);
			inventory.setUpdateDate(LocalDateTime.now());
			inventory.setStatus(Inventory.STATUS.valueOf(inventoryDto.getStatus()));
			
			inventoryService.save(inventory);
			
			return new ResponseEntity<Long>(inventory.getInventoryId(), HttpStatus.OK);
		}
	}
		
	@GetMapping("/deleteInventory")
	public ResponseEntity<String> deleteInventory(@RequestParam Long inventoryId) {
		System.out.println("deleteInventory " + inventoryId);
		boolean hasRelation = false;
		String message = "";
		
		List<InventoryRequest> listRequests = inventoryRequestService.getByInventoryId(inventoryService.get(inventoryId));
		if(listRequests.isEmpty()) {
			hasRelation = false;
		}else {
			hasRelation = true;
			message = "Inventory cannot be deleted. Inventory requests have been sent to the suppliers.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		try {
			if(!hasRelation) {
				for(InventorySupplierLink i : inventorySupplierLinkService.getByInventoryId(inventoryService.get(inventoryId))) {
					inventorySupplierLinkService.delete(i.getIslId());
				}
				inventoryService.delete(inventoryId);
			}
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			message = "Inventory cannot be deleted. Something went wrong.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/changeInventoryStatus")
	public ResponseEntity<String> changeInventoryStatus(@RequestParam Long inventoryId, @RequestParam String sts, Authentication authentication) {
		System.out.println("changeInventoryStatus");
		String message = "";
		User user = userService.findByUsername(authentication.getName());
		
		try {
			Inventory inventory = inventoryService.get(inventoryId);
			inventory.setStatus(Inventory.STATUS.valueOf(sts));
			inventory.setUpdateBy(user);
			inventory.setUpdateDate(LocalDateTime.now());
			inventoryService.save(inventory);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			message = "Inventory status cannot be change. Something went wrong.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/viewInventorySuppliers")
	public ModelAndView viewInventorySuppliers(@RequestParam Long inventoryId, Authentication authentication) {
		System.out.println("viewInventorySuppliers " + inventoryId);
		ModelAndView mav = new ModelAndView("admin/inventory/inventory-supplier-list");
						
		User user = userService.findByUsername(authentication.getName());
		
		mav.addObject("user", user);
		mav.addObject("inventory", inventoryService.get(inventoryId));
	    mav.addObject("inventorySupplierLinks", inventorySupplierLinkService.getByInventoryId(inventoryService.get(inventoryId)));
		mav.addObject("listSuppliers", supplierService.getByStatus(Supplier.STATUS.ACTIVE));
	    
	    return mav;
	}
	
	@RequestMapping("/secure/linkSupplier")
	public ResponseEntity<String> linkSupplier(@ModelAttribute InventorySupplierLinkDto linkSupplierDto, HttpSession session) {
		System.out.println("linkSupplier " + linkSupplierDto);
		boolean isDupplicate = false;
		
		User user = (User)session.getAttribute("user");
		Inventory inventory = inventoryService.get(linkSupplierDto.getInventoryId());
		String message = "";
		Supplier supplier;
		int finalInventoryAmount = 0;
		
		try {
			supplier = supplierService.getBySerial(linkSupplierDto.getSupplierId());
		}catch (Exception e) {
			System.out.println(e);
			message = "Supplier isn't available in the system. Please select another supplier";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		List<InventorySupplierLink> listSupplierInventories = inventorySupplierLinkService.get();
		for (InventorySupplierLink i : listSupplierInventories) 
		{
			if((i.getInventoryId().equals(inventory)) && (i.getSupplierId().equals(supplier))) {
				isDupplicate = true;
				break;
			}
		}
		
		try {
			if(!isDupplicate) {
				InventorySupplierLink inventorySupplier = new InventorySupplierLink();
				inventorySupplier.setInventoryId(inventory);
				inventorySupplier.setSupplierId(supplier);
				inventorySupplier.setUnitPrice(linkSupplierDto.getUnitPrice());
				inventorySupplier.setSpecialNote(linkSupplierDto.getSpecialNote());
				inventorySupplier.setAvailableAmount(linkSupplierDto.getAvailableAmount());
				inventorySupplier.setStatus(InventorySupplierLink.STATUS.valueOf(linkSupplierDto.getStatus()));
				inventorySupplier.setFirstExDate(linkSupplierDto.getFirstExDate());
				inventorySupplier.setImage(linkSupplierDto.getImage());
				inventorySupplier.setCreateBy(user);
				inventorySupplier.setCreateDate(LocalDateTime.now());
				inventorySupplier.setUpdateBy(user);
				inventorySupplier.setUpdateDate(LocalDateTime.now());
				inventorySupplierLinkService.save(inventorySupplier);
				for(InventorySupplierLink is : inventorySupplierLinkService.getByInventoryId(inventory)) {
					if(is.getStatus() == InventorySupplierLink.STATUS.AVAILABLE) {
						finalInventoryAmount += is.getAvailableAmount();
					}
				}
				
				if(inventory.getAvailableAmount() != finalInventoryAmount) {
					inventory.setAvailableAmount(finalInventoryAmount);
					inventory.setUpdateBy(user);
					inventory.setUpdateDate(LocalDateTime.now());
					inventoryService.save(inventory);
				}
				
				return new ResponseEntity<>("success", HttpStatus.OK);
			}else {
				message = "Already linked with selected supplier. Please update relevant record.";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/unLinkSupplier")
	public ResponseEntity<String> unLinkSupplier(@RequestParam Long islId) {
		System.out.println("unLinkSupplier " + islId + " |");
		boolean amountExceed = false;
		String message = "";
		
		if(inventorySupplierLinkService.get(islId).getAvailableAmount() > 0) {
			amountExceed = true;
			message = "Supplier cannot be removed. There are remaining inventories associated with this supplier.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}else {
			amountExceed = false;
		}

		try {
			if(!amountExceed) {
				inventorySupplierLinkService.delete(islId);
			}
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			message = "Supplier cannot be removed. Something went wrong.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/secure/availableAmountUpdate")
	public ResponseEntity<String> availableAmountUpdate(@ModelAttribute InventorySupplierLinkDto linkSupplierDto, HttpSession session) {
		System.out.println("availableAmountUpdate " + linkSupplierDto);
				
		User user = (User)session.getAttribute("user");
		Inventory inventory = inventoryService.get(linkSupplierDto.getInventoryId());
		int finalInventoryAmount = 0;
		int tempAmount = 0;
		
		try {
			InventorySupplierLink linkSupplier = inventorySupplierLinkService.get(linkSupplierDto.getIslId());
			
			tempAmount = linkSupplier.getAvailableAmount();
			
			if(tempAmount != linkSupplierDto.getAvailableAmount()) {
				linkSupplier.setAvailableAmount(linkSupplierDto.getAvailableAmount());
				linkSupplier.setUpdateBy(user);
				linkSupplier.setUpdateDate(LocalDateTime.now());
				inventorySupplierLinkService.save(linkSupplier);
				
				
				for(InventorySupplierLink is : inventorySupplierLinkService.getByInventoryId(inventory)) {
					if(is.getStatus() == InventorySupplierLink.STATUS.AVAILABLE) {
						finalInventoryAmount += is.getAvailableAmount();
					}
				}
				
				if(inventory.getAvailableAmount() != finalInventoryAmount) {
					inventory.setAvailableAmount(finalInventoryAmount);
					inventory.setUpdateBy(user);
					inventory.setUpdateDate(LocalDateTime.now());
					inventoryService.save(inventory);
				}
			}
						
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/secure/supplierInventoryStatusUpdate")
	public ResponseEntity<String> supplierInventoryStatusUpdate(@ModelAttribute InventorySupplierLinkDto linkSupplierDto, HttpSession session) {
		System.out.println("supplierInventoryStatusUpdate " + linkSupplierDto);
				
		User user = (User)session.getAttribute("user");
		
		InventorySupplierLink.STATUS tempStatus;
		InventorySupplierLink.STATUS newStatus;
		int finalInventoryAmount = 0;
		
		try {
			InventorySupplierLink linkSupplier = inventorySupplierLinkService.get(linkSupplierDto.getIslId());
			Inventory inventory = inventoryService.get(linkSupplierDto.getInventoryId());
			tempStatus = linkSupplier.getStatus();
			newStatus = InventorySupplierLink.STATUS.valueOf(linkSupplierDto.getStatus());
			
			if(tempStatus != newStatus) {
				linkSupplier.setStatus(newStatus);
				linkSupplier.setUpdateBy(user);
				linkSupplier.setUpdateDate(LocalDateTime.now());
				inventorySupplierLinkService.save(linkSupplier);
				
				for(InventorySupplierLink is : inventorySupplierLinkService.getByInventoryId(inventory)) {
					if(is.getStatus() == InventorySupplierLink.STATUS.AVAILABLE) {
						finalInventoryAmount += is.getAvailableAmount();
					}
				}
				
				if(inventory.getAvailableAmount() != finalInventoryAmount) {
					inventory.setAvailableAmount(finalInventoryAmount);
					inventory.setUpdateBy(user);
					inventory.setUpdateDate(LocalDateTime.now());
					inventoryService.save(inventory);
				}
			}
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/viewInventoryRequests")
	public ModelAndView viewInventoryRequests(@RequestParam Long inventoryId, Authentication authentication) {
		System.out.println("viewInventoryRequests " + inventoryId);
		ModelAndView mav = new ModelAndView("admin/inventory/inventory-request-list");
						
		User user = userService.findByUsername(authentication.getName());
		
		mav.addObject("user", user);
		mav.addObject("inventory", inventoryService.get(inventoryId));
	    mav.addObject("inventoryRequests", inventoryRequestService.getByInventoryId(inventoryService.get(inventoryId)));
		mav.addObject("listSuppliers", supplierService.getByStatus(Supplier.STATUS.ACTIVE));
	    
	    return mav;
	}
	
	@RequestMapping("/secure/requestInventory")
	public ResponseEntity<String> requestInventory(@ModelAttribute InventoryRequestDto inventoryRequestDto, HttpSession session) {
		System.out.println("requestInventory " + inventoryRequestDto);
		
		User user = (User)session.getAttribute("user");
		Inventory inventory = inventoryService.get(inventoryRequestDto.getInventoryId());
		Supplier supplier;
		String message = "";
		
		try {
			supplier = supplierService.getBySerial(inventoryRequestDto.getSupplierId());
		}catch (Exception e) {
			System.out.println(e);
			message = "Supplier isn't available in the system. Please select another supplier";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
		for (InventoryRequest i : inventoryRequestService.getByInventoryId(inventory)) 
		{
			if((i.getRequestCode().equals(inventoryRequestDto.getRequestCode()))) {
				message = "Entered request code has been already in the system. Please add different request code.";
				return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}
		}	
		
		try {
			InventoryRequest inventoryRequest = new InventoryRequest();
			inventoryRequest.setInventoryId(inventory);
			inventoryRequest.setSupplierId(supplier);
			inventoryRequest.setSpecialNote(inventoryRequestDto.getSpecialNote());
			inventoryRequest.setRequestedAmount(inventoryRequestDto.getRequestedAmount());
			inventoryRequest.setStatus(InventoryRequest.STATUS.PENDING);
			inventoryRequest.setRequestCode(inventoryRequestDto.getRequestCode());
			inventoryRequest.setCreateBy(user);
			inventoryRequest.setCreateDate(LocalDateTime.now());
			inventoryRequest.setUpdateBy(user);
			inventoryRequest.setUpdateDate(LocalDateTime.now());
			inventoryRequestService.save(inventoryRequest);
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteInventoryRequest")
	public ResponseEntity<String> deleteInventoryRequest(@RequestParam Long irId) {
		System.out.println("deleteInventoryRequest " + irId);

		String message = "";
		InventoryRequest request = inventoryRequestService.get(irId);
		
		if(request.getStatus() == InventoryRequest.STATUS.PENDING) {
			inventoryRequestService.delete(irId);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else {
			message = "Requested inventory is on " + request.getStatus() + ". Cannot cancel now.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/secure/inventoryRequestStatusUpdate")
	public ResponseEntity<String> inventoryRequestStatusUpdate(@ModelAttribute InventoryRequestDto inventoryRequestDto, HttpSession session) {
		System.out.println("inventoryRequestStatusUpdate " + inventoryRequestDto);
				
		User user = (User)session.getAttribute("user");
		InventoryRequest.STATUS currentStatus;
		InventoryRequest.STATUS newStatus;
		String message = "";
		
		try {
			InventoryRequest inventoryRequest = inventoryRequestService.get(inventoryRequestDto.getIrId());
			currentStatus = inventoryRequest.getStatus();
			newStatus = InventoryRequest.STATUS.valueOf(inventoryRequestDto.getStatus());
			message = "Requested inventory is on " + currentStatus + ". Cannot change to " + newStatus + " now.";
			
			if(newStatus.equals(InventoryRequest.STATUS.PENDING)){
				if(!currentStatus.equals(InventoryRequest.STATUS.APPROVED))
						return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}else if(newStatus.equals(InventoryRequest.STATUS.APPROVED)){
				if(!currentStatus.equals(InventoryRequest.STATUS.PENDING))
					return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}if(newStatus.equals(InventoryRequest.STATUS.REQUESTED)){
				if(!currentStatus.equals(InventoryRequest.STATUS.APPROVED)) {
					return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
				}else {
					//Send emails to supplier & admins
				}
			}if(newStatus.equals(InventoryRequest.STATUS.PROCESSING)){
				if(!currentStatus.equals(InventoryRequest.STATUS.REQUESTED)) {
					return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
				}else {
					//Send emails to supplier & admins
				}
			}if(newStatus.equals(InventoryRequest.STATUS.COMPLETED)){
				if(!currentStatus.equals(InventoryRequest.STATUS.PROCESSING))
					return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
			}

			inventoryRequest.setStatus(newStatus);
			inventoryRequest.setUpdateBy(user);
			inventoryRequest.setUpdateDate(LocalDateTime.now());
			inventoryRequestService.save(inventoryRequest);
				
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
