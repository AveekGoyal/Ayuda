package com.example.admin.ayuda.Model;

/**
 * Created by Admin on 19-Mar-18.
 */

public class Event {
    public String eventDescription;
    public String eventEndDate;
    public String eventEndTime;
    public String eventStartDate;
    public String eventStartTime;
    public String eventTitle;
    public String eventType;
    private String picProof;
    public String sponsorRequired;
    public String volunteerRequired;
    public String eventOrgName;
    public String eventOrgDp;

    public Event() {
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPicProof() {
        return picProof;
    }

    public void setPicProof(String picProof) {
        this.picProof = picProof;
    }

    public String getSponsorRequired() {
        return sponsorRequired;
    }

    public void setSponsorRequired(String sponsorRequired) {
        this.sponsorRequired = sponsorRequired;
    }

    public String getVolunteerRequired() {
        return volunteerRequired;
    }

    public void setVolunteerRequired(String volunteerRequired) {
        this.volunteerRequired = volunteerRequired;
    }

    public String getEventOrgName() {
        return eventOrgName;
    }

    public void setEventOrgName(String eventOrgName) {
        this.eventOrgName = eventOrgName;
    }

    public String getEventOrgDp() {
        return eventOrgDp;
    }

    public void setEventOrgDp(String eventOrgDp) {
        this.eventOrgDp = eventOrgDp;
    }
}
