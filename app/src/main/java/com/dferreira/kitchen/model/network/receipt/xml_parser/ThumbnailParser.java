package com.dferreira.kitchen.model.network.receipt.xml_parser;

import android.text.TextUtils;
import android.util.Xml;

import com.dferreira.kitchen.model.network.GenericXmlParser;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Parses one html content and return the first the first src of the tag with img
 * with class thumbnail
 */

public class ThumbnailParser extends GenericXmlParser<String> {

    @SuppressWarnings("FieldCanBeLocal")
    private final String divTag = "div";
    @SuppressWarnings("FieldCanBeLocal")
    private final String imgTag = "img";
    @SuppressWarnings("FieldCanBeLocal")
    private final String srcAttr = "src";

    /**
     * @return The tag of the main of the main element to make the parsing
     */
    @Override
    public String getMainTagName() {
        return divTag;
    }

    /**
     * @param parser The reference to the xml pull parser
     * @return the object that was parsed
     * @throws Exception Something went wrong
     */
    @Override
    public String parseObject(XmlPullParser parser) throws Exception {
        String html = parser.getText();
        String thumbnailUrl = null;

        InputStream htmlStream = null;
        try {
            XmlPullParser htmlParser = Xml.newPullParser();
            htmlParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            htmlStream = new ByteArrayInputStream(html.getBytes());
            htmlParser.setInput(htmlStream, parser.getInputEncoding());

            thumbnailUrl = parserHtml(htmlParser);
        } catch (Exception e) {
            return null;
        } finally {
            if (htmlStream != null) {
                try {
                    htmlStream.close();
                } catch (IOException ignored) {
                }
            }
        }


        return thumbnailUrl;
    }

    /**
     * Uses the htmlParser to get the src of the first img with class thumbnail
     *
     * @param htmlParser Reference to the parser of the html
     * @return The src attribute of the first img that can find
     */
    private String parserHtml(XmlPullParser htmlParser) throws Exception {
        int eventType = htmlParser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = htmlParser.getName();
            if (!TextUtils.isEmpty(name)) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        switch (name) {
                            case imgTag:
                                String src = htmlParser.getAttributeValue(null, srcAttr);
                                if (!TextUtils.isEmpty(src)) {
                                    return src;
                                }
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        break;
                }
            }
            eventType = htmlParser.next();
        }

        return null;
    }
}
