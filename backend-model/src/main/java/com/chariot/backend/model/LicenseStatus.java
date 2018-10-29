package com.chariot.backend.model;

/**
 * Created by Dariusz Kijania on 8/14/2017.
 */
public class LicenseStatus {
    private boolean isExpired;
    private String comment;
    private boolean isExceedingTps;

    public LicenseStatus(boolean isExpired, String comment, boolean isExceedingTps) {
        this.isExpired = isExpired;
        this.comment = comment;
        this.isExceedingTps = isExceedingTps;
    }

    public LicenseStatus() {
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isExceedingTps() {
        return isExceedingTps;
    }

    public void setExceedingTps(boolean exceedingTps) {
        isExceedingTps = exceedingTps;
    }

    public boolean isOk() {
        return !(isExpired() || isExceedingTps());
    }
}
