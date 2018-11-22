package com.itcast.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class QueueConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        //创建工厂连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.137:61616");
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //获取session'
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Queue queue = session.createQueue("test-queue");
        //创建信息消费
        MessageConsumer consumer = session.createConsumer(queue);
        //监听消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("接受到消息："+textMessage.getText());

                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
			//什么鬼
        });
        //等待键盘输入
        System.in.read(); //9.关闭资源
        consumer.close();
        session.close();
        connection.close();


    }



}
