package com.tompee.utilities.photodisplay.controller.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tompee.utilities.photodisplay.controller.utilities.AssetUtility;
import com.tompee.utilities.photodisplay.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetProductFromListTask extends AsyncTask<Void, Product, Boolean> {
    private static final String TAG = "GetProductFromListTask";
    private static final String TAG_DATA = "data";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_STATUS = "status";
    private static final String TAG_LIKE = "num_likes";
    private static final String TAG_COMMENTS = "num_comments";
    private static final String TAG_PRICE = "price";
    private static final String TAG_PHOTO = "photo";
    private static final String ON_SALE = "on_sale";
    private final Context mContext;
    private final String mFilename;
    private final GetProductFromListTaskListener mListener;

    public GetProductFromListTask(Context context, String filename,
                                  GetProductFromListTaskListener listener) {
        mContext = context;
        mFilename = filename;
        mListener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        JSONObject object = AssetUtility.getJsonFromAsset(mContext, mFilename);

        /** Parse one product at a time */
        try {
            if (object != null) {
                JSONArray array = object.getJSONArray(TAG_DATA);
                for (int count = 0; count < array.length(); count++) {
                    JSONObject productObject = array.getJSONObject(count);
                    String id = productObject.getString(TAG_ID);
                    String name = productObject.getString(TAG_NAME);
                    boolean status = productObject.getString(TAG_STATUS).equals(ON_SALE);
                    int like = productObject.getInt(TAG_LIKE);
                    int comments = productObject.getInt(TAG_COMMENTS);
                    int price = productObject.getInt(TAG_PRICE);
                    String photo = productObject.getString(TAG_PHOTO);
                    publishProgress(new Product(id, name, status, like, comments, price, photo));
                }
            }
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Product... products) {
        mListener.onNewProduct(products[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean) {
            mListener.onFinished();
        } else {
            mListener.onError();
        }
    }

    public interface GetProductFromListTaskListener {
        void onNewProduct(Product product);

        void onFinished();

        void onError();
    }
}
