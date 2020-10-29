package project.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.exception.AppJsonParseException;
import project.exception.AppQueueException;
import project.utils.CartListWrapper;
import project.utils.JSONWrapperParser;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

@Component
public class Producer {

    @Autowired private ActiveMQConnectionFactory factory;
    @Autowired private JSONWrapperParser parser;
    private String queueName = "QUEUE";

    public void sendMessage(CartListWrapper wrapper) throws AppQueueException, AppJsonParseException {
        try {
            Connection connection = factory.createQueueConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            ActiveMQTextMessage message = (ActiveMQTextMessage) session.createTextMessage();
            String s = parser.serializeToJson(wrapper);
            message.setText(s);
            producer.send(message);
            session.close();
            connection.close();
        } catch (JMSException e) {
            throw new AppQueueException("Exception in ActiveMQ connection", e);
        }
    }
}
