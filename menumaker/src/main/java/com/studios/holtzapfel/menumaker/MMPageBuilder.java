package com.studios.holtzapfel.menumaker;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public MMPageBuilder withMenuItems(@NonNull IMenuItem... items){
        if (mPage.getMenuItems().size() > 0){
            throw new RuntimeException("Menu items cannot be declared more than once!");
        }
        List<IMenuItem> menuItems = new ArrayList<>();
        Collections.addAll(menuItems, items);
        mPage.setMenuItems(menuItems);
        return this;
    }

    public MMPageBuilder withMenuItems(@NonNull List<IMenuItem> items){
        if (mPage.getMenuItems().size() > 0){
            throw new RuntimeException("Menu items cannot be declared more than once!");
        }
        mPage.setMenuItems(items);
        return this;
    }

    public MMPageBuilder withHeaderTitleTextColor(@ColorRes int colorRes){
        mPage.setHeaderTitleTextColor(colorRes);
        return this;
    }

    public MMPageBuilder withBodyTitleTextColor(@ColorRes int colorRes){
        mPage.setBodyTitleTextColor(colorRes);
        return this;
    }

    public MMPageBuilder withBodyDescriptionTextColor(@ColorRes int colorRes){
        mPage.setBodyDescriptionTextColor(colorRes);
        return this;
    }

    public MMPageBuilder withBodyValueTextColor(@ColorRes int colorRes){
        mPage.setBodyValueTextColor(colorRes);
        return this;
    }

    public MMPageBuilder withBodyContentTextColor(@ColorRes int colorRes){
        mPage.setBodyContentTextColor(colorRes);
        return this;
    }

    public MMPageBuilder withPageTitle(@Nullable String title){
        mPage.setPageTitle(title);
        return this;
    }

    public MMPageBuilder withFABEnabled(boolean isFABEnabled){
        mPage.setFABEnabled(isFABEnabled);
        return this;
    }

    public MMPageBuilder withFAB(@Nullable View.OnClickListener onClickListener){
        mPage.setFABEnabled(true);
        mPage.setFABOnClickListener(onClickListener);
        return this;
    }

    public MMPageBuilder withFAB(@DrawableRes int iconRes, View.OnClickListener onClickListener){
        withFAB(onClickListener);
        mPage.setFABIconRes(iconRes);
        return this;
    }

    public MMPageBuilder withFAB(@DrawableRes int iconRes, @ColorRes int backgroundColorRes, View.OnClickListener onClickListener){
        withFAB(iconRes, onClickListener);
        mPage.setFABBackgroundColorRes(backgroundColorRes);
        return this;
    }

    public MMPageBuilder withIconColor(@ColorRes int colorRes){
        mPage.setIconColor(colorRes);
        return this;
    }

    public MMPageBuilder withIconRightColor(@ColorRes int colorRes){
        mPage.setIconRightColor(colorRes);
        return this;
    }

    public MMPageBuilder withIconLeftColor(@ColorRes int colorRes){
        mPage.setIconLeftColor(colorRes);
        return this;
    }

    public MMPageBuilder withDividersEnabled(boolean isEnabled){
        mPage.setDividersEnabled(isEnabled);
        return this;
    }

    public MMPageBuilder withDividerColor(@ColorRes int colorRes){
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
                if (mPage.getHeaderTitleTextColorRes() != -1){
                    if (headerMenuItem.getTitleTextColorRes() == -1){
                        headerMenuItem.withTitleTextColor(mPage.getHeaderTitleTextColorRes());
                    }
                }

                return headerMenuItem;
            case IMenuItem.MENU_ITEM_TYPE_BODY:
                BodyMenuItem bodyMenuItem = (BodyMenuItem) item;

                if (mPage.getBodyTitleTextColorRes() != -1){
                    if (bodyMenuItem.getTitleTextColorRes() == -1){
                        bodyMenuItem.withTitleTextColor(mPage.getBodyTitleTextColorRes());
                    }
                }

                if (mPage.getBodyDescriptionTextColorRes() != -1){
                    if (bodyMenuItem.getDescriptionTextColorRes() == -1){
                        bodyMenuItem.withDescriptionTextColor(mPage.getBodyDescriptionTextColorRes());
                    }
                }

                if (mPage.getBodyValueTextColorRes() != -1){
                    if (bodyMenuItem.getValueTextColorRes() == -1){
                        bodyMenuItem.withValueTextColor(mPage.getBodyValueTextColorRes());
                    }
                }

                if (mPage.getBodyContentTextColorRes() != -1){
                    if (bodyMenuItem.getContentTextColorRes() == -1){
                        bodyMenuItem.withContentTextColor(mPage.getBodyContentTextColorRes());
                    }
                }

                if (bodyMenuItem.getIconLeftColorRes() == -1) {
                    if (mPage.getIconLeftColorRes() != -1) {
                        bodyMenuItem.withIconLeftColor(mPage.getIconLeftColorRes());
                    } else bodyMenuItem.withIconLeftColor(mPage.getIconColorRes());
                }

                if (bodyMenuItem.getIconRightColorRes() == -1){
                    if (mPage.getIconRightColorRes() != -1){
                        bodyMenuItem.withIconRightColor(mPage.getIconRightColorRes());
                    } else bodyMenuItem.withIconRightColor(mPage.getIconColorRes());
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
