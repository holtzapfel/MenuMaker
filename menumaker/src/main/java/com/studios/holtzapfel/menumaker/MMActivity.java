package com.studios.holtzapfel.menumaker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

/**
 * Created by holtzapfel on 6/21/17.
 */

public abstract class MMActivity extends AppCompatActivity implements MMFragment.OnFragmentInteractionListener {

    private MMMenuBuilder mMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMenu = configureMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        if (mMenu == null){
            throw new RuntimeException("Please create a menu object");
        } else {
            showPage(mMenu.getInitialPageID(), true);
        }
    }

    public abstract MMMenuBuilder configureMenu();

    public abstract IMenuItem configureMenuItemClick(IMenuItem menuItem);

    @Override
    public MMPageBuilder onRequestPage(int pageID) {
        if (mMenu != null){
            return mMenu.getPage(pageID);
        }
        return null;
    }

    @Override
    public IMenuItem onMenuItemClick(IMenuItem menuItem) {
        return configureMenuItemClick(menuItem);
    }

    public void showPage(int pageID){
        getSupportFragmentManager().beginTransaction()
                .replace(mMenu.mFrameRes, MMFragment.newInstance(pageID))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    public void showPage(int pageID, boolean isRefresh){
        if (isRefresh) {
            getSupportFragmentManager().beginTransaction()
                    .replace(mMenu.mFrameRes, MMFragment.newInstance(pageID))
                    .commit();
        } else showPage(pageID);
    }
}
