package com.studios.holtzapfel.menumaker.model;

import android.support.v7.widget.RecyclerView;

import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

/**
 * Created by holtzapfel on 6/19/17.
 */

public abstract class AbstractMenuItem<T, VH extends RecyclerView.ViewHolder> implements IMenuItem<T, VH>{

    protected int mID = -1;
    protected boolean mIsEnabled;

    @Override
    public int getID() {
        return mID;
    }

    @Override
    public T setID(int id) {
        this.mID = id;
        return (T) this;
    }

    @Override
    public boolean isEnabled() {
        return mIsEnabled;
    }

    @Override
    public T setEnabled(boolean enabled) {
        mIsEnabled = enabled;
        return (T) this;
    }
}
