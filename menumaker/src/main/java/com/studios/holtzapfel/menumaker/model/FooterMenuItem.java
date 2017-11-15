package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.R2;
import com.studios.holtzapfel.menumaker.model.interfaces.IFooterItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by holtzapfel on 6/20/17.
 */

public class FooterMenuItem extends AbstractMenuItem<FooterMenuItem, FooterMenuItem.FooterViewHolder> implements IFooterItem<FooterMenuItem, FooterMenuItem.FooterViewHolder>{

    public FooterMenuItem(){}

    private CharSequence mButtonLeftText;
    private View.OnClickListener mButtonLeftClickListener;
    private boolean isButtonLeftVisible = false;

    @Override
    public FooterMenuItem withButtonLeft(CharSequence text, View.OnClickListener onClickListener) {
        this.mButtonLeftText = text;
        this.mButtonLeftClickListener = onClickListener;
        this.isButtonLeftVisible = true;
        return this;
    }

    private CharSequence mButtonCenterText;
    private View.OnClickListener mButtonCenterClickListener;
    private boolean isButtonCenterVisible = false;

    @Override
    public FooterMenuItem withButtonCenter(CharSequence text, View.OnClickListener onClickListener) {
        this.mButtonCenterText = text;
        this.mButtonCenterClickListener = onClickListener;
        this.isButtonCenterVisible = true;
        return this;
    }

    private CharSequence mButtonRightText;
    private View.OnClickListener mButtonRightClickListener;
    private boolean isButtonRightVisible = false;

    @Override
    public FooterMenuItem withButtonRight(CharSequence text, View.OnClickListener onClickListener) {
        this.mButtonRightText = text;
        this.mButtonRightClickListener = onClickListener;
        this.isButtonRightVisible = true;
        return this;
    }

    @Override
    public int getMenuType() {
        return MENU_ITEM_TYPE_FOOTER;
    }

    @Override
    public void bindView(Context context, FooterMenuItem.FooterViewHolder holder, final MMMenu.OnMenuItemClickListener listener) {
        // Configure left button
        if (isButtonLeftVisible){
            holder.buttonLeft.setVisibility(View.VISIBLE);
            holder.buttonLeft.setText(mButtonLeftText);
            holder.buttonLeft.setOnClickListener(mButtonLeftClickListener);
        } else holder.buttonLeft.setVisibility(View.GONE);

        // Configure center button
        if (isButtonCenterVisible){
            holder.buttonCenter.setVisibility(View.VISIBLE);
            holder.buttonCenter.setText(mButtonCenterText);
            holder.buttonCenter.setOnClickListener(mButtonCenterClickListener);
        } else holder.buttonCenter.setVisibility(View.GONE);

        // Configure right button
        if (isButtonRightVisible){
            holder.buttonRight.setVisibility(View.VISIBLE);
            holder.buttonRight.setText(mButtonRightText);
            holder.buttonRight.setOnClickListener(mButtonRightClickListener);
        } else holder.buttonRight.setVisibility(View.GONE);
    }

    @Override
    public void unbindView(FooterMenuItem.FooterViewHolder holder) {

    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R2.id.mm_item_footer_cardview) CardView cardView;
        @BindView(R2.id.mm_item_footer_button_left) Button buttonLeft;
        @BindView(R2.id.mm_item_footer_button_center) Button buttonCenter;
        @BindView(R2.id.mm_item_footer_button_right) Button buttonRight;

        public FooterViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
