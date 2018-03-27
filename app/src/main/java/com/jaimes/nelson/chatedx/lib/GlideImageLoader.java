package com.jaimes.nelson.chatedx.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Glide Implementation.
 *
 * @author Nelson Jaimes Gonzales on 25/03/2018.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager requestManager;

    public GlideImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imageView, String url) {
        requestManager.load(url).transition(withCrossFade()).
                thumbnail(requestManager.load(url).apply(new RequestOptions().
                        override(imageView.getWidth(), imageView.getHeight()))).into(imageView);
    }
}
