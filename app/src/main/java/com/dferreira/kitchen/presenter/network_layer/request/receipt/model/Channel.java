package com.dferreira.kitchen.presenter.network_layer.request.receipt.model;

import java.util.List;

/**
 * Channel of the item
 */
public class Channel {

    /**
     * Title of the channel
     */
    public String title;

    /**
     * The receipts of the channel if any
     */
    public List<ReceiptItem> receipts;
}
