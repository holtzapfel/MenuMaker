package com.studios.holtzapfel.menumaker;

import android.view.View;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
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
        this.mPage = new MMPage();
        this.mPage.setPageID(pageID);
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

    public MMPageBuilder withIconColor(int colorRes){
        mPage.setIconColor(colorRes);
        return this;
    }

    public MMPageBuilder withDividersEnabled(boolean isEnabled){
        mPage.setDividersEnabled(isEnabled);
        return this;
    }

    public MMPageBuilder withDividerColor(int colorRes){
        mPage.setDividerColor(colorRes);
        return this;
    }

    private IMenuItem prepareMenuItem(IMenuItem item, IMenuItem nextItem){
        if (nextItem != null){
            if (nextItem instanceof FooterMenuItem){
                item.withLastItem(true);
            } else item.withLastItem(false);
        }

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
            case IMenuItem.MENU_ITEM_TYPE_BODY:
                BodyMenuItem bodyMenuItem = (BodyMenuItem) item;

                if (mPage.getBodyTitleTextColor() != -1){
                    if (bodyMenuItem.getTitleTextColorRes() == -1){
                        bodyMenuItem.withTitleTextColor(mPage.getBodyTitleTextColor());
                    }
                }

                if (mPage.getIconColorRes() != -1){
                    if (bodyMenuItem.getIconLeftColorRes() == -1){
                        bodyMenuItem.withIconLeftColor(mPage.getIconColorRes());
                    }

                    if (bodyMenuItem.getIconRightColorRes() == -1){
                        bodyMenuItem.withIconRightColor(mPage.getIconColorRes());
                    }
                }

                bodyMenuItem.withDividerEnabled(mPage.isDividersEnabled());
                if (mPage.getDividerColorRes() != -1){
                    if (bodyMenuItem.getDividerColorRes() == -1) {
                        bodyMenuItem.withDividerColor(mPage.getDividerColorRes());
                    }
                }

                return bodyMenuItem;
        }

        return item;
    }

    public MMPage build(){
        for (int x = 0; x < mPage.getMenuItems().size(); x++){
            IMenuItem item = mPage.getMenuItems().get(x);
            IMenuItem nextItem = null;
            if ((x + 1) != mPage.getMenuItems().size()){
                nextItem = mPage.getMenuItems().get(x + 1);
            }
            mPage.getMenuItems().remove(x);
            item = prepareMenuItem(item, nextItem);
            mPage.getMenuItems().add(x, item);
        }

        return mPage;
    }
}
