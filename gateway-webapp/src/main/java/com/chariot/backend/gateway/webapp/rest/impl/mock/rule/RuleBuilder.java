package com.chariot.backend.gateway.webapp.rest.impl.mock.rule;

public class RuleBuilder {

    public INotificationRule createOutOfBoundsRule(double lowerBound, double upperBound)
    {
        return new MeasurementOutOfBoundRule(lowerBound,upperBound);
    }

    public INotificationRule createNewMeasurementRule()
    {
        return new NewMeasurementValueRule();
    }
}
