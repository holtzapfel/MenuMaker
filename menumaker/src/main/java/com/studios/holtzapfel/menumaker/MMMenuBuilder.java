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
    private List<MMPage> mPages;

    private int mInitialPageID = -1;

    public MMMenuBuilder(Context context, int fragmentFrameContainerResource){
        this.mContext = context;
        this.mFrameRes = fragmentFrameContainerResource;
    }

    public MMMenuBuilder addPages(MMPage... pages){
        if (pages != null) {
            mPages = new ArrayList<>();
            Collections.addAll(mPages, pages);
        }
        return this;
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

    public MMMenuBuilder setInitialPageID(int pageID){
        this.mInitialPageID = pageID;
        return this;
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
