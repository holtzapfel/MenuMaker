package com.studios.holtzapfel.menumaker.model.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/20/17.
 */

public interface IBaseBodyItem<T, VH extends RecyclerView.ViewHolder> extends IBaseMenuItem<T, VH>{

    T withDescription(String description);

    String getDescription();

}
