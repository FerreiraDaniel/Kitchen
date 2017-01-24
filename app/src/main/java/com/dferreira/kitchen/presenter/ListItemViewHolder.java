package com.dferreira.kitchen.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.dferreira.kitchen.R;

/**
 * View holder to the recycle view
 */

public class ListItemViewHolder extends RecyclerView.ViewHolder {
    public NetworkImageView thumbnail;
    public TextView title;
    public TextView description;

    /**
     * In the constructor of the view holder also find the
     * sub-elements of the item
     *
     * @param v View that is going to support the item of the list
     */
    public ListItemViewHolder(@NonNull View v) {
        super(v);
        this.thumbnail = (NetworkImageView) v.findViewById(R.id.thumbnail);
        this.title = (TextView) v.findViewById(R.id.title);
        this.description = (TextView) v.findViewById(R.id.description);
    }
}
