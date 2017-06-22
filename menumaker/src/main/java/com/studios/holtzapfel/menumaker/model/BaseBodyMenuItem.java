package com.studios.holtzapfel.menumaker.model;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

import com.studios.holtzapfel.menumaker.model.interfaces.IBaseBodyItem;

/**
 * Created by holtzapfel on 6/20/17.
 */

public abstract class BaseBodyMenuItem<T, VH extends RecyclerView.ViewHolder> extends AbstractMenuItem<T, VH> implements IBaseBodyItem<T, VH> {

    protected String mTitle;
    protected String mDescription;
    private Drawable mIcon;
    private int mIconRes = -1;
    private boolean isIconVisible = true;
    private boolean isLastItem = false;
    private int mTitleTextColorRes = -1;

    @Override
    public T setTitle(String title) {
        this.mTitle = title;
        return (T) this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public T setDescription(String description) {
        this.mDescription = description;
        return (T) this;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public T setIcon(Drawable icon) {
        this.mIcon = icon;
        return (T) this;
    }

    @Override
    public T setIcon(int iconRes) {
        this.mIconRes = iconRes;
        return (T) this;
    }

    @Override
    public Drawable getIcon() {
        return mIcon;
    }

    @Override
    public int getIconRes() {
        return mIconRes;
    }

    @Override
    public T setIconVisible(boolean isVisible) {
        this.isIconVisible = isVisible;
        return (T) this;
    }

    @Override
    public boolean isIconVisible() {
        return isIconVisible;
    }

    @Override
    public T setLastItem(boolean isLastItem) {
        this.isLastItem = isLastItem;
        return (T) this;
    }

    @Override
    public boolean isLastItem() {
        return isLastItem;
    }

    @Override
    public T setTitleTextColor(int colorRes) {
        this.mTitleTextColorRes = colorRes;
        return (T) this;
    }

    @Override
    public int getTitleTextColorRes() {
        return mTitleTextColorRes;
    }
}
