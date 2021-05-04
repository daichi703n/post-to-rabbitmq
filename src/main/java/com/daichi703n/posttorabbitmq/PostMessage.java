package com.daichi703n.posttorabbitmq;

import javax.validation.constraints.NotNull;

public class PostMessage {
  @NotNull
  private String id;
  @NotNull
  private String message;
  
  public String getId(){
    return id;
  }
  public String getMessage(){
    return message;
  }
}
