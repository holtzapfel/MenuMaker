package com.studios.holtzapfel.menumaker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.adapters.MenuFragmentRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MMFragment extends Fragment{

    private static final String ARG_PAGE_ID = "ARG_PAGE_ID";

    private int mPageID;

    private OnFragmentInteractionListener mListener;

    @BindView(R2.id.menu_maker_menu_fragment_recycler) RecyclerView mRecycler;
    @BindView(R2.id.menu_maker_menu_fragment_fab) FloatingActionButton mFAB;

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
        ButterKnife.bind(this, v);

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
        // Retrieve menu; throw exception if null
        MMMenu menu = mListener.onRetrieveMenu();
        if (menu == null){
            throw new RuntimeException("MMMenu is null!");
        }

        // Retrieve page; throw exception if null
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
            // Make visible
            mFAB.setVisibility(View.VISIBLE);

            // Set image
            if (page.getFABIconRes() != -1) {
                mFAB.setImageDrawable(ResourcesCompat.getDrawable(menu.getContext().getResources(), page.getFABIconRes(), menu.getContext().getTheme()));
            }

            // Set background color
            if (page.getFABBackgroundColorRes() != -1) {
                mFAB.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(menu.getContext().getResources(), page.getFABBackgroundColorRes(), menu.getContext().getTheme())));
            }

            // Set click listener
            mFAB.setOnClickListener(page.getFABOnClickListener());
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

        mRecycler.setAdapter(new MenuFragmentRecyclerAdapter(menu.getContext(), page.getMenuItems(), menu.onRequestMenuItemClickListener()));

        mListener.onNotifyCurrentPageID(mPageID);
    }
}
