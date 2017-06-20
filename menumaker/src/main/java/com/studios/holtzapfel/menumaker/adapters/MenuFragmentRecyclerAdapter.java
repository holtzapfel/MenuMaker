package com.studios.holtzapfel.menumaker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.MenuFragment.OnFragmentInteractionListener;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IHeaderItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/20/17.
 */

public class MenuFragmentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<IMenuItem> mItems;
    private OnFragmentInteractionListener mListener;

    public MenuFragmentRecyclerAdapter(List<IMenuItem> menuItems, OnFragmentInteractionListener listener){
        this.mItems = menuItems;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.id.menu_maker_item_header) {
            return new HeaderMenuItem.HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_header, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getMenuType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IMenuItem menuItem = mItems.get(position);

        if (menuItem instanceof HeaderMenuItem){
            HeaderMenuItem headerMenuItem = (HeaderMenuItem) menuItem;
            headerMenuItem.bindView((HeaderMenuItem.HeaderViewHolder) holder, mListener);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
