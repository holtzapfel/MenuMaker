package com.studios.holtzapfel.menumaker.model;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MenuFragment;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IHeaderItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/19/17.
 */

public class HeaderMenuItem extends AbstractMenuItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder> implements IHeaderItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder>{

    protected String mTitle;

    @Override
    public int getMenuType() {
        return R.id.menu_maker_item_header;
    }

    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public void bindView(HeaderViewHolder holder, final MenuFragment.OnFragmentInteractionListener listener) {
        holder.title.setText(mTitle);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMenuItemClick(HeaderMenuItem.this);
            }
        });
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

    @Override
    public HeaderViewHolder getViewHolder(View v) {
        return new HeaderViewHolder(v);
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
