package com.studios.holtzapfel.menumaker.model.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/20/17.
 */

public interface IBodyDefaultItem<T, VH extends RecyclerView.ViewHolder> extends IBaseBodyItem<T,VH> {

    T setValue(String value);

    String getValue();

}
