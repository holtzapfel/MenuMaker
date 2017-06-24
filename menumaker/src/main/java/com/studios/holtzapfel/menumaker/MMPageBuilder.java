package com.studios.holtzapfel.menumaker;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.studios.holtzapfel.menumaker.model.BodyDefaultMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by holtzapfel on 6/21/17.
 */

public class MMPageBuilder {

    private int mPageID;
    private List<IMenuItem> mMenuItems;
    private View.OnClickListener mFABOnClickListener;

    private int mPrefHeaderTitleTextColorRes = -1;
    private int mPrefBodyTitleTextColorRes = -1;
    private String mPageTitle;
    private boolean isFABEnabled = false;

    public MMPageBuilder(int pageID){
        this.mPageID = pageID;
    }

    interface OnPageBuilderListener{
        FloatingActionButton onRequestFAB();
    }

    public int getPageID(){
        return mPageID;
    }

    public MMPageBuilder addMenuItems(IMenuItem... items){
        if (items != null) {
            mMenuItems = new ArrayList<>();

            Collections.addAll(mMenuItems, items);
        }
        return this;
    }

    public MMPageBuilder setHeaderTitleTextColor(int colorRes){
        this.mPrefHeaderTitleTextColorRes = colorRes;
        return this;
    }

    public MMPageBuilder setBodyTitleTextColor(int colorRes){
        this.mPrefBodyTitleTextColorRes = colorRes;
        return this;
    }

    public MMPageBuilder withPageTitle(String title){
        this.mPageTitle = title;
        return this;
    }

    public String getPageTitle(){
        return mPageTitle;
    }

    public MMPageBuilder withFABEnabled(boolean isFABEnabled){
        this.isFABEnabled = isFABEnabled;
        return this;
    }

    public boolean isFABEnabled(){
        return isFABEnabled;
    }

    public MMPageBuilder withFABClickListener(View.OnClickListener onClickListener){
        if (onClickListener != null){
            isFABEnabled = true;
            this.mFABOnClickListener = onClickListener;
        } else isFABEnabled = false;
        return this;
    }

    FloatingActionButton buildFAB(FloatingActionButton fab){
        if (isFABEnabled){
            fab.setVisibility(View.VISIBLE);
            if (mFABOnClickListener != null){
                fab.setOnClickListener(mFABOnClickListener);
            }
        } else fab.setVisibility(View.GONE);
        return fab;
    }

    private IMenuItem prepareMenuItem(IMenuItem item){
        switch (item.getMenuType()){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                HeaderMenuItem headerMenuItem = (HeaderMenuItem) item;

                // Set custom header title text color if not already defined
                if (mPrefHeaderTitleTextColorRes != -1){
                    if (headerMenuItem.getTitleTextColorRes() == -1){
                        headerMenuItem.withTitleTextColor(mPrefHeaderTitleTextColorRes);
                    }
                }

                return headerMenuItem;
            case IMenuItem.MENU_ITEM_TYPE_BODY_DEFAULT:
                BodyDefaultMenuItem bodyDefaultMenuItem = (BodyDefaultMenuItem) item;

                // Set custom body title text color if not already defined
                if (mPrefBodyTitleTextColorRes != -1){
                    if (bodyDefaultMenuItem.getTitleTextColorRes() == -1){
                        bodyDefaultMenuItem.withTitleTextColor(mPrefBodyTitleTextColorRes);
                    }
                }

                return bodyDefaultMenuItem;
        }

        return item;
    }

    List<IMenuItem> build(){
        for (int x = 0; x < mMenuItems.size(); x++){
            IMenuItem item = mMenuItems.get(x);
            mMenuItems.remove(x);
            item = prepareMenuItem(item);
            mMenuItems.add(x, item);
        }

        return mMenuItems;
    }
}
