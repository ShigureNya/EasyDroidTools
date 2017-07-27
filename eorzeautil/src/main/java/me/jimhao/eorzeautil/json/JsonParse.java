package me.jimhao.eorzeautil.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： guhaoran
 * 创建于： 2017/7/26
 * 包名： me.jimhao.eorzeautil.json
 * 文档描述：Json解析
 */
public class JsonParse {
    private Gson gson ;

    public static JsonParse jsonParse = null;
    public static JsonParse getInstance(){
        if(jsonParse == null){
            jsonParse = new JsonParse();
        }
        return jsonParse;
    }

    /**
     * @param json Json字符串
     * @param cls 实体类对象
     * @param <T> 实体类
     * @return 实体
     * Json转实体类对象
     */
    public <T>T jsonToBean(String json , Class<T> cls){
        T t = null ;
        if(json != null){
            t = gson.fromJson(json , cls);
        }
        return t ;
    }

    /**
     * @param json Json字符串
     * @param cls 实体类对象
     * @param <T> 实体类
     * @return 实体集合
     * Json赚实体类集合
     */
    public <T> List<T> jsonToList(String json , Class<T> cls){
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }
}
