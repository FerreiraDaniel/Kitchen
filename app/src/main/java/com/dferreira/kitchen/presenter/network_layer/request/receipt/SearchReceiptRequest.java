package com.dferreira.kitchen.presenter.network_layer.request.receipt;

import android.util.Log;

import com.android.volley.Response;
import com.dferreira.kitchen.presenter.network_layer.request.XmlPullParserRequest;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.model.Channel;
import com.dferreira.kitchen.presenter.network_layer.request.receipt.xml_parser.ChannelParser;

import org.xmlpull.v1.XmlPullParser;


/**
 * Makes the request of a list of receipts and parses the xml
 * associated
 */
public class SearchReceiptRequest extends XmlPullParserRequest<Channel> {

    private static final String rssTag = "rss";


    /**
     * Make a request and return a parsed object from xml.
     *
     * @param method        Which method (GET,POST)
     * @param url           URL of the request to make
     * @param listener      Listener that is going to be notify when the parsing is over
     * @param errorListener Listener that is going to be notify when something wrong happen
     */
    public SearchReceiptRequest(int method, String url, Response.Listener<Channel> listener, Response.ErrorListener errorListener) {
        super(method, url, rssTag, null, listener, errorListener);
    }

    /**
     * Makes the parsing of one channel
     *
     * @param parser The parse of the xml
     * @return The channel that is the result of the parsing
     * @throws Exception Something went front
     */
    @Override
    protected Channel parseObject(XmlPullParser parser) throws Exception {
        Channel searchResults = null;
        ChannelParser channelParser = new ChannelParser();

        int eventType = parser.getEventType();

        StringBuilder sb = new StringBuilder();


        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (channelParser.getMainTagName().equals(name)) {
                        searchResults = channelParser.parseObject(parser);
                    }
                    break;
            }
            eventType = parser.next();
            Log.d("tag", sb.toString());
        }

        return searchResults;
    }
}
