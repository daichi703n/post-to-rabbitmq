package com.daichi703n.posttorabbitmq;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PostPublishController {
  @RequestMapping()
  private String hello(){
    return "hello! Please POST to /publish";
  }
  @PostMapping("/publish")
  private ResponseEntity<?> publish(@RequestBody @Validated PostMessage postMassage){
    System.out.println(postMassage.getId()+postMassage.getMessage());
    
    return ResponseEntity.ok(postMassage);
  }
}
