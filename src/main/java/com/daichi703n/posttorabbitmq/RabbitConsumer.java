package com.daichi703n.posttorabbitmq;

import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

  public void receiveMessage(String message) throws InterruptedException {
    System.out.println("Received <" + message + ">");
  }
}
