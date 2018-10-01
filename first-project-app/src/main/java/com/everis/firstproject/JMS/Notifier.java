package com.everis.firstproject.JMS;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

@Singleton 
public class Notifier {

	@Resource(lookup = "jms/__defaultConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(lookup = "jms/carNotificationQueue")
	private Queue queue;
	
	public void sendNotification(long id, String brand, String action) {
		try (final JMSContext jmsContext = connectionFactory.createContext()){
			final JMSProducer jmsProducer = jmsContext.createProducer();
			jmsProducer.setProperty("ID", id);
			jmsProducer.setProperty("BRAND", brand);
			jmsProducer.setProperty("ACTION", action);
			jmsProducer.send(queue,(String) null);
			
		}
	}
	
}
