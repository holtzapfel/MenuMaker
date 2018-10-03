package com.studios.holtzapfel.menumaker;

import android.view.View;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.FooterMenuItem;
import com.studios.holtzapfel.menumaker.model.HeaderMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by holtzapfel on 6/21/17.
 */

@SuppressWarnings({"unused", "UnusedReturnValue", "WeakerAccess"})
public class MMPageBuilder {

    int mID = -1;

    public MMPageBuilder(int pageID){
        this.mID = pageID;
    }

    List<IMenuItem> mItems = new ArrayList<>();

    public MMPageBuilder withMenuItems(@NonNull IMenuItem... items){
        if (mItems.size() > 0){
            throw new RuntimeException("Menu items cannot be declared more than once!");
        }
        Collections.addAll(mItems, items);
        return this;
    }

    public MMPageBuilder withMenuItems(@NonNull List<IMenuItem> items){
        if (mItems.size() > 0){
            throw new RuntimeException("Menu items cannot be declared more than once!");
        }
        mItems = items;
        return this;
    }

    String mPageTitle;

    public MMPageBuilder withPageTitle(@Nullable String title){
        this.mPageTitle = title;
        return this;
    }

    boolean isFABEnabled = false;
    View.OnClickListener mFABOnClickListener;
    int mFABIconRes = -1;
    int mFABBackgroundColorRes = -1;

    public MMPageBuilder withFABEnabled(boolean isFABEnabled){
        this.isFABEnabled = isFABEnabled;
        return this;
    }

    public MMPageBuilder withFAB(@Nullable View.OnClickListener onClickListener){
        this.isFABEnabled = true;
        this.mFABOnClickListener = onClickListener;
        return this;
    }

    public MMPageBuilder withFAB(@DrawableRes int iconRes, View.OnClickListener onClickListener){
        withFAB(onClickListener);
        this.mFABIconRes = iconRes;
        return this;
    }

    public MMPageBuilder withFAB(@DrawableRes int iconRes, @ColorRes int backgroundColorRes, View
            .OnClickListener onClickListener){
        withFAB(iconRes, onClickListener);
        this.mFABBackgroundColorRes = backgroundColorRes;
        return this;
    }

    int mHeaderTitleTextColorRes = -1;

    public MMPageBuilder withHeaderTitleTextColor(@ColorRes int colorRes){
        this.mHeaderTitleTextColorRes = colorRes;
        return this;
    }

    int mBodyTitleTextColorRes = -1;

    public MMPageBuilder withBodyTitleTextColor(@ColorRes int colorRes){
        this.mBodyTitleTextColorRes = colorRes;
        return this;
    }

    int mBodyDescriptionTextColorRes = -1;

    public MMPageBuilder withBodyDescriptionTextColor(@ColorRes int colorRes){
        this.mBodyDescriptionTextColorRes = colorRes;
        return this;
    }

    int mBodyValueTextColorRes = -1;

    public MMPageBuilder withBodyValueTextColor(@ColorRes int colorRes){
        this.mBodyValueTextColorRes = colorRes;
        return this;
    }

    int mBodyContentTextColorRes = -1;

    public MMPageBuilder withBodyContentTextColor(@ColorRes int colorRes){
        this.mBodyDescriptionTextColorRes = colorRes;
        return this;
    }

    int mIconColorRes = -1;

    private float mHeaderTitleTextSize = -1;
    private int mHeaderTitleTextSizeUnit = -1;

    public MMPageBuilder withHeaderTitleTextSize(float size){
        this.mHeaderTitleTextSize = size;
        return this;
    }

    public MMPageBuilder withHeaderTitleTextSize(int unit, float size){
        this.mHeaderTitleTextSizeUnit = unit;
        this.mHeaderTitleTextSize = size;
        return this;
    }

    private float mBodyTitleTextSize = -1;
    private int mBodyTitleTextSizeUnit = -1;

