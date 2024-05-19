package com.naizzbakers.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naizzbakers.bms.model.Measurement;
import com.naizzbakers.bms.model.Measurement.STATUS;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

	List<Measurement> findByStatus(STATUS active);

}
