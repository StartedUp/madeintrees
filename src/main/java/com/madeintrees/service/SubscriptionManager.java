package com.madeintrees.service;

import com.madeintrees.model.Subscription;

import java.util.List;

/**
 * Created by Prithu on 29-03-2017.
 */
public interface SubscriptionManager {
    public List<Subscription> findBySubscriptionStatus(String subscriptionStatus);
    public List<Subscription> findBySubscriptionStatusAndPaymentType(String subscriptionStatus, String paymentType);
    public Subscription findById(Integer id);
}
