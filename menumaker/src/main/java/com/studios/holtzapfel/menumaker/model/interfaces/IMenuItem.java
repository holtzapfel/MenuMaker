package com.studios.holtzapfel.menumaker.model.interfaces;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.MenuFragment;

import java.util.List;

/**
 * Created by holtzapfel on 6/19/17.
 */

public interface IMenuItem<T, VH extends RecyclerView.ViewHolder> {

    int getID();

    T setID(int id);

    int getMenuType();

    int getLayoutRes();

    boolean isEnabled();

    T setEnabled(boolean isEnabled);

    VH getViewHolder(ViewGroup parent);

    void bindView(VH holder, MenuFragment.OnFragmentInteractionListener listener);

    void unbindView(VH holder);
}
