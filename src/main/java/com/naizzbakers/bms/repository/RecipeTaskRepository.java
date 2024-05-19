package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.RecipeTask;

public interface RecipeTaskRepository extends JpaRepository<RecipeTask, Long> {

}
