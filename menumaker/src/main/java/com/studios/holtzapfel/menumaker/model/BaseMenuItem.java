package com.studios.holtzapfel.menumaker.model;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

import com.studios.holtzapfel.menumaker.model.interfaces.IBaseMenuItem;

/**
 * Created by holtzapfel on 6/23/17.
 */

@SuppressWarnings("unchecked")
abstract class BaseMenuItem<T, VH extends RecyclerView.ViewHolder> extends AbstractMenuItem<T, VH> implements IBaseMenuItem<T, VH> {

    String mTitle;
    Drawable mIcon;
    int mIconRes = -1;
    boolean isIconVisible = true;
    boolean isLastItem = false;
    int mTitleTextColorRes = -1;

    @Override
    public T withTitle(String title) {
        this.mTitle = title;
        return (T) this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public T withIcon(Drawable icon) {
        this.mIcon = icon;
        return (T) this;
    }

    @Override
    public T withIcon(int iconRes) {
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
    public T withIconVisible(boolean isVisible) {
        this.isIconVisible = isVisible;
        return (T) this;
    }

    @Override
    public boolean isIconVisible() {
        return isIconVisible;
    }

    @Override
    public T withLastItem(boolean isLastItem) {
        this.isLastItem = isLastItem;
        return (T) this;
    }

    @Override
    public boolean isLastItem() {
        return isLastItem;
    }

    @Override
    public T withTitleTextColor(int colorRes) {
        this.mTitleTextColorRes = colorRes;
        return (T) this;
    }

    @Override
    public int getTitleTextColorRes() {
        return mTitleTextColorRes;
    }
}
