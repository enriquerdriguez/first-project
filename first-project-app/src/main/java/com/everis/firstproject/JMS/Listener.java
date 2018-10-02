package com.everis.firstproject.JMS;

import java.util.logging.Logger;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/carNotificationQueue")
public class Listener implements MessageListener{

	private static final  Logger LOG = Logger.getLogger(Listener.class.getName());
	
	@Override
	public void onMessage(Message message) {
		try {
			long id = message.getLongProperty("ID");
			String brand = message.getStringProperty("BRAND");
			String action = message.getStringProperty("ACTION");
			String mensaje = "Car number: " + id + " branded: " + brand +" has been: "+ action; 
			LOG.info(mensaje);
		}catch(JMSException e) {
			e.printStackTrace();
		}
	}
}
