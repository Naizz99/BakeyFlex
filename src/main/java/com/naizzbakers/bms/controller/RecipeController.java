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

import com.naizzbakers.bms.dto.RecipeDto;
import com.naizzbakers.bms.model.Recipe;
import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.service.RecipeService;
import com.naizzbakers.bms.service.UserService;

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
		
	@RequestMapping("/listRecipies")
	public ModelAndView listRecipies() {
		System.out.println("listRecipies");
	    ModelAndView mav = new ModelAndView("admin/Recipe/recipe-list");
		
		List<Recipe> listRecipies = recipeService.get();
	    mav.addObject("listRecipies", listRecipies);
		
	    return mav;
	}
	
	@RequestMapping("/recipeDetails")
	public ModelAndView recipeDetails(@RequestParam Long recipeId, Authentication authentication) {
		System.out.println("recipeDetails " + recipeId);
		ModelAndView mav = new ModelAndView("admin/recipe/recipe-details");
		
		User user = userService.findByUsername(authentication.getName());
		Recipe recipe = recipeService.get(recipeId); 	
				
		mav.addObject("user", user);
		mav.addObject("recipe", recipe);
	    
	    return mav;
	}
	
	@GetMapping("/addRecipe")
	public ModelAndView addRecipe(Authentication authentication) {
		System.out.println("addRecipe");
		ModelAndView mav = new ModelAndView("admin/recipe/recipe-details");
		
		Recipe recipe = new Recipe();
		
		User user = userService.findByUsername(authentication.getName());
		mav.addObject("user", user);
		
		recipe.setCreateBy(user);
		recipe.setCreateDate(LocalDateTime.now());
		recipe.setUpdateBy(user);
		recipe.setUpdateDate(LocalDateTime.now());
		recipe.setStatus(Recipe.STATUS.DEACTIVE);
		mav.addObject("recipe", recipe);
				
		return mav;
	}
	
	@RequestMapping("/secure/saveRecipe")
	public ResponseEntity<Long> saveRecipe(@ModelAttribute RecipeDto recipeDto, HttpSession session) {
		System.out.println("saveRecipe " + recipeDto);
		boolean isDupplicate = false;
		
		Recipe recipe;
		
		User user = (User)session.getAttribute("user");
        
		List<Recipe> listRecepies = recipeService.get();
		for (Recipe i : listRecepies) 
		{
			if(((i.getRecipeId()) == (recipeDto.getRecipeId()))) {
				isDupplicate = true;
				break;
			}
		}
		
		if(!isDupplicate) {
			recipe = new Recipe();
			recipe.setDescription(recipeDto.getDescription());
			recipe.setSpecialNote(recipeDto.getSpecialNote());
			recipe.setImage(recipeDto.getImage());
			recipe.setCreateBy(user);
			recipe.setCreateDate(LocalDateTime.now());
			recipe.setUpdateBy(user);
			recipe.setUpdateDate(LocalDateTime.now());
			recipe.setStatus(Recipe.STATUS.DEACTIVE);
			recipeService.save(recipe);
			
			return new ResponseEntity<>(recipe.getRecipeId(), HttpStatus.OK);
		}else {				
			recipe = recipeService.get(recipeDto.getRecipeId());
			recipe.setDescription(recipeDto.getDescription());
			recipe.setSpecialNote(recipeDto.getSpecialNote());
			recipe.setImage(recipeDto.getImage());
			recipe.setUpdateBy(user);
			recipe.setUpdateDate(LocalDateTime.now());
			recipe.setStatus(Recipe.STATUS.valueOf(recipeDto.getStatus()));
			recipeService.save(recipe);
			
			return new ResponseEntity<>(recipe.getRecipeId(), HttpStatus.OK);
		}
	}
		
	@GetMapping("/deleteRecipe")
	public ResponseEntity<String> deleteRecipe(@RequestParam Long recipeId) {
		System.out.println("deleteRecipe " + recipeId);
		
		try {
			recipeService.delete(recipeId);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			String message = "Recipe cannot be deleted. Already linked with another data.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/changeRecipeStatus")
	public ResponseEntity<String> changeRecipeStatus(@RequestParam Long recipeId,@RequestParam String sts, Authentication authentication) {
		System.out.println("changeRecipeStatus");
		String message = "";
		User user = userService.findByUsername(authentication.getName());
		
		try {
			Recipe recipe = recipeService.get(recipeId);
			recipe.setStatus(Recipe.STATUS.valueOf(sts));
			recipe.setUpdateBy(user);
			recipe.setUpdateDate(LocalDateTime.now());
			recipeService.save(recipe);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			message = "Recipe status cannot be change. Something went wrong.";
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
}
