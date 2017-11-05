package com.github.olivier.ccmanager.service.creditcard;

import com.github.olivier.ccmanager.entity.creditcard.CreditCard;
import com.github.olivier.ccmanager.entity.creditcard.CreditCardValidationStatus;
import com.github.olivier.ccmanager.entity.creditcard.PersistCreditCardStatus;
import com.github.olivier.ccmanager.entity.user.User;

/**
 * Interface defining the way to interact with {@link CreditCard} Objects
 * @author odonn
 *
 */
public interface CreditCardService {
    	
    	/**
    	 * Perform validation steps on given card and returns the validation status
    	 * 
    	 * @param card the card to validate
    	 * @return
    	 */
	public CreditCardValidationStatus validate(CreditCard card);
	
	/**
	 * Persist the given credit card with its associated user
	 * @param userId id of the current logged user
	 * @param cardNumber
	 * @param cardOwner
	 * @param expiryMonth
	 * @param expiryYear
	 */
	public PersistCreditCardStatus saveCrediCard(Long userId, String cardNumber, String cardOwner, String expiryMonth, String expiryYear);
	
	/**
	 * Search for a credit card by it's card number
	 * @param user the current logged user
	 * @param number credit card number to search for
	 * @return
	 */
	public CreditCard getCreditCardByNumber(User user, String number);
}
