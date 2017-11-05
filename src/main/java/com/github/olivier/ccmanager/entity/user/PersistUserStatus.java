package com.github.olivier.ccmanager.entity.user;

/**
 * Status of user persist
 * @author odonn
 *
 */
public enum PersistUserStatus {
    
    SUCCESS("Your account have been created"),
    FAILURE("An error has occured, please contact an administrator");
    
    private String message;
    
    private PersistUserStatus(String messagge) {
	this.setMessage(messagge);
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
    
}
