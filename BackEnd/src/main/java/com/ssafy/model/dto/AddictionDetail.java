package com.ssafy.model.dto;

import java.util.List;

public class AddictionDetail {
    private Addiction addiction;
    private List<Badge> badges;

    public AddictionDetail(Addiction addiction, List<Badge> badges) {
        this.addiction = addiction;
        this.badges = badges;
    }

    public Addiction getAddiction() {
        return addiction;
    }

    public void setAddiction(Addiction addiction) {
        this.addiction = addiction;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "AddictionDetail{" +
               "addiction=" + addiction +
               ", badges=" + badges +
               '}';
    }
}