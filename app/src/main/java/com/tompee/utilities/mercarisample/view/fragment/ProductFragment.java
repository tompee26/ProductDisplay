package com.tompee.utilities.mercarisample.view.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tompee.utilities.mercarisample.R;
import com.tompee.utilities.mercarisample.controller.task.GetProductFromListTask;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    public static final String MEN = "men.json";
    public static final String WOMEN = "women.json";
    public static final String ALL = "all.json";
    private static final String VARIANT = "variant";

    private GetProductFromListTask mFetchTask;

    public static ProductFragment newInstance(String type) {
        ProductFragment fragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString(VARIANT, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        /** Create task for fetching */
        mFetchTask = new GetProductFromListTask(getContext(), )
        return view;
    }
}
