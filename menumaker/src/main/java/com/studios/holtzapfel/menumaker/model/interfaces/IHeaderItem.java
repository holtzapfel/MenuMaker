package com.studios.holtzapfel.menumaker.model.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/19/17.
 */

public interface IHeaderItem<T, VH extends RecyclerView.ViewHolder> extends IMenuItem<T, VH>{

    T withTitle(String title);

    String getTitle();

    T withTitleTextColor(int colorRes);

    int getTitleTextColorRes();

    T withTitleTextSize(float size);

    T withTitleTextSize(int unit, float size);

    float getTitleTextSize();

    int getTitleTextSizeUnit();
}
