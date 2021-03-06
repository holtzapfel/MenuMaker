package com.studios.holtzapfel.menumaker;

import android.content.Context;
import android.os.Bundle;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/28/17.
 */

@SuppressWarnings({"UnusedReturnValue", "unused", "WeakerAccess"})
public class MMMenu {

    static final String BUNDLE_CURRENT_PAGE_ID = "BUNDLE_CURRENT_PAGE_ID";

    private MMMenuBuilder mMenuBuilder;
    private OnMMMenuListener mListener;
    private int mCurrentPageID;

    protected MMMenu(MMMenuBuilder menuBuilder){
        this.mMenuBuilder = menuBuilder;
        mListener = (OnMMMenuListener) menuBuilder.mContext;
        mCurrentPageID = menuBuilder.mInitialPageID;
    }

    interface OnMMMenuListener{
        void onShowPage(int frameLayoutRes, int pageID, boolean useSlidingAnimation);
        void onRefreshPage(int frameLayoutRes, int pageID);
    }

    public interface OnMenuItemClickListener {
        void onBodyItemClick(BodyMenuItem bodyMenuItem);
        void onHeaderItemClick(HeaderMenuItem headerMenuItem);
    }

    OnMenuItemClickListener onRequestMenuItemClickListener() {
        return mMenuBuilder.mOnMenuItemClickListener;
    }

    Context getContext(){
        return mMenuBuilder.mContext;
    }

    public MMPage getPage(int pageID) {
        if (mMenuBuilder.mPages != null){
            for (int x = 0; x < mMenuBuilder.mPages.size(); x++){
                if (mMenuBuilder.mPages.get(x).getPageID() == pageID){
                    return mMenuBuilder.mPages.get(x);
                }
            }
        }
        return null;
    }

    int getFrameRes(){
        return mMenuBuilder.mFrameRes;
    }

    public List<MMPage> getPages(){
        return mMenuBuilder.mPages;
    }

    private boolean replacePage(MMPage page){
        boolean isSuccessful = false;
        if (mMenuBuilder.mPages != null){
            for (int x = 0; x < mMenuBuilder.mPages.size(); x++){
                if (mMenuBuilder.mPages.get(x).getPageID() == page.getPageID()){
                    mMenuBuilder.mPages.remove(x);
                    mMenuBuilder.mPages.add(x, page);
                    isSuccessful = true;
                }
            }
        }
        return isSuccessful;
    }

    void setCurrentPageID(int currentPageID){
        this.mCurrentPageID = currentPageID;
    }

    int getCurrentPageID(){
        return mCurrentPageID;
    }

    int getInitialPageID(){
        return mMenuBuilder.mInitialPageID;
    }

    boolean getUseUpArrowOnInitialPage(){
        return mMenuBuilder.useUpArrowOnInitialPage;
    }

    boolean getUpArrowEnabled(){
        return mMenuBuilder.upArrowEnabled;
    }

    public void showPage(int pageID){
        showPage(pageID, mMenuBuilder.useSlidingAnimation);
    }

    private void showPage(int pageID, boolean withSlidingAnimation){
        mCurrentPageID = pageID;
        mListener.onShowPage(getFrameRes(), pageID, withSlidingAnimation);
    }

    public void refreshPage(){
        refreshCurrentPage(mCurrentPageID);
    }

    public void updatePage(MMPage page, boolean showPage){
        replacePage(page);

        if (showPage){
            if (page.getPageID() == mCurrentPageID){
                refreshPage();
            } else showPage(page.getPageID());
        }
    }

    @SuppressWarnings("SimplifiableIfStatement")
    public boolean updateItem(IMenuItem item, boolean refreshPage){
        if (item instanceof BodyMenuItem){
            return updateBodyMenuItem((BodyMenuItem) item, refreshPage);
        }

        if (item instanceof HeaderMenuItem){
            return updateHeaderMenuItem((HeaderMenuItem) item, refreshPage);
        }

        return false;
    }

    private boolean updateHeaderMenuItem(HeaderMenuItem item, boolean refreshPage){
        boolean isReplaced = false;
        for (int x = 0; x < mMenuBuilder.mPages.size(); x++){
            MMPage page = mMenuBuilder.mPages.get(x);
            IMenuItem menuItem = page.getMenuItem(item.getID());
            if (menuItem != null){
                page.replaceMenuItem(item);
                isReplaced = true;
                break;
            }
        }

        if (isReplaced){
            if (refreshPage){
                refreshPage();
            }
            return true;
        } else return false;
    }

    private boolean updateBodyMenuItem(BodyMenuItem item, boolean refreshPage){
        boolean isReplaced = false;
        for (int x = 0; x < mMenuBuilder.mPages.size(); x++){
            MMPage page = mMenuBuilder.mPages.get(x);
            IMenuItem menuItem = page.getMenuItem(item.getID());
            if (menuItem != null){
                page.replaceMenuItem(item);
                isReplaced = true;
                break;
            }
        }

        if (isReplaced){
            if (refreshPage) {
                refreshPage();
            }
            return true;
        } else return false;
    }


    private void refreshCurrentPage(int pageID){
        mListener.onRefreshPage(getFrameRes(), pageID);
    }

    Bundle saveInstanceState(Bundle savedInstanceState){
        if (savedInstanceState != null){
            savedInstanceState.putInt(BUNDLE_CURRENT_PAGE_ID, mCurrentPageID);
        }
        return savedInstanceState;
    }

    void setSavedInstanceState(Bundle savedInstanceState){
        this.mMenuBuilder.withSavedInstanceState(savedInstanceState);
    }

    Bundle getSavedInstanceState(){
        return this.mMenuBuilder.mSavedInstanceState;
    }
}
