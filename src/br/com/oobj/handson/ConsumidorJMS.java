package br.com.oobj.handson;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "activemq/queue/Entrada") })
public class ConsumidorJMS implements MessageListener {

	@Inject
	private ProdutorJMS produtor;

	@Override
	public void onMessage(Message message) {
		System.out.println("Got it! I'm doing some processing...");
		try {
			System.out.println("Dormindo a thread: " + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("Voltando a thread: " + Thread.currentThread().getName());
			String text = ((TextMessage) message).getText();
			produtor.enviarMsgResposta("I did a lot of processing, so now I'll answer: '" + text + "'");
		} catch (JMSException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
