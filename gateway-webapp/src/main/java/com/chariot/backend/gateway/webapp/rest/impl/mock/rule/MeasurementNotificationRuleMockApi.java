package com.chariot.backend.gateway.webapp.rest.impl.mock.rule;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MeasurementNotificationRuleMockApi  {

    private Map<String,INotificationRule> ruleChannelMapping = new HashMap<>();

    public RuleBuilder getBuilder()
    {
        return new RuleBuilder();
    }

    public Map<String,INotificationRule> getRuleChannelMapping() {
        return ruleChannelMapping;
    }
}

