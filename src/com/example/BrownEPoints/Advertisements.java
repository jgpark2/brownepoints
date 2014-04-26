package com.example.BrownEPoints;

import android.util.Log;

/**
 * Created by Ahindes5 on 4/14/14.
 */
public class Advertisements {

    private int AdId;
    private int pointValue;
    private String Domain;
    private String companyName;
    private int companyID;

    public Advertisements(int adid, int ptValue, String website, String cmpName, int cmpId){

        Log.d("Function Call to constructor Successful ", "adid " + adid + "ptValue " + ptValue + "website " + website + "cmpname " + cmpName +"cmpId " + cmpId);
        AdId = adid;
        pointValue = ptValue;
        Domain = website;
        companyName = cmpName;
        companyID = cmpId;
    }

    public int getAdId() {
        return AdId;
    }

    public void setAdId(int adId) {
        AdId = adId;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyName(int companyID) {
        this.companyID = companyID;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }


}
