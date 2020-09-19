package com.cybene.farmloan.utils.items;

import com.google.gson.annotations.SerializedName;

public class DetailsResponse {
    public String FirstName;
    @SerializedName("PrimaryPhone")
    public String PrimaryPhone;
    @SerializedName("SecondaryPhone")
    public String SecondaryPhone;
    @SerializedName("Email")
    public String email;
    @SerializedName("IdNo")
    public String IdNo;
    @SerializedName("BusinessType")
    public String BusinessType;
    @SerializedName("Location")
    public String Location;
    @SerializedName("MonthlyRevenue")
    public  String MonthlyRevenue;

    public DetailsResponse(String firstName, String primaryPhone, String secondaryPhone, String email, String idNo, String businessType, String location, String monthlyRevenue) {
        FirstName = firstName;
        PrimaryPhone = primaryPhone;
        SecondaryPhone = secondaryPhone;
        this.email = email;
        IdNo = idNo;
        BusinessType = businessType;
        Location = location;
        MonthlyRevenue = monthlyRevenue;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getPrimaryPhone() {
        return PrimaryPhone;
    }

    public String getSecondaryPhone() {
        return SecondaryPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getIdNo() {
        return IdNo;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public String getLocation() {
        return Location;
    }

    public String getMonthlyRevenue() {
        return MonthlyRevenue;
    }
}
