package com.github.olivier.ccmanager.entity.creditcard;

/**
 * Possible status while trying to persist a credit card
 * @author odonn
 *
 */
public enum PersistCreditCardStatus {
    
    SUCCESS("Credit card added"),
    FAILURE_ALREADY_EXISTS("The credit card you are trying to save already exists"),
    FAILURE_VALIDATION("Failed to validate the credit card information"),
    FAILURE("Failed persisting card information");
    
    private String message;
    
    private PersistCreditCardStatus(String message) {
	this.setMessage(message);
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
