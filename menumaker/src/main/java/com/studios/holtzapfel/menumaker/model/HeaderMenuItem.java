package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.Master;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IHeaderItem;

import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by holtzapfel on 6/19/17.
 */

public class HeaderMenuItem extends AbstractMenuItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder> implements IHeaderItem<HeaderMenuItem, HeaderMenuItem.HeaderViewHolder>{


    public HeaderMenuItem(int id){
        withID(id);
    }

    @Override
    public int getMenuType() {
        return MENU_ITEM_TYPE_HEADER;
    }

    private String mTitle;

    @Override
    public HeaderMenuItem withTitle(String title) {
        this.mTitle = title;
        return this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    private int mTitleTextColorRes = -1;

    @Override
    public HeaderMenuItem withTitleTextColor(int colorRes) {
        this.mTitleTextColorRes = colorRes;
        return this;
    }

    @Override
    public int getTitleTextColorRes() {
        return mTitleTextColorRes;
    }

    private float mTitleTextSize = -1;
    private int mTitleTextSizeUnit = -1;

    @Override
    public HeaderMenuItem withTitleTextSize(float size){
        this.mTitleTextSize = -1;
        return this;
    }

    @Override
    public HeaderMenuItem withTitleTextSize(int unit, float size){
        this.mTitleTextSizeUnit = unit;
        this.mTitleTextSize = size;
        return this;
    }

    @Override
    public float getTitleTextSize() {
        return mTitleTextSize;
    }

    @Override
    public int getTitleTextSizeUnit() {
        return mTitleTextSizeUnit;
    }

    @Override
    public void bindView(Context context, HeaderViewHolder holder, final MMMenu.OnMenuItemClickListener listener) {
        // Configure title
        if (getTitle() != null) {
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(Master.fromHtml(getTitle()));

            // Set text color
            if (getTitleTextColorRes() != -1) {
                holder.title.setTextColor(ResourcesCompat.getColor(context.getApplicationContext().getResources(), getTitleTextColorRes(), context.getTheme()));
            }

            // Set text size
            if (mTitleTextSize != -1) {
                if (mTitleTextSizeUnit != -1) {
                    holder.title.setTextSize(mTitleTextSizeUnit, mTitleTextSize);
                } else holder.title.setTextSize(mTitleTextSize);
            }
        } else holder.title.setVisibility(View.GONE);
    }

    @Override
    public void unbindView(HeaderViewHolder holder) {

    }

    @SuppressWarnings("unused")
    public static class HeaderViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView title;

        public HeaderViewHolder(View v) {
            super(v);

            // Associate views
            cardView = v.findViewById(R.id.mm_item_header_cardview);
            title = v.findViewById(R.id.mm_item_header_title);
        }
    }
}
