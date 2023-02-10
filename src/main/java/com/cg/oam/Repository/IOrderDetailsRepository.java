package com.cg.oam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.oam.Entity.OrderDetails;

@Repository
public interface IOrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
	//method to find orderDetails By its Id
	@Query("SELECT o from OrderDetails o where o.orderId=?1")
	public OrderDetails findByOrderDetailsId(Integer OrderId);
}
