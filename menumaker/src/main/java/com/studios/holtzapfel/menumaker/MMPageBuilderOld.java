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

public class MMPageBuilderOld {

    private int mPageID;
    private List<IMenuItem> mMenuItems;
    private View.OnClickListener mFABOnClickListener;

    private int mHeaderTitleTextColorRes = -1;
    private int mBodyTitleTextColorRes = -1;
    private String mPageTitle;
    private boolean isFABEnabled = false;

    public MMPageBuilderOld(int pageID){
        this.mPageID = pageID;
    }

    interface OnPageBuilderListener{
        FloatingActionButton onRequestFAB();
    }

    public int getPageID(){
        return mPageID;
    }

    public MMPageBuilderOld withMenuItems(IMenuItem... items){
        if (items != null) {
            mMenuItems = new ArrayList<>();

            Collections.addAll(mMenuItems, items);
        }
        return this;
    }

    public MMPageBuilderOld withHeaderTitleTextColor(int colorRes){
        this.mHeaderTitleTextColorRes = colorRes;
        return this;
    }

    public MMPageBuilderOld withBodyTitleTextColor(int colorRes){
        this.mBodyTitleTextColorRes = colorRes;
        return this;
    }

    public MMPageBuilderOld withPageTitle(String title){
        this.mPageTitle = title;
        return this;
    }

    public String getPageTitle(){
        return mPageTitle;
    }

    public MMPageBuilderOld withFABEnabled(boolean isFABEnabled){
        this.isFABEnabled = isFABEnabled;
        return this;
    }

    public boolean isFABEnabled(){
        return isFABEnabled;
    }

    public MMPageBuilderOld withFABOnClickListener(View.OnClickListener onClickListener){
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
                if (mHeaderTitleTextColorRes != -1){
                    if (headerMenuItem.getTitleTextColorRes() == -1){
                        headerMenuItem.withTitleTextColor(mHeaderTitleTextColorRes);
                    }
                }

                return headerMenuItem;
            case IMenuItem.MENU_ITEM_TYPE_BODY_DEFAULT:
                BodyDefaultMenuItem bodyDefaultMenuItem = (BodyDefaultMenuItem) item;

                // Set custom body title text color if not already defined
                if (mBodyTitleTextColorRes != -1){
                    if (bodyDefaultMenuItem.getTitleTextColorRes() == -1){
                        bodyDefaultMenuItem.withTitleTextColor(mBodyTitleTextColorRes);
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
