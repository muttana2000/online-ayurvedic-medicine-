package com.cg.oam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.oam.entity.OrderDetails;

@Repository
public interface IOrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
	//method to find orderDetails By its Id
	@Query("SELECT o from OrderDetails o where o.orderId=?1")
	public OrderDetails findByOrderDetailsId(Integer OrderId);

	public List<OrderDetails> findByCustomerUserId(Integer userId);
}
