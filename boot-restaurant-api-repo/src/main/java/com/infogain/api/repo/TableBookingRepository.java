package com.infogain.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.TableBooking;
@Repository
public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {

}