    public MMPageBuilder withBodyTitleTextSize(float size){
        this.mBodyTitleTextSize = size;
        return this;
    }

    public MMPageBuilder withBodyTitleTextSize(int unit, float size){
        this.mBodyTitleTextSizeUnit = unit;
        this.mBodyTitleTextSize = size;
        return this;
    }

    private float mBodyDescriptionTextSize = -1;
    private int mBodyDescriptionTextSizeUnit = -1;

    public MMPageBuilder withBodyDescriptionTextSize(float size){
        this.mBodyDescriptionTextSize = size;
        return this;
    }

    public MMPageBuilder withBodyDescriptionTextSize(int unit, float size){
        this.mBodyDescriptionTextSizeUnit = unit;
        this.mBodyDescriptionTextSize = size;
        return this;
    }

    private float mBodyValueTextSize = -1;
    private int mBodyValueTextSizeUnit = -1;

    public MMPageBuilder withBodyValueTextSize(float size){
        this.mBodyValueTextSize = size;
        return this;
    }

    public MMPageBuilder withBodyValueTextSize(int unit, float size){
        this.mBodyValueTextSizeUnit = unit;
        this.mBodyValueTextSize = size;
        return this;
    }

    private float mBodyContentTextSize = -1;
    private int mBodyContentTextSizeUnit = -1;

    public MMPageBuilder withBodyContentTextSize(float size){
        this.mBodyContentTextSize = size;
        return this;
    }

    public MMPageBuilder withBodyContentTextSize(int unit, float size){
        this.mBodyContentTextSizeUnit = unit;
        this.mBodyContentTextSize = size;
        return this;
    }

    public MMPageBuilder withIconColor(@ColorRes int colorRes){
        this.mIconColorRes = colorRes;
        return this;
    }

    int mIconRightColorRes = -1;

    public MMPageBuilder withIconRightColor(@ColorRes int colorRes){
        this.mIconRightColorRes = colorRes;
        return this;
    }

    int mIconLeftColorRes = -1;

    public MMPageBuilder withIconLeftColor(@ColorRes int colorRes){
        this.mIconLeftColorRes = colorRes;
        return this;
    }

    boolean isDividersEnabled = true;

    public MMPageBuilder withDividersEnabled(boolean isEnabled){
        this.isDividersEnabled = isEnabled;
        return this;
    }

    int mDividerColorRes = -1;

    public MMPageBuilder withDividerColor(@ColorRes int colorRes){
        this.mDividerColorRes = colorRes;
        return this;
    }

