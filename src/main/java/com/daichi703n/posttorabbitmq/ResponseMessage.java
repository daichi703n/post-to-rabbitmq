package com.daichi703n.posttorabbitmq;

public class ResponseMessage {
  private String status;
  private String message;
  private String request;
  
  public String getStatus(){
    return status;
  }
  public String getMessage(){
    return message;
  }
  public String getRequest(){
    return request;
  }
  public void setStatus(String status){
    this.status = status;
  }
  public void setMessage(String message){
    this.message = message;
  }
  public void setRequest(String request){
    this.request = request;
  }
}
