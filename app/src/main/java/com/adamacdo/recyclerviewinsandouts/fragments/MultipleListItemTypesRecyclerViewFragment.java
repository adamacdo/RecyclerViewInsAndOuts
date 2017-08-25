package com.adamacdo.recyclerviewinsandouts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adamacdo.recyclerviewinsandouts.R;
import com.adamacdo.recyclerviewinsandouts.adapaters.MasterListAdapter;
import com.adamacdo.recyclerviewinsandouts.managers.CatManager;
import com.adamacdo.recyclerviewinsandouts.models.CatFact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by adammacdonald on 7/6/17.
 */

public class MultipleListItemTypesRecyclerViewFragment extends BaseFragment implements
        MasterListAdapter.MasterListListener {

    private RecyclerView mRecyclerView;

    final List<MasterListAdapter.BaseMasterListItem> mListItems = Collections.synchronizedList(new ArrayList<MasterListAdapter.BaseMasterListItem>());
    private MasterListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public static MultipleListItemTypesRecyclerViewFragment newInstance() {
        Bundle args = new Bundle();

        MultipleListItemTypesRecyclerViewFragment fragment = new MultipleListItemTypesRecyclerViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_standard_recycler_view, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // Seed data from repository
        CatFact[] catFacts = CatManager.getCatFacts();
        for (int i = 0; i < catFacts.length; i++) {
            if (i % 2 == 0) {
                mListItems.add(new MasterListAdapter.CatFactListItem(catFacts[i]));
            } else {
                mListItems.add(new MasterListAdapter.AltCatFactListItem(catFacts[i]));
            }
        }

        // Create Adapter with Data
        mAdapter = new MasterListAdapter(mListItems, this);

        // Create Layout Manager to Dictate how List Items are Laid Out
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        // Set Adapter to RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

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
