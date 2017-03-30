package com.madeintrees.controllers;

import com.madeintrees.model.Subscription;
import com.madeintrees.repository.SubscriptionRepository;
import com.madeintrees.service.SubscriptionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Prithu on 29-03-2017.
 */
@Controller
public class UserController {
    @Autowired
    private SubscriptionManager subscriptionManager;
    private static final Log _log = LogFactory.getLog(UserController.class);
    @RequestMapping(value = "/user")
    public String showUserPage(Model model){
        List<Subscription> activeSubscriptions=subscriptionManager.findBySubscriptionStatus("Active");
        List<Subscription> intializedCodSubscriptions=subscriptionManager.findBySubscriptionStatusAndPaymentType("initialized", "cod");
        model.addAttribute("activeSubscriptions", activeSubscriptions);
        model.addAttribute("intializedCodSubscriptions", intializedCodSubscriptions);
        _log.info("activeSubscriptions : "+activeSubscriptions);
        _log.info("intializedCodSubscriptions : "+intializedCodSubscriptions);
        return "userHome";
    }
}
