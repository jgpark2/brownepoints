package com.example.BrownEPoints;

/**
 * Created by Ahindes5 on 4/24/14.
 */
public class CompanyWithNumberAds {




    private int companyID;
    private int numberOfAds;
    private String companyName;

    CompanyWithNumberAds(int compID, String compName,  int numAds){
        companyID = compID;
        numberOfAds = numAds;
        companyName = compName;
    }

    public int getCompanyID() {
        return companyID;
    }

    public int getNumberOfAds() {
        return numberOfAds;
    }

    public String getCompanyName() {
        return companyName;
    }


}
