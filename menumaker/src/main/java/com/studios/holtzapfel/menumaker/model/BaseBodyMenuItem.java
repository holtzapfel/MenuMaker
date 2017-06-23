package com.studios.holtzapfel.menumaker.model;

import android.support.v7.widget.RecyclerView;

import com.studios.holtzapfel.menumaker.model.interfaces.IBaseBodyItem;

/**
 * Created by holtzapfel on 6/20/17.
 */

@SuppressWarnings("unchecked")
public abstract class BaseBodyMenuItem<T, VH extends RecyclerView.ViewHolder> extends BaseMenuItem<T, VH> implements IBaseBodyItem<T, VH> {

    String mDescription;

    @Override
    public T withDescription(String description) {
        this.mDescription = description;
        return (T) this;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }
}
