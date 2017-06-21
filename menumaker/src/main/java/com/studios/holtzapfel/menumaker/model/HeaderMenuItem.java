package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MenuFragment;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IHeaderItem;

/**
 * Created by holtzapfel on 6/19/17.
 */

public class HeaderMenuItem extends AbstractMenuItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder> implements IHeaderItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder>{

    private String mTitle;

    public HeaderMenuItem(int id){
        this.mID = id;
    }

    public HeaderMenuItem(int id, String title){
        this.mID = id;
        this.mTitle = title;
    }

    public HeaderMenuItem(String title){
        this.mTitle = title;
    }

    @Override
    public int getMenuType() {
        return MENU_ITEM_TYPE_HEADER;
    }

    @Override
    public void bindView(Context context, HeaderViewHolder holder, final MenuFragment.OnFragmentInteractionListener listener) {
        holder.title.setText(mTitle);

        if (mID != -1) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMenuItemClick(HeaderMenuItem.this);
                }
            });
        }
    }

    @Override
    public void unbindView(HeaderViewHolder holder) {

    }

    @Override
    public HeaderMenuItem setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private CardView cardView;
        private TextView title;

        public HeaderViewHolder(View v) {
            super(v);

            this.view = v;
            cardView = v.findViewById(R.id.mm_item_header_cardview);
            title = v.findViewById(R.id.mm_item_header_title);
        }
    }
}
