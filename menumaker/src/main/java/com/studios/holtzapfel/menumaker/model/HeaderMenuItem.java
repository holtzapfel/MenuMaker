package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MenuFragment;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IHeaderItem;

import static android.content.ContentValues.TAG;

/**
 * Created by holtzapfel on 6/19/17.
 */

public class HeaderMenuItem extends AbstractMenuItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder> implements IHeaderItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder>{

    private String mTitle;
    private int mTitleTextColorRes = -1;

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
        // Configure title
        holder.title.setText(mTitle);
        if (mTitleTextColorRes != -1){
            holder.title.setTextColor(ResourcesCompat.getColor(context.getApplicationContext().getResources(), mTitleTextColorRes, context.getTheme()));
            Log.d(TAG, "bindView: 1");
        }

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

    @Override
    public HeaderMenuItem setTitleTextColor(int colorRes) {
        this.mTitleTextColorRes = colorRes;
        return this;
    }

    @Override
    public int getTitleTextColorRes() {
        return mTitleTextColorRes;
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
