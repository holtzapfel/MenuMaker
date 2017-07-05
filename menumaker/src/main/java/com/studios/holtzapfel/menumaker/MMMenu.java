package com.studios.holtzapfel.menumaker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/28/17.
 */

public class MMMenu implements MMFragment.OnFragmentInteractionListener{

    private static final String TAG = "MMMenu";
    public static MMMenu mMenu;

    private AppCompatActivity mActivity;
    private Context mContext;
    private int mFrameRes;
    private List<MMPage> mPages;
    private int mInitialPageID;
    private int mCurrentPageID;
    private boolean useSlidingAnimation = false;
    private OnMMMenuInteractionListener mListener;
    private OnMenuItemClickListener mMenuItemClickListener;


    public MMMenu(AppCompatActivity activity){
        this.mActivity = activity;
        mMenu = this;
        //mListener = (OnMMMenuInteractionListener) context;
    }

    @Override
    public MMPage onRequestPage(int pageID) {
        return getPage(pageID);
    }

    @Override
    public OnMenuItemClickListener onRequestMenuItemClickListener() {
        return mMenuItemClickListener;
    }

    public static MMFragment.OnFragmentInteractionListener getListener(){
        return mMenu;
    }

    @Override
    public void onMenuItemClick(IMenuItem menuItem) {
        if (menuItem instanceof BodyMenuItem){
            mMenuItemClickListener.onBodyItemClick((BodyMenuItem) menuItem);
        }
        if (menuItem instanceof HeaderMenuItem){
            mMenuItemClickListener.onHeaderItemClick((HeaderMenuItem) menuItem);
        }
    }

    @Override
    public void onNotifyCurrentPageID(int pageID) {
        mCurrentPageID = pageID;
    }

    public interface OnMenuItemClickListener {
        void onBodyItemClick(BodyMenuItem bodyMenuItem);
        void onHeaderItemClick(HeaderMenuItem headerMenuItem);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener){
        this.mMenuItemClickListener = onMenuItemClickListener;
    }

    public OnMenuItemClickListener getOnMenuItemClickListener(){
        return this.mMenuItemClickListener;
    }

    interface OnMMMenuInteractionListener{
        void onRefreshPage(int frameRes, int pageID);
    }

    public void setFrameRes(int frameRes){
        this.mFrameRes = frameRes;
    }

    public int getFrameRes(){
        return mFrameRes;
    }

    public void setPages(List<MMPage> pages){
        this.mPages = pages;
    }

    public List<MMPage> getPages(){
        return mPages;
    }

    public MMPage getPage(int pageID){
        if (mPages != null){
            for (int x = 0; x < mPages.size(); x++){
                if (mPages.get(x).getPageID() == pageID){
                    return mPages.get(x);
                }
            }
        }
        return null;
    }

    public boolean replacePage(MMPage page){
        boolean isSuccessful = false;
        if (mPages != null){
            for (int x = 0; x < mPages.size(); x++){
                if (mPages.get(x).getPageID() == page.getPageID()){
                    mPages.remove(x);
                    mPages.add(x, page);
                    isSuccessful = true;
                }
            }
        }
        return isSuccessful;
    }

    public void setInitialPageID(int pageID){
        this.mInitialPageID = pageID;
        this.mCurrentPageID = pageID;
    }

    public int getInitialPageID(){
        return mInitialPageID;
    }

    public void setUseSlidingAnimation(boolean withSlidingAnimation){
        this.useSlidingAnimation = withSlidingAnimation;
    }

    public boolean getUseSlidingAnimation(){
        return this.useSlidingAnimation;
    }

    public void setCurrentPageID(int pageID){
        this.mCurrentPageID = pageID;
    }

    public int getCurrentPageID(){
        return mCurrentPageID;
    }

    public void showPage(int pageID){
        this.mCurrentPageID = pageID;
        showPage(pageID, useSlidingAnimation);
    }

    public void showPage(int pageID, boolean withSlidingAnimation){
        this.mCurrentPageID = pageID;
        if (withSlidingAnimation) {
            mActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(getFrameRes(), MMFragment.newInstance(pageID), String.valueOf(pageID))
                    .addToBackStack(null)
                    .commit();
        } else {
            mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(getFrameRes(), MMFragment.newInstance(pageID), String.valueOf(pageID))
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    public void refreshPage(){
        //this.mCurrentPageID = mListener.requestCurrentID();
        updateCurrentPage(mCurrentPageID);
    }

    public void updatePage(MMPage page, boolean showPage){
        replacePage(page);

        if (showPage){
            if (page.getPageID() == mCurrentPageID){
                refreshPage();
            } else showPage(page.getPageID());
        }
    }

    public boolean updateItem(IMenuItem item, boolean refreshPage){
        if (item instanceof BodyMenuItem){
            return updateBodyMenuItem((BodyMenuItem) item, refreshPage);
        }

        return false;
    }

    private boolean updateBodyMenuItem(BodyMenuItem item, boolean refreshPage){
        boolean isReplaced = false;
        for (int x = 0; x < mPages.size(); x++){
            MMPage page = mPages.get(x);
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


    private void updateCurrentPage(int pageID){
        Fragment fragment = mActivity.getSupportFragmentManager().findFragmentByTag(String.valueOf(pageID));
        if (fragment != null) {
            if (fragment instanceof MMFragment) {
                ((MMFragment) fragment).updateUI(pageID);
                Log.d(TAG, "updateCurrentPage: ");
            }
        } else {
            showPage(pageID, false);
        }
    }

    public void startMenu(){
        mActivity.getSupportFragmentManager().beginTransaction()
                .replace(mFrameRes, MMFragment.newInstance(mInitialPageID))
                .commit();
    }
}
