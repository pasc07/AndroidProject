package com.pascal.sensortestapp.ui;

import android.renderscript.Type;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.pascal.sensortestapp.JsonFile;
import com.pascal.sensortestapp.ui.home.SensorData;

import java.util.ArrayList;

import java.util.List;

public class JsonDeserialiserForTest {

    /* Cette classe deserialiser les donnees Json en format lisible . Il prend un objet de type JSON et
    * return un tableau des object json de notre capteur

     */
    private List<SensorData> listOfValue ;
    private SensorData valueTest;
    public JsonDeserialiserForTest() {
    }

    public List<SensorData> dataString(){
        Gson gson =new Gson();
        JsonFile jsonFile =new JsonFile();
        String file=jsonFile.getFile();
        //List<SensorData> list = new ArrayList<SensorData>();
        // la structure du fichier est un tableau d'objet
        //dans un premier temsp on parser l'elemt en Array de string
        //Type collectionType = (Type) new TypeToken<ArrayList<SensorData>>(){}.getType();
        //list = gson.fromJson(file, collectionType);

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(file).getAsJsonArray();
        List<SensorData> list =new ArrayList<SensorData>();
        int taille =array.size();
        for (int i = 0; i < taille; i++) {
             list.add( gson.fromJson(array.get(i), SensorData.class));
        }
        return list;
    }

}
