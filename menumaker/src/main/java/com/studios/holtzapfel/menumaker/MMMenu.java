package com.studios.holtzapfel.menumaker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/28/17.
 */

@SuppressWarnings({"unused"})
public class MMMenu implements MMFragment.OnFragmentInteractionListener{

    protected static final String BUNDLE_CURRENT_PAGE_ID = "BUNDLE_CURRENT_PAGE_ID";

    private static final String TAG = "MMMenu";
    private static MMMenu mMenu;
    private MMMenuBuilder mMenuBuilder;
    MMFragment.OnFragmentInteractionListener mListener;

    private int mCurrentPageID;


    protected MMMenu(MMMenuBuilder menuBuilder){
        this.mMenuBuilder = menuBuilder;
        mCurrentPageID = menuBuilder.mInitialPageID;
        MMMenu.mMenu = this;
    }

    static MMFragment.OnFragmentInteractionListener getListener(){
        return mMenu;
    }

    @Override
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

    @Override
    public OnMenuItemClickListener onRequestMenuItemClickListener() {
        return mMenuBuilder.mOnMenuItemClickListener;
    }

    @Override
    public void onNotifyCurrentPageID(int pageID) {
        mCurrentPageID = pageID;
    }

    public interface OnMenuItemClickListener {
        void onBodyItemClick(BodyMenuItem bodyMenuItem);
        void onHeaderItemClick(HeaderMenuItem headerMenuItem);
    }

    public int getFrameRes(){
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

    public int getCurrentPageID(){
        return mCurrentPageID;
    }

    public void showPage(int pageID){
        showPage(pageID, mMenuBuilder.useSlidingAnimation);
    }

    private void showPage(int pageID, boolean withSlidingAnimation){
        mCurrentPageID = pageID;
        if (withSlidingAnimation) {
            mMenuBuilder.mActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(mMenuBuilder.mFrameRes, MMFragment.newInstance(pageID), String.valueOf(pageID))
                    .addToBackStack(null)
                    .commit();
        } else {
            mMenuBuilder.mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(mMenuBuilder.mFrameRes, MMFragment.newInstance(pageID), String.valueOf(pageID))
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
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
        Fragment fragment = mMenuBuilder.mActivity.getSupportFragmentManager().findFragmentByTag(String.valueOf(pageID));
        if (fragment != null) {
            if (fragment instanceof MMFragment) {
                ((MMFragment) fragment).updateUI(pageID);
                Log.d(TAG, "refreshCurrentPage: ");
            }
        } else {
            showPage(pageID, false);
        }
    }

    public void startMenu(){
        // It took me forever to discover that I really needed this single line of code
        // When traversing to a new activity that also had a menu, and then pressing 'back,'
        // the menu on the previous activity would show.  Now it does not do that :)
        mMenu = this;
        int pageIDToShow = mMenuBuilder.mInitialPageID;
        if (mMenuBuilder.mInitialPageID != mCurrentPageID){
            pageIDToShow = mCurrentPageID;
        }
        if (mMenuBuilder.mSavedInstanceState != null){
            int bundle_current_page_ID = mMenuBuilder.mSavedInstanceState.getInt(BUNDLE_CURRENT_PAGE_ID, -1);
            if (bundle_current_page_ID != -1){
                Log.d(TAG, "startMenu: ");
                pageIDToShow = bundle_current_page_ID;
            }
        }
        if (mMenuBuilder.mActivity.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            mMenuBuilder.mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(mMenuBuilder.mFrameRes, MMFragment.newInstance(pageIDToShow))
                    .commit();
        }
    }

    public Bundle saveInstanceState(Bundle savedInstanceState){
        if (savedInstanceState != null){
            savedInstanceState.putInt(BUNDLE_CURRENT_PAGE_ID, mCurrentPageID);
        }
        return savedInstanceState;
    }
}
