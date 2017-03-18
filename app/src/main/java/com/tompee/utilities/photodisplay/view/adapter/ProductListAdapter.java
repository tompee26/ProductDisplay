package com.tompee.utilities.photodisplay.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tompee.utilities.photodisplay.R;
import com.tompee.utilities.photodisplay.model.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private final List<Product> mProducts;
    private final Context mContext;

    public ProductListAdapter(Context context, List<Product> products) {
        mContext = context;
        mProducts = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.productview, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        if (product.isStatus()) {
            holder.getBanner().setVisibility(View.INVISIBLE);
        } else {
            holder.getBanner().setVisibility(View.VISIBLE);
        }
        holder.getName().setText(product.getName());
        holder.getLike().setText(String.valueOf(product.getLikeCount()));
        holder.getComment().setText(String.valueOf(product.getCommentCount()));
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMaximumFractionDigits(0);
        format.setMinimumFractionDigits(0);
        holder.getPrice().setText(format.format(product.getPrice()));
        /** Picasso protocol redirect issue */
        Picasso.with(mContext).load(product.getPhotoUrl().replace("http://", "https://")).
                into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        private final ImageView mBanner;
        private final TextView mName;
        private final TextView mPrice;
        private final TextView mComment;
        private final TextView mLike;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.product_image);
            mBanner = (ImageView) itemView.findViewById(R.id.sold_out_banner);
            mName = (TextView) itemView.findViewById(R.id.name);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            mComment = (TextView) itemView.findViewById(R.id.comment);
            mLike = (TextView) itemView.findViewById(R.id.like);
        }

        ImageView getImageView() {
            return mImageView;
        }

        ImageView getBanner() {
            return mBanner;
        }

        TextView getName() {
            return mName;
        }

        TextView getComment() {
            return mComment;
        }

        TextView getLike() {
            return mLike;
        }

        TextView getPrice() {
            return mPrice;
        }
    }
}
