package com.studios.holtzapfel.menumaker;

import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by holtzapfel on 6/24/17.
 */

public class MMMenuBuilder {

    private AppCompatActivity mActivity;

    /**
     * Default constructor
     */
    public MMMenuBuilder(){

    }

    /**
     * Constructor using activity that will contain frame resource
     *
     * @param activity - will be used for getting support fragment manager
     */
    public MMMenuBuilder(@NonNull AppCompatActivity activity){
        this.mActivity = activity;
    }

    // Frame layout resource for hosting MMFragment
    private int mFrameRes = -1;

    /**
     * Sets the FrameLayout resource to be used for hosting MMFragment
     *
     * @param frameLayoutRes - FrameLayout resource ID
     */
    public MMMenuBuilder withFrameLayout(@IdRes int frameLayoutRes){
        this.mFrameRes = frameLayoutRes;
        return this;
    }

    /**
     * Sets the FrameLayout resource to be used for hosting MMFragment
     *
     * @param frameLayout - FrameLayout view to be converted to resource ID
     */
    public MMMenuBuilder withFrameLayout(@NonNull FrameLayout frameLayout){
        withFrameLayout(frameLayout.getId());
        return this;
    }

    // List of MMPages that provide the menu's content
    private List<MMPage> mPages = new ArrayList<>();

    /**
     * Adds the pages to be used in the creation and display of the menu
     *
     * @param pages - MMPages that can be individually added
     */
    public MMMenuBuilder withPages(@NonNull MMPage... pages){
        Collections.addAll(this.mPages, pages);
        return this;
    }

    /**
     * Adds the pages to be used in the creation and display of the menu
     *
     * @param pages - List of MMPages
     */
    public MMMenuBuilder withPages(@NonNull List<MMPage> pages){
        this.mPages = pages;
        return this;
    }

    // Stores MMPage ID of page to first display
    private int mInitialPageID = -1;

    /**
     * Sets which page will be displayed first when menu is started
     *
     * @param pageID - ID of MMPage to first be displayed
     */
    public MMMenuBuilder withInitialPageID(int pageID){
        this.mInitialPageID = pageID;
        return this;
    }

    // Color resources
    // NOTE: Any resource set here will be overridden if also set in MMPage or IMenuItem
    private int mHeaderTitleTextColorRes = -1;
    private int mBodyTitleTextColorRes = -1;
    private int mIconColorRes = -1;
    private int mIconRightColorRes = -1;
    private int mIconLeftColorRes = -1;

    /**
     * Sets color resource for all HeaderMenuItems title text
     *
     * @param colorRes - Color resource ID
     */
    public MMMenuBuilder withHeaderTitleTextColor(@ColorRes int colorRes){
        this.mHeaderTitleTextColorRes = colorRes;
        return this;
    }

    /**
     * Sets color resource for all BodyMenuItems title text
     *
     * @param colorRes - Color resource ID
     */
    public MMMenuBuilder withBodyTitleTextColor(int colorRes){
        this.mBodyTitleTextColorRes = colorRes;
        return this;
    }

    /**
     * Sets color resource for all left and right icons if used
     *
     * @param colorRes - Color resource ID
     */
    public MMMenuBuilder withIconColor(int colorRes){
        this.mIconColorRes = colorRes;
        return this;
    }

    /**
     * Sets color resource for all right icons if used
     * NOTE: This will override @withIconColor (if used) for right icons
     *
     * @param colorRes - Color resource ID
     */
    public MMMenuBuilder withIconRightColor(int colorRes){
        this.mIconRightColorRes = colorRes;
        return this;
    }

    /**
     * Sets color resource for all left icons if used
     * NOTE: This will override @withIconColor (if used) for left icons
     *
     * @param colorRes - Color resource ID
     */
    public MMMenuBuilder withIconLeftColor(int colorRes){
        this.mIconLeftColorRes = colorRes;
        return this;
    }

