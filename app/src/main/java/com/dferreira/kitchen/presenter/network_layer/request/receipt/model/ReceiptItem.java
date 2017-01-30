package com.dferreira.kitchen.presenter.network_layer.request.receipt.model;

/**
 * Receipt in one item
 */
public class ReceiptItem {

    /**
     * The title of the receipt
     */
    public String title;

    /**
     * Link to the page of details of the receipt
     */
    public String link;

    /**
     * The description of the receipt
     */
    public String description;

    /**
     * Date when the receipt was publish
     */
    @SuppressWarnings("unused")
    public String pubDate;

    /**
     * Identifier of the receipt
     */
    @SuppressWarnings("unused")
    public String guiId;

    /**
     * Content of the receipt encoded
     */
    public String contentEncoded;

    /**
     * Url with thumbnail of the item
     */
    public String thumbnailUrl;
}
