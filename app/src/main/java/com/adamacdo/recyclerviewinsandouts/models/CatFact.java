package com.adamacdo.recyclerviewinsandouts.models;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by adammacdonald on 6/25/17.
 */

public class CatFact {

    @DrawableRes
    private int mCatResId;
    @StringRes
    private int mTitleResId;
    @StringRes
    private int mDescriptionResId;

    public CatFact(int catResId, int titleResId, int descriptionResId) {
        mCatResId = catResId;
        mTitleResId = titleResId;
        mDescriptionResId = descriptionResId;
    }

    @DrawableRes
    public int getCatResId() {
        return mCatResId;
    }

    @StringRes
    public int getTitleResId() {
        return mTitleResId;
    }

    @StringRes
    public int getDescriptionResId() {
        return mDescriptionResId;
    }
}
