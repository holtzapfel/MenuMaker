package com.studios.holtzapfel.menumaker.model.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.studios.holtzapfel.menumaker.MMMenu;

/**
 * Created by holtzapfel on 6/19/17.
 */

public interface IMenuItem<T, VH extends RecyclerView.ViewHolder> {

    int ICON_ARROW_RIGHT = 100;
    int ICON_ARROW_LEFT = 101;
    int ICON_ARROW_UP = 102;
    int ICON_ARROW_DOWN = 103;
    int ICON_OPEN_IN_NEW = 104;
    int ICON_OPEN_IN_BROWSER = 105;

    int MENU_ITEM_TYPE_HEADER = 200;
    int MENU_ITEM_TYPE_BODY = 300;
    int MENU_ITEM_TYPE_FOOTER = 400;

    int getID();

    T withID(int id);

    int getMenuType();

    boolean isEnabled();

    T withEnabled(boolean isEnabled);

    void bindView(Context context, VH holder, MMMenu.OnMenuItemClickListener listener);

    void unbindView(VH holder);

    T withLastItem(boolean isLastItem);

    boolean isLastItem();

    T withHiddenValue(String id);

    String getHiddenValue();
}
