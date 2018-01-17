package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.enums.ClientType;
import com.example.enums.CouponType;

import company.exceptions.CompanyNotExistException;
import coupon.exceptions.CouponNameExistException;
import coupon.exceptions.NoCouponException;
import coupon.exceptions.NoCouponsException;
import customer.exceptions.CustomerHoldingCouponException;

@Service
@Scope("prototype")
public class CompanyFacade extends ClientFacade {

	// Company that logged in
	private Company loginCompany;
	
	// Company DAO
	@Autowired
	private CompanyRepo compdb;
	
	// Coupon DAO
	@Autowired
	private CouponRepo coupdb;
	
	
	/***
	 * Login method for Company
	 */
	@Override
	public CompanyFacade login(String name, String password, ClientType type) {
		
		// Checking type for first
		if(!type.equals(ClientType.COMPANY))
		{
			System.out.println("You are trying to login with the wrong type:" + type);
			return null;
		}
		
		// Getting the Company base on the name
		// And Checking if exist
		Company check = compdb.findByName(name);
		if(check == null)
		{
			System.out.println("There is no Company with the name:" + name);
			return null;
		}
		
		// Checking password
		if(!check.getPassword().equals(password))
		{
			System.out.println("Password:" + password + " is not correct");
			return null;
		}
		
		// Setting the Company that logged in
		loginCompany = compdb.findOne(check.getId());
		return this;
	}
	
	/***
	 * Create new Coupon 
	 * @param coupon
	 * @throws CouponNameExistException
	 */
	public void createCoupon(Coupon coupon)throws CouponNameExistException
	{
		// Checking if Coupon already exist
		Coupon check = coupdb.findByTitle(coupon.getTitle());
		if(check == null)
		{
			Coupon couponToSave = new Coupon(coupon.getTitle(), coupon.getStartDate(), 
											coupon.getEndDate(), coupon.getAmount(), coupon.getType(), 
											coupon.getMessage(), coupon.getPrice(), coupon.getImage());
			couponToSave.setCompany(loginCompany);
			coupdb.save(couponToSave);
		}
		else
		{
			throw new CouponNameExistException("Coupon with the title:" + coupon.getTitle() + " , already exist");
		}
	}
	
	/***
	 * Updating Coupon only endDate & price
	 * @param coupon
	 * @throws NoCouponException
	 */
	public void updateCoupon(Coupon coupon)throws NoCouponException
	{
		// Getting the original coupon from the data base
		// & check if it exist
		Coupon original = coupdb.findOne(coupon.getId());
		if(original != null)
		{
			// Update Coupon
			original.setEndDate(coupon.getEndDate());
			original.setPrice(coupon.getPrice());
			original.setCompany(loginCompany);
			coupdb.updateCoupon(coupon.getEndDate(), coupon.getPrice(), coupon.getId());
		}
		else
		{
			// Throw exception
			throw new NoCouponException("There is no Coupon with the id:" + coupon.getId());
		}
	}

	/***
	 * Remove Coupon
	 * @param id
	 * @throws NoCouponException
	 */
	public void removeCoupon(int compId, int coupId)throws NoCouponException, CompanyNotExistException , CustomerHoldingCouponException
	{
		// Getting the Company
		Company company = compdb.findOne(compId);
		
		// Checking if Company exist
		if(company == null)
		{
			throw new CompanyNotExistException("There is no Company with the id:" + compId);
		}
		
		// Checking if Coupon exist & if Customer got it
		Coupon check = coupdb.findOne(coupId);
		if(check == null)
		{
			throw new NoCouponException("There is no Coupon with the id:" + coupId);
		}
		
		Coupon custCoupon = coupdb.findCouponInCustomersTable(check.getId());
		
		if(custCoupon != null)
		{
			throw new CustomerHoldingCouponException("One of the Customers holding:" + check.getTitle());
		}
		
		// Removing Coupon
		coupdb.removeCoupon(coupId, compId);
	}

