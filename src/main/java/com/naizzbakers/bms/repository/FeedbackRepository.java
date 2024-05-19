package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
