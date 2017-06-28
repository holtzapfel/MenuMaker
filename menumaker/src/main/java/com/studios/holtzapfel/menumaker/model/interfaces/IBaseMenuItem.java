package com.studios.holtzapfel.menumaker.model.interfaces;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/23/17.
 */

public interface IBaseMenuItem<T, VH extends RecyclerView.ViewHolder> extends IMenuItem<T, VH>{

    T withTitle(String title);

    String getTitle();

    T withIcon(Drawable icon);

    T withIcon(int iconRes);

    Drawable getIcon();

    int getIconRes();

    T withIconVisible(boolean isVisible);

    boolean isIconVisible();

    T withIconColor(int colorRes);

    int getIconColorRes();

    T withTitleTextColor(int colorRes);

    int getTitleTextColorRes();

}
