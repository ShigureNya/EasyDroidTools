package me.jimhao.eorzeautil.json;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.json
 * 文档描述：Json构成
 */
public class JsonBuilder {
    private Gson gson ;

    public static JsonBuilder jsonParse = null;
    public static JsonBuilder getInstance(){
        if(jsonParse == null){
            jsonParse = new JsonBuilder();
        }
        return jsonParse;
    }

    public String buildJson(Object obj){
        if(gson == null){
            gson = new Gson();
        }
        return gson.toJson(obj);
    }

    public <T> String buildJson(ArrayList<T> array){
        if(gson == null){
            gson = new Gson();
        }
        return gson.toJson(array);
    }
}
