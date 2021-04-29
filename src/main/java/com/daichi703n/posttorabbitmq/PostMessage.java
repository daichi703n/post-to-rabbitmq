package com.daichi703n.posttorabbitmq;

import javax.validation.constraints.NotNull;

public class PostMessage {
  @NotNull
  private String id;
  @NotNull
  private String message;
  private String status;
  
  public String getId(){
    return id;
  }
  public String getMessage(){
    return message;
  }
  public String getStatus(){
    return status;
  }
  public void setStatus(String status){
    this.status = status;
  }
}
