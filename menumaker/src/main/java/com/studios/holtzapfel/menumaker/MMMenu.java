package com.studios.holtzapfel.menumaker;

import android.content.Context;

import com.studios.holtzapfel.menumaker.model.BodyMenuItem;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

/**
 * Created by holtzapfel on 6/28/17.
 */

public class MMMenu {

    private int mFrameRes;
    private List<MMPage> mPages;
    private int mInitialPageID;
    private int mCurrentPageID;
    private boolean useSlidingAnimation = false;
    private OnMMMenuInteractionListener mListener;

    public MMMenu(Context context){
        mListener = (OnMMMenuInteractionListener) context;
    }

    interface OnMMMenuInteractionListener{
        void showPage(int frameRes, int pageID, boolean withSlidingAnimation);
        void refreshPage(int frameRes, int pageID);
        int requestCurrentID();
    }

    public void setFrameRes(int frameRes){
        this.mFrameRes = frameRes;
    }

    public int getFrameRes(){
        return mFrameRes;
    }

    public void setPages(List<MMPage> pages){
        this.mPages = pages;
    }

    public List<MMPage> getPages(){
        return mPages;
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

    public void setInitialPageID(int pageID){
        this.mInitialPageID = pageID;
        this.mCurrentPageID = pageID;
    }

    public int getInitialPageID(){
        return mInitialPageID;
    }

    public void setUseSlidingAnimation(boolean withSlidingAnimation){
        this.useSlidingAnimation = withSlidingAnimation;
    }

    public boolean getUseSlidingAnimation(){
        return this.useSlidingAnimation;
    }

    public void setCurrentPageID(int pageID){
        this.mCurrentPageID = pageID;
    }

    public int getCurrentPageID(){
        return mCurrentPageID;
    }

    public void showPage(int pageID){
        this.mCurrentPageID = pageID;
        mListener.showPage(mFrameRes, pageID, useSlidingAnimation);
    }

    public void showPage(int pageID, boolean withSlidingAnimation){
        this.mCurrentPageID = pageID;
        mListener.showPage(mFrameRes, pageID, withSlidingAnimation);
    }

    public void refreshPage(){
        this.mCurrentPageID = mListener.requestCurrentID();
        mListener.refreshPage(mFrameRes, mCurrentPageID);
    }

    public void updatePage(MMPage page, boolean showPage){
        replacePage(page);

        if (showPage){
            if (page.getPageID() == mCurrentPageID){
                refreshPage();
            } else showPage(page.getPageID());
        }
    }

    public boolean updateItem(IMenuItem item, boolean refreshPage){
        if (item instanceof BodyMenuItem){
            return updateBodyMenuItem((BodyMenuItem) item, refreshPage);
        }

        return false;
    }

    private boolean updateBodyMenuItem(BodyMenuItem item, boolean refreshPage){
        boolean isReplaced = false;
        for (int x = 0; x < mPages.size(); x++){
            MMPage page = mPages.get(x);
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


}
