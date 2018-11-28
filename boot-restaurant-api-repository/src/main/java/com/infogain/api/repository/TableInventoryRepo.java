package com.infogain.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.infogain.api.entity.InventoryForTable;
@Repository
public interface TableInventoryRepo extends JpaRepository<InventoryForTable, Integer> {
	InventoryForTable getInventoryByinventoryId(int id);

}
