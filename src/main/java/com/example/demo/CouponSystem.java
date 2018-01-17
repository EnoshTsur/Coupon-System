package com.example.demo;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.enums.ClientType;


@Service
@Scope("singleton")
public class CouponSystem {
	
	
	// Facade Factory
	@Autowired
	private FacadeFactory facade;
	
	private Thread thread;
	
	private boolean running = false;
	
	@Autowired
	private DailyService ds;
	
	/***
	 * Private CTR
	 */
	public CouponSystem() {
		
	}

	
	/***
	 * Login to the system
	 * 
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 */
	public ClientFacade login(String name, String password, ClientType type) 
	{
		start();
		return facade.login(name, password, type);
	}	
	

	/***
	 * Stopping the daily Thread
	 * 
	 */
	public synchronized void shutdown() 
	{
		if(!running) return;
		running = false;
	}
	
	/***
	 * Starting the daily Thread
	 */
	public synchronized void start() {
		if(running) return;
		running = true;
		
		 thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(running){
					
					List<Coupon> expireds = ds.getExpired();
					ds.removeExpired(expireds);
					
					try {
						Thread.sleep(86400000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.setName("Elyahu");
		thread.start();
	}
	
}
