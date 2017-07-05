package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.Master;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IHeaderItem;

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
    public void bindView(Context context, HeaderViewHolder holder, final MMMenu.OnMenuItemClickListener listener) {
        // Configure title
        holder.title.setText(Master.fromHtml(mTitle));
        if (mTitleTextColorRes != -1 && mTitleTextColorRes != 0){
            holder.title.setTextColor(ResourcesCompat.getColor(context.getApplicationContext().getResources(), mTitleTextColorRes, context.getTheme()));
        }
    }

    @Override
    public void unbindView(HeaderViewHolder holder) {

    }

    @Override
    public HeaderMenuItem withTitle(String title) {
        this.mTitle = title;
        return this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public HeaderMenuItem withTitleTextColor(int colorRes) {
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
            cardView = (CardView) v.findViewById(R.id.mm_item_header_cardview);
            title = (TextView) v.findViewById(R.id.mm_item_header_title);
        }
    }
}
