package com.adamacdo.recyclerviewinsandouts;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.adamacdo.recyclerviewinsandouts.constants.FragmentTags;
import com.adamacdo.recyclerviewinsandouts.fragments.AnimatingRecyclerView1Fragment;
import com.adamacdo.recyclerviewinsandouts.fragments.AnimatingRecyclerView2Fragment;
import com.adamacdo.recyclerviewinsandouts.fragments.MultipleListItemTypesRecyclerViewFragment;
import com.adamacdo.recyclerviewinsandouts.fragments.StandardRecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.toolbar_title);
        }

        showStandardFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_standard_recycler_view:{
                showStandardFragment();
                return true;
            }
            case R.id.menu_multi_item_types_recycler_view:{
                showMultiItemTypesFragment();
                return true;
            }
            case R.id.menu_animating_recycler_view_1:{
                showAnimating1Fragment();
                return true;
            }
            case R.id.menu_animating_recycler_view_2:{
                showAnimating2Fragment();
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }

    // -------------------------------------------------------------------------------------------------------------
    // Convenience Methods for Showing Fragments
    // -------------------------------------------------------------------------------------------------------------
    private void showStandardFragment() {
        if (getSupportFragmentManager().findFragmentByTag(FragmentTags.STANDARD_RECYCLER_VIEW_FRAGMENT) != null) {
            getSupportFragmentManager().popBackStack(FragmentTags.STANDARD_RECYCLER_VIEW_FRAGMENT, 0);

        } else {
            replaceFragment(StandardRecyclerViewFragment.newInstance(), FragmentTags.STANDARD_RECYCLER_VIEW_FRAGMENT);
        }
    }

    private void showMultiItemTypesFragment() {
        if (getSupportFragmentManager().findFragmentByTag(FragmentTags.MULTI_ITEM_TYPES_RECYCLER_VIEW_FRAGMENT) != null) {
            getSupportFragmentManager().popBackStack(FragmentTags.MULTI_ITEM_TYPES_RECYCLER_VIEW_FRAGMENT, 0);

        } else {
            replaceFragment(MultipleListItemTypesRecyclerViewFragment.newInstance(), FragmentTags.MULTI_ITEM_TYPES_RECYCLER_VIEW_FRAGMENT);
        }
    }

    private void showAnimating1Fragment() {
        if (getSupportFragmentManager().findFragmentByTag(FragmentTags.ANIMATING_RECYCLER_VIEW_1_FRAGMENT) != null) {
            getSupportFragmentManager().popBackStack(FragmentTags.ANIMATING_RECYCLER_VIEW_1_FRAGMENT, 0);

        } else {
            replaceFragment(AnimatingRecyclerView1Fragment.newInstance(), FragmentTags.ANIMATING_RECYCLER_VIEW_1_FRAGMENT);
        }
    }

    private void showAnimating2Fragment() {
        if (getSupportFragmentManager().findFragmentByTag(FragmentTags.ANIMATING_RECYCLER_VIEW_2_FRAGMENT) != null) {
            getSupportFragmentManager().popBackStack(FragmentTags.ANIMATING_RECYCLER_VIEW_2_FRAGMENT, 0);

        } else {
            replaceFragment(AnimatingRecyclerView2Fragment.newInstance(), FragmentTags.ANIMATING_RECYCLER_VIEW_2_FRAGMENT);
        }
    }

    private void replaceFragment(Fragment fragment, String fragmentTag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(fragmentTag);
        transaction.setCustomAnimations(android.R.animator.fade_in,
                                        android.R.animator.fade_out,
                                        android.R.animator.fade_in,
                                        android.R.animator.fade_out);
        transaction.replace(R.id.main_container_layout, fragment, fragmentTag);
        transaction.commit();
    }
    // -------------------------------------------------------------------------------------------------------------
}
