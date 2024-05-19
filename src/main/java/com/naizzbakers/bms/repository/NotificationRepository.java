package com.naizzbakers.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
