package com.github.olivier.ccmanager.service.creditcard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.olivier.ccmanager.entity.creditcard.CreditCard;
import com.github.olivier.ccmanager.entity.creditcard.CreditCardValidationStatus;
import com.github.olivier.ccmanager.entity.creditcard.PersistCreditCardStatus;
import com.github.olivier.ccmanager.entity.user.User;
import com.github.olivier.ccmanager.repository.creditcard.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardValidator creditCardValidator;

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Override
    public CreditCardValidationStatus validate(CreditCard card) {
	CreditCardValidationStatus result = CreditCardValidationStatus.SUCCESS;

	if (!creditCardValidator.isValid(card.getNumber())) {

	    result = CreditCardValidationStatus.INVALID_NUMBER;

	} else if (card.getExpiryDate().before(new Date())) {

	    result = CreditCardValidationStatus.EXPIRED_DATE;

	} else if (StringUtils.isBlank(card.getName()) 
		|| card.getName().split(" ").length < 2) {

	    result = CreditCardValidationStatus.INVALID_OWNER;
	}

	return result;
    }

    @Override
    public PersistCreditCardStatus saveCrediCard(Long userId, String cardNumber, String cardOwner, String expiryMonth, String expiryYear) {
	
	PersistCreditCardStatus result = null;
	
	try {

	    final Date expiryDate = simpleDateFormat.parse(expiryMonth + "/" + expiryYear);
	    final CreditCard creditCard = new CreditCard(userId, cardNumber, cardOwner, expiryDate);

	    final CreditCardValidationStatus validationResult = validate(creditCard);

	    if (validationResult.equals(CreditCardValidationStatus.SUCCESS)) {
		
		final List<CreditCard> creditCardList = creditCardRepository.findByNumber(cardNumber);
		
		// No credit card was found with this number, we can persist it in the system
		if (creditCardList == null || creditCardList.size() == 0) {
		    
		    creditCardRepository.save(creditCard);
		    
		    result = PersistCreditCardStatus.SUCCESS;
		} else {

		    logger.info("Failed persist, card information already existing");
		    result = PersistCreditCardStatus.FAILURE_ALREADY_EXISTS;
		}
	    } else {

		logger.info("Failed validation, reason is : " + validationResult);
		result = PersistCreditCardStatus.FAILURE_VALIDATION;
	    }
	} catch (ParseException e) {

	    logger.info("Could not parse given expiry date");
	    result = PersistCreditCardStatus.FAILURE;
	}
	
	return result;
    }

    @Override
    public CreditCard getCreditCardByNumber(User user, String number) {

	CreditCard result = null;
	List<CreditCard> creditCardsForNumber = null;

	if (StringUtils.isNotBlank(user.getRole())) {
	    
	    // If user is admin, he gets access to any card number.
	    // Otherwise, we need to validate he is the creator of the card
	    if (user.getRole().equals("ADMIN")) {

		creditCardsForNumber = creditCardRepository.findByNumber(number);

	    } else {

		creditCardsForNumber = creditCardRepository.findByNumberAndUserId(number, user.getId());
	    }
	}
	
	if (CollectionUtils.isNotEmpty(creditCardsForNumber)) {

	    result = creditCardsForNumber.get(0);
	}

	return result;
    }
}
