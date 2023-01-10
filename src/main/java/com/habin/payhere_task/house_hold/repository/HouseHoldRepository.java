package com.habin.payhere_task.house_hold.repository;

import com.habin.payhere_task.house_hold.entity.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseHoldRepository extends JpaRepository<HouseHold, Integer>, QHouseHoldRepository {
}
