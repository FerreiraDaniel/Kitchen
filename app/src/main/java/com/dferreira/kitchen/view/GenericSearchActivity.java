package com.dferreira.kitchen.view;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

/**
 * Show be extends by all the activities made to provide search to the user
 */

public abstract class GenericSearchActivity<T extends RecyclerView.Adapter> extends GenericActivity {

    protected RecyclerView lstResults;
    protected T  lstAdapter;
    protected RecyclerView.LayoutManager lstLayoutManager;
    protected ProgressBar loadProgress;
}
