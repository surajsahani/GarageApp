package com.martial.garageapp;

public class CarRVModalPlus {

    private String makeName;
    private String ModelName;

    public CarRVModalPlus(String makeName, String modelName) {
        this.makeName = makeName;
        ModelName = modelName;
    }
    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }


    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }
}
