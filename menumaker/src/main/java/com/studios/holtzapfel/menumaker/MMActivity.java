package com.studios.holtzapfel.menumaker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

/**
 * Created by holtzapfel on 6/21/17.
 */

public abstract class MMActivity extends AppCompatActivity implements MMFragment.OnFragmentInteractionListener, MMMenu.OnMMMenuInteractionListener {

    private int mCurrentPageID;

    public void initiateMenu(){
        MMMenu menu = onRequestMenu();
        getSupportFragmentManager().beginTransaction()
                .replace(menu.getFrameRes(), MMFragment.newInstance(menu.getInitialPageID()), String.valueOf(menu.getInitialPageID()))
                .commit();
    }

    public abstract MMMenu onRequestMenu();

    public abstract void onBodyItemClick(BodyMenuItem bodyItem);

    @Override
    public void showPage(int frameRes, int pageID) {
        getSupportFragmentManager().beginTransaction()
                .replace(frameRes, MMFragment.newInstance(pageID), String.valueOf(pageID))
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void refreshPage(int frameRes, int pageID) {
        updateCurrentPage(frameRes, pageID);
    }

    private void updateCurrentPage(int frameRes, int pageID){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(pageID));
        if (fragment != null) {
            if (fragment instanceof MMFragment) {
                ((MMFragment) fragment).updateUI();
            }
        } else {
            showPage(frameRes, pageID);
        }
    }


    @Override
    public MMPage onRequestPage(int pageID) {
        MMMenu menu = onRequestMenu();
        if (menu != null){
            return menu.getPage(pageID);
        }
        return null;
    }

    @Override
    public void onMenuItemClick(IMenuItem menuItem) {
        if (menuItem instanceof BodyMenuItem) {
            onBodyItemClick((BodyMenuItem) menuItem);
        }
    }

    @Override
    public void onNotifyCurrentPageID(int pageID) {
        mCurrentPageID = pageID;
    }

    @Override
    public int requestCurrentID() {
        return mCurrentPageID;
    }
}
