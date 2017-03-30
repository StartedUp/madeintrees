package com.madeintrees.service;

import com.madeintrees.model.Subscription;
import com.madeintrees.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Prithu on 29-03-2017.
 */
@Service
@Transactional
public class SubscriptionManagerImpl implements SubscriptionManager {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> findBySubscriptionStatus(String subscriptionStatus) {
        return subscriptionRepository.findBySubscriptionStatus(subscriptionStatus);
    }

    @Override
    public List<Subscription> findBySubscriptionStatusAndPaymentType(String subscriptionStatus, String paymentType) {
        return subscriptionRepository.findBySubscriptionStatusAndPaymentType(subscriptionStatus, paymentType);
    }
}
