package com.vedas.weightloss.ServerObjects;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.vedas.weightloss.Models.NewsFeedObject;

import java.util.ArrayList;

/**
 * Created by Rise on 14/06/2018.
 */

public class NewsServerObject {
    @SerializedName("data")
    public ArrayList<NewsFeedObject> data;


    @SerializedName("result")
    public String response;


   /* private String result;

    public String getResponse() { return this.result; }

    public void setResponse(String result) { this.result = result; }

    public JSONObject data;
*/

}


