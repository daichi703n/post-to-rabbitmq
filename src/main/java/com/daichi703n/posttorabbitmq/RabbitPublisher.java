package com.daichi703n.posttorabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RabbitPublisher{

  @Autowired
  private TopicExchange exchange;
  
  private final RabbitTemplate rabbitTemplate;

  public RabbitPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publish(String postMessage) throws Exception {
    System.out.println("Sending <"+postMessage+">");
    rabbitTemplate.convertAndSend(
      exchange.getName(), "foo.bar.baz", postMessage
      );

  }
}
