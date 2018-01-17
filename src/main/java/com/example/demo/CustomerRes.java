package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.enums.ClientType;
import com.example.enums.CouponType;

import coupon.exceptions.NoCouponException;
import coupon.exceptions.NoCouponsException;
import customer.exceptions.CustomerGotCouponException;
import customer.exceptions.CustomerNotExistException;
import sidekicks.EnumsConverter;



@CrossOrigin(origins = "*")
@RequestMapping(value = "Customer")
@RestController
public class CustomerRes {

//	@Autowired
//	private CouponSystem couponSystem;
	
	/***
	 * Login
	 * @return Customer Facade
	 */
	private CustomerFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		CustomerFacade cf = (CustomerFacade) session.getAttribute("facade");
		return cf;
	}
	
	/***
	 * Fake Login
	 * @param request
	 * @param response
	 * @return
	 */
//	private CustomerFacade getFacade(HttpServletRequest request, HttpServletResponse response)
//	{
//		return (CustomerFacade) couponSystem.login("Enosh", "1234", ClientType.CUSTOMER);
//	}
	
	/***
	 * Purchase coupon
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/purchaseCoupon" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity purchaseCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		try {
			cf.purchaseCoupon(cf.getLoginCustomer().getId(), coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
		} catch (CustomerGotCouponException | NoCouponException | CustomerNotExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Get all purchased coupons
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchasedCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllPurchasedCoupons(HttpServletRequest request , HttpServletResponse response)
	{
		//Login
		CustomerFacade cf = getFacade(request, response);
		
		try{
		List<Coupon> coupons = cf.getAllPurchasedCoupon(cf.getLoginCustomer().getId());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch(NoCouponsException | CustomerNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get all purchased coupons by type
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchasedCoupons/type/{type}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllPurchasedCouponsByType(@PathVariable("type") String type , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		CouponType cType = EnumsConverter.stringToCoupon(type);
		
		try{
		List<Coupon> coupons = cf.getAllPurchasedCouponByType(cf.getLoginCustomer().getId(), cType);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch(NoCouponsException | CustomerNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get all purchased coupons by price
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "/getAllPurchasedCoupons/price/{price}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllPurchasedCouponsByPrice(@PathVariable("price") double price , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		try{
		List<Coupon> coupons = cf.getAllPurchasedCouponByPrice(cf.getLoginCustomer().getId(), price);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch(NoCouponsException | CustomerNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get all Coupons for purchase
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getAllCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCoupons(HttpServletRequest request, HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		try{
			List<Coupon> coupons = cf.getAllCouponsOnData(cf.getLoginCustomer().getId());
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch(CustomerNotExistException | NoCouponsException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
}
