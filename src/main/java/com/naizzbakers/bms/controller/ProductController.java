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

import com.naizzbakers.bms.dto.InventorySupplierLinkDto;
import com.naizzbakers.bms.dto.ProductDto;
import com.naizzbakers.bms.dto.ProductTypeDto;
import com.naizzbakers.bms.model.Inventory;
import com.naizzbakers.bms.model.InventorySupplierLink;
import com.naizzbakers.bms.model.Product;
import com.naizzbakers.bms.model.ProductType;
import com.naizzbakers.bms.model.Recipe;
import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.service.ProductService;
import com.naizzbakers.bms.service.ProductTypeService;
import com.naizzbakers.bms.service.RecipeService;
import com.naizzbakers.bms.service.UserService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/listProducts")
	public ModelAndView listProducts() {
		System.out.println("listProducts");
	    ModelAndView mav = new ModelAndView("admin/product/product-list");
		
		List<Product> listProducts = productService.get();
	    mav.addObject("listProducts", listProducts);
		
	    return mav;
	}
	
	@RequestMapping("/productDetails")
	public ModelAndView productDetails(@RequestParam Long productId, Authentication authentication) {
		System.out.println("productDetails " + productId);
		ModelAndView mav = new ModelAndView("admin/product/product-details");
		
		User user = userService.findByUsername(authentication.getName());
		Product product = productService.get(productId); 	
				
		mav.addObject("user", user);
		mav.addObject("product", product);
		mav.addObject("productTypeList", productTypeService.getByStatus(ProductType.STATUS.ACTIVE));
		mav.addObject("recipeList", recipeService.getByStatus(Recipe.STATUS.ACTIVE));
		
	    return mav;
	}
	
	@GetMapping("/addProduct")
	public ModelAndView addProduct(Authentication authentication) {
		System.out.println("addProduct");
		ModelAndView mav = new ModelAndView("admin/product/product-details");
		
		Product product = new Product();
		
		User user = userService.findByUsername(authentication.getName());
		mav.addObject("user", user);
		
		mav.addObject("productTypeList", productTypeService.getByStatus(ProductType.STATUS.ACTIVE));
		mav.addObject("recipeList", recipeService.getByStatus(Recipe.STATUS.ACTIVE));
		
		product.setCreateBy(user);
		product.setCreateDate(LocalDateTime.now());
		product.setUpdateBy(user);
		product.setUpdateDate(LocalDateTime.now());
		product.setStatus(Product.STATUS.DEACTIVE);
		mav.addObject("product", product);
				
		return mav;
	}
	
	@RequestMapping("/secure/saveProduct")
	public ResponseEntity<Long> saveProduct(@ModelAttribute ProductDto productDto, HttpSession session) {
		System.out.println("saveProduct " + productDto);
		boolean isDupplicate = false;
		
		Product product;
		
		User user = (User)session.getAttribute("user");
        
		List<Product> listProducts = productService.get();
		for (Product p : listProducts) 
		{
			if(((p.getProductId()) == (productDto.getProductId()))){
				isDupplicate = true;
				break;
			}
		}
		
		if(!isDupplicate) {
			product = new Product();
			product.setProductSerial(productDto.getProductSerial());
			product.setProductName(productDto.getProductName());
			product.setSpecialNote(productDto.getSpecialNote());
			product.setImage(productDto.getImage());
			product.setDiscountRate(productDto.getDiscountRate());
			product.setLongDescription(productDto.getLongDescription());
			product.setProductType(productTypeService.get(productDto.getProductType()));
			product.setRecipeId(recipeService.get(productDto.getRecipeId()));
			product.setShortDescription(productDto.getShortDescription());
			product.setUnitPrice(productDto.getUnitPrice());
			product.setCreateBy(user);
			product.setCreateDate(LocalDateTime.now());
			product.setUpdateBy(user);
			product.setUpdateDate(LocalDateTime.now());
			product.setStatus(Product.STATUS.DEACTIVE);
				
			productService.save(product);
			
			return new ResponseEntity<>(product.getProductId(), HttpStatus.OK);
		}else {				
			product = productService.get(productDto.getProductId());
			product.setProductSerial(productDto.getProductSerial());
			product.setProductName(productDto.getProductName());
			product.setSpecialNote(productDto.getSpecialNote());
			product.setImage(productDto.getImage());
			product.setDiscountRate(productDto.getDiscountRate());
			product.setLongDescription(productDto.getLongDescription());
			product.setProductType(productTypeService.get(productDto.getProductType()));
			product.setRecipeId(recipeService.get(productDto.getRecipeId()));
			product.setShortDescription(productDto.getShortDescription());
			product.setUnitPrice(productDto.getUnitPrice());
			product.setUpdateBy(user);
			product.setUpdateDate(LocalDateTime.now());
			product.setStatus(Product.STATUS.valueOf(productDto.getStatus()));
			productService.save(product);
			
			return new ResponseEntity<>(product.getProductId(), HttpStatus.OK);
		}
	}
		
	@GetMapping("/deleteProduct")
	public ResponseEntity<String> deleteProduct(@RequestParam Long productId) {
		System.out.println("deleteProduct " + productId);
		
		try {
			productService.delete(productId);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			String message = "Product cannot be deleted. Already linked with another data.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/changeProductStatus")
	public ResponseEntity<String> changeProductStatus(@RequestParam Long productId,@RequestParam String sts, Authentication authentication) {
		System.out.println("changeInventoryStatus");
		String message = "";
		User user = userService.findByUsername(authentication.getName());
		
		try {
			Product product = productService.get(productId);
			product.setStatus(Product.STATUS.valueOf(sts));
			product.setUpdateBy(user);
			product.setUpdateDate(LocalDateTime.now());
			productService.save(product);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			message = "Product status cannot be change. Something went wrong.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping("/listProductTypes")
	public ModelAndView listProductTypes() {
		System.out.println("listProductTypes");
	    ModelAndView mav = new ModelAndView("admin/product/product-type-list");
		
		List<ProductType> listProductTypes = productTypeService.get();
	    mav.addObject("listProductTypes", listProductTypes);
		
	    return mav;
	}
	
	@RequestMapping("/getProductType")
	public ResponseEntity<ProductType> getProductType(@RequestParam Long ptId, Authentication authentication) {
		System.out.println("getProductType " + ptId);
		
		try {
			ProductType productType = productTypeService.get(ptId); 	
			return new ResponseEntity<ProductType>(productType, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping("/secure/saveProductType")
	public ResponseEntity<Long> saveProductType(@ModelAttribute ProductTypeDto productTypeDto, HttpSession session) {
		System.out.println("saveProductType " + productTypeDto);
		boolean isDupplicate = false;
		
		ProductType productType;
		
		User user = (User)session.getAttribute("user");
        
		List<ProductType> listProductTypes = productTypeService.get();
		for (ProductType p : listProductTypes) 
		{
			if(((p.getPtId()) == (productTypeDto.getPtId()))){
				isDupplicate = true;
				break;
			}
		}
		
		if(!isDupplicate) {
			productType = new ProductType();
			productType.setProductType(productTypeDto.getProductType());
			productType.setProductTypeName(productTypeDto.getProductTypeName());
			productType.setDescription(productTypeDto.getDescription());
			productType.setCreateBy(user);
			productType.setCreateDate(LocalDateTime.now());
			productType.setUpdateBy(user);
			productType.setUpdateDate(LocalDateTime.now());
			productType.setStatus(ProductType.STATUS.valueOf(productTypeDto.getStatus()));
			productTypeService.save(productType);
			
			return new ResponseEntity<>(productType.getPtId(), HttpStatus.OK);
		}else {				
			productType = productTypeService.get(productTypeDto.getPtId());
			productType.setProductType(productTypeDto.getProductType());
			productType.setProductTypeName(productTypeDto.getProductTypeName());
			productType.setDescription(productTypeDto.getDescription());
			productType.setUpdateBy(user);
			productType.setUpdateDate(LocalDateTime.now());
			productType.setStatus(ProductType.STATUS.valueOf(productTypeDto.getStatus()));
			productTypeService.save(productType);
			
			return new ResponseEntity<>(productType.getPtId(), HttpStatus.OK);
		}
	}
		
	@GetMapping("/deleteProductType")
	public ResponseEntity<String> deleteProductType(@RequestParam Long ptId) {
		System.out.println("deleteProductType " + ptId);
		
		try {
			productTypeService.delete(ptId);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			String message = "Product cannot be deleted. Already linked with another data.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping("/secure/productTypeStatusUpdate")
	public ResponseEntity<String> productTypeStatusUpdate(@ModelAttribute ProductTypeDto productTypeDto, HttpSession session) {
		System.out.println("productTypeStatusUpdate " + productTypeDto);
				
		User user = (User)session.getAttribute("user");
		
		ProductType.STATUS tempStatus;
		ProductType.STATUS newStatus;
		
		try {
			ProductType productType = productTypeService.get(productTypeDto.getPtId());
			tempStatus = productType.getStatus();
			newStatus = ProductType.STATUS.valueOf(productTypeDto.getStatus());
			
			if(tempStatus != newStatus) {
				productType.setStatus(newStatus);
				productType.setUpdateBy(user);
				productType.setUpdateDate(LocalDateTime.now());
				productTypeService.save(productType);
			}
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
