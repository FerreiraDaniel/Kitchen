package com.dferreira.kitchen.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.toolbox.ImageLoader;
import com.dferreira.kitchen.R;
import com.dferreira.kitchen.model.ListItem;
import com.dferreira.kitchen.presenter.network_layer.NetworkRequestsSingleton;

import java.util.List;

/**
 * Adapter with generic stuff that should be common to all the recycle view adapters
 */

public abstract class GenericRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> implements View.OnClickListener {

    protected Activity activity;
    private List<ListItem> dataSet;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    /**
     * Constructor to the view adapter
     *
     * @param activity      Reference to the context where the adapter will be used
     * @param recyclerView Reference to the recycle view to use
     * @param progressBar  Reference to the progress bar that indicates to the user that is using data
     */
    public GenericRecyclerViewAdapter(@NonNull Activity activity,
                                      @NonNull RecyclerView recyclerView,
                                      @NonNull ProgressBar progressBar
    ) {
        this.activity = activity;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receipt_list_item, parent, false);
        // set the view's size, margins, padding and layout parameters
        v.setOnClickListener(this);
        ListItemViewHolder vh = new ListItemViewHolder(v);
        return vh;
    }


    /**
     * Replace the contents of a view (invoked by the layout manager)
     *
     * @param holder    The holder of the item of the recycle view
     * @param position The position of the the item
     */
    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        if ((dataSet != null) && (position < dataSet.size())) {
            ListItem item = dataSet.get(position);

            if(!TextUtils.isEmpty(item.imageUrl)) {
                ImageLoader imageLoader = NetworkRequestsSingleton.getInstance(activity.getApplicationContext()).getImageLoader();
                holder.thumbnail.setImageUrl(item.imageUrl, imageLoader);
            }
            holder.title.setText(item.title);
            holder.description.setText(item.description);
        }
    }


    /**
     * If during the measurement of the content has zero elements is going to show the spinner
     * instead of the list
     *
     * @return The number of items that has
     */
    @Override
    public int getItemCount() {
        if ((dataSet == null) || (dataSet.isEmpty())) {
            this.progressBar.setVisibility(View.VISIBLE);
            this.recyclerView.setVisibility(View.INVISIBLE);
            return 0;
        } else {
            this.progressBar.setVisibility(View.GONE);
            this.recyclerView.setVisibility(View.VISIBLE);
            return dataSet.size();
        }
    }


    /**
     * Find the item that is supported by a certain view
     *
     * @param v view that is going to support the item
     * @return item that match the view
     */
    protected ListItem getItemByView(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        ListItem item = dataSet.get(itemPosition);
        return item;
    }

    /**
     * @param dataSet The reference to the new data set
     */
    public void setDataSet(List<ListItem> dataSet) {
        this.dataSet = dataSet;
    }





    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    @Override
    public abstract void onClick(View v);


    /**
     * Should be used by the activity to indicate that is able to receive content
     */
    public abstract void startLoading();

    /**
     * Should be used by the activity to indicate that is able no able to receive content
     * anymore
     */
    public abstract void stopLoading();

}
