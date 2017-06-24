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

    private int mRootID;
    private MMPageBuilder mPageBuilder;

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

    @Override
    public FloatingActionButton onRequestFAB() {
        return mFAB;
    }

    public interface OnFragmentInteractionListener {
        MMPageBuilder onRequestPage(int pageID);
        IMenuItem onMenuItemClick(IMenuItem menuItem);
    }

    private void updateUI(){
        mPageBuilder = mListener.onRequestPage(mRootID);

        if (mPageBuilder == null){
            throw new RuntimeException("PageBuilder is null!");
        } else {
            // Set title
            if (mPageBuilder.getPageTitle() != null) {
                getActivity().setTitle(mPageBuilder.getPageTitle());
            }

            // Configure FAB
            mFAB = mPageBuilder.buildFAB(mFAB);
            /*if (mPageBuilder.isFABEnabled()) {
                mFAB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPageBuilder;
                    }
                });
            } else mFAB.setVisibility(View.GONE);*/

            // Configure recycler
            mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            if (mPageBuilder.isFABEnabled()) {
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

            // TODO SETUP RECYCLER ADAPTER
            mRecycler.setAdapter(new MenuFragmentRecyclerAdapter(getContext(), mPageBuilder.build(), mListener));
        }
    }
}
