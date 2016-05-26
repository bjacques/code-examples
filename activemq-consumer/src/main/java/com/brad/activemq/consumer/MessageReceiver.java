package com.brad.activemq.consumer;

import javax.jms.MapMessage;
import javax.jms.Message;

public class MessageReceiver {
    public void onMessage(final Message message) {
        if (message instanceof MapMessage) {
            final MapMessage mapMessage = (MapMessage) message;
            System.out.println("Yahoo!");
        }
    }
}
