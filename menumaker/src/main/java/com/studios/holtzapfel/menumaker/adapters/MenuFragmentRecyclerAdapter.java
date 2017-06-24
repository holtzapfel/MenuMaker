package com.studios.holtzapfel.menumaker.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.MMFragment.OnFragmentInteractionListener;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.BodyDefaultMenuItem;
import com.studios.holtzapfel.menumaker.model.BodySwitchMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/20/17.
 */

public class MenuFragmentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<IMenuItem> mItems;
    private OnFragmentInteractionListener mListener;

    public MenuFragmentRecyclerAdapter(Context context, List<IMenuItem> menuItems, OnFragmentInteractionListener listener){
        this.mContext = context;
        this.mItems = menuItems;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                return new HeaderMenuItem.HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_header, parent, false));
            case IMenuItem.MENU_ITEM_TYPE_BODY_DEFAULT:
                return new BodyDefaultMenuItem.BodyDefaultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_body_default, parent, false));
            case IMenuItem.MENU_ITEM_TYPE_BODY_SWITCH:
                return new BodySwitchMenuItem.BodySwitchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_body_switch, parent, false));
            case IMenuItem.MENU_ITEM_TYPE_FOOTER:
                return new FooterMenuItem.FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_footer, parent, false));
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

        switch (menuItem.getMenuType()){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                HeaderMenuItem headerMenuItem = (HeaderMenuItem) menuItem;
                headerMenuItem.bindView(mContext, (HeaderMenuItem.HeaderViewHolder) holder, mListener);
                break;
            case IMenuItem.MENU_ITEM_TYPE_BODY_DEFAULT:
                BodyDefaultMenuItem bodyDefaultMenuItem = (BodyDefaultMenuItem) menuItem;
                bodyDefaultMenuItem.bindView(mContext, (BodyDefaultMenuItem.BodyDefaultViewHolder) holder, mListener);
                break;
            case IMenuItem.MENU_ITEM_TYPE_BODY_SWITCH:
                BodySwitchMenuItem bodySwitchMenuItem = (BodySwitchMenuItem) menuItem;
                bodySwitchMenuItem.bindView(mContext, (BodySwitchMenuItem.BodySwitchViewHolder) holder, mListener);
                break;
            case IMenuItem.MENU_ITEM_TYPE_FOOTER:
                FooterMenuItem footerMenuItem = (FooterMenuItem) menuItem;
                footerMenuItem.bindView(mContext, (FooterMenuItem.FooterViewHolder) holder, mListener);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
