package com.studios.holtzapfel.menumaker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by holtzapfel on 6/21/17.
 */

public abstract class MMActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener {

    private int mContainer = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContainer = onRequestFragmentContainerResource();

        showPage(getInitialMenuPageID(), true);
    }

    public abstract int onRequestFragmentContainerResource();

    public abstract int getInitialMenuPageID();

    public void showPage(int pageID){
        getSupportFragmentManager().beginTransaction()
                .replace(mContainer, MenuFragment.newInstance(pageID))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    public void showPage(int pageID, boolean isRefresh){
        if (isRefresh) {
            getSupportFragmentManager().beginTransaction()
                    .replace(mContainer, MenuFragment.newInstance(pageID))
                    .commit();
        } else showPage(pageID);
    }
}
