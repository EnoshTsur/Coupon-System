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

import company.exceptions.CompanyNameExistException;
import company.exceptions.CompanyNotExistException;
import company.exceptions.NoCompaniesException;
import customer.exceptions.CustomerNameExistException;
import customer.exceptions.CustomerNotExistException;
import customer.exceptions.NoCustomersException;


@CrossOrigin(origins = "*")
@RequestMapping(value = "Admin")
@RestController
public class AdminRes {

	@Autowired
	private CouponSystem couponSystem;
	
	/***
	 * Login
	 * @return Admin Facade
	 */
	private AdminFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		AdminFacade af = (AdminFacade) session.getAttribute("facade");
		return af;
	}
	
	/***
	 * Fake Login
	 * @return fake admin
	 */
//	 private AdminFacade getFacade(HttpServletRequest request , HttpServletResponse response)
//	 {
//		 return (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
//	 }

	/***
	 * Get ADMIN
	 * @return
	 */
	@RequestMapping(value = "/getAdmin" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAdmin(HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		AdminFacade af = getFacade(request, response);
		
		if(af != null)
		{
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(af);	
		}else{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body("Login was failed, please try to login again");	
		}
	}
	
	//!!!------Company------!!!
	
	/***
	 * Create Company
	 */
	@RequestMapping(value = "/createCompany" , method = RequestMethod.POST)
	public ResponseEntity createCompany(@RequestBody Company company , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		AdminFacade af = getFacade(request, response);
		
		try {
			af.createCompany(company);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(company);
		} catch (CompanyNameExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Remove Company
	 */
	@RequestMapping(value = "/removeCompany/{id}" , method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity removeCompany(@RequestBody @PathVariable("id") int id,
			HttpServletRequest request , HttpServletResponse response )
	{
		// Login
		
		AdminFacade af = getFacade(request, response);
		
		try{
		af.removeCompany(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Company was removed successfully");
		}catch (CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Update Company
	 */
	@RequestMapping(value = "/updateCompany" , method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity updateCompany(@RequestBody Company company , HttpServletRequest request , HttpServletResponse response )
	{
		// Login
		AdminFacade af = getFacade(request, response);
		
		
		try{
		af.updateCompany(company);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("New Password:" + company.getPassword() + " And new email:" + company.getEmail() + " was updated successfully");
		}catch(CompanyNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Get Company
	 */
	@RequestMapping(value = "/getCompany/{id}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCompany(@PathVariable("id") int id , HttpServletRequest request , HttpServletResponse response)
		{
		// Login
		AdminFacade af = getFacade(request, response);
	
		try{
			Company company = af.getCompany(id);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(company);
		}catch(CompanyNotExistException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
		}

	/***
	 * Get All Companies
	 */
	@RequestMapping(value = "getAllCompanies" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCompanies(HttpServletRequest request , HttpServletResponse response)
		{
		// Login
		AdminFacade af = getFacade(request, response);
		
		try{
			List<Company> companies = af.getAllCompanies();
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(companies);
		}catch(NoCompaniesException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
		}
	
	//!!!--------Customer--------!!!
	
	/***
	 * Create Customer
	 */
	@RequestMapping(value = "createCustomer" , method = RequestMethod.POST)
	public ResponseEntity createCustomer(@RequestBody Customer customer , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		AdminFacade af = getFacade(request, response);
		
		try {
			af.createCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customer);
		} catch (CustomerNameExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Remove Customer
	 * @param weCustomer
	 * @return
	 */
	@RequestMapping(value = "/removeCustomer/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity removeCoupon(@RequestBody @PathVariable("id") int id, HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		AdminFacade af = getFacade(request, response);
		
		try{
		af.removeCustomer(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Customer was removed successfully");
		}catch(CustomerNotExistException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Update Customer
	 */
	@RequestMapping(value = "updateCustomer" , method = RequestMethod.PUT)
	public ResponseEntity updateCustomer(@RequestBody Customer customer , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		AdminFacade af = getFacade(request, response);
	
		
		try{
			af.updateCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("The new password:" + customer.getPassword() + " was updated successfully");
		}catch(CustomerNotExistException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	/***
	 * Get Customer
	 */
	@RequestMapping(value = "/getCustomer/{id}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getCustomer(@PathVariable("id") int id , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		AdminFacade af = getFacade(request, response);
		
		try{
		Customer customer = af.getCustomer(id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customer);
		}catch(CustomerNotExistException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
		
	}
	
	/***
	 * Get All Customers
	 */
	@RequestMapping(value = "/getAllCustomers" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getAllCustomers(HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		AdminFacade af = getFacade(request, response);
		try{
		List<Customer> customers = af.getAllCustomers();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customers);
		}catch(NoCustomersException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
			
		}
	}
}
