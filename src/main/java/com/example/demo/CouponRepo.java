package com.example.demo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.enums.CouponType;

@Repository
public interface CouponRepo extends CrudRepository<Coupon, Integer> {
	
	
	
	/***
	 * Removing Company Coupon
	 * @param couponId
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon t WHERE t.id = :couponId AND t.company.id = :companyId")
	void removeCoupon(@Param("couponId") int couponId, @Param("companyId") int companyId);
	
	/***
	 * Updating Coupon Amount
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon t SET t.amount = t.amount-1 WHERE t.id = :couponId")
	void updateCouponAmount(@Param("couponId") int couponId);
	
	/***
	 * Updating Coupon 
	 * @param couponId
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Coupon t SET t.endDate = :endDate, t.price = :price WHERE t.id = :couponId")
	void updateCoupon(@Param("endDate") Date date , @Param("price") double price, @Param("couponId") int couponId);
	
	
	/***
	 * Removing Coupon
	 * @param couponId
	 * @param companyId
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon t WHERE t.id = :couponId")
	void removeCoupon(@Param("couponId") int couponId);
	
	/***
	 * Find Coupon by title
	 * @param title
	 * @return
	 */
	@Query("SELECT t FROM Coupon t WHERE t.title = :title") 
    Coupon findByTitle(@Param("title") String title);
	
	/***
	 * Find All Company's Coupons
	 * @param id
	 * @return
	 */
	@Query("SELECT t FROM Coupon t WHERE t.company.id = :id") 
    List<Coupon> findAllByCompany(@Param("id") int id);
	
	/***
	 * Find Company's Coupon
	 * @param companyId
	 * @param couponId
	 * @return
	 */
	@Query("SELECT t FROM Coupon t WHERE t.company.id = :companyId AND t.id = :couponId") 
    Coupon findByCompany(@Param("companyId") int companyId, @Param("couponId") int couponId);
	
	/***
	 * Find Company's Coupons by type
	 * @param companyId
	 * @param type
	 * @return
	 */
	@Query("SELECT t FROM Coupon t WHERE t.company.id = :companyId AND t.type = :type") 
	List<Coupon> findByCompanyAndType(@Param("companyId") int companyId, @Param("type") CouponType type);
	
	/***
	 * Find Company's Coupons by price
	 * @param companyId
	 * @param price
	 * @return
	 */
	@Query("SELECT t FROM Coupon t WHERE t.company.id = :companyId AND t.price < :price") 
	List<Coupon> findByCompanyAndPrice(@Param("companyId") int companyId, @Param("price") double price);
	
	/***
	 * Find Company's Coupons by date
	 * @param companyId
	 * @param date
	 * @return
	 */
	@Query("SELECT t FROM Coupon t WHERE t.company.id = :companyId AND t.endDate < :date") 
	List<Coupon> findByCompanyAndDate(@Param("companyId") int companyId, @Param("date") Date date);
	
	/***
	 * Find All Customer's Coupons
	 * @param customerId
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.id IN (SELECT coup.id FROM coup.customers c WHERE c.id = :customerId)") 
	List<Coupon> findCustomerCoupons(@Param("customerId") int customerId);
	
	/***
	 * Find Customer's Coupon
	 * @param customerId
	 * @param couponId
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.id = :couponId AND coup.id IN (SELECT coup.id FROM coup.customers c WHERE c.id = :customerId)") 
	Coupon findCustomerCoupon(@Param("customerId") int customerId, @Param("couponId") int couponId);
	
	/***
	 * Find Coupon in Customer_Coupon table
	 * @param couponId
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.id IN (SELECT coup.id FROM coup.customers c WHERE coup.id = :couponId)") 
	Coupon findCouponInCustomersTable(@Param("couponId") int couponId);

	/***
	 * Find Customer's Coupons by type
	 * @param customerId
	 * @param type
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.type = :type AND coup.id IN (SELECT coup.id FROM coup.customers c WHERE c.id = :customerId)") 
	List<Coupon> findCustomerCouponsByType(@Param("customerId") int customerId, @Param("type") CouponType type);

	/***
	 * Find Customer's Coupons by price
	 * @param customerId
	 * @param price
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.price < :price AND coup.id IN (SELECT coup.id FROM coup.customers c WHERE c.id = :customerId)") 
	List<Coupon> findCustomerCouponsByPrice(@Param("customerId") int customerId, @Param("price") double price);

	/***
	 * Find Customer's Coupons by date
	 * @param customerId
	 * @param date
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.endDate < :date AND coup.id IN (SELECT coup.id FROM coup.customers c WHERE c.id = :customerId)") 
	List<Coupon> findCustomerCouponsByDate(@Param("customerId") int customerId, @Param("date")Date date);
	
	/***
	 * Find Expired Coupons
	 * @param expiredDate
	 * @return
	 */
	@Query("SELECT coup FROM Coupon coup WHERE coup.endDate < :expiredDate")
	List<Coupon> findExpiredCoupons(@Param("expiredDate")Date expiredDate);
	
}