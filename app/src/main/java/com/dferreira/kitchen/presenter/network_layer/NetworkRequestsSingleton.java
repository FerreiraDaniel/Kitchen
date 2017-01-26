package com.dferreira.kitchen.presenter.network_layer;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.dferreira.kitchen.presenter.network_layer.request.ImageLoaderCache;

/**
 * Singleton that is going to be responsible for making the
 * network requests
 */
public class NetworkRequestsSingleton {
    @SuppressLint("StaticFieldLeak")
    private static NetworkRequestsSingleton mInstance;
    private RequestQueue mRequestQueue;

    /**
     * ImageLoader is a an orchestrator for large numbers of ImageRequests
     */
    private ImageLoader mImageLoader;

    /**
     * Should be one application context otherwise there is risk of a memory leak
     */
    private Context mCtx;



    /**
     * Constructor of the
     *
     * @param context   Context that is going to be used to start the cache
     */
    private NetworkRequestsSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = setupImageLoader(mRequestQueue);
    }

    /**
     * Setup the image loader
     *
     * @param requestQueue The queue of network request
     * @return The image loader that was create
     */
    private static ImageLoader setupImageLoader(RequestQueue requestQueue) {
        ImageLoader.ImageCache imageLoader = new ImageLoaderCache();
        return new ImageLoader(requestQueue, imageLoader);
    }

    /**
     * Get the instance of the network singleton
     *
     * @param context The application context
     * @return the instance of the network requests class
     */
    public static synchronized NetworkRequestsSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkRequestsSingleton(context);
        }
        return mInstance;
    }

    /**
     * @return The queue of requests
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /**
     * @return
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
