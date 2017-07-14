package baway.com.queandans.ask.model.bean;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 贾秀坤 on 2017/7/14.
 */

public class AskRequestBean implements Serializable{



    public boolean success;
    public boolean notFind;
    public List<ListBean> list;

    public static AskRequestBean objectFromData(String str) {

        return new Gson().fromJson(str, AskRequestBean.class);
    }

    public static AskRequestBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AskRequestBean.class);
        } catch (JSONException e) {
            return JSON.parseObject(str, AskRequestBean.class);
        }

    }

    public static class ListBean implements Serializable{

        public int aid;
        public String sid;
        public String title;
        public String tag;
        public String description;
        public String imgUrl;
        public String beginTime;
        public String endTime;
        public int authorID;
        public String authorName;
        public String authorTitle;
        public String authorDesc;
        public String authorFace;
        public String askTime;
        public int orderID;
        public int publishStatus;
        public int askCount;
        public int interestCount;
        public int isFollow;
        public int readCount;

        public static ListBean objectFromData(String str) {

            return new Gson().fromJson(str, ListBean.class);
        }

        public static ListBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ListBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