	/***
	 * Get all Coupons
	 * @return All Coupons
	 * @throws NoCouponsException
	 */
	public List<Coupon> getAllCoupons(int compId)throws NoCouponsException, CompanyNotExistException
	{
		List<Coupon> allCoupons = new ArrayList<>();
		
		// Getting The Company
		Company comp = compdb.findOne(compId);
		
		// Checking if Company exist
		if(comp == null)
		{
			throw new CompanyNotExistException("There is no Company with the id:" + compId);
		}
		// Get it Coupons
		allCoupons = coupdb.findAllByCompany(comp.getId());
		
		// Checking if exist
		if(!allCoupons.isEmpty() && allCoupons != null)
		{
			// Return all Coupons
			return allCoupons;
		}
		else
		{
			// Throw exception
			throw new NoCouponsException("There is no Coupons on the data base for: " + comp.getCompName());
		}
	}
	
	/***
	 * Get Company Coupon by id
	 * @param id
	 * @return Coupon
	 * @throws NoCouponException
	 */
	public Coupon getCoupon(int compId , int coupId)throws NoCouponException, CompanyNotExistException
	{
		
		// Getting Company
		Company company = compdb.findOne(compId);
		
		// Checking if Company Exist
		if(company == null)
		{
			throw new CompanyNotExistException("There is no Company with the id:" + compId);
		}
		
		// Checking if Coupon exist
		Coupon check = coupdb.findByCompany(company.getId(), coupId);
		if(check != null)
		{
			// Return Coupon
			return check;
		}
		else
		{
			// Throw exception
			throw new NoCouponException("There is no Coupon with the id:" + coupId + " for: " + company.getCompName());
		}
	}
	
	/***
	 * Get Coupons by type
	 * @param type
	 * @return
	 * @throws NoCouponsException
	 */
	public List<Coupon> getCouponsByType(int compId, CouponType type)throws NoCouponsException, CompanyNotExistException
	{
		// Getting the Company
		Company company = compdb.findOne(compId);
		
		// Checking if Company exist
		if(company == null)
		{
			throw new CompanyNotExistException("There is no Company with the id:" + compId);
		}
		
		List<Coupon> coupons = new ArrayList<>();
		coupons = coupdb.findByCompanyAndType(company.getId(), type);
		// Checking if exist
		if(!coupons.isEmpty() && coupons != null)
		{
			// Return Coupons
			return coupons;
		}
		else
		{
			// Throw exception
			throw new NoCouponsException("There is no Coupons for: " + company.getCompName() + " with the type: " + type.name());
		}
	}
	
	/***
	 * Get Coupons by price
	 * @param price
	 * @return
	 * @throws NoCouponsException
	 */
	public List<Coupon> getCouponsByPrice(int compId, double price)throws NoCouponsException , CompanyNotExistException
	{
		// Getting Company
		Company company = compdb.findOne(compId);
		
		// Checking if Company exist
		if(company == null)
		{
			throw new CompanyNotExistException("There is no Company with the id:" + compId);
		}
		
		List<Coupon> coupons = new ArrayList<>();
		coupons = coupdb.findByCompanyAndPrice(company.getId(), price);
		// Checking if exist
		if(!coupons.isEmpty() && coupons != null)
		{
			// Return Coupons
			return coupons;
		}
		else
		{
			// Throw exception
			throw new NoCouponsException("There is no Coupons for: " + company.getCompName() + " lower then price: " + price);
		}
	}
	
	/***
	 * Get Coupons by last Date
	 * @param date
	 * @return
	 * @throws NoCouponsException
	 */
	public List<Coupon> getCouponsByDate(int compId, Date date)throws NoCouponsException, CompanyNotExistException
	{
		// Getting the Company
		Company company = compdb.findOne(compId);
		
		// Checking if Company exist
		if(company == null)
		{
			throw new CompanyNotExistException("There is no Company with the id:" + compId);
		}
		
		List<Coupon> coupons = new ArrayList<>();
		coupons = coupdb.findByCompanyAndDate(company.getId(), date);
		// Checking if exist
		if(!coupons.isEmpty() && coupons != null)
		{
			// Return Coupons
			return coupons;
		}
		else
		{
			// Throw exception
			throw new NoCouponsException("There is no Coupons for: " + company.getCompName() + " before then: " + date);
		}
	}
	
	/***
	 * Get Company that logged in
	 * @return login company
	 * @throws CompanyNotExistException
	 */
	public Company getLoginCompany() throws CompanyNotExistException {
		if(loginCompany == null)
		{
			throw new CompanyNotExistException("Company was not found, try to relogin");
		}
		return loginCompany;
	}
}
