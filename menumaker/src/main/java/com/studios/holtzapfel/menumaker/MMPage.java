package com.studios.holtzapfel.menumaker;

import android.view.View;

import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holtzapfel on 6/25/17.
 */

public class MMPage {

    private int mID = -1;
    private List<IMenuItem> mItems = new ArrayList<>();

    private String mPageTitle;
    private int mHeaderTitleTextColorRes = -1;
    private int mBodyTitleTextColorRes = -1;
    private boolean isFABEnabled = false;
    private View.OnClickListener mFABOnClickListener;
    private int mIconColorRes = -1;
    private int mIconRightColorRes = -1;
    private int mIconLeftColorRes = -1;
    private boolean isDividersEnabled = true;
    private int mDividerColorRes = -1;

    public void setPageID(int id){
        this.mID = id;
    }

    public int getPageID(){
        return mID;
    }

    public void setMenuItems(List<IMenuItem> menuItems){
        this.mItems = menuItems;
    }

    public List<IMenuItem> getMenuItems(){
        return mItems;
    }

    public IMenuItem getMenuItem(int menuItemID){
        for (int x = 0; x < mItems.size(); x++){
            if (mItems.get(x).getID() == menuItemID){
                return mItems.get(x);
            }
        }
        return null;
    }

    public boolean replaceMenuItem(IMenuItem menuItem){
        boolean isSuccessful = false;
        for (int x = 0; x < mItems.size(); x++){
            if (mItems.get(x).getID() == menuItem.getID()){
                mItems.remove(x);
                mItems.add(x, menuItem);
                isSuccessful = true;
            }
        }
        return isSuccessful;
    }

    public void setPageTitle(String title){
        this.mPageTitle = title;
    }

    public String getPageTitle(){
        return mPageTitle;
    }

    public void setHeaderTitleTextColor(int colorRes){
        this.mHeaderTitleTextColorRes = colorRes;
    }

    public int getHeaderTitleTextColor(){
        return mHeaderTitleTextColorRes;
    }

    public void setBodyTitleTextColor(int colorRes){
        this.mBodyTitleTextColorRes = colorRes;
    }

    public int getBodyTitleTextColor(){
        return mBodyTitleTextColorRes;
    }

    public void setFABEnabled(boolean isFABEnabled){
        this.isFABEnabled = isFABEnabled;
    }

    public boolean isFABEnabled(){
        return isFABEnabled;
    }

    public void setFABOnClickListener(View.OnClickListener onClickListener){
        this.mFABOnClickListener = onClickListener;
        this.isFABEnabled = true;
    }

    public View.OnClickListener getFABOnClickListener(){
        return mFABOnClickListener;
    }

    public void setIconColor(int colorRes){
        this.mIconColorRes = colorRes;
    }

    public int getIconColorRes(){
        return mIconColorRes;
    }

    public void setIconLeftColor(int colorRes){
        this.mIconLeftColorRes = colorRes;
    }

    public int getIconLeftColorRes(){
        return mIconLeftColorRes;
    }

    public void setIconRightColor(int colorRes){
        this.mIconRightColorRes = colorRes;
    }

    public int getIconRightColorRes(){
        return mIconRightColorRes;
    }

    public void setDividersEnabled(boolean isEnabled){
        this.isDividersEnabled = isEnabled;
    }

    public boolean isDividersEnabled(){
        return isDividersEnabled;
    }

    public void setDividerColor(int colorRes){
        this.mDividerColorRes = colorRes;
    }

    public int getDividerColorRes(){
        return mDividerColorRes;
    }
}
