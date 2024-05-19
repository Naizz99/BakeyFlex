package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Recipe;
import com.naizzbakers.bms.model.Recipe.STATUS;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	Object getByStatus(STATUS status);

}
