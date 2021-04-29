package com.daichi703n.posttorabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  
  static final String topicExchangeName = "spring-boot-exchange";
  static final String queueName = "spring-boot";
  
  @Bean
  public Queue queue() {
      return new Queue(queueName, false);
  }

  @Bean
  public TopicExchange exchange() {
      return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
          MessageListenerAdapter listenerAdapter) {
      SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
      container.setConnectionFactory(connectionFactory);
      container.setQueueNames(queueName);
      container.setMessageListener(listenerAdapter);
      return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(RabbitConsumer rabbitConsumer) {
      return new MessageListenerAdapter(rabbitConsumer, "receiveMessage");
  }

}
