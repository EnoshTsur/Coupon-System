package com.example.demo;

import com.example.enums.ClientType;

public abstract class ClientFacade {

	
	public abstract ClientFacade login(String name , String password , ClientType type);
}
