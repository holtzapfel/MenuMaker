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

    private MMPage mPage;

    public MMPageBuilder(int pageID){
        this.mPage = new MMPage(pageID);
    }

    interface OnPageBuilderListener{
        FloatingActionButton onRequestFAB();
    }

    public MMPageBuilder withMenuItems(IMenuItem... items){
        if (items != null) {
            if (mPage.getMenuItems().size() > 0){
                throw new RuntimeException("Menu items cannot be declared more than once!");
            }
            List<IMenuItem> menuItems = new ArrayList<>();
            Collections.addAll(menuItems, items);
            mPage.setMenuItems(menuItems);
        }
        return this;
    }

    public MMPageBuilder withMenuItems(List<IMenuItem> items){
        if (items != null){
            if (mPage.getMenuItems().size() > 0){
                throw new RuntimeException("Menu items cannot be declared more than once!");
            }
            mPage.setMenuItems(items);
        }
        return this;
    }

    public MMPageBuilder withHeaderTitleTextColor(int colorRes){
        mPage.setHeaderTitleTextColor(colorRes);
        return this;
    }

    public MMPageBuilder withBodyTitleTextColor(int colorRes){
        mPage.setBodyTitleTextColor(colorRes);
        return this;
    }

    public MMPageBuilder withPageTitle(String title){
        mPage.setPageTitle(title);
        return this;
    }

    public MMPageBuilder withFABEnabled(boolean isFABEnabled){
        mPage.setFABEnabled(isFABEnabled);
        return this;
    }

    public MMPageBuilder withFABOnClickListener(View.OnClickListener onClickListener){
        mPage.setFABOnClickListener(onClickListener);
        return this;
    }

    /*FloatingActionButton buildFAB(FloatingActionButton fab){
        if (isFABEnabled){
            fab.setVisibility(View.VISIBLE);
            if (mFABOnClickListener != null){
                fab.setOnClickListener(mFABOnClickListener);
            }
        } else fab.setVisibility(View.GONE);
        return fab;
    }*/

    private IMenuItem prepareMenuItem(IMenuItem item){
        switch (item.getMenuType()){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                HeaderMenuItem headerMenuItem = (HeaderMenuItem) item;

                // Set custom header title text color if not already defined
                if (mPage.getHeaderTitleTextColor() != -1){
                    if (headerMenuItem.getTitleTextColorRes() == -1){
                        headerMenuItem.withTitleTextColor(mPage.getHeaderTitleTextColor());
                    }
                }

                return headerMenuItem;
            case IMenuItem.MENU_ITEM_TYPE_BODY_DEFAULT:
                BodyDefaultMenuItem bodyDefaultMenuItem = (BodyDefaultMenuItem) item;

                // Set custom body title text color if not already defined
                if (mPage.getBodyTitleTextColor() != -1){
                    if (bodyDefaultMenuItem.getTitleTextColorRes() == -1){
                        bodyDefaultMenuItem.withTitleTextColor(mPage.getBodyTitleTextColor());
                    }
                }

                return bodyDefaultMenuItem;
        }

        return item;
    }

    /*List<IMenuItem> build(){
        for (int x = 0; x < mMenuItems.size(); x++){
            IMenuItem item = mMenuItems.get(x);
            mMenuItems.remove(x);
            item = prepareMenuItem(item);
            mMenuItems.add(x, item);
        }

        return mMenuItems;
    }*/

    public MMPage build(){
        for (int x = 0; x < mPage.getMenuItems().size(); x++){
            IMenuItem item = mPage.getMenuItems().get(x);
            mPage.getMenuItems().remove(x);
            item = prepareMenuItem(item);
            mPage.getMenuItems().add(x, item);
        }

        return mPage;
    }
}
