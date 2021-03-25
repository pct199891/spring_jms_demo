package com.offcn.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

//消息生产者     //模拟另外一个人修改了代码
@Component
public class QueueProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination queueTextDestination;

    @Autowired
    private Destination dongyimai_queue_sms;//短信发送



    /**
     * 发送短信
     * @param text
     */
    public void sendTextMessage(String text){
        jmsTemplate.send(dongyimai_queue_sms, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setString("mobile","18580147550");
                mapMessage.setString("smscode","884822");
                return mapMessage;
            }
        });
    }
}
