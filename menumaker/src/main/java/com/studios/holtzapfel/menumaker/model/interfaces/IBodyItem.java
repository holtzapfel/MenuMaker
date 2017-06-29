package com.studios.holtzapfel.menumaker.model.interfaces;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/27/17.
 */

public interface IBodyItem<T, VH extends RecyclerView.ViewHolder> extends IMenuItem<T, VH> {
    T withTitle(String title);

    String getTitle();

    T withTitleTextColor(int colorRes);

    int getTitleTextColorRes();

    T withDescription(String description);

    String getDescription();

    T withValue(String value);

    String getValue();

    T withBooleanValue(boolean value);

    boolean getBooleanValue();

    T withContent(String content);

    String getContent();

    T withIconLeft(int iconRes);

    T withIconLeft(Drawable icon);

    int getIconLeftRes();

    Drawable getIconLeft();

    T withIconRight(int iconRes);

    T withIconRight(Drawable icon);

    int getIconRightRes();

    Drawable getIconRight();

    T withIconLeftVisible(boolean isVisible);

    boolean isIconLeftVisible();

    T withIconRightVisible(boolean isVisible);

    boolean isIconRightVisible();

    T withIconLeftColor(int colorRes);

    int getIconLeftColorRes();

    T withIconRightColor(int colorRes);

    int getIconRightColorRes();

    T withDividerEnabled(boolean isEnabled);

    boolean isDividerEnabled();

    T withDividerColor(int colorRes);

    int getDividerColorRes();
}
