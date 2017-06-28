package com.studios.holtzapfel.menumaker.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.studios.holtzapfel.menumaker.MMFragment;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.interfaces.IFooterItem;

/**
 * Created by holtzapfel on 6/20/17.
 */

public class FooterMenuItem extends AbstractMenuItem<FooterMenuItem, FooterMenuItem.FooterViewHolder> implements IFooterItem<FooterMenuItem, FooterMenuItem.FooterViewHolder>{

    public FooterMenuItem(){}

    @Override
    public int getMenuType() {
        return MENU_ITEM_TYPE_FOOTER;
    }

    @Override
    public void bindView(Context context, FooterMenuItem.FooterViewHolder holder, final MMFragment.OnFragmentInteractionListener listener) {

    }

    @Override
    public void unbindView(FooterMenuItem.FooterViewHolder holder) {

    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public FooterViewHolder(View v) {
            super(v);

            this.cardView = v.findViewById(R.id.mm_item_footer_cardview);
        }
    }
}
