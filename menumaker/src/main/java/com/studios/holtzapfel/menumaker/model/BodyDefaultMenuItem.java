package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MenuFragment;
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

    public BodyDefaultMenuItem(int id, String title){
        this.mID = id;
        this.mTitle = title;
    }

    public BodyDefaultMenuItem(int id, String title, String description){
        this.mID = id;
        this.mTitle = title;
        this.mDescription = description;
    }

    @Override
    public BodyDefaultMenuItem setValue(String value) {
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
    public void bindView(Context context, BodyDefaultViewHolder holder, final MenuFragment.OnFragmentInteractionListener listener) {
        // Configure card
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMenuItemClick(BodyDefaultMenuItem.this);
            }
        });

        // Configure title
        if (getTitle() != null){
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(getTitle());
        } else holder.title.setVisibility(View.GONE);

        // Configure description
        if (getDescription() != null){
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(getDescription());
        } else holder.description.setVisibility(View.GONE);

        // Configure value
        if (getValue() != null){
            holder.value.setVisibility(View.VISIBLE);
            holder.value.setText(getValue());
        } else holder.value.setVisibility(View.GONE);

        // Configure icon
        if (isIconVisible()){
            holder.icon.setVisibility(View.VISIBLE);
            if (getIcon() != null){
                holder.icon.setImageDrawable(getIcon());
            } else if (getIconRes() != -1){
                holder.icon.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), getIconRes(), context.getTheme()));
            } else holder.icon.setVisibility(View.GONE);
        } else holder.icon.setVisibility(View.GONE);

        // Configure divider
        if (isLastItem()){
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
