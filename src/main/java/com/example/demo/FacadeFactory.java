package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.enums.ClientType;
@Scope("singleton")
@Service
public class FacadeFactory {

	@Autowired
	private AdminFacade af;
	
	@Autowired
	private CustomerFacade custf;
	
	@Autowired
	private CompanyFacade compf;
	
	/***
	 * Facade Factory for getting the right facade;
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 */
	public ClientFacade login(String name, String password, ClientType type)
	{
		
		
		switch(type)
		{
		case ADMIN:
			return af.login(name, password, type);
		case COMPANY:
			return compf.login(name, password, type);
		case CUSTOMER:
			return custf.login(name, password, type);
		default:
		System.out.println("Type not known to this version");
		return null;
		}
	}

}
