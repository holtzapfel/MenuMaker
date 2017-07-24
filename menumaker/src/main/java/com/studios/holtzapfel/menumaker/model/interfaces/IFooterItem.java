package com.studios.holtzapfel.menumaker.model.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by holtzapfel on 6/20/17.
 */

public interface IFooterItem<T, VH extends RecyclerView.ViewHolder> extends IMenuItem<T, VH> {

    T withButtonLeft(CharSequence text, View.OnClickListener onClickListener);

    T withButtonCenter(CharSequence text, View.OnClickListener onClickListener);

    T withButtonRight(CharSequence text, View.OnClickListener onClickListener);
}
