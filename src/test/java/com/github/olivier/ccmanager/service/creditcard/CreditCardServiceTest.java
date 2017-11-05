package com.github.olivier.ccmanager.service.creditcard;

import java.util.Calendar;
import java.util.Date;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.olivier.ccmanager.entity.creditcard.CreditCard;
import com.github.olivier.ccmanager.entity.creditcard.CreditCardValidationStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(value={"classpath:creditcard.properties"})
public class CreditCardServiceTest {
    
    @Autowired
    private CreditCardService creditCardService;
    
    @Value("${credit.card.number.valid}")
    private String validCardNumber;
    
    @Value("${credit.card.owner.valid}")
    private String validOwner;
    
    @Value("${credit.card.number.not.valid}")
    private String notValidCardNumber;
    
    @Value("${credit.card.owner.not.valid}")
    private String notValidOwner;
    
    @Test
    public void validateTest() {
	
	final Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, 1);
	
	final Date validDate = calendar.getTime();
	
	final CreditCard validCreditCard = new CreditCard(1L, validCardNumber, validOwner, validDate);
	final CreditCardValidationStatus validResult = creditCardService.validate(validCreditCard);
	
	MatcherAssert.assertThat("Valid Result", validResult.equals(CreditCardValidationStatus.SUCCESS));
    }
    
    @Test
    public void validateNumberTest() {
	
	final Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, 1);
	
	final Date validDate = calendar.getTime();
	
	final CreditCard invalidCreditCard = new CreditCard(1L, notValidCardNumber, validOwner, validDate);
	final CreditCardValidationStatus invalidResult = creditCardService.validate(invalidCreditCard);
	
	MatcherAssert.assertThat("Invalid Number", invalidResult.equals(CreditCardValidationStatus.INVALID_NUMBER));
    }
    
    @Test
    public void validateDateTest() {
	
	final Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);
	
	final Date invalidDate = calendar.getTime();
	
	final CreditCard invalidCreditCard = new CreditCard(1L, validCardNumber, validOwner, invalidDate);
	final CreditCardValidationStatus invalidResult = creditCardService.validate(invalidCreditCard);
	
	MatcherAssert.assertThat("Invalid Date", invalidResult.equals(CreditCardValidationStatus.EXPIRED_DATE));
    }
    
    @Test
    public void validateOwnerTest() {
	
	final Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, 1);
	
	final Date validDate = calendar.getTime();
	
	
	final CreditCard invalidCreditCard = new CreditCard(1L, validCardNumber, notValidOwner, validDate);
	final CreditCardValidationStatus invalidResult = creditCardService.validate(invalidCreditCard);
	
	MatcherAssert.assertThat("Invalid Owner", invalidResult.equals(CreditCardValidationStatus.INVALID_OWNER));
    }
}
