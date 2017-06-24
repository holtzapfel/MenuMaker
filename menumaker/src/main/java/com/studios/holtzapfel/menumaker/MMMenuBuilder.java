package com.studios.holtzapfel.menumaker;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by holtzapfel on 6/24/17.
 */

public class MMMenuBuilder {

    Context mContext;
    int mFrameRes;
    List<MMPageBuilder> mPages;

    private int mInitialPageID = -1;

    public MMMenuBuilder(Context context, int fragmentFrameContainerResource){
        this.mContext = context;
        this.mFrameRes = fragmentFrameContainerResource;
    }

    public MMMenuBuilder addPages(MMPageBuilder... pages){
        if (pages != null) {
            mPages = new ArrayList<>();
            Collections.addAll(mPages, pages);
        }
        return this;
    }

    public MMMenuBuilder setInitialPageID(int pageID){
        this.mInitialPageID = pageID;
        return this;
    }

    public MMPageBuilder getPage(int pageID){
        MMPageBuilder pageBuilder = null;
        if (mPages != null){
            for (int x = 0; x < mPages.size(); x++){
                if (mPages.get(x).getPageID() == pageID){
                    pageBuilder = mPages.get(x);
                }
            }
        }
        return pageBuilder;
    }

    int getInitialPageID(){
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
}
