package com.wipro.exchange.service;

import com.wipro.exchange.entity.*;
import com.wipro.exchange.exception.*;

public class ExchangeService {

	public boolean validateExchange(int sourceID, int destinationID)
			throws InvalidSourceNodeException, InvalidDestinationNodeException {

		try {
			if ((sourceID < 1) || (sourceID >10)) {
				throw new InvalidSourceNodeException();
			}
			
		} catch (InvalidSourceNodeException e) {
			System.out.println(e.toString());
			return false;
		}

		try {
			if ( (destinationID < 1) || (destinationID > 10)) {
				throw new InvalidDestinationNodeException();
			}
		} catch (InvalidDestinationNodeException e) {
			System.out.println(e.toString());
			return false;
		}

		if (sourceID != destinationID )
			return true;
		else
			return false;

	}

	public ExchangeSourceBean registerSource(int sourceID, int destinationID) {
		ExchangeSourceBean esourceBean = new ExchangeSourceBean();
		try {
			if (validateExchange(sourceID, destinationID)) {
				String sourceKey = "*" + sourceID + "*";

				esourceBean.setConnectionState(true);
			} else {
				esourceBean.setConnectionState(false);
			}

		} catch (InvalidSourceNodeException | InvalidDestinationNodeException e) {
			esourceBean.setConnectionState(false);
			e.toString();
		}
		esourceBean.setSourceID(sourceID);
		return esourceBean;

	}

	public ExchangeDestinationBean registerDestination(int sourceID, int destinationID) {
		ExchangeDestinationBean edestinationBean = new ExchangeDestinationBean();
		try {
			if (validateExchange(sourceID, destinationID)) {
				String destinationKey = "+" + destinationID + "+";

				edestinationBean.setConnectionState(true);
			} else {
				//System.out.println(" Not true sourceId");
				edestinationBean.setConnectionState(false);
			}

		} catch (InvalidSourceNodeException | InvalidDestinationNodeException e) {
			edestinationBean.setConnectionState(false);
			e.toString();
		}
		edestinationBean.setDestinationID(destinationID);
		return edestinationBean;
	}

	public ExchangeBean transmitStatus(ExchangeSourceBean source, ExchangeDestinationBean dest) {
		ExchangeBean exchangeBean=new ExchangeBean();
		if(source.isConnectionState()==true && dest.isConnectionState()==true) {
			exchangeBean.setExchangeStatus(true);
		}
		else {
			exchangeBean.setExchangeStatus(false);
		}
		exchangeBean.setSourceID(source.getSourceID());
		exchangeBean.setDestinationID(dest.getDestinationID());
		return exchangeBean;
	}

	public ExchangeBean[] testService(int source[], int destination[]) {
		ExchangeBean[] exchangeBeanarr=new ExchangeBean[source.length];
		ExchangeSourceBean sourceBean=new ExchangeSourceBean();
		ExchangeDestinationBean destinationBean=new ExchangeDestinationBean();
		ExchangeBean exchangeBean=new ExchangeBean();
		for(int i=0;i<source.length;i++) {// 10,2,34,7,8
			                              //2,3,3,4,4
			 sourceBean=registerSource(source[i],destination[i]);
			 destinationBean=registerDestination(source[i],destination[i]);
			 exchangeBeanarr[i]=transmitStatus(sourceBean,destinationBean);
		}
		return exchangeBeanarr;

	}

	public String searchExchange(int source[], int destination[], int index) {
		ExchangeBean[] exchangeBeanarr=testService(source,destination);
		ExchangeBean exchnage=exchangeBeanarr[index];
		String Message="";
		if(exchnage.isExchangeStatus()==true) {
			Message=exchnage.getSourceID()+"-"+exchnage.getDestinationID()+":Success";
		}
		else {
			Message=exchnage.getSourceID()+"-"+exchnage.getDestinationID()+":Failure";
		}
		return Message;

	}

}
