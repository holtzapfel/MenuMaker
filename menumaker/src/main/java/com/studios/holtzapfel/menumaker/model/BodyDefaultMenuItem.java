package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MMFragment;
import com.studios.holtzapfel.menumaker.Master;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IBodyDefaultItem;

/**
 * Created by holtzapfel on 6/20/17.
 */

public class BodyDefaultMenuItem extends BaseBodyMenuItem<BodyDefaultMenuItem, BodyDefaultMenuItem.BodyDefaultViewHolder> implements IBodyDefaultItem<BodyDefaultMenuItem, BodyDefaultMenuItem.BodyDefaultViewHolder>{

    private String mValue;

    public BodyDefaultMenuItem(int id){
        this.mID = id;
    }

    @Override
    public BodyDefaultMenuItem withValue(String value) {
        this.mValue = value;
        return this;
    }

    @Override
    public String getValue() {
        return mValue;
    }

    @Override
    public int getMenuType() {
        return MENU_ITEM_TYPE_BODY_DEFAULT;
    }

    @Override
    public void bindView(Context context, BodyDefaultViewHolder holder, final MMFragment.OnFragmentInteractionListener listener) {
        // Configure card
        if (isEnabled) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMenuItemClick(BodyDefaultMenuItem.this);
                }
            });
        }

        // Configure title
        if (mTitle != null){
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(Master.fromHtml(mTitle));
        } else holder.title.setVisibility(View.GONE);
        if (mTitleTextColorRes != -1){
            holder.title.setTextColor(ResourcesCompat.getColor(context.getResources(), mTitleTextColorRes, context.getTheme()));
        }
        holder.title.setEnabled(isEnabled);

        // Configure description
        if (mDescription != null){
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(Master.fromHtml(mDescription));
        } else holder.description.setVisibility(View.GONE);
        holder.description.setEnabled(isEnabled);

        // Configure value
        if (mValue != null){
            holder.value.setVisibility(View.VISIBLE);
            holder.value.setText(Master.fromHtml(mValue));
        } else holder.value.setVisibility(View.GONE);
        holder.value.setEnabled(isEnabled);

        // Configure icon
        if (isIconVisible){
            holder.icon.setVisibility(View.VISIBLE);
            if (mIcon != null){
                holder.icon.setImageDrawable(mIcon);
            } else if (mIconRes != -1){
                holder.icon.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), mIconRes, context.getTheme()));
            } else holder.icon.setVisibility(View.GONE);
        } else holder.icon.setVisibility(View.GONE);
        holder.icon.setEnabled(isEnabled);

        // Configure divider
        if (isLastItem){
            holder.divider.setVisibility(View.GONE);
        } else holder.divider.setVisibility(View.VISIBLE);
    }

    @Override
    public void unbindView(BodyDefaultViewHolder holder) {

    }

    public static class BodyDefaultViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView icon;
        private TextView title;
        private TextView description;
        private TextView value;
        private TextView divider;

        public BodyDefaultViewHolder(View v) {
            super(v);

            this.cardView = v.findViewById(R.id.mm_item_body_default_cardview);
            this.icon = v.findViewById(R.id.mm_item_body_default_icon);
            this.title = v.findViewById(R.id.mm_item_body_default_title);
            this.description = v.findViewById(R.id.mm_item_body_default_description);
            this.value = v.findViewById(R.id.mm_item_body_default_value);
            this.divider = v.findViewById(R.id.mm_item_body_default_divider);
        }
    }
}
