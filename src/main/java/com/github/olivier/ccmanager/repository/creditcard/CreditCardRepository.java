package com.github.olivier.ccmanager.repository.creditcard;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.olivier.ccmanager.entity.creditcard.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {

    /**
     * Allows to find a CreditCard by its number
     * @param number
     * @return
     */
    public List<CreditCard> findByNumber(String number);

    /**
     * Allows to find a CreditCard for a specific user
     * @param number
     * @param userId
     * @return
     */
    public List<CreditCard> findByNumberAndUserId(String number, Long userId);
}
