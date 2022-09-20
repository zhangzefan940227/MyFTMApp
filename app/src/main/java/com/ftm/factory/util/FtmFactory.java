package com.ftm.factory.util;

import android.content.Context;

import com.ftm.factory.Model.ItemModel;

public class FtmFactory {

    private Context mContext = null;
    private FtmFactory mFtmFactory = null;
    private static ItemModel mItemModel = null;

    public FtmFactory getInstance() {
        synchronized (FtmFactory.class) {
            if (mFtmFactory == null) {
                mFtmFactory = new FtmFactory();
            }
        }
        return mFtmFactory;
    }

    public void init(Context context) {
        mContext = context;
    }

    public static ItemModel getItemTestAdapter() {
        synchronized (ItemModel.class) {
            if (mItemModel == null) {
                mItemModel = new ItemModel();
            }
        }
        return mItemModel;
    }
}
