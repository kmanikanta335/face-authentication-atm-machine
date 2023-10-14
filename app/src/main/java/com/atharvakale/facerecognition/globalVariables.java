package com.atharvakale.facerecognition;

public class globalVariables {
    private static globalVariables instance;
    private boolean faceRecognized;

    private globalVariables() {
        // Private constructor to prevent direct instantiation
    }

    public static globalVariables getInstance() {
        if (instance == null) {
            instance = new globalVariables();
        }
        return instance;
    }

    public boolean getGlobalVariable() {
        return faceRecognized;
    }

    public void setGlobalVariable(boolean value) {
        faceRecognized = value;

    }
}
