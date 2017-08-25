package com.adamacdo.recyclerviewinsandouts.adapaters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adamacdo.recyclerviewinsandouts.R;
import com.adamacdo.recyclerviewinsandouts.models.CatFact;

import java.util.List;

/**
 * Created by adammacdonald on 6/25/17.
 */

public class MasterListAdapter extends RecyclerView.Adapter {

    private static final int ITEM_TYPE_CAT_FACT = 0;
    private static final int ITEM_TYPE_ALT_CAT_FACT = 1;

    private final List<BaseMasterListItem> mListItems;

    private final MasterListListener mMasterListListener;

    public MasterListAdapter(
            List<BaseMasterListItem> listItems,
            MasterListListener masterListListener) {

        mListItems = listItems;
        mMasterListListener = masterListListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_TYPE_CAT_FACT: {
                View view = inflater.inflate(R.layout.list_item_cat_fact, parent, false);
                return new CatFactHolder(view);
            }
            case ITEM_TYPE_ALT_CAT_FACT: {
                View view = inflater.inflate(R.layout.list_item_alt_cat_fact, parent, false);
                return new AltCatFactHolder(view);
            }
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMasterListItem listItem = mListItems.get(position);

        if (holder != null && listItem != null) {
            if (holder.getClass().equals(CatFactHolder.class) && listItem.getClass().equals(CatFactListItem.class)) {
                ((CatFactHolder) holder).bindItem((CatFactListItem) listItem);

            } else if (holder.getClass().equals(AltCatFactHolder.class) && listItem.getClass().equals(AltCatFactListItem.class)) {
                ((AltCatFactHolder) holder).bindItem((AltCatFactListItem) listItem);

            }
        }
    }

    @Override
    public int getItemCount() {
        return mListItems == null ? 0 : mListItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        BaseMasterListItem item = mListItems.get(position);

        if (item.getClass().equals(CatFactListItem.class)) {
            return ITEM_TYPE_CAT_FACT;

        } else if (item.getClass().equals(AltCatFactListItem.class)) {
            return ITEM_TYPE_ALT_CAT_FACT;

        }

        return -1;
    }


    // -------------------------------------------------------------------------------------------------------------
    // Class Methods
    // -------------------------------------------------------------------------------------------------------------
    public void clearList() {
        int size = mListItems.size();
        mListItems.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addItem(MasterListAdapter.BaseMasterListItem item, int position) {
        mListItems.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(MasterListAdapter.BaseMasterListItem item, int position) {
        if (mListItems.contains(item)) {
            mListItems.remove(position);
            notifyItemRemoved(position);
        }
    }
    // -------------------------------------------------------------------------------------------------------------


    // -------------------------------------------------------------------------------------------------------------
    // ListItem classes
    // -------------------------------------------------------------------------------------------------------------
    public abstract static class BaseMasterListItem {
    }

    private abstract static class SimpleBaseMasterListItem<T> extends BaseMasterListItem {

        private T mItem;

        SimpleBaseMasterListItem(T item) {
            this.mItem = item;
        }

        T getItem() {
            return mItem;
        }

        public void setItem(T item) {
            this.mItem = item;
        }
    }

    public static class CatFactListItem extends SimpleBaseMasterListItem<CatFact> {
        public CatFactListItem(CatFact item) {
            super(item);
        }
    }

    public static class AltCatFactListItem extends SimpleBaseMasterListItem<CatFact> {
        public AltCatFactListItem(CatFact item) {
            super(item);
        }
    }
    // -------------------------------------------------------------------------------------------------------------


    // -------------------------------------------------------------------------------------------------------------
    // ViewHolder classes
    // -------------------------------------------------------------------------------------------------------------
    private class CatFactHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private CatFactListItem mListItem;

        ImageView mCatImageView;
        TextView mTitleTextView;
        TextView mDescriptionTextView;

        CatFactHolder(View itemView) {
            super(itemView);

            mCatImageView = (ImageView) itemView.findViewById(R.id.list_item_cat_image_view);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_title_text_view);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_description_text_view);

            itemView.setOnClickListener(this);
        }

        void bindItem(CatFactListItem item) {
            mListItem = item;

            if (mListItem == null || mListItem.getItem() == null) return;

            CatFact catFact = mListItem.getItem();

            if (catFact.getCatResId() > 0) {
                mCatImageView.setImageResource(catFact.getCatResId());
            } else {
                mCatImageView.setImageResource(-1);
            }

            if (catFact.getTitleResId() > 0) {
                mTitleTextView.setText(catFact.getTitleResId());
            } else {
                mTitleTextView.setText("");
            }

            if (catFact.getDescriptionResId() > 0) {
                mDescriptionTextView.setText(catFact.getDescriptionResId());
            } else {
                mDescriptionTextView.setText("");
            }
        }

        // -------------------------------------------------------------------------------------------------------------
        // View.OnClickListener Methods
        // -------------------------------------------------------------------------------------------------------------
        @Override
        public void onClick(View v) {
            if (mListItem != null && mMasterListListener != null) {
                mMasterListListener.onCatFactListItemClicked(mListItem.getItem());
            }
        }
        // -------------------------------------------------------------------------------------------------------------
    }

    private class AltCatFactHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private AltCatFactListItem mListItem;

        ImageView mCatImageView;
        TextView mTitleTextView;
        TextView mDescriptionTextView;

        AltCatFactHolder(View itemView) {
            super(itemView);

            mCatImageView = (ImageView) itemView.findViewById(R.id.list_item_cat_image_view);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_title_text_view);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_description_text_view);

            itemView.setOnClickListener(this);
        }

        void bindItem(AltCatFactListItem item) {
            mListItem = item;

            if (mListItem == null || mListItem.getItem() == null) return;

            CatFact catFact = mListItem.getItem();

            if (catFact.getCatResId() > 0) {
                mCatImageView.setImageResource(catFact.getCatResId());
            } else {
                mCatImageView.setImageResource(-1);
            }

            if (catFact.getTitleResId() > 0) {
                mTitleTextView.setText(catFact.getTitleResId());
            } else {
                mTitleTextView.setText("");
            }

            if (catFact.getDescriptionResId() > 0) {
                mDescriptionTextView.setText(catFact.getDescriptionResId());
            } else {
                mDescriptionTextView.setText("");
            }
        }

        // -------------------------------------------------------------------------------------------------------------
        // View.OnClickListener Methods
        // -------------------------------------------------------------------------------------------------------------
        @Override
        public void onClick(View v) {
            if (mListItem != null && mMasterListListener != null) {
                mMasterListListener.onCatFactListItemClicked(mListItem.getItem());
            }
        }
        // -------------------------------------------------------------------------------------------------------------
    }
    // -------------------------------------------------------------------------------------------------------------


    // -------------------------------------------------------------------------------------------------------------
    // Listener classes
    // -------------------------------------------------------------------------------------------------------------
    public interface MasterListListener {
        void onCatFactListItemClicked(CatFact catFact);
    }
    // -------------------------------------------------------------------------------------------------------------

}
