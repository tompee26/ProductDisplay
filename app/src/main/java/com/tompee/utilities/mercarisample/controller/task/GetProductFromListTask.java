package com.tompee.utilities.mercarisample.controller.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tompee.utilities.mercarisample.controller.utilities.AssetUtility;

import org.json.JSONObject;

public class GetProductFromListTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "GetProductFromListTask";
    private final Context mContext;
    private final String mFilename;

    public GetProductFromListTask(Context context, String filename) {
        mContext = context;
        mFilename = filename;
    }

    @Override
    protected Void doInBackground(Void... params) {
        JSONObject object = AssetUtility.getJsonFromAsset(mContext, mFilename);
        Log.d(TAG, object.toString());
        return null;
    }
}
