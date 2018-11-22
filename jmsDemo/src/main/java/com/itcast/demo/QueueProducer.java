package com.itcast.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {
    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.137:61616");
        //获取连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //获取session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列对象
        Queue queue = session.createQueue("test-queue");
        //创建消费生产者
        MessageProducer producer = session.createProducer(queue);
        //创建消息
        TextMessage textMessage = session.createTextMessage("欢迎来品优购世界");
        //发送消息
        producer.send(textMessage);
        //关闭资源
        producer.close();
        session.close();
        connection.close();


    }
}
