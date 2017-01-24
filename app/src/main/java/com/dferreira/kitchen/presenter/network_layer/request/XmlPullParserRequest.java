package com.dferreira.kitchen.presenter.network_layer.request;

import android.util.Xml;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Makes a request and parses the element using XmlPullParser library
 */

public abstract class XmlPullParserRequest<T> extends Request<T> {
    private final Map<String, String> headers;
    private final Listener<T> listener;

    /**
     * Make a request and return a parsed object from xml.
     *
     * @param method    Which method (GET,POST)
     * @param url       URL of the request to make
     * @param headers   Map of request headers
     * @param listener  Listener that is going to be notify when the parsing is over
     * @param errorListener Listener that is going to be notify when something wrong happen
     */
    public XmlPullParserRequest(int method, String url, Map<String, String> headers,
                                Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.headers = headers;
        this.listener = listener;
    }

    /**
     * @return The headers that should use during the request
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers == null ? super.getHeaders() : headers;
    }

    /**
     * response to their listeners.  The given response is guaranteed to
     * be non-null; responses that fail to parse are not delivered.
     *
     * @param response The parsed response returned
     */
    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    /**
     * This method will be
     * called from a worker thread.  The response will not be delivered
     * if you return null.
     *
     * @param response Response from the network
     * @return The parsed response, or null in the case of an error
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        InputStream xmlStream = null;
        try {

            xmlStream = new ByteArrayInputStream(response.data);
            String inputEncoding = HttpHeaderParser.parseCharset(response.headers);
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(xmlStream, inputEncoding);
            parser.nextTag();
            return Response.success(
                    parseObject(parser),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        } finally {
            if (xmlStream != null) {
                try {
                    xmlStream.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * Should be implemented by the sub-classes
     * In order to parse one object
     *
     * @param parser
     * @return
     * @throws Exception
     */
    protected abstract T parseObject(XmlPullParser parser) throws Exception;
}