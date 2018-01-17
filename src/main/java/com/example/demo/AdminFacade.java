package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.enums.ClientType;

import company.exceptions.CompanyNameExistException;
import company.exceptions.CompanyNotExistException;
import company.exceptions.NoCompaniesException;
import customer.exceptions.CustomerNameExistException;
import customer.exceptions.CustomerNotExistException;
import customer.exceptions.NoCustomersException;

@Service
@Scope("prototype")
public class AdminFacade extends ClientFacade{
	
	// Company DAO
	@Autowired
	private CompanyRepo compdb;
	
	// Customer DAO
	@Autowired
	private CustomerRepo custdb;
	
	private final String NAME = "admin";
	private final String PASSWORD = "1234";
	
	/***
	 * Login method for Admin
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 */
	@Override
	public AdminFacade login(String name , String password , ClientType type)
	{
		if(!type.equals(ClientType.ADMIN))
		{
			System.out.println("You are trying to connect with the wrong type:" + type);
			return null;
		}
		String question = name + password;
		String answer = NAME + PASSWORD;
		
		if(question.equals(answer))
		{
			return this;	
		}
			else
		{
			return null;	
		}
	}
	
	
	//!!!!----Company----!!!!
	
	
	/***
	 * Creating new Company
	 * @param company
	 */
    public void createCompany(Company company)throws CompanyNameExistException
    {
    	// Checking if company already exist
    	Company check = compdb.findByName(company.getCompName());
    	if(check == null)
    	{
    		// Saving Company
    		compdb.save(company);
    	}
    	else
    	{
    		// Throwing Exception
    		throw new CompanyNameExistException("Company name:" + company.getCompName() + " , already exist.");
    	}
    }
 
    /***
     * Update Company
     * @param company
     * @throws CompanyNotExistException
     */
    public void updateCompany(Company company)throws CompanyNotExistException
    {
    	// Checking company exist for update
    	Company original = compdb.findOne(company.getId());
    	
    	if(original != null)
    	{
    		// Setting the original name to the new Company
    		// And update to the data base
    		company.setCompName(original.getCompName());
    		compdb.updateCompany(company.getPassword(), company.getEmail(), company.getId());
    	}
    	else
    	{
    		// Throwing exception
    		throw new CompanyNotExistException("Company with the id:" + company.getId() + " does not exist.");
    	}
    }
    
    
    /***
     * Remove Company by id
     * @param id
     * @throws CompanyNotExistException
     */
    public void removeCompany(int id)throws CompanyNotExistException
    {
    	// Checking if Company exist for remove
    	Company c = compdb.findOne(id);
    	if(c != null)
    	{
    	  // Removing
    	  compdb.delete(c);	
    	}
    	else
    	{
    		// Throwing exception
    		throw new CompanyNotExistException("There is no Company with the id:" + id);
    	}
    }
    
    /***
     * Get Company by id
     * @param id
     * @return
     * @throws CompanyNotExistException
     */
    public Company getCompany(int id)throws CompanyNotExistException
    {
    	// Checking Company exist for return
    	Company c = compdb.findOne(id);
    	if(c != null)
    	{
    		// Return Company
    		return c;
    	}
    	else
    	{
    		// Throwing exception
    		throw new CompanyNotExistException("There is no Company with the id:" + id);
    	}
    }
    
    /***
	 * Get Company by name
	 * @param name
	 * @return
	 * @throws CompanyNotExistException
	 */
	public Company getCompanyByName(String name)throws CompanyNotExistException
	{
		// Checking Company exist for return
		Company c = compdb.findByName(name);
		if(c != null)
		{
			// Return Company
			return c;
		}else
		{
			// Throwing exception
			throw new CompanyNotExistException("There is no Company with the name:" + name);
		}
	}
	
	/***
	 * Get All Companies
	 * @return
	 * @throws NoCompaniesException
	 */
	public List<Company> getAllCompanies()throws NoCompaniesException
	{
		// Making a list to return
		List<Company> companies = new ArrayList<>();
		companies = (List<Company>) compdb.findAll();
		
		// Checking if there is Companies on the data base
		if(!companies.isEmpty() && companies != null)
		{
			// return Companies
			return companies;
		}
		else
		{
			// Throwing exception
			throw new NoCompaniesException("There are no Companies on the data base");
		}
	}
	
	//!!!!----Customer----!!!!
	/***
	 * Create Customer
	 * @param customer
	 * @throws CustomerNameExistException
	 */
	public void createCustomer(Customer customer)throws CustomerNameExistException
	{
		// Checking if Customer already exist
		Customer check = custdb.findByName(customer.getCustName());
		if(check == null)
		{
			// Saving Customer
			custdb.save(customer);
		}
		else
		{
			// Throwing exception
			throw new CustomerNameExistException("Customer name:" + customer.getCustName() + " , already exist.");
		}
		
	}
	
	 /***
     * Update Customer
     * @param customer
     * @throws CustomerNotExistException
     */
    public void updateCustomer(Customer customer)throws CustomerNotExistException
    {
    	// Checking customer exist for update
    	Customer original = custdb.findOne(customer.getId());
    	
    	if(original != null)
    	{
    		// Setting the original name to the new Customer
    		// And update to the data base
    		customer.setCustName(original.getCustName());
    		custdb.updateCustomer(customer.getPassword(), customer.getId());
    	}
    	else
    	{
    		// Throwing exception
    		throw new CustomerNotExistException("Customer with the id:" + customer.getId() + " does not exist.");
    	}
    }


    /***
     * Remove Customer by id
     * @param id
     * @throws CustomerNotExistException
     */
    public void removeCustomer(int id)throws CustomerNotExistException
    {
    	// Checking if Customer exist for remove
    	Customer c = custdb.findOne(id);
    	if(c != null)
    	{
    	  // Removing
    	  custdb.deleteCustoerCoupon(id);
    	  custdb.delete(c);	
    	}
    	else
    	{
    		// Throwing exception
    		throw new CustomerNotExistException("There is no Customer with the id:" + id);
    	}
    }
    
    /***
     * Get Customer by id
     * @param id
     * @return
     * @throws CustomerNotExistException
     */
    public Customer getCustomer(int id)throws CustomerNotExistException
    {
    	// Checking Customer exist for return
    	Customer c = custdb.findOne(id);
    	if(c != null)
    	{
    		// Return Customer
    		return c;
    	}
    	else
    	{
    		// Throwing exception
    		throw new CustomerNotExistException("There is no Customer with the id:" + id);
    	}
    }
    
    /***
	 * Get Customer by name
	 * @param name
	 * @return
	 * @throws CustomerNotExistException
	 */
	public Customer getCustomerByName(String name)throws CustomerNotExistException
	{
		// Checking Customer exist for return
		Customer c = custdb.findByName(name);
		if(c != null)
		{
			// Return Customer
			return c;
		}else
		{
			// Throwing exception
			throw new CustomerNotExistException("There is no Customer with the name:" + name);
		}
	}
	
	/***
	 * Get All Customers
	 * @return
	 * @throws NoCustomersException
	 */
	public List<Customer> getAllCustomers()throws NoCustomersException
	{
		// Making a list to return
		List<Customer> customers = new ArrayList<>();
		customers = (List<Customer>) custdb.findAll();
		
		// Checking if there is Customers on the data base
		if(!customers.isEmpty() && customers != null)
		{
			// return Customers
			return customers;
		}
		else
		{
			// Throwing exception
			throw new NoCustomersException("There are no Customers on the data base");
		}
	}
}
