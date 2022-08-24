package com.martial.garageapp;

public class CarRVModelPlus {

    private String makeName;
    private String makeID;
    private String ModelID;
    private String ModelName;

    public CarRVModelPlus(String makeName, String makeID, String modelID, String modelName) {
        this.makeName = makeName;
        this.makeID = makeID;
        ModelID = modelID;
        ModelName = modelName;
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

    public String getModelID() {
        return ModelID;
    }

    public void setModelID(String modelID) {
        ModelID = modelID;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }
}
