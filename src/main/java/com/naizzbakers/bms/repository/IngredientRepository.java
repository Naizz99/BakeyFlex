package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
