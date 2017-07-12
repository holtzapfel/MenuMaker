package com.studios.holtzapfel.menumaker;

import android.support.annotation.NonNull;
import android.view.View;

import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/25/17.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class MMPage {

    private MMPageBuilder mPageBuilder;

    public MMPage(MMPageBuilder pageBuilder){
        this.mPageBuilder = pageBuilder;
    }

    public int getPageID(){
        return mPageBuilder.mID;
    }

    public List<IMenuItem> getMenuItems(){
        return mPageBuilder.mItems;
    }

    public IMenuItem getMenuItem(int menuItemID){
        for (int x = 0; x < mPageBuilder.mItems.size(); x++){
            if (mPageBuilder.mItems.get(x).getID() == menuItemID){
                return mPageBuilder.mItems.get(x);
            }
        }
        return null;
    }

    public boolean replaceMenuItem(@NonNull IMenuItem menuItem){
        boolean isSuccessful = false;
        for (int x = 0; x < mPageBuilder.mItems.size(); x++){
            if (mPageBuilder.mItems.get(x).getID() == menuItem.getID()){
                mPageBuilder.mItems.remove(x);
                mPageBuilder.mItems.add(x, menuItem);
                isSuccessful = true;
            }
        }
        return isSuccessful;
    }

    public String getPageTitle(){
        return mPageBuilder.mPageTitle;
    }

    public int getHeaderTitleTextColorRes(){
        return mPageBuilder.mHeaderTitleTextColorRes;
    }

    public int getBodyTitleTextColorRes(){
        return mPageBuilder.mBodyTitleTextColorRes;
    }

    public int getBodyDescriptionTextColorRes(){
        return mPageBuilder.mBodyDescriptionTextColorRes;
    }

    public int getBodyValueTextColorRes(){
        return mPageBuilder.mBodyValueTextColorRes;
    }

    public int getBodyContentTextColorRes(){
        return mPageBuilder.mBodyContentTextColorRes;
    }

    public boolean isFABEnabled(){
        return mPageBuilder.isFABEnabled;
    }

    public int getFABIconRes(){
        return mPageBuilder.mFABIconRes;
    }

    public int getFABBackgroundColorRes(){
        return mPageBuilder.mFABBackgroundColorRes;
    }

    public View.OnClickListener getFABOnClickListener(){
        return mPageBuilder.mFABOnClickListener;
    }

    public int getIconColorRes(){
        return mPageBuilder.mIconColorRes;
    }

    public int getIconLeftColorRes(){
        return mPageBuilder.mIconLeftColorRes;
    }

    public int getIconRightColorRes(){
        return mPageBuilder.mIconRightColorRes;
    }

    public boolean isDividersEnabled(){
        return mPageBuilder.isDividersEnabled;
    }

    public int getDividerColorRes(){
        return mPageBuilder.mDividerColorRes;
    }
}
