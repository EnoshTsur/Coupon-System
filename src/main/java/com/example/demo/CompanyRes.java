package com.example.demo;

import java.util.Date;
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

import company.exceptions.CompanyNotExistException;
import coupon.exceptions.CouponNameExistException;
import coupon.exceptions.NoCouponException;
import coupon.exceptions.NoCouponsException;
import customer.exceptions.CustomerHoldingCouponException;
import sidekicks.EnumsConverter;


@CrossOrigin(origins = "*")
@RequestMapping(value = "Company")
@RestController
public class CompanyRes {

//	@Autowired
//	private CouponSystem cs;				
	
	/***
	 * Login
	 * @return Company Facade
	 */
	private CompanyFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		CompanyFacade cf = (CompanyFacade) session.getAttribute("facade");
		return cf;
	}
	
	
	/***
	 * Fake Login
	 */
//	private CompanyFacade getFacade(HttpServletRequest request , HttpServletResponse response)
//	{
//		return (CompanyFacade) cs.login("Aroma","1234", ClientType.COMPANY);
//	}
	
	
	/***
	 * Creating new coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/createCoupon" , method = RequestMethod.POST)
	public ResponseEntity createCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try {
			
			cf.createCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
		} catch (CouponNameExistException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Updateing coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/updateCoupon" , method = RequestMethod.PUT)
	public ResponseEntity updateCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try
		{
		cf.updateCoupon(coupon);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("new price:" + coupon.getPrice() + " and new end date:" + coupon.getEndDate() + " was updated successfully");
		
		}catch(NoCouponException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Removing a coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/removeCoupon/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity removeCoupon(@RequestBody @PathVariable("id") int id , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try{
		cf.removeCoupon(cf.getLoginCompany().getId(), id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Coupon was removed from the data base");
		}catch(NoCouponException | CompanyNotExistException | CustomerHoldingCouponException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get this Company
	 * @return
	 */
	@RequestMapping(value = "getThisCompany" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getThisCompany(HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try{
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(cf.getLoginCompany());	
		}catch(CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get all Coupons
	 * @return
	 */
	@RequestMapping(value = "getAllCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCoupons(HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try{
		List<Coupon> coupons = cf.getAllCoupons(cf.getLoginCompany().getId());
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch( NoCouponsException | CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get coupon by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getCoupon/{id}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCoupon(@PathVariable("id") int id ,HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		
		try{
		Coupon coupon = cf.getCoupon(cf.getLoginCompany().getId(), id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon); 
		}catch(NoCouponException | CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get Coupons by type
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "getCouponsByType/{type}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCouponsByType(@PathVariable("type") String type , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		CouponType cType = EnumsConverter.stringToCoupon(type);
		
		try{
	    List<Coupon> coupons = cf.getCouponsByType(cf.getLoginCompany().getId(), cType);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch( NoCouponsException | CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get Coupons by price
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "/getCouponsByPrice/{price}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCouponsByPrice(@PathVariable("price") double price , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		try{
		List<Coupon> coupons = cf.getCouponsByPrice(cf.getLoginCompany().getId(),price);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch(NoCouponsException | CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	@RequestMapping(value = "/getCouponsByDate/{timeStamp}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCouponsByDate(@PathVariable("timeStamp")  long timeStamp , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = getFacade(request, response);
		
		Date date = new Date(timeStamp);
			
		try{
		List<Coupon> coupons = cf.getCouponsByDate(cf.getLoginCompany().getId(),date);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		}catch(NoCouponsException | CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
		
	}
}



