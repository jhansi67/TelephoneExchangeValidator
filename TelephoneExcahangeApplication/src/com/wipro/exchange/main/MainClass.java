package com.wipro.exchange.main;

import com.wipro.exchange.service.ExchangeService;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	int source[]={35,5,3,4,5,10};
	int destination[]={1,5,10,2,2,5};
	int index=0;
	ExchangeService ess = new ExchangeService();
	System.out.println(ess.searchExchange(source,destination,index));
	}

}
