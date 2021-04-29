package com.daichi703n.posttorabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PostPublishController {
  
  @Autowired RabbitPublisher publisher;
  
  private ObjectMapper mapper = new ObjectMapper();

  @RequestMapping()
  private String hello(){
    return "hello! Please POST to /publish";
  }
  @PostMapping("/publish")
  private ResponseEntity<?> publish(@RequestBody @Validated PostMessage postMassage) throws Exception {
    System.out.println("POST: "+mapper.writeValueAsString(postMassage));
    try {
      publisher.publish(mapper.writeValueAsString(postMassage));
    } catch (Exception e) {
      e.printStackTrace();
      postMassage.setStatus("500");
      return ResponseEntity.status(500).body(postMassage);
    }
    postMassage.setStatus("200");
    return ResponseEntity.status(200).body(postMassage);
  }
}
