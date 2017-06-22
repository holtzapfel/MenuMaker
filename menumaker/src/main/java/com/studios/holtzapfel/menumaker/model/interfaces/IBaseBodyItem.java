package com.studios.holtzapfel.menumaker.model.interfaces;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/20/17.
 */

public interface IBaseBodyItem<T, VH extends RecyclerView.ViewHolder> extends IMenuItem<T, VH>{

    T setTitle(String title);

    String getTitle();

    T setDescription(String description);

    String getDescription();

    T setIcon(Drawable icon);

    T setIcon(int iconRes);

    Drawable getIcon();

    int getIconRes();

    T setIconVisible(boolean isVisible);

    boolean isIconVisible();

    T setLastItem(boolean isLastItem);

    boolean isLastItem();

    T setTitleTextColor(int colorRes);

    int getTitleTextColorRes();

}
