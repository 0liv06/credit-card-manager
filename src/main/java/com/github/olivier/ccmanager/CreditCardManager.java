package com.github.olivier.ccmanager;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.olivier.ccmanager.entity.creditcard.CreditCard;
import com.github.olivier.ccmanager.entity.creditcard.PersistCreditCardStatus;
import com.github.olivier.ccmanager.entity.response.Response;
import com.github.olivier.ccmanager.entity.user.PersistUserStatus;
import com.github.olivier.ccmanager.entity.user.User;
import com.github.olivier.ccmanager.service.creditcard.CreditCardService;
import com.github.olivier.ccmanager.service.user.UserService;
import com.google.gson.Gson;

@Configuration
@RestController
@SpringBootApplication
public class CreditCardManager {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private Gson gson;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json")
    public String saveUser(HttpServletRequest request, 
	    @RequestParam("username") String username,
	    @RequestParam("password") String password, 
	    @RequestParam(value = "admin", required = false) boolean admin) {
	
	final PersistUserStatus result = userService.saveUser(username, password, admin);
	final Response<PersistUserStatus> response = new Response<>(result, result.getMessage(), 200);
	
	return gson.toJson(response);
    }

    @RequestMapping(value = "/saveCard", method = RequestMethod.POST, produces = "application/json")
    public String saveCard(HttpServletRequest request,
	    @RequestParam("username") String username,
	    @RequestParam("cardNumber") String cardNumber,
	    @RequestParam("cardOwner") String cardOwner,
	    @RequestParam("expiryMonth") String expiryMonth,
	    @RequestParam("expiryYear") String expiryYear) {

	final User user = userService.getUserByLogin(username);
	final PersistCreditCardStatus result = creditCardService.saveCrediCard(user.getId(), cardNumber, cardOwner, expiryMonth, expiryYear);
	final Response<PersistCreditCardStatus> response = new Response<>(result, result.getMessage(), 200);

	return gson.toJson(response);
    }

    @RequestMapping(value = "/getCreditCard", method = RequestMethod.POST, produces = "application/json")
    public String getCreditCard(HttpServletRequest request, 
	    @RequestParam("username") String username,
	    @RequestParam("cardNumber") String cardNumber) {

	final User user = userService.getUserByLogin(username);
	final CreditCard creditCard = creditCardService.getCreditCardByNumber(user, cardNumber);
	final Response<CreditCard> response = new Response<CreditCard>(creditCard, StringUtils.EMPTY, 200);

	return gson.toJson(response);
    }

    public static void main(String[] args) throws Throwable {
	SpringApplication.run(CreditCardManager.class, args);
    }

}
