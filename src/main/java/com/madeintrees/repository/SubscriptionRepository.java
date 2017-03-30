package com.madeintrees.repository;

import com.madeintrees.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Prithu on 29-03-2017.
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer>{
    public List<Subscription> findBySubscriptionStatus(String subscriptionStatus);
    public List<Subscription> findBySubscriptionStatusAndPaymentType(String subscriptionStatus, String paymentType);
}
