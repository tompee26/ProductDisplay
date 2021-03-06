package com.tompee.utilities.photodisplay.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tompee.utilities.photodisplay.R;
import com.tompee.utilities.photodisplay.controller.listener.EndlessRecyclerViewScrollListener;
import com.tompee.utilities.photodisplay.controller.task.GetProductFromListTask;
import com.tompee.utilities.photodisplay.model.Product;
import com.tompee.utilities.photodisplay.view.adapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements
        GetProductFromListTask.GetProductFromListTaskListener {
    private static final String TAG = "ProductFragment";
    public static final String MEN = "men.json";
    public static final String WOMEN = "women.json";
    public static final String ALL = "all.json";
    private static final String VARIANT = "variant";

    private List<Product> mProductList;
    private ProductListAdapter mAdapter;
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

        mProductList = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_product_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(30);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        mAdapter = new ProductListAdapter(getContext(), mProductList);
        recyclerView.setAdapter(mAdapter);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getProductList(mProductList.size());
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        getProductList(0);
        return view;
    }

    private void getProductList(int start) {
        if (mFetchTask == null) {
            mFetchTask = new GetProductFromListTask(getContext(),
                    getArguments().getString(VARIANT), start, this);
            mFetchTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onNewProduct(Product product) {
        int currentSize = mAdapter.getItemCount();
        mProductList.add(product);
        mAdapter.notifyItemRangeInserted(currentSize, 1);
    }

    @Override
    public void onFinished() {
        Log.d(TAG, "Fetch task finished");
        mFetchTask = null;
    }

    @Override
    public void onError() {
        Log.e(TAG, "Fetch task error");
        mFetchTask = null;
    }
}
