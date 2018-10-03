package com.studios.holtzapfel.menumaker.model;

import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/19/17.
 */

@SuppressWarnings("unchecked")
abstract class AbstractMenuItem<T, VH extends RecyclerView.ViewHolder> implements IMenuItem<T, VH>{

    private int mID = -1;

    @Override
    public int getID() {
        return mID;
    }

    @Override
    public T withID(int id) {
        this.mID = id;
        return (T) this;
    }

    private boolean isEnabled = true;

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public T withEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return (T) this;
    }

    private boolean isLastItem = false;

    @Override
    public boolean isLastItem() {
        return isLastItem;
    }

    @Override
    public T withLastItem(boolean isLastItem) {
        this.isLastItem = isLastItem;
        return (T) this;
    }

    int getMMIconRes(int iconRes) {switch (iconRes){
        case ICON_ARROW_RIGHT:
            return R.drawable.ic_arrow_right;
        case ICON_ARROW_LEFT:
            return R.drawable.ic_arrow_left;
        case ICON_ARROW_UP:
            return R.drawable.ic_arrow_up;
        case ICON_ARROW_DOWN:
            return R.drawable.ic_arrow_down;
        case ICON_OPEN_IN_NEW:
            return R.drawable.ic_action_open_in_new;
        case ICON_OPEN_IN_BROWSER:
            return R.drawable.ic_action_open_in_browser;
    }
        return -1;
    }

    private String hiddenValue;

    @Override
    public T withHiddenValue(String id) {
        this.hiddenValue = id;
        return (T) this;
    }

    @Override
    public String getHiddenValue() {
        return hiddenValue;
    }

    private boolean isClickable = true;

    @Override
    public boolean isClickable() {
        return this.isClickable;
    }

    @Override
    public T withClickable(boolean isClickable) {
        this.isClickable = isClickable;
        return (T) this;
    }
}
