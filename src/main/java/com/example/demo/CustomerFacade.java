package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.enums.ClientType;
import com.example.enums.CouponType;

import coupon.exceptions.NoCouponException;
import coupon.exceptions.NoCouponsException;
import customer.exceptions.CustomerGotCouponException;
import customer.exceptions.CustomerNotExistException;

@Service
@Scope("prototype")
public class CustomerFacade extends ClientFacade {

	// Customer that logged in
	private Customer loginCustomer;
	
	// Customer DAO
	@Autowired
	private CustomerRepo custdb;
	
	// Coupon DAO
	@Autowired
	private CouponRepo coupdb;
	
	/***
	 * Login method for Customer
	 */
	@Override
	public CustomerFacade login(String name, String password, ClientType type) {
		
		// Checking type for first
		if(!type.equals(ClientType.CUSTOMER))
		{
			System.out.println("You are trying to login with the wrong type:" + type);
			return null;
		}
		
		// Getting the Customer base on the name
		// And Checking if exist
		Customer check = custdb.findByName(name);
		if(check == null)
		{
			System.out.println("There is no Customer with the name:" + name);
			return null;
		}
		
		// Checking password
		if(!check.getPassword().equals(password))
		{
			System.out.println("Password:" + password + " is not correct");
			return null;
		}
		// Setting the customer to the one that logged in
		loginCustomer = check;
		loginCustomer.setId(check.getId());
		return this;
		
	}

	/***
	 * Purchase Coupon if it exist on Coupon's table, and Customer didn't purchased it yet
	 * @param customerId
	 * @param coupon
	 * @throws NoCouponException
	 * @throws CustomerNotExistException
	 * @throws CustomerGotCouponException
	 */
	public void purchaseCoupon(int customerId, Coupon coupon)throws NoCouponException , CustomerNotExistException , CustomerGotCouponException
	{
		// Getting the Customer
		Customer customer = custdb.findOne(customerId);
		if(customer == null)
		{
			throw new CustomerNotExistException("There is no Customer with the id:" + customerId);
		}
		
		// Checking if Coupon exist in Coupon table
		Coupon check = coupdb.findOne(coupon.getId());
		if(check == null)
		{
			throw new NoCouponException("There is no Coupon with the id:" + coupon.getId());
		}
		
		// Checking if Customer already got this Coupon
		Coupon customerCoupon =  coupdb.findCustomerCoupon(customerId, coupon.getId());
		if(customerCoupon != null)
		{
			throw new CustomerGotCouponException("Customer Already got Coupon:" + coupon.getTitle());
		}
		
		// Purchase Coupon
		custdb.insertCustoerCoupon(customerId, coupon.getId());
		coupdb.updateCouponAmount(coupon.getId());
	}
	
	/***
	 * Get All Customer Coupons
	 * @param customerId
	 * @return
	 * @throws NoCouponsException
	 * @throws CustomerNotExistException
	 */
	public List<Coupon> getAllPurchasedCoupon(int customerId)throws NoCouponsException , CustomerNotExistException
	{
		// Checking if Customer exist
		Customer customer = custdb.findOne(customerId);
		if(customer == null)
		{
			throw new CustomerNotExistException("There is no Customer with the id:" + customerId);
		}
		
		// Checking if Customer got Coupons
		List<Coupon> coupons = new ArrayList<>();
		coupons = coupdb.findCustomerCoupons(customerId);
		if(coupons.isEmpty() || coupons == null)
		{
			throw new NoCouponsException("There is no Coupons for:" + customer.getCustName());
		}
		
		// Return Coupons
		return coupons;
	}
	
	/***
	 * Get Customer's Coupon by type
	 * @param customerId
	 * @param type
	 * @return list of coupons
	 * @throws NoCouponsException
	 * @throws CustomerNotExistException
	 */
	public List<Coupon> getAllPurchasedCouponByType(int customerId, CouponType type)throws NoCouponsException, CustomerNotExistException
	{
		// Checking if Customer exist
		Customer customer = custdb.findOne(customerId);
		if(customer == null)
		{
			throw new CustomerNotExistException("There is no Customer with the id:" + customerId);
		}
				
		// Checking if Customer got Coupons
		List<Coupon> coupons = new ArrayList<>();
		coupons = coupdb.findCustomerCouponsByType(customerId, type);
		if(coupons.isEmpty() || coupons == null)
		{
			throw new NoCouponsException("There is no Coupons for:" + customer.getCustName() + " with the type:" + type.name());
		}
		
		// Return Coupons
		return coupons;
	}
	
	/***
	 * Get Customer's Coupon by price
	 * @param customerId
	 * @param price
	 * @return list of coupons
	 * @throws NoCouponsException
	 * @throws CustomerNotExistException
	 */
	public List<Coupon> getAllPurchasedCouponByPrice(int customerId, double price)throws NoCouponsException, CustomerNotExistException
	{
		// Checking if Customer exist
		Customer customer = custdb.findOne(customerId);
		if(customer == null)
		{
			throw new CustomerNotExistException("There is no Customer with the id:" + customerId);
		}
				
		// Checking if Customer got Coupons
		List<Coupon> coupons = new ArrayList<>();
		coupons = coupdb.findCustomerCouponsByPrice(customerId, price);
		if(coupons.isEmpty() || coupons == null)
		{
			throw new NoCouponsException("There is no Coupons for:" + customer.getCustName() + " lower then price:" + price + "$");
		}
		
		// Return Coupons
		return coupons;
	}
	
	/***
	 * getAll Coupons from data base
	 * @param custId
	 * @return
	 * @throws CustomerNotExistException
	 * @throws NoCouponsException
	 */
	public List<Coupon> getAllCouponsOnData(int custId)throws CustomerNotExistException, NoCouponsException
	{
		// Checking that Customer exist
		Customer customer = custdb.findOne(custId);
		if(customer == null)
		{
			throw new CustomerNotExistException("There is no Customer with the id:" + custId);
		}
		
		List<Coupon> coupons = new ArrayList<>();
		coupons = (List<Coupon>) coupdb.findAll();
		
		// Checking there are any Coupons
		if(coupons.isEmpty() || coupons == null)
		{
			throw new NoCouponsException("There are no Coupons on the data base yet.");
		}
		
		// Return Coupons
		return coupons;
	}
	
	
	/***
	 * Get Customer that logged in
	 * @return login customer
	 * @throws CustomerNotExistException
	 */
	public Customer getLoginCustomer()throws CustomerNotExistException {
		if(loginCustomer == null)
		{
			throw new CustomerNotExistException("Custmer was not found, try to relogin");
		}
		return loginCustomer;
	}
}
