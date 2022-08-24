package com.martial.garageapp;

public class CarRVModal {
    private String makeName;
    private String makeID;

    public CarRVModal(String makeName, String makeID) {
        this.makeName = makeName;
        this.makeID = makeID;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getMakeID() {
        return makeID;
    }

    public void setMakeID(String makeID) {
        this.makeID = makeID;
    }

}
