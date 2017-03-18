package com.tompee.utilities.mercarisample.controller.utilities;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtility {

    public static JSONObject getJsonFromAsset(Context context, String filename) {
        JSONObject jsonObject;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonObject = new JSONObject(new String(buffer, "UTF-8"));
        } catch (IOException | JSONException e) {
            return null;
        }
        return jsonObject;
    }
}
