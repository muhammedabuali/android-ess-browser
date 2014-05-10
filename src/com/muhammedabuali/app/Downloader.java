package com.muhammedabuali.app;

import android.content.Context;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

public class Downloader {

    public static final String baseUrl = "http://www.ess.mx";
    public static int pageCount = 2;
    public static int newCount = 2;
    public static int lameCount = 2;
    public static final String baseTrending = "http://www.ess.mx/discover/trending/";
    public static final String baseNewest = "http://www.ess.mx/discover/newest/";
    public static final String baseLame = "http://www.ess.mx/discover/lames/";

    /**
     * initializes the imagedownloader with a specific configuration
     * I CALL THIS METHOD RIGHT AFTER APP STARTUP
     *
     * @param c
     */
    public static void initialize(Context c) {
        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(c)
                .threadPoolSize(20)
                .threadPriority(Thread.NORM_PRIORITY) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .memoryCacheSize(20 * 1024 * 1024)
                .memoryCacheSizePercentage(15) // default
                .discCacheSize(10 * 1024 * 1024)
                .discCacheFileCount(100)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
                .build();

        ImageLoader.getInstance().init(config);
    }

    /**
     * gets the display options that are needed when displaying an image
     *
     * @return
     */
    public static DisplayImageOptions getDisplayOptions() {

        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ess)
                .showImageOnFail(R.drawable.ess)
                        //.delayBeforeLoading(300)
                .resetViewBeforeLoading(false)  // default
                .imageScaleType(ImageScaleType.NONE)
                .cacheInMemory(true) // default
                .cacheOnDisc(true) // default
                .build();
    }

    public static ImageLoader getInstance() {
        return ImageLoader.getInstance();
    }
}