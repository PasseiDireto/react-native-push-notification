package com.dieam.reactnativepushnotification.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {
    private final int BORDER_COLOR = Color.WHITE;
    private final int BORDER_RADIUS = 1;
    int x, y;

    @Override
    public Bitmap transform(Bitmap source) {
        try {
            int size = Math.min(source.getWidth(), source.getHeight());

            x = (source.getWidth() - size) / 2;
            y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;

            // Prepare the background
            Paint paintBg = new Paint();
            paintBg.setColor(BORDER_COLOR);
            paintBg.setAntiAlias(true);

            // Draw the background circle
            canvas.drawCircle(r, r, r, paintBg);

            // Draw the image smaller than the background so a little border will be seen
            canvas.drawCircle(r, r, r - BORDER_RADIUS, paint);

            squaredBitmap.recycle();
            return bitmap;
        }catch (Exception e){
            Log.d("CIRCLETRANSFORM", "ERRO");
            return source;
        }
    }

    @Override
    public String key() {
        return  "circle(x=" + x + ",y=" + y + ")";
    }
}