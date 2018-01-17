package com.example.demo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

	/***
	 * Insert into Customer Coupon
	 * @param customerId
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO customer_coupons (customers_id, coupons_id) VALUES (:customerId, :couponId)", nativeQuery = true) 
	void insertCustoerCoupon(@Param("customerId") int  customerId, @Param("couponId") int couponId);
	
	/***
	 * Insert into Customer Coupon
	 * @param customerId
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM customer_coupons WHERE customers_id= :customerId", nativeQuery = true) 
	void deleteCustoerCoupon(@Param("customerId") int  customerId);
//	
	/***
	 * Select by name
	 * @param name
	 * @return
	 */
	@Query("SELECT t FROM Customer t where t.custName = :name") 
    Customer findByName(@Param("name") String name);
	
	/***
	 * Update Customer
	 * @param password
	 * @param id
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Customer t SET t.password = :password  WHERE t.id = :id")
	void updateCustomer(@Param("password") String password, @Param("id") int id);

}
