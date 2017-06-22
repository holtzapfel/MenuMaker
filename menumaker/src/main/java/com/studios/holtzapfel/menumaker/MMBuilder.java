package com.studios.holtzapfel.menumaker;

import android.content.Context;

import com.studios.holtzapfel.menumaker.model.BodyDefaultMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by holtzapfel on 6/21/17.
 */

public class MMBuilder {

    private Context mContext;
    private List<IMenuItem> mMenuItems;

    private int mPrefHeaderTitleTextColorRes = -1;
    private int mPrefBodyTitleTextColorRes = -1;

    public MMBuilder(Context context){
        this.mContext = context;
        mMenuItems = new ArrayList<>();
    }

    public MMBuilder addMenuItems(IMenuItem... items){
        Collections.addAll(mMenuItems, items);
        return this;
    }

    public MMBuilder setHeaderTitleTextColor(int colorRes){
        this.mPrefHeaderTitleTextColorRes = colorRes;
        return this;
    }

    public MMBuilder setBodyTitleTextColor(int colorRes){
        this.mPrefBodyTitleTextColorRes = colorRes;
        return this;
    }

    private IMenuItem prepareMenuItem(IMenuItem item){
        switch (item.getMenuType()){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                HeaderMenuItem headerMenuItem = (HeaderMenuItem) item;

                // Set custom header title text color if not already defined
                if (mPrefHeaderTitleTextColorRes != -1){
                    if (headerMenuItem.getTitleTextColorRes() == -1){
                        headerMenuItem.setTitleTextColor(mPrefHeaderTitleTextColorRes);
                    }
                }

                return headerMenuItem;
            case IMenuItem.MENU_ITEM_TYPE_BODY_DEFAULT:
                BodyDefaultMenuItem bodyDefaultMenuItem = (BodyDefaultMenuItem) item;

                // Set custom body title text color if not already defined
                if (mPrefBodyTitleTextColorRes != -1){
                    if (bodyDefaultMenuItem.getTitleTextColorRes() == -1){
                        bodyDefaultMenuItem.setTitleTextColor(mPrefBodyTitleTextColorRes);
                    }
                }

                return bodyDefaultMenuItem;
        }

        return item;
    }

    public List<IMenuItem> generateList(){
        for (int x = 0; x < mMenuItems.size(); x++){
            IMenuItem item = mMenuItems.get(x);
            mMenuItems.remove(x);
            item = prepareMenuItem(item);
            mMenuItems.add(x, item);
        }

        return mMenuItems;
    }
}
