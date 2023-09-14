package com.samfrosh.martmobile.environemnt;

public class Environment {

    private static  final String STAGING_ENVIRONMENT = "https://localhost:8080/";
    private static final String PRODUCTION_ENVIRONMENT = "https://amused-tooth-production.up.railway.app/";

    static EnvironmentType environmentType = EnvironmentType.PRODUCTION;

    public static String getBaseUrl(){

        switch (environmentType){
            case PRODUCTION :
                return PRODUCTION_ENVIRONMENT;
            case STAGING :
                return STAGING_ENVIRONMENT;
            default:
                return "";
        }
    }


    private enum EnvironmentType{
        PRODUCTION,
        STAGING
    }
}