    private IMenuItem prepareMenuItem(IMenuItem item, IMenuItem nextItem){
        if (nextItem != null){
            if (nextItem instanceof FooterMenuItem){
                item.withLastItem(true);
            } else item.withLastItem(false);
        }

        switch (item.getMenuType()){
            case IMenuItem.MENU_ITEM_TYPE_HEADER:
                HeaderMenuItem headerMenuItem = (HeaderMenuItem) item;

                // Update title text color if not already set
                if (mHeaderTitleTextColorRes != -1){
                    if (headerMenuItem.getTitleTextColorRes() == -1){
                        headerMenuItem.withTitleTextColor(mHeaderTitleTextColorRes);
                    }
                }

                // Update title text size if not already not set
                if (mHeaderTitleTextSize != -1){
                    if (headerMenuItem.getTitleTextSize() == -1){
                        if (mHeaderTitleTextSizeUnit != -1){
                            headerMenuItem.withTitleTextSize(mHeaderTitleTextSizeUnit, mHeaderTitleTextSize);
                        } else headerMenuItem.withTitleTextSize(mHeaderTitleTextSize);
                    }
                }

                return headerMenuItem;
            case IMenuItem.MENU_ITEM_TYPE_BODY:
                BodyMenuItem bodyMenuItem = (BodyMenuItem) item;

                // Update title text color if not already set
                if (bodyMenuItem.getTitleTextColorRes() == -1){
                    bodyMenuItem.withTitleTextColor(mBodyTitleTextColorRes);
                }

                // Update title text size if not already set
                if (mBodyTitleTextSize != -1){
                    if (bodyMenuItem.getTitleTextSize() == -1){
                        if (mBodyTitleTextSizeUnit != -1){
                            bodyMenuItem.withTitleTextSize(mBodyTitleTextSizeUnit, mBodyTitleTextSize);
                        } else bodyMenuItem.withTitleTextSize(mBodyTitleTextSize);
                    }
                }

                // Update description text color if not already set
                if (bodyMenuItem.getDescriptionTextColorRes() == -1){
                    bodyMenuItem.withDescriptionTextColor(mBodyDescriptionTextColorRes);
                }

                // Update description text size if not already set
                if (mBodyDescriptionTextSize != -1){
                    if (bodyMenuItem.getDescriptionTextSize() == -1){
                        if (mBodyDescriptionTextSizeUnit != -1){
                            bodyMenuItem.withDescriptionTextSize(mBodyDescriptionTextSizeUnit, mBodyDescriptionTextSize);
                        } else bodyMenuItem.withDescriptionTextSize(mBodyDescriptionTextSize);
                    }
                }

                // Update value text color if not already set
                if (bodyMenuItem.getValueTextColorRes() == -1){
                    bodyMenuItem.withValueTextColor(mBodyValueTextColorRes);
                }

                // Update value text size if not already set
                if (mBodyValueTextSize != -1){
                    if (bodyMenuItem.getValueTextSize() == -1){
                        if (mBodyValueTextSizeUnit != -1){
                            bodyMenuItem.withValueTextSize(mBodyValueTextSizeUnit, mBodyValueTextSize);
                        } else bodyMenuItem.withValueTextSize(mBodyValueTextSize);
                    }
                }

                // Update content text color if not already set
                if (bodyMenuItem.getContentTextColorRes() == -1){
                    bodyMenuItem.withContentTextColor(mBodyContentTextColorRes);
                }

                // Update content text size if not already set
                if (mBodyContentTextSize != -1){
                    if (bodyMenuItem.getContentTextSize() == -1){
                        if (mBodyContentTextSizeUnit != -1){
                            bodyMenuItem.withContentTextSize(mBodyContentTextSizeUnit, mBodyContentTextSize);
                        } else bodyMenuItem.withContentTextSize(mBodyContentTextSize);
                    }
                }

                // Update icon left color if not already set
                if (bodyMenuItem.getIconLeftColorRes() == -1) {
                    if (mIconLeftColorRes != -1) {
                        bodyMenuItem.withIconLeftColor(mIconLeftColorRes);
                    } else bodyMenuItem.withIconLeftColor(mIconColorRes);
                }

                // Update icon right color if not already set
                if (bodyMenuItem.getIconRightColorRes() == -1){
                    if (mIconRightColorRes != -1){
                        bodyMenuItem.withIconRightColor(mIconRightColorRes);
                    } else bodyMenuItem.withIconRightColor(mIconColorRes);
                }

                // Update divider color if not already set
                bodyMenuItem.withDividerEnabled(isDividersEnabled);
                if (mDividerColorRes != -1){
                    if (bodyMenuItem.getDividerColorRes() == -1) {
                        bodyMenuItem.withDividerColor(mDividerColorRes);
                    }
                }

                return bodyMenuItem;
        }

        return item;
    }

    public MMPage build(){
        for (int x = 0; x < mItems.size(); x++){
            IMenuItem item = mItems.get(x);
            IMenuItem nextItem = null;
            if ((x + 1) != mItems.size()){
                nextItem = mItems.get(x + 1);
            }
            mItems.remove(x);
            item = prepareMenuItem(item, nextItem);
            mItems.add(x, item);
        }

        return new MMPage(this);
    }
}
