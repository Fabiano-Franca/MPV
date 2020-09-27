package com.nptec.mvp.com.nptec.mvp.dao;

import com.nptec.mvp.com.nptec.mvp.model.Sensores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

    public Sensores extraiSensor(String string){

        Sensores sensor = null;
        JSONObject myObject =  null;
        JSONArray jArray = null;

        try {
            myObject = new JSONObject(string);
            jArray = myObject.getJSONArray("data");
            myObject = jArray.getJSONObject(0);
            sensor = new Sensores();
            sensor.setTemp(myObject.getString("TEMP"));
            sensor.setPh(myObject.getString("PH"));
            sensor.setRpm(myObject.getString("RPM"));
            sensor.setC1(myObject.getString("C1"));
            sensor.setC2(myObject.getString("C2"));
            sensor.setT1(myObject.getString("T1"));
            sensor.setT2(myObject.getString("T2"));
            sensor.setN(myObject.getString("N"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sensor;
    }

    public Sensores extraiSensorSemArray(String string){

        Sensores sensor = null;
        JSONObject myObject =  null;
        JSONArray jArray = null;

        try {
            myObject = new JSONObject(string);
            sensor = new Sensores();
            sensor.setTemp(myObject.getString("TEMP"));
            sensor.setPh(myObject.getString("PH"));
            sensor.setRpm(myObject.getString("RPM"));
            sensor.setC1(myObject.getString("C1"));
            sensor.setC2(myObject.getString("C2"));
            sensor.setT1(myObject.getString("T1"));
            sensor.setT2(myObject.getString("T2"));
            sensor.setN(myObject.getString("N"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sensor;
    }

}
