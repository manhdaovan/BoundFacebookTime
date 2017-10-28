package com.manhdaovan.boundfacebooktime.models;

import com.manhdaovan.boundfacebooktime.utils.Utils;

public class SnsPage {
    private String name;
    private String url;
    private boolean useAppTime;
    private int boundTime; // minutes
    private boolean confirmBeforeQuit;

    public SnsPage(String _name, String _url, boolean _confirmBeforeQuit, int _boundTime){
        name = _name;
        url = _url;
        useAppTime = false;
        boundTime = _boundTime;
        confirmBeforeQuit = _confirmBeforeQuit;
    }

    public SnsPage(String _name, String _url, boolean _confirmBeforeQuit, boolean _useAppTime){
        name = _name;
        url = _url;
        useAppTime = _useAppTime;
        boundTime = 0;
        confirmBeforeQuit = _confirmBeforeQuit;
    }

    public String toJson(){
        String json = "{";
        json += Utils.attributeToJson("name", name) + ',';
        json += Utils.attributeToJson("url", url) + ',';
        json += Utils.attributeToJson("useAppTime", useAppTime) + ',';
        json += Utils.attributeToJson("boundTime", boundTime) + ',';
        json += Utils.attributeToJson("confirmBeforeQuit", confirmBeforeQuit);
        json += "}";
        return json;
    }
}
