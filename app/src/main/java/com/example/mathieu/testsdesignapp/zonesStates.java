package com.example.mathieu.testsdesignapp;


public class zonesStates {

    private int zone0State = 0;
    private int zone1State = 0;
    private int zone2State = 0;
    private int zone3State = 0;

    public void setZone0State(int selectedState){
        zone0State = selectedState;
    }
    public void setZone1State(int selectedState){
        zone1State = selectedState;
    }
    public void setZone2State(int selectedState){
        zone2State = selectedState;
    }
    public void setZone3State(int selectedState){
        zone3State = selectedState;
    }

    public int getZone0State(){
        return zone0State;
    }
    public int getZone1State(){
        return zone1State;
    }
    public int getZone2State(){
        return zone2State;
    }
    public int getZone3State(){
        return zone3State;
    }

}
