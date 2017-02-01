package com.dferreira.kitchen.model.network.receipt.xml_parser;

import com.dferreira.kitchen.model.network.GenericXmlParser;
import com.dferreira.kitchen.model.network.receipt.entities.ReceiptItem;

import org.xmlpull.v1.XmlPullParser;

/**
 * Parses one item that contains one receipt
 */
public class ReceiptItemParser extends GenericXmlParser<ReceiptItem> {

    @SuppressWarnings("FieldCanBeLocal")
    private final String itemTag = "item";
    @SuppressWarnings("FieldCanBeLocal")
    private final String titleTag = "title";
    @SuppressWarnings("FieldCanBeLocal")
    private final String linkTag = "link";
    @SuppressWarnings("FieldCanBeLocal")
    private final String descriptionTag = "description";
    @SuppressWarnings("FieldCanBeLocal")
    private final String pubDateTag = "pubDate";
    @SuppressWarnings("FieldCanBeLocal")
    private final String guiIdTag = "guiId";
    @SuppressWarnings("FieldCanBeLocal")
    private final String contentEncodedTag = "content:encoded";


    /**
     * @return The tag of the main of the main element to make the parsing
     */
    @Override
    public String getMainTagName() {
        return itemTag;
    }

    /**
     * Parses one receipt
     *
     * @param parser The parser of the xml
     * @return The receipt that was got as result of the parsing
     * @throws Exception Something went wrong
     */
    @Override
    public ReceiptItem parseObject(XmlPullParser parser) throws Exception {
        ReceiptItem item = new ReceiptItem();
        int eventType = parser.getEventType();
        ThumbnailParser thumbnailParser = new ThumbnailParser();

        while ((eventType != XmlPullParser.END_TAG) || (!getMainTagName().equals(parser.getName()))) {
            String name = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    switch (name) {
                        case titleTag:
                            item.title = getTextInsideTag(parser);
                            break;
                        case linkTag:
                            item.link = getTextInsideTag(parser);
                            break;
                        case descriptionTag:
                            item.description = getTextInsideTag(parser);
                            break;
                        case pubDateTag:
                            item.pubDate = getTextInsideTag(parser);
                            break;
                        case guiIdTag:
                            item.guiId = getTextInsideTag(parser);
                            break;
                        case contentEncodedTag:
                            item.contentEncoded = getTextInsideTag(parser);
                            item.thumbnailUrl = thumbnailParser.parseObject(parser);
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
                case XmlPullParser.TEXT:
                    break;
            }
            eventType = parser.next();
        }

        return item;
    }
}
