package com.studios.holtzapfel.menumaker.model.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.studios.holtzapfel.menumaker.MenuFragment;

/**
 * Created by holtzapfel on 6/19/17.
 */

public interface IMenuItem<T, VH extends RecyclerView.ViewHolder> {

    int MENU_ITEM_TYPE_HEADER = 100;
    int MENU_ITEM_TYPE_BODY_DEFAULT = 200;
    int MENU_ITEM_TYPE_BODY_SWITCH = 201;
    int MENU_ITEM_TYPE_FOOTER = 300;

    int getID();

    T setID(int id);

    int getMenuType();

    boolean isEnabled();

    T setEnabled(boolean isEnabled);

    void bindView(Context context, VH holder, MenuFragment.OnFragmentInteractionListener listener);

    void unbindView(VH holder);
}
