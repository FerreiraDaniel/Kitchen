package com.dferreira.kitchen.model;


/**
 * Represents all the information to show an item in the recycle view
 */

public class ListItem {

    /**
     * Identifier of the item
     */
    @SuppressWarnings("unused")
    public long id;

    /**
     * Url to the image of the item
     */
    public String imageUrl;

    /**
     * Title of the item
     */
    public String title;

    /**
     * Description of the item
     */
    public String description;

    /**
     * Url with details of the item
     */
    public String detailsUrl;
}