    // Default preference of option to use sliding animation with fragment transitions
    private boolean useSlidingAnimation = false;

    /**
     * Sets whether or not fragments use sliding animation in transitions
     *
     * @param withSlidingAnimation - use sliding animation in fragment transitions
     */
    public MMMenuBuilder withSlidingAnimation(boolean withSlidingAnimation){
        this.useSlidingAnimation = withSlidingAnimation;
        return this;
    }

    // Interface object for item clicks
    private MMMenu.OnMenuItemClickListener mOnMenuItemClickListener;

    /**
     * Allows for programming what to if an item is clicked
     *
     * @param onMenuItemClickListener - will be called each time an item is clicked
     */
    public MMMenuBuilder withOnMenuItemClickListener(@NonNull MMMenu.OnMenuItemClickListener onMenuItemClickListener){
        this.mOnMenuItemClickListener = onMenuItemClickListener;
        return this;
    }

    /**
     * Prepares and uilds MMMenu object
     *
     * @return MMMenu object
     */
    public MMMenu build(){
        if (mActivity == null) {
            throw new RuntimeException("Must pass an Activity to builder!");
        }

        if (mFrameRes == -1){
            throw new RuntimeException("Must pass a valid FrameLayout resource!");
        }

        MMMenu menu = new MMMenu(mActivity);
        menu.setFrameRes(mFrameRes);
        menu.setPages(prepareAndGetPages());
        menu.setInitialPageID(getInitialPageID());
        menu.setUseSlidingAnimation(useSlidingAnimation);
        menu.setOnMenuItemClickListener(mOnMenuItemClickListener);
        return menu;
    }

    private int getInitialPageID(){
        if (mInitialPageID != -1){
            return mInitialPageID;
        }

        if (mPages != null){
            if (mPages.size() > 0){
                mInitialPageID = mPages.get(0).getPageID();
            }
        }

        return mInitialPageID;
    }

    private List<MMPage> prepareAndGetPages(){
        for (int x = 0; x < mPages.size(); x++){
            for (int y = 0; y < mPages.get(x).getMenuItems().size(); y++){
                IMenuItem item = mPages.get(x).getMenuItems().get(y);
                mPages.get(x).getMenuItems().remove(y);
                mPages.get(x).getMenuItems().add(y, updateItemWithCustomSettings(item));
            }
        }
        return mPages;
    }

    private IMenuItem updateItemWithCustomSettings(IMenuItem item){
        if (item instanceof HeaderMenuItem){
            return updateHeaderMenuItemWithCustomSettings((HeaderMenuItem) item);
        }

        if (item instanceof BodyMenuItem){
            return updateBodyMenuItemWithCustomSettings((BodyMenuItem) item);
        }

        return item;
    }

    private HeaderMenuItem updateHeaderMenuItemWithCustomSettings(HeaderMenuItem item){
        if (item.getTitleTextColorRes() == -1){
            item.withTitleTextColor(mHeaderTitleTextColorRes);
        }

        return item;
    }

    private BodyMenuItem updateBodyMenuItemWithCustomSettings(BodyMenuItem item){
        // Update title text color if not already set
        if (item.getTitleTextColorRes() == -1){
            item.withTitleTextColor(mBodyTitleTextColorRes);
        }

        // Update icon left color if not already set
        if (item.getIconLeftColorRes() == -1){
            // Set with left color res if user defined, otherwise use BL icon color
            if (mIconLeftColorRes != -1){
                item.withIconLeftColor(mIconLeftColorRes);
            } else item.withIconLeftColor(mIconColorRes);
        }

        // Update icon right color if not already set
        if (item.getIconRightColorRes() == -1){
            // Set with right color res if user defined, otherwise use BL icon color
            if (mIconRightColorRes != -1){
                item.withIconRightColor(mIconRightColorRes);
            } else item.withIconRightColor(mIconColorRes);
        }

        return item;
    }
}
