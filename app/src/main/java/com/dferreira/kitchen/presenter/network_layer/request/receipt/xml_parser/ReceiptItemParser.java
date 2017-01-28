package com.dferreira.kitchen.presenter.network_layer.request.receipt.xml_parser;

import com.dferreira.kitchen.presenter.network_layer.request.GenericXmlParser;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.model.ReceiptItem;

import org.xmlpull.v1.XmlPullParser;

/**
 * Parses one item that contains one receipt
 */
public class ReceiptItemParser extends GenericXmlParser<ReceiptItem> {

    private final String itemTag = "item";
    private final String titleTag = "title";
    private final String linkTag = "link";
    private final String descriptionTag = "description";
    private final String pubDateTag = "pubDate";
    private final String guiIdTag = "guiId";
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
                        case contentEncodedTag:
                            item.contentEncoded = getTextInsideTag(parser);
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
