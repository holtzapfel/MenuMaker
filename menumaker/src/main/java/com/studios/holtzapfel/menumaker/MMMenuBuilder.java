package com.studios.holtzapfel.menumaker;

import android.content.Context;

import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by holtzapfel on 6/24/17.
 */

public class MMMenuBuilder {

    Context mContext;
    int mFrameRes;
    private List<MMPage> mPages;
    private int mInitialPageID = -1;
    private int mHeaderTitleTextColorRes = -1;

    public MMMenuBuilder(Context context, int fragmentFrameContainerResource){
        this.mContext = context;
        this.mFrameRes = fragmentFrameContainerResource;
    }

    public MMMenuBuilder withPages(MMPage... pages){
        if (pages != null) {
            mPages = new ArrayList<>();
            Collections.addAll(mPages, pages);
        }
        return this;
    }

    public MMMenuBuilder withInitialPageID(int pageID){
        this.mInitialPageID = pageID;
        return this;
    }

    public MMMenuBuilder withHeaderTitleTextColor(int colorRes){
        this.mHeaderTitleTextColorRes = colorRes;
        return this;
    }

    public MMMenu build(){
        MMMenu menu = new MMMenu(mContext);
        menu.setFrameRes(mFrameRes);
        menu.setPages(prepareAndGetPages());
        menu.setInitialPageID(getInitialPageID());
        return menu;
    }

    private int getInitialPageID(){
        if (mInitialPageID != -1){
            return mInitialPageID;
        }

        if (mPages != null){
            if (mPages.size() > 0){
                mInitialPageID = mPages.get(0).getPageID();
            }
        }

        return mInitialPageID;
    }

    private List<MMPage> prepareAndGetPages(){
        for (int x = 0; x < mPages.size(); x++){
            for (int y = 0; y < mPages.get(x).getMenuItems().size(); y++){
                IMenuItem item = mPages.get(x).getMenuItems().get(y);
                mPages.get(x).getMenuItems().remove(y);
                mPages.get(x).getMenuItems().add(y, updateItemWithCustomSettings(item));
            }
        }
        return mPages;
    }

    private IMenuItem updateItemWithCustomSettings(IMenuItem item){
        if (item instanceof HeaderMenuItem){
            return updateHeaderMenuItemWithCustomSettings((HeaderMenuItem) item);
        }

        return item;
    }

    private HeaderMenuItem updateHeaderMenuItemWithCustomSettings(HeaderMenuItem item){
        if (item.getTitleTextColorRes() == -1){
            item.withTitleTextColor(mHeaderTitleTextColorRes);
        }
        return item;
    }
}
