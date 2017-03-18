package com.tompee.utilities.photodisplay.model;

public class Product {
    private final String mId;
    private final String mName;
    private final boolean mStatus;
    private final int mLikeCount;
    private final int mCommentCount;
    private final int mPrice;
    private final String mPhotoUrl;

    public Product(String id, String name, boolean status, int likecount, int commentCount,
                   int price, String url) {
        mId = id;
        mName = name;
        mStatus = status;
        mLikeCount = likecount;
        mCommentCount = commentCount;
        mPrice = price;
        mPhotoUrl = url;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isStatus() {
        return mStatus;
    }

    public int getLikeCount() {
        return mLikeCount;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}
