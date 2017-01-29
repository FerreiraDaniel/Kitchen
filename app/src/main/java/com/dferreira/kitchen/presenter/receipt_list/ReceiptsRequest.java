package com.dferreira.kitchen.presenter.receipt_list;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dferreira.kitchen.model.ListItem;
import com.dferreira.kitchen.presenter.GenericRecyclerViewAdapter;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.SearchReceiptRequest;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.model.Channel;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.model.ReceiptItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one string request that ends up filling the data of an
 * recycle view adapter
 */

public class ReceiptsRequest extends SearchReceiptRequest {

    private static final String TAG = "ReceiptsRequest";

    /**
     * Creates a new request with the given method.
     *
     * @param method
     * @param url
     * @param recyclerViewAdapter
     */
    public ReceiptsRequest(int method, String url, final GenericRecyclerViewAdapter recyclerViewAdapter) {
        super(method, url, getNormalListener(recyclerViewAdapter), _getErrorListener());
    }

    /**
     * Creates the handler that is going to handle the response
     *
     * @param recyclerViewAdapter
     * @return
     */
    private static Response.Listener<Channel> getNormalListener(final GenericRecyclerViewAdapter recyclerViewAdapter) {
        return new Response.Listener<Channel>() {
            @Override
            public void onResponse(Channel response) {

                if ((response != null) && (response.receipts != null) && (!response.receipts.isEmpty())) {
                    List<ListItem> items = new ArrayList<>();

                    for (ReceiptItem result : response.receipts) {
                        ListItem item = new ListItem();
                        item.title = result.title;
                        item.description = result.description;
                        item.imageUrl = result.thumbnailUrl;
                        item.detailsUrl = result.link;

                        items.add(item);
                    }

                    recyclerViewAdapter.setDataSet(items);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };
    }

    private static Response.ErrorListener _getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Response is: " + "That didn't work!");
            }
        };
    }
}
