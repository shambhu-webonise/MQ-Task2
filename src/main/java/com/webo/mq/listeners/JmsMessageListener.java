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
		
		String currentUsersHomeDir = System.getProperty("user.home");
		String mqTempDirectory = String.format("%s%s", currentUsersHomeDir, "/MQ/tmp");
		File txtFile = new File(mqTempDirectory, message.getJMSMessageID()+ ".txt");
		if (!txtFile.getParentFile().exists()) {
			txtFile.getParentFile().mkdirs();
		}
		try(PrintWriter pw = new PrintWriter(txtFile)) {
			pw.write(message.getText());
			pw.flush();
		} catch (FileNotFoundException e) {
			LOG.error("Error Occured while writing into file: {}", txtFile.getName());
		}
	}

}
