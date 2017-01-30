package com.dferreira.kitchen.presenter.network_layer.request;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Cache that is going to keep the images response of images from network
 * in cache
 */
public class ImageLoaderCache implements ImageLoader.ImageCache {

    /**
     * Maximum number of entries in the cache
     */
    private final static int MAX_NUMBER_OF_ENTRIES = 20;

    private final LruCache<String, Bitmap> cache;

    /**
     * Constructor of the image cache loader
     */
    public ImageLoaderCache() {
        this.cache = new LruCache<>(MAX_NUMBER_OF_ENTRIES);
    }

    /**
     * Fetch one bitmap from cache
     *
     * @param url The url where the image was loaded
     * @return The bitmap that exists in cache
     */
    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    /**
     * Put one bitmap in cache
     *
     * @param url    The url where the image was loaded
     * @param bitmap The bitmap that is to put in cache
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}
