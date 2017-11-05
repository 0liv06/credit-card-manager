package com.github.olivier.ccmanager.entity.response;

/**
 * Response object allowing to send richer information to the front end
 * @author odonn
 *
 * @param <T>
 */
public class Response<T> {
    private T entity;
    private String message;
    private int code;
    
    public Response(T entity, String message, int code) {
	this.entity = entity;
	this.message = message;
	this.code = code;
    }
    
    public T getEntity() {
	return entity;
    }
    
    public void setEntity(T entity) {
	this.entity = entity;
    }
    
    public String getMessage() {
	return message;
    }
    
    public void setMessage(String message) {
	this.message = message;
    }
    
    public int getCode() {
	return code;
    }
    
    public void setCode(int code) {
	this.code = code;
    }
}
