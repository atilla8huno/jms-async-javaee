package br.com.oobj.handson;

import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Singleton
public class ProdutorJMS {

	// tentar jms/ConnectionFactory
	@Resource(mappedName = "java:/activemq/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:/activemq/queue/Respostas")
	private Destination queueRespostas;
	
	@Resource(mappedName = "java:/activemq/queue/Entrada")
	private Destination queueEntrada;
	
	private Connection conn;
	
	public void enviarMsgResposta(String textMsg) throws JMSException {
		init();
		Session session = null;
		MessageProducer produtor = null;
		try {
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			produtor = session.createProducer(queueRespostas);
			produtor.setDeliveryMode(DeliveryMode.PERSISTENT);

			TextMessage msg = session.createTextMessage(textMsg);
			produtor.send(msg);
		} finally {
			try {
				if (produtor != null) {
					produtor.close();
				}
			} finally {
				if (session != null) {
					session.close();
				}
			}
			destroy();
		}
	}
	
	public void enviarMsgEntrada(String textMsg) throws JMSException {
		init();
		Session session = null;
		MessageProducer produtor = null;
		try {
			session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
			produtor = session.createProducer(queueEntrada);
			produtor.setDeliveryMode(DeliveryMode.PERSISTENT);

			TextMessage msg = session.createTextMessage(textMsg);
			produtor.send(msg);
		} finally {
			try {
				if (produtor != null) {
					produtor.close();
				}
			} finally {
				if (session != null) {
					session.close();
				}
			}
			destroy();
		}
	}
	
	public void init() throws JMSException {
		conn = connectionFactory.createConnection();
		conn.start();
	}

	public void destroy() throws JMSException {
		conn.close();
	}
}
