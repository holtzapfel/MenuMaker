package com.studios.holtzapfel.menumaker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.adapters.MenuFragmentRecyclerAdapter;

public class MMFragment extends Fragment{

    private static final String TAG = "MMFragment";

    private static final String ARG_PAGE_ID = "ARG_PAGE_ID";

    private int mPageID;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecycler;
    private FloatingActionButton mFAB;

    public MMFragment() {
        // Required empty public constructor
    }

    public static MMFragment newInstance(int pageID) {
        MMFragment fragment = new MMFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_ID, pageID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageID = getArguments().getInt(ARG_PAGE_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        // Associate views
        mRecycler = (RecyclerView) v.findViewById(R.id.menu_maker_menu_fragment_recycler);
        mFAB = (FloatingActionButton) v.findViewById(R.id.menu_maker_menu_fragment_fab);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(mPageID);
    }

    interface OnFragmentInteractionListener {
        MMMenu onRetrieveMenu();
        void onNotifyCurrentPageID(int pageID);
    }

    public void updateUI(int pageID){
        MMMenu menu = mListener.onRetrieveMenu();

        if (menu == null){
            throw new RuntimeException("MMMenu is null!");
        }

        MMPage page = menu.getPage(pageID);

        if (page == null){
            throw new RuntimeException("MMPage is null!");
        }

        // Set title
        if (page.getPageTitle() != null) {
            getActivity().setTitle(page.getPageTitle());
        }

        // Configure FAB
        if (page.isFABEnabled()) {
            mFAB.setVisibility(View.VISIBLE);
            if (page.getFABOnClickListener() != null) {
                mFAB.setOnClickListener(page.getFABOnClickListener());
            }
        } else mFAB.setVisibility(View.GONE);

        // Configure recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        if (page.isFABEnabled()) {
            mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        mFAB.hide();
                    } else mFAB.show();
                }
            });
        }

        mRecycler.setAdapter(new MenuFragmentRecyclerAdapter(getContext(), page.getMenuItems(), menu.onRequestMenuItemClickListener()));

        mListener.onNotifyCurrentPageID(mPageID);
    }

    int getCurrentPageID(){
        return mPageID;
    }

    /*public void updateUI(int pageID){


        MMPage page = null;

        if (mListener != null) {
            page = mListener.getPage(pageID);
        } else {
            Log.d(TAG, "updateUI: mListener == null");
            getActivity().recreate();
        }

        if (page == null){
            throw new RuntimeException("MMPage is null!");
        } else {
            // Set title
            if (page.getPageTitle() != null) {
                getActivity().setTitle(page.getPageTitle());
            }

            // Configure FAB
            if (page.isFABEnabled()) {
                mFAB.setVisibility(View.VISIBLE);
                if (page.getFABOnClickListener() != null) {
                    mFAB.setOnClickListener(page.getFABOnClickListener());
                }
            } else mFAB.setVisibility(View.GONE);

            // Configure recycler
            mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            if (page.isFABEnabled()) {
                mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (dy > 0) {
                            mFAB.hide();
                        } else mFAB.show();
                    }
                });
            }

            mRecycler.setAdapter(new MenuFragmentRecyclerAdapter(getContext(), page.getMenuItems(), mListener.onRequestMenuItemClickListener()));

            mListener.onNotifyCurrentPageID(mPageID);
        }
    }*/
}
