package com.studios.holtzapfel.menumaker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by holtzapfel on 7/6/17.
 */

public abstract class MMActivity extends AppCompatActivity implements MMMenu.OnMMMenuListener, MMFragment.OnFragmentInteractionListener{

    private static final String TAG = "MMActivity";
    Bundle mSavedInstanceState;
    int mCurrentPageID = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSavedInstanceState = savedInstanceState;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        MMMenu menu = onRequestMenu();
        menu.setCurrentPageID(mCurrentPageID);
        outState = menu.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public abstract MMMenu onRequestMenu();

    public void startMenu(){
        MMMenu menu = onRequestMenu();

        if (menu != null){
            menu.setSavedInstanceState(mSavedInstanceState);

            int pageIDToShow = menu.getInitialPageID();

            if (menu.getInitialPageID() != menu.getCurrentPageID()){
                pageIDToShow = menu.getCurrentPageID();
            }

            if (menu.getSavedInstanceState() != null){
                int bundle_current_page_ID = menu.getSavedInstanceState().getInt(MMMenu.BUNDLE_CURRENT_PAGE_ID, -1);
                if (bundle_current_page_ID != -1){
                    Log.d(TAG, "startMenu: ");
                    pageIDToShow = bundle_current_page_ID;
                }
            }

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                getSupportFragmentManager().beginTransaction()
                        .replace(menu.getFrameRes(), MMFragment.newInstance(pageIDToShow))
                        .commit();
            }
        }
    }

    @Override
    public void onShowPage(int frameLayoutRes, int pageID, boolean useSlidingAnimation) {
        if (useSlidingAnimation) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(frameLayoutRes, MMFragment.newInstance(pageID), String.valueOf(pageID))
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(frameLayoutRes, MMFragment.newInstance(pageID), String.valueOf(pageID))
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    @Override
    public void onRefreshPage(int frameLayoutRes, int pageID, boolean useSlidingAnimation) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(pageID));
        if (fragment != null) {
            if (fragment instanceof MMFragment) {
                ((MMFragment) fragment).updateUI(pageID);
            }
        } else {
            onShowPage(frameLayoutRes, pageID, useSlidingAnimation);
        }
    }

    @Override
    public MMMenu onRetrieveMenu() {
        return onRequestMenu();
    }

    @Override
    public void onNotifyCurrentPageID(int pageID) {
        mCurrentPageID = pageID;
    }
}
