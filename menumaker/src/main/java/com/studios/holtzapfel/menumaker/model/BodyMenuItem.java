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

import com.studios.holtzapfel.menumaker.MMFragment;
import com.studios.holtzapfel.menumaker.Master;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IBodyItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

/**
 * Created by holtzapfel on 6/27/17.
 */

public class BodyMenuItem extends AbstractMenuItem<BodyMenuItem, BodyMenuItem.BodyMenuItemViewHolder> implements IBodyItem<BodyMenuItem, BodyMenuItem.BodyMenuItemViewHolder>{

    private String mTitle;
    private int mTitleColorRes = -1;

    private String mDescription;
    private String mShortValue;
    private boolean mSwitchValue = false;
    private boolean isSwitchUsed = false;
    private String mLongValue;

    private int mIconLeftRes = -1;
    private boolean isIconLeftVisible = true;
    private int mIconLeftColorRes = -1;

    private int mIconRightRes = -1;
    private boolean isIconRightVisible = true;
    private int mIconRightColorRes = -1;

    private boolean isDividerEnabled = true;
    private int mDividerColorRes = -1;

    public BodyMenuItem(int id){
        this.mID = id;
    }

    @Override
    public BodyMenuItem withTitle(String title) {
        this.mTitle = title;
        return this;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public BodyMenuItem withTitleTextColor(int colorRes) {
        this.mTitleColorRes = colorRes;
        return this;
    }

    @Override
    public int getTitleTextColorRes() {
        return mTitleColorRes;
    }

    @Override
    public BodyMenuItem withDescription(String description) {
        this.mDescription = description;
        return this;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public BodyMenuItem withShortValue(String value) {
        this.mShortValue = value;
        return this;
    }

    @Override
    public String getShortValue() {
        return mShortValue;
    }

    @Override
    public BodyMenuItem withSwitchValue(boolean value) {
        this.mSwitchValue = value;
        this.isSwitchUsed = true;
        return this;
    }

    @Override
    public boolean getSwitchValue() {
        return mSwitchValue;
    }

    @Override
    public BodyMenuItem withLongValue(String value) {
        this.mLongValue = value;
        return this;
    }

    @Override
    public String getLongValue() {
        return mLongValue;
    }

    @Override
    public BodyMenuItem withIconLeft(int iconRes) {
        this.mIconLeftRes = iconRes;
        return this;
    }

    @Override
    public int getIconLeftRes() {
        return mIconLeftRes;
    }

    @Override
    public BodyMenuItem withIconRight(int iconRes) {
        this.mIconRightRes = iconRes;
        return this;
    }

    @Override
    public int getIconRightRes() {
        return mIconRightRes;
    }

    @Override
    public BodyMenuItem withIconLeftVisible(boolean isVisible) {
        this.isIconLeftVisible = isVisible;
        return this;
    }

    @Override
    public boolean isIconLeftVisible() {
        return isIconLeftVisible;
    }

    @Override
    public BodyMenuItem withIconRightVisible(boolean isVisible) {
        this.isIconRightVisible = isVisible;
        return this;
    }

    @Override
    public boolean isIconRightVisible() {
        return isIconRightVisible;
    }

    @Override
    public BodyMenuItem withIconLeftColor(int colorRes) {
        this.mIconLeftColorRes = colorRes;
        return this;
    }

    @Override
    public int getIconLeftColorRes() {
        return mIconLeftColorRes;
    }

    @Override
    public BodyMenuItem withIconRightColor(int colorRes) {
        this.mIconRightColorRes = colorRes;
        return this;
    }

    @Override
    public int getIconRightColorRes() {
        return mIconRightColorRes;
    }

    @Override
    public BodyMenuItem withDividerEnabled(boolean isEnabled) {
        this.isDividerEnabled = isEnabled;
        return this;
    }

    @Override
    public boolean isDividerEnabled() {
        return isDividerEnabled;
    }

    @Override
    public BodyMenuItem withDividerColor(int colorRes) {
        this.mDividerColorRes = colorRes;
        return this;
    }

    @Override
    public int getDividerColorRes() {
        return mDividerColorRes;
    }

    @Override
    public int getMenuType() {
        return IMenuItem.MENU_ITEM_TYPE_BODY;
    }

    @Override
    public void bindView(Context context, final BodyMenuItemViewHolder holder, final MMFragment.OnFragmentInteractionListener listener) {
        // Configure card
        if(isEnabled()){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isSwitchUsed){
                        holder.switchValue.setChecked(!getSwitchValue());
                    } else listener.onMenuItemClick(BodyMenuItem.this);
                }
            });
        }

        // Configure title
        if (getTitle() != null){
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(Master.fromHtml(getTitle()));

            // Set text color
            if (getTitleTextColorRes() != -1){
                holder.title.setTextColor(ResourcesCompat.getColor(context.getResources(), getTitleTextColorRes(), context.getTheme()));
            }
        } else holder.title.setVisibility(View.GONE);

        // Configure description
        if (getDescription() != null){
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(Master.fromHtml(getDescription()));
        } else holder.description.setVisibility(View.GONE);

        // Configure switch
        if (isSwitchUsed){
            holder.switchValue.setVisibility(View.VISIBLE);
            holder.switchValue.setChecked(!getSwitchValue());
            holder.switchValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    withSwitchValue(b);
                    listener.onMenuItemClick(BodyMenuItem.this);
                }
            });
        } else holder.switchValue.setVisibility(View.GONE);

        // Configure short value
        if (getShortValue() != null){
            holder.shortValue.setVisibility(View.VISIBLE);
            holder.shortValue.setText(Master.fromHtml(getShortValue()));
        } else holder.shortValue.setVisibility(View.GONE);

        // Configure long value
        if (getLongValue() != null){
            holder.longValue.setVisibility(View.VISIBLE);
            holder.longValue.setText(Master.fromHtml(getLongValue()));
        } else holder.longValue.setVisibility(View.GONE);

        // Configure left icon
        if (getIconLeftRes() != -1){
            if (isIconLeftVisible()) {
                holder.iconLeft.setVisibility(View.VISIBLE);
            } else holder.iconLeft.setVisibility(View.GONE);

            holder.iconLeft.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), getIconLeftRes(), context.getTheme()));

            if (getIconLeftColorRes() != -1){
                holder.iconLeft.setColorFilter(ResourcesCompat.getColor(context.getResources(), getIconLeftColorRes(), context.getTheme()));
            }
        } else holder.iconLeft.setVisibility(View.GONE);

        // Configure right icon
        if (getIconRightRes() != -1){
            if (isIconRightVisible()) {
                holder.iconRight.setVisibility(View.VISIBLE);
            } else holder.iconRight.setVisibility(View.GONE);

            holder.iconRight.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), getIconRightRes(), context.getTheme()));

            if (getIconRightColorRes() != -1){
                holder.iconRight.setColorFilter(ResourcesCompat.getColor(context.getResources(), getIconRightColorRes(), context.getTheme()));
            }
        } else holder.iconRight.setVisibility(View.GONE);

        // Configure divider
        if (isDividerEnabled()){
            holder.divider.setVisibility(View.VISIBLE);
            if (getDividerColorRes() != -1){
                holder.divider.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), getDividerColorRes(), context.getTheme()));
            }
        } else holder.divider.setVisibility(View.GONE);

        // Configure enabled
        holder.cardView.setEnabled(isEnabled());
        holder.title.setEnabled(isEnabled());
        holder.description.setEnabled(isEnabled());
        holder.switchValue.setEnabled(isEnabled());
        holder.shortValue.setEnabled(isEnabled());
        holder.longValue.setEnabled(isEnabled());
        holder.iconLeft.setEnabled(isEnabled());
        holder.iconRight.setEnabled(isEnabled());
    }

    @Override
    public void unbindView(BodyMenuItemViewHolder holder) {

    }

    public static class BodyMenuItemViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private ImageView iconLeft;
        private TextView title;
        private TextView description;
        private TextView shortValue;
        private Switch switchValue;
        private ImageView iconRight;
        private TextView longValue;
        private TextView divider;

        public BodyMenuItemViewHolder(View v) {
            super(v);
            this.cardView = v.findViewById(R.id.mm_item_body_cardview);
            this.iconLeft = v.findViewById(R.id.mm_item_body_icon_left);
            this.title = v.findViewById(R.id.mm_item_body_title);
            this.description = v.findViewById(R.id.mm_item_body_description);
            this.shortValue = v.findViewById(R.id.mm_item_body_short_value);
            this.switchValue = v.findViewById(R.id.mm_item_body_switch);
            this.iconRight = v.findViewById(R.id.mm_item_body_icon_right);
            this.longValue = v.findViewById(R.id.mm_item_body_long_value);
            this.divider = v.findViewById(R.id.mm_item_body_divider);
        }
    }
}
