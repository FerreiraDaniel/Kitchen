package com.dferreira.kitchen.model.network.receipt.xml_parser;

import android.text.TextUtils;

import com.dferreira.kitchen.model.network.GenericXmlParser;
import com.dferreira.kitchen.model.network.receipt.entities.Channel;
import com.dferreira.kitchen.model.network.receipt.entities.ReceiptItem;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * Makes the parsing of a channel xml object
 */

public class ChannelParser extends GenericXmlParser<Channel> {

    @SuppressWarnings("FieldCanBeLocal")
    private final String channelTag = "channel";
    @SuppressWarnings("FieldCanBeLocal")
    private final String titleTag = "title";

    /**
     * @return The tag of the main of the main element to make the parsing
     */
    @Override
    public String getMainTagName() {
        return channelTag;
    }

    /**
     * @param parser The reference to the xml pull parser
     * @return the channel result of parser
     * @throws Exception Something went wrong
     */
    @Override
    public Channel parseObject(XmlPullParser parser) throws Exception {
        Channel channel = new Channel();
        ReceiptItemParser receiptItemParser = new ReceiptItemParser();
        channel.receipts = new ArrayList<>();
        int eventType = parser.getEventType();

        while ((eventType != XmlPullParser.END_TAG) || (!getMainTagName().equals(parser.getName()))) {
            String name = parser.getName();
            if (!TextUtils.isEmpty(name)) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        switch (name) {
                            case titleTag:
                                channel.title = getTextInsideTag(parser);
                                break;
                            default:
                                if (receiptItemParser.getMainTagName().equals(name)) {
                                    ReceiptItem item = receiptItemParser.parseObject(parser);
                                    channel.receipts.add(item);
                                }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        break;
                }
            }
            eventType = parser.next();
        }

        return channel;
    }
}
