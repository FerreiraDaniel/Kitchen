package com.dferreira.kitchen.presenter.receipt_list;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.dferreira.kitchen.model.ListItem;
import com.dferreira.kitchen.presenter.GenericRecyclerViewAdapter;
import com.dferreira.kitchen.presenter.network_layer.NetworkRequestsSingleton;
import com.dferreira.kitchen.view.receipt_details.ReceiptDetailsActivity;

/**
 * Adapter of the list of elements
 */
public class ReceiptsAdapter extends GenericRecyclerViewAdapter {


    /**
     * Constructor to the view adapter
     *
     * @param activity     Reference to the context where the adapter will be used
     * @param recyclerView Reference to the recycle view to use
     * @param progressBar  Reference to the progress bar that indicates to the user that is using data
     */
    public ReceiptsAdapter(@NonNull Activity activity, RecyclerView recyclerView, ProgressBar progressBar) {
        super(activity, recyclerView, progressBar);
    }

    /**
     * Called when the user clicks in one item of the list
     *
     * @param v The view that was clicked.
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onClick(View v) {
        ListItem item = getItemByView(v);
        Toast.makeText(activity, item.title, Toast.LENGTH_LONG).show();
        if ((item != null) && (!TextUtils.isEmpty(item.detailsUrl))) {
            Intent intent = new Intent(activity, ReceiptDetailsActivity.class);
            intent.putExtra(ReceiptDetailsActivity.TITLE, item.title);
            intent.putExtra(ReceiptDetailsActivity.URL, item.detailsUrl);
            activity.startActivity(intent);
        }
    }

    /**
     * Should be used by the activity to indicate that is able to receive content
     */
    @Override
    public void startLoading() {
        if (requestPermission(Manifest.permission.INTERNET)) {
            // Instantiate the RequestQueue.
            String url = "http://receitas-le.webnode.com.br/rss/all.xml";

            ReceiptsRequest request = new ReceiptsRequest(Request.Method.GET, url, this);

            NetworkRequestsSingleton.getInstance(activity.getApplicationContext()).addToRequestQueue(request);
        }
    }

    /**
     * Should be used by the activity to indicate that is able no able to receive content
     * anymore
     */
    @Override
    public void stopLoading() {
    }
}
