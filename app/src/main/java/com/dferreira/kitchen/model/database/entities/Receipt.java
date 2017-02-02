package com.dferreira.kitchen.model.database.entities;

/**
 * Represents one receipt in database
 */

public class Receipt {

    public static final String TABLE_NAME = "Receipt";
    public static final String ID_COLUMN_NAME = "ID";
    public static final String LINK_COLUMN_NAME = "LINK";
    public static final String DESCRIPTION_COLUMN_NAME = "DESCRIPTION";
    public static final String TITLE_COLUMN_NAME = "TITLE";
    public static final String CONTENT_ENCODED_COLUMN_NAME = "CONTENT_ENCODED";
    public static final String THUMBNAIL_URL_COLUMN_NAME = "THUMBNAIL_URL";
    public static final String THUMBNAIL_PATH_COLUMN_NAME = "THUMBNAIL_PATH";

    /**
     * Identifier of the receipt
     */
    public long id;

    /**
     * Link to the receipt
     */
    public String link;

    /**
     * Description of the receipt
     */
    public String description;

    /**
     * Title of the receipt
     */
    public String title;

    /**
     * Details of the receipt encoded
     */
    public String contentEncoded;

    /**
     * Url of the thumbnail of the receipt
     */
    public String thumbnailUrl;

    /**
     * Path in disk of the thumbnail of the receipt (If meanwhile the thumbnail was downloaded
     */
    public String thumbnailPath;


}
