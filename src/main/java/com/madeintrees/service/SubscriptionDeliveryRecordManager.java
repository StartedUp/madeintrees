package com.madeintrees.service;

import com.madeintrees.model.SubscriptionDeliveryRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Prithu on 30-03-2017.
 */
public interface SubscriptionDeliveryRecordManager {
    public List<SubscriptionDeliveryRecord> findByDate(Date date);
}
