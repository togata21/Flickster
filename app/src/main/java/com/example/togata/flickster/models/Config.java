package com.example.togata.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by togata on 6/28/18.
 */

public class Config {
    String imageBaseUrl;
    String posterSize;

    public Config(JSONObject object) throws JSONException {
        JSONObject images = object.getJSONObject("images");
        imageBaseUrl = images.getString("secure_base_url");
        JSONArray array = images.getJSONArray("poster_sizes"); //use value at array index 3 or size w342
        posterSize = array.optString(3, "w342");

    }

    public String getImageUrl(String size, String path){
        return String.format("%s%s%s", imageBaseUrl, size, path);

    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public String getPosterSize() {
        return posterSize;
    }
}
