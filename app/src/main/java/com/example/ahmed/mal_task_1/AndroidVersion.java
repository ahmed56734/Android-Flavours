package com.example.ahmed.mal_task_1;

/**
 * Created by ahmed on 10/14/16.
 */

public class AndroidVersion {
    private String name;
    private String version;
    private String apiLevel;
    private int imageID;

    AndroidVersion(String name, String version, String apiLevel){

        this.name = name;
        this.version = version;
        this.apiLevel = apiLevel;
    }

    AndroidVersion(String name, String version, String apiLevel, int imageID){
        this(name, version, apiLevel);
        this.imageID = imageID;
    }

    public String getName(){
        return this.name;
    }

    public String getVersion(){
        return this.version;
    }

    public String getApiLevel(){
        return this.apiLevel;
    }

    public int getImageID(){
        return this.imageID;
    }
}
