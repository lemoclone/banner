package com.test.banner.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test.banner.ui.RoundAngleImageView;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext()).load(path).into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        return new RoundAngleImageView(context);
    }
}
