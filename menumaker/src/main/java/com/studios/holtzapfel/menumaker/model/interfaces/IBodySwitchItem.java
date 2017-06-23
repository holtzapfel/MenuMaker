package com.studios.holtzapfel.menumaker.model.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/20/17.
 */

public interface IBodySwitchItem<T, VH extends RecyclerView.ViewHolder> extends IBaseBodyItem<T, VH> {

    T withBooleanValue(boolean value);

    boolean getBooleanValue();

    T withSwitchEnabled(boolean isSwitchEnabled);

    boolean isSwitchEnabled();

}
