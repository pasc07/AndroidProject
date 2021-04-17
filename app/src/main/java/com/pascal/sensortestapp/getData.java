package com.pascal.sensortestapp;

import com.pascal.sensortestapp.ui.JsonDeserialiserForTest;
import com.pascal.sensortestapp.ui.home.SensorData;

import java.util.ArrayList;
import java.util.List;

public class getData {
    private double[] resistance =new double[100];
    private int date,i=0;
    private JsonDeserialiserForTest data;
    private List<SensorData> list= new ArrayList<SensorData>();

    public  getData() {
        data= new JsonDeserialiserForTest();
        list =data.dataString();
        for (int j = 3; j < (int) list.size() ; j++) {
            resistance[j]=Double.parseDouble( list.get(j).getFalse3());
        }
        i=0;

    }

    public double[] getResistance() {

        return resistance;
    }
}
