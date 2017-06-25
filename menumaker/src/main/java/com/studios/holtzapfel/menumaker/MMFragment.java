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
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

public class MMFragment extends Fragment implements MMPageBuilder.OnPageBuilderListener{

    private static final String ARG_ROOT_ID = "ARG_ROOT_ID";

    private int mPageID;
    private MMPage mPage;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecycler;
    private FloatingActionButton mFAB;

    public MMFragment() {
        // Required empty public constructor
    }

    public static MMFragment newInstance(int rootID) {
        MMFragment fragment = new MMFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ROOT_ID, rootID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageID = getArguments().getInt(ARG_ROOT_ID);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public FloatingActionButton onRequestFAB() {
        return mFAB;
    }

    public interface OnFragmentInteractionListener {
        MMPage onRequestPage(int pageID);
        IMenuItem onMenuItemClick(IMenuItem menuItem);
    }

    private void updateUI(){
        mPage = mListener.onRequestPage(mPageID);

        if (mPage == null){
            throw new RuntimeException("PageBuilder is null!");
        } else {
            // Set title
            if (mPage.getPageTitle() != null) {
                getActivity().setTitle(mPage.getPageTitle());
            }

            // Configure FAB
            if (mPage.isFABEnabled()) {
                mFAB.setVisibility(View.VISIBLE);
                if (mPage.getFABOnClickListener() != null) {
                    mFAB.setOnClickListener(mPage.getFABOnClickListener());
                }
            } else mFAB.setVisibility(View.GONE);

            // Configure recycler
            mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            if (mPage.isFABEnabled()) {
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

            mRecycler.setAdapter(new MenuFragmentRecyclerAdapter(getContext(), mPage.getMenuItems(), mListener));
        }
    }
}
