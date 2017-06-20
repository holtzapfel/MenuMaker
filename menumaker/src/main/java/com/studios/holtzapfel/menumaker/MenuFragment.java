package com.studios.holtzapfel.menumaker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.studios.holtzapfel.menumaker.adapters.MenuFragmentRecyclerAdapter;
import com.studios.holtzapfel.menumaker.model.interfaces.IMenuItem;

import java.util.List;

public class MenuFragment extends Fragment {

    private static final String ARG_ROOT_ID = "ARG_ROOT_ID";

    private int mRootID;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecycler;
    private FloatingActionButton mFAB;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(int rootID) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ROOT_ID, rootID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRootID = getArguments().getInt(ARG_ROOT_ID);
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

    public interface OnFragmentInteractionListener {
        String getTitle(int rootID);
        boolean isFABEnabled(int rootID);
        void onFABClick(int rootID);
        List<IMenuItem> onRequestMenuItems(int rootID);
        IMenuItem onMenuItemClick(IMenuItem menuItem);
    }

    private void updateUI(){
        // Set title
        if (mListener.getTitle(mRootID) != null){
            getActivity().setTitle(mListener.getTitle(mRootID));
        }

        // Configure FAB
        if (mListener.isFABEnabled(mRootID)){
            mFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onFABClick(mRootID);
                }
            });
        } else mFAB.setVisibility(View.GONE);

        // Retrieve menu items
        List<IMenuItem> menuItems = mListener.onRequestMenuItems(mRootID);

        // Configure recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    mFAB.hide();
                } else mFAB.show();
            }
        });
        // TODO SETUP RECYCLER ADAPTER
        mRecycler.setAdapter(new MenuFragmentRecyclerAdapter(menuItems, mListener));
    }
}
