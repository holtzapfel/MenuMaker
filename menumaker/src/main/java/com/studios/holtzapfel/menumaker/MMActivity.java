package com.studios.holtzapfel.menumaker;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;

/**
 * Created by holtzapfel on 6/21/17.
 */

public abstract class MMActivity extends AppCompatActivity implements MMFragment.OnFragmentInteractionListener {

    private MMMenuBuilder mMenu;

    public void initiateMenu(){
        updateUI();
    }

    private void updateUI(){
        mMenu = configureMenu();
        if (mMenu == null){
            throw new RuntimeException("Please create a menu object");
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(mMenu.mFrameRes, MMFragment.newInstance(mMenu.getInitialPageID()))
                    .commit();
        }
    }

    public abstract MMMenuBuilder configureMenu();

    public abstract void configBodyItemClick(BodyMenuItem bodyItem);

    @Override
    public MMPage onRequestPage(int pageID) {
        if (mMenu != null){
            return mMenu.getPage(pageID);
        }
        return null;
    }

    @Override
    public void onBodyItemClick(BodyMenuItem bodyItem) {
        configBodyItemClick(bodyItem);
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
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction()
                    .replace(mMenu.mFrameRes, MMFragment.newInstance(pageID))
                    .addToBackStack(null)
                    .commit();
        } else showPage(pageID);
    }

    public boolean updatePage(MMPage page){
        return mMenu.replacePage(page);
    }

    public boolean updatePage(MMPage page, boolean showPage){
        if (mMenu.replacePage(page) && showPage){
            showPage(page.getPageID(), true);
            return true;
        } else return false;
    }
}
