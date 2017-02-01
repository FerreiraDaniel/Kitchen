package com.dferreira.kitchen.model.network;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Contains a set of methods useful for the parsing of XML
 */
public abstract class GenericXmlParser<T> {
    /**
     * Get the content that is inside one tag
     *
     * @param parser Reference to XmlPullParser
     * @return The string that was got from the xml tag
     */
    protected static String getTextInsideTag(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.next();
        return parser.getText();
    }

    /**
     * @return The tag of the main of the main element to make the parsing
     */
    @SuppressWarnings("unused")
    public abstract String getMainTagName();


    /**
     * @param parser The reference to the xml pull parser
     * @return the object that was parsed
     * @throws Exception Something went wrong
     */
    @SuppressWarnings("unused")
    public abstract T parseObject(XmlPullParser parser) throws Exception;
}
