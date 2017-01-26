package com.dferreira.kitchen.view;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

/**
 * Show be extends by all the activities made to provide search to the user
 */

public abstract class GenericRecycleViewActivity<T extends RecyclerView.Adapter> extends GenericActivity {

    /**
     * Reference to the recycle view where the items will be show
     */
    protected RecyclerView lstResults;

    /**
     * The adapter of the recycle view
     */
    protected T lstAdapter;

    /**
     * The layout manager of the recycleView
     */
    protected RecyclerView.LayoutManager lstLayoutManager;

    /**
     * Used to indicate that is trying to load the data to the recycleView
     */
    protected ProgressBar loadProgress;
}
