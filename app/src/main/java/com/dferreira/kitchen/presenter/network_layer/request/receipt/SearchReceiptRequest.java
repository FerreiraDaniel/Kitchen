package com.dferreira.kitchen.presenter.network_layer.request.receipt;

import com.android.volley.Response;
import com.dferreira.kitchen.presenter.network_layer.request.XmlPullParserRequest;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.model.Channel;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.model.ReceiptItem;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * Makes the request of a list of receipts and parses the xml
 * associated
 */

public class SearchReceiptRequest extends XmlPullParserRequest<Channel> {

    private final String channelTag = "channel";
    private final String titleTag = "title";
    private final String itemTag = "item";
    private final String linkTag = "link";
    private final String descriptionTag = "description";
    private final String pubDateTag = "pubDate";
    private final String guiidTag = "guiId";


    /**
     * Make a request and return a parsed object from xml.
     *
     * @param method        Which method (GET,POST)
     * @param url           URL of the request to make
     * @param listener      Listener that is going to be notify when the parsing is over
     * @param errorListener Listener that is going to be notify when something wrong happen
     */
    public SearchReceiptRequest(int method, String url, Response.Listener<Channel> listener, Response.ErrorListener errorListener) {
        super(method, url, null, listener, errorListener);
    }

    /**
     * Should be implemented by the sub-classes
     * In order to parse one object
     *
     * @param parser
     * @return
     * @throws Exception
     */
    @Override
    protected Channel parseObject(XmlPullParser parser) throws Exception {
        Channel searchResults = null;


        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = parser.getName();
            switch (eventType){
                case XmlPullParser.START_TAG:
                    if(channelTag.equals(name)) {
                        searchResults = parseChannel(parser);
                    }
                    break;
            }
            eventType = parser.next();
        }


        return searchResults;
    }

    /**
     *
     * @param parser
     * @return
     * @throws Exception
     */
    private Channel parseChannel(XmlPullParser parser) throws Exception {
        Channel channel = new Channel();
        channel.receipts = new ArrayList<>();
        int eventType = parser.getEventType();

        while ((eventType != XmlPullParser.END_TAG) || (!channelTag.equals(parser.getName()))) {
            String name = parser.getName();
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    switch (name) {
                        case titleTag:
                            parser.next();
                            channel.title = parser.getText();
                            break;
                        case itemTag:
                            ReceiptItem item = parseItem(parser);
                            channel.receipts.add(item);
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

        return channel;
    }

    /**
     *
     * @param parser
     * @return
     * @throws Exception
     */
    private ReceiptItem parseItem(XmlPullParser parser) throws Exception {
        ReceiptItem item = new ReceiptItem();
        int eventType = parser.getEventType();

        while ((eventType != XmlPullParser.END_TAG) || (!itemTag.equals(parser.getName()))) {
            String name = parser.getName();
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    switch (name) {
                        case titleTag:
                            parser.next();
                            item.title = parser.getText();
                            break;
                        case linkTag:
                            parser.next();
                            item.link = parser.getText();
                            break;
                        case descriptionTag:
                            parser.next();
                            item.description = parser.getText();
                            break;
                        case pubDateTag:
                            parser.next();
                            item.pubDate = parser.getText();
                            break;
                        case guiidTag:
                            parser.next();
                            item.guiId = parser.getText();
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
