package com.studios.holtzapfel.menumaker.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.MMMenu;
import com.studios.holtzapfel.menumaker.R;
import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/20/17.
 */

public class MenuFragmentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<IMenuItem> mItems;
    private MMMenu.OnMenuItemClickListener mListener;

    public MenuFragmentRecyclerAdapter(Context context, List<IMenuItem> menuItems, MMMenu.OnMenuItemClickListener listener){
        this.mContext = context;
        this.mItems = menuItems;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                return new HeaderMenuItem.HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_header, parent, false));
            case IMenuItem.MENU_ITEM_TYPE_BODY:
                return new BodyMenuItem.BodyMenuItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_body, parent, false));
            case IMenuItem.MENU_ITEM_TYPE_FOOTER:
                return new FooterMenuItem.FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mm_item_footer, parent, false));
        }

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getMenuType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IMenuItem menuItem = mItems.get(position);

        switch (menuItem.getMenuType()){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                HeaderMenuItem headerMenuItem = (HeaderMenuItem) menuItem;
                headerMenuItem.bindView(mContext, (HeaderMenuItem.HeaderViewHolder) holder, mListener);
                break;
            case IMenuItem.MENU_ITEM_TYPE_BODY:
                BodyMenuItem bodyMenuItem = (BodyMenuItem) menuItem;
                if (bodyMenuItem.isDividerEnabled()) {
                    if (position + 1 < mItems.size()) {
                        IMenuItem nextItem = mItems.get(position + 1);
                        if (nextItem.getMenuType() != IMenuItem.MENU_ITEM_TYPE_BODY) {
                            bodyMenuItem.withDividerEnabled(false);
                        } else bodyMenuItem.withDividerEnabled(true);
                    } else bodyMenuItem.withDividerEnabled(false);
                }
                bodyMenuItem.bindView(mContext, (BodyMenuItem.BodyMenuItemViewHolder) holder, mListener);
                break;
            case IMenuItem.MENU_ITEM_TYPE_FOOTER:
                FooterMenuItem footerMenuItem = (FooterMenuItem) menuItem;
                footerMenuItem.bindView(mContext, (FooterMenuItem.FooterViewHolder) holder, mListener);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
