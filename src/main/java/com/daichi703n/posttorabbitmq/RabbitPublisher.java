package com.daichi703n.posttorabbitmq;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.connection.CorrelationData.Confirm;
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

  @Value("${app.is-confirm-enabled:false}")
  private boolean isConfirmEnabled;

  public void publish(String postMessage) throws Exception {
    System.out.println("Sending <"+postMessage+">");
    CorrelationData correlationData = new CorrelationData("Correlation for message "+postMessage);

    if (isConfirmEnabled){
      System.out.println(correlationData);
      rabbitTemplate.convertAndSend(
        exchange.getName(), "foo.bar.baz", postMessage
        ,correlationData
      );
      System.out.println("Wait confirm...");
      Confirm confirm = correlationData.getFuture().get(10, TimeUnit.SECONDS);
      System.out.println("Confirm received, ack = " + confirm.isAck());
  } else {
      rabbitTemplate.convertAndSend(
        exchange.getName(), "foo.bar.baz", postMessage
      );
    }
  }

  }
}
