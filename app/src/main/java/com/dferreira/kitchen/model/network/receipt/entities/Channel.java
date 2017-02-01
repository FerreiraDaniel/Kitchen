package com.dferreira.kitchen.model.network.receipt.entities;

import java.util.List;

/**
 * Channel of the item
 */
public class Channel {

    /**
     * Title of the channel
     */
    @SuppressWarnings("unused")
    public String title;

    /**
     * The receipts of the channel if any
     */
    public List<ReceiptItem> receipts;
}
