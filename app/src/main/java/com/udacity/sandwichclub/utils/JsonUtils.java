package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject name = jsonObject.getJSONObject("name");

            Log.d("JsonUtils", jsonObject.toString());
            Log.d("JsonUtils", name.toString());

            String mainName = name.getString("mainName");
            List<String> alsoKnownAs = convertJsonArrayToList(name.getJSONArray("alsoKnownAs"));
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            List<String> ingredients = convertJsonArrayToList(jsonObject.getJSONArray("ingredients"));

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

            Log.i("JsonUtils", "Parsed Sandwich:");
            Log.i("JsonUtils", sandwich.toString());

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> convertJsonArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> convertedList = new ArrayList<>();

        if(jsonArray != null) {
            for(int i = 0; i < jsonArray.length(); i++) {
                convertedList.add(jsonArray.getString(i));
            }
        }

        return convertedList;
    }
}
