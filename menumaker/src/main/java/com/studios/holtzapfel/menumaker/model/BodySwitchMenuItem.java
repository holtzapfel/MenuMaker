package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.studios.holtzapfel.menumaker.MenuFragment;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IBodySwitchItem;

/**
 * Created by holtzapfel on 6/20/17.
 */

public class BodySwitchMenuItem extends BaseBodyMenuItem<BodySwitchMenuItem, BodySwitchMenuItem.BodySwitchViewHolder> implements IBodySwitchItem<BodySwitchMenuItem, BodySwitchMenuItem.BodySwitchViewHolder> {

    private boolean mBooleanValue = false;
    private boolean isSwitchEnabled = true;

    public BodySwitchMenuItem(int id, boolean value){
        this.mID = id;
        this.mBooleanValue = value;
    }

    public BodySwitchMenuItem(int id, String title, boolean value){
        this.mID = id;
        this.mTitle = title;
        this.mBooleanValue = value;
    }

    public BodySwitchMenuItem(int id, String title, String description, boolean value){
        this.mID = id;
        this.mTitle = title;
        this.mDescription = description;
        this.mBooleanValue = value;
    }

    @Override
    public BodySwitchMenuItem setBooleanValue(boolean value) {
        this.mBooleanValue = value;
        return this;
    }

    @Override
    public boolean getBooleanValue() {
        return mBooleanValue;
    }

    @Override
    public BodySwitchMenuItem setSwitchEnabled(boolean isSwitchEnabled) {
        this.isSwitchEnabled = isSwitchEnabled;
        return this;
    }

    @Override
    public boolean isSwitchEnabled() {
        return isSwitchEnabled;
    }

    @Override
    public int getMenuType() {
        return MENU_ITEM_TYPE_BODY_SWITCH;
    }

    @Override
    public void bindView(Context context, final BodySwitchViewHolder holder, final MenuFragment.OnFragmentInteractionListener listener) {
        // Configure card
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.booleanSwitch.setChecked(!holder.booleanSwitch.isChecked());
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

        // Configure switch
        holder.booleanSwitch.setChecked(mBooleanValue);
        holder.booleanSwitch.setEnabled(isSwitchEnabled);
        holder.booleanSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setBooleanValue(b);
                listener.onMenuItemClick(BodySwitchMenuItem.this);
            }
        });

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
    public void unbindView(BodySwitchViewHolder holder) {

    }

    public static class BodySwitchViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView icon;
        private TextView title;
        private TextView description;
        private Switch booleanSwitch;
        private TextView divider;

        public BodySwitchViewHolder(View v) {
            super(v);

            this.cardView = v.findViewById(R.id.mm_item_body_switch_cardview);
            this.icon = v.findViewById(R.id.mm_item_body_switch_icon);
            this.title = v.findViewById(R.id.mm_item_body_switch_title);
            this.description = v.findViewById(R.id.mm_item_body_switch_description);
            this.booleanSwitch = v.findViewById(R.id.mm_item_body_switch_switch);
            this.divider = v.findViewById(R.id.mm_item_body_switch_divider);
        }
    }
}
