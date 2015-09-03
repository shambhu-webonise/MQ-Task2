package com.webo.mq.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class JmsMessageListener implements SessionAwareMessageListener<TextMessage> {
	private static final Logger LOG = LoggerFactory.getLogger(JmsMessageListener.class);
	
	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		LOG.info("Message Recieved: {}", message.getText());
		ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage();
		activeMQTextMessage.setText(message.getText());
		
		File file = new File("/home/webonise/NM/tmp");
		if (!file.exists()) {
			file.mkdirs();
			
		}
		File txtFile = new File("/home/webonise/NM/tmp/", message.getJMSMessageID()+ ".txt");
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(txtFile);
			printWriter.write(message.getText());
		} catch (FileNotFoundException e) {
			LOG.error("Error Occured while writing into file: {}", txtFile.getName());
		}
	}

}
