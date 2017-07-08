package com.studios.holtzapfel.menumaker;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holtzapfel on 6/25/17.
 */

@SuppressWarnings("WeakerAccess")
public class MMPage {

    private int mID = -1;

    public void setPageID(int id){
        this.mID = id;
    }

    public int getPageID(){
        return mID;
    }

    private List<IMenuItem> mItems = new ArrayList<>();

    public void setMenuItems(@NonNull List<IMenuItem> menuItems){
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

    public boolean replaceMenuItem(@NonNull IMenuItem menuItem){
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

    private String mPageTitle;

    public void setPageTitle(@Nullable String title){
        this.mPageTitle = title;
    }

    public String getPageTitle(){
        return mPageTitle;
    }

    private int mHeaderTitleTextColorRes = -1;
    private int mBodyTitleTextColorRes = -1;
    private int mBodyDescriptionTextColorRes = -1;
    private int mBodyValueTextColorRes = -1;
    private int mBodyContentTextColorRes = -1;

    public void setHeaderTitleTextColor(@ColorRes int colorRes){
        this.mHeaderTitleTextColorRes = colorRes;
    }

    public int getHeaderTitleTextColorRes(){
        return mHeaderTitleTextColorRes;
    }

    public void setBodyTitleTextColor(@ColorRes int colorRes){
        this.mBodyTitleTextColorRes = colorRes;
    }

    public int getBodyTitleTextColorRes(){
        return mBodyTitleTextColorRes;
    }

    public void setBodyDescriptionTextColor(@ColorRes int colorRes){
        this.mBodyDescriptionTextColorRes = colorRes;
    }

    public int getBodyDescriptionTextColorRes(){
        return mBodyDescriptionTextColorRes;
    }

    public void setBodyValueTextColor(@ColorRes int colorRes){
        this.mBodyValueTextColorRes = colorRes;
    }

    public int getBodyValueTextColorRes(){
        return mBodyValueTextColorRes;
    }

    public void setBodyContentTextColor(@ColorRes int colorRes){
        this.mBodyContentTextColorRes = colorRes;
    }

    public int getBodyContentTextColorRes(){
        return mBodyContentTextColorRes;
    }

    private boolean isFABEnabled = false;
    private View.OnClickListener mFABOnClickListener;
    private int mFABIconRes = -1;
    private int mFABBackgroundColorRes = -1;

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

    public void setFABIconRes(int iconRes){
        this.mFABIconRes = iconRes;
    }

    public int getFABIconRes(){
        return mFABIconRes;
    }

    public void setFABBackgroundColorRes(int colorRes){
        this.mFABBackgroundColorRes = colorRes;
    }

    public int getFABBackgroundColorRes(){
        return mFABBackgroundColorRes;
    }

    public View.OnClickListener getFABOnClickListener(){
        return mFABOnClickListener;
    }

    private int mIconColorRes = -1;

    public void setIconColor(int colorRes){
        this.mIconColorRes = colorRes;
    }

    public int getIconColorRes(){
        return mIconColorRes;
    }

    private int mIconLeftColorRes = -1;

    public void setIconLeftColor(int colorRes){
        this.mIconLeftColorRes = colorRes;
    }

    public int getIconLeftColorRes(){
        return mIconLeftColorRes;
    }

    private int mIconRightColorRes = -1;

    public void setIconRightColor(int colorRes){
        this.mIconRightColorRes = colorRes;
    }

    public int getIconRightColorRes(){
        return mIconRightColorRes;
    }

    private boolean isDividersEnabled = true;

    public void setDividersEnabled(boolean isEnabled){
        this.isDividersEnabled = isEnabled;
    }

    public boolean isDividersEnabled(){
        return isDividersEnabled;
    }

    private int mDividerColorRes = -1;

    public void setDividerColor(int colorRes){
        this.mDividerColorRes = colorRes;
    }

    public int getDividerColorRes(){
        return mDividerColorRes;
    }
}
