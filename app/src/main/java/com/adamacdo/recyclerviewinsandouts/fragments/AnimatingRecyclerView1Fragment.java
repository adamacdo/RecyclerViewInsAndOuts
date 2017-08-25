package com.adamacdo.recyclerviewinsandouts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.adamacdo.recyclerviewinsandouts.R;
import com.adamacdo.recyclerviewinsandouts.adapaters.MasterListAdapter;
import com.adamacdo.recyclerviewinsandouts.managers.CatManager;
import com.adamacdo.recyclerviewinsandouts.models.CatFact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by adammacdonald on 7/6/17.
 */

public class AnimatingRecyclerView1Fragment extends BaseFragment implements
        MasterListAdapter.MasterListListener {

    private RecyclerView mRecyclerView;
    private Button mAddButton;
    private Button mRemoveButton;

    final List<MasterListAdapter.BaseMasterListItem> mListItems = Collections.synchronizedList(new ArrayList<MasterListAdapter.BaseMasterListItem>());
    private MasterListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public static AnimatingRecyclerView1Fragment newInstance() {
        Bundle args = new Bundle();

        AnimatingRecyclerView1Fragment fragment = new AnimatingRecyclerView1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animating_recycler_view, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAddButton = (Button) view.findViewById(R.id.add_button);
        mRemoveButton = (Button) view.findViewById(R.id.remove_button);

        // Seed data from repository
        for (CatFact catFact : CatManager.getCatFacts()) {
            mListItems.add(new MasterListAdapter.CatFactListItem(catFact));
        }

        // Create Adapter with Data
        mAdapter = new MasterListAdapter(mListItems, this);

        // Create Animator
        SlideInLeftAnimator animator = new SlideInLeftAnimator(new AccelerateDecelerateInterpolator());
        animator.setChangeDuration(350);
        animator.setMoveDuration(350);
        animator.setAddDuration(350);
        animator.setRemoveDuration(350);
        mRecyclerView.setItemAnimator(animator);

        // Create Layout Manager to Dictate how List Items are Laid Out
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        // Set Adapter to RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListItems.add(1, new MasterListAdapter.CatFactListItem(CatManager.getCatFact()));
                mAdapter.notifyItemInserted(1);
            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListItems.remove(0);
                mAdapter.notifyItemRemoved(0);
            }
        });

        return view;
    }

    // -------------------------------------------------------------------------------------------------------------
    // MasterListAdapter.MasterListListener Methods
    // -------------------------------------------------------------------------------------------------------------
    @Override
    public void onCatFactListItemClicked(CatFact catFact) {
        Toast.makeText(
                getActivity(),
                "Touched " + getString(catFact.getTitleResId()),
                Toast.LENGTH_SHORT
        ).show();
    }
    // -------------------------------------------------------------------------------------------------------------
}
