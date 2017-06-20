package com.studios.holtzapfel.menumaker.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/19/17.
 */

public abstract class AbstractMenuItem<T, VH extends RecyclerView.ViewHolder> implements IMenuItem<T, VH>{

    protected int id = -1;
    protected int menuType = -1;
    protected boolean isEnabled;

    @Override
    public int getID() {
        return id;
    }

    @Override
    public T setID(int id) {
        this.id = id;
        return (T) this;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public T setEnabled(boolean enabled) {
        isEnabled = enabled;
        return (T) this;
    }

    @Override
    public VH getViewHolder(ViewGroup parent) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false));
    }

    public abstract VH getViewHolder(View v);
}
