package com.studios.holtzapfel.menumaker.model;

import android.support.v7.widget.RecyclerView;

import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

/**
 * Created by holtzapfel on 6/19/17.
 */

@SuppressWarnings("unchecked")
abstract class AbstractMenuItem<T, VH extends RecyclerView.ViewHolder> implements IMenuItem<T, VH>{

    int mID = -1;
    boolean isEnabled = true;

    @Override
    public int getID() {
        return mID;
    }

    @Override
    public T withID(int id) {
        this.mID = id;
        return (T) this;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public T withEnabled(boolean enabled) {
        isEnabled = enabled;
        return (T) this;
    }

    /*ImageView holderSetupIcon(ImageView icon){

    }*/
}
