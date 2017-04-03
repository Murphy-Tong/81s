package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by TONG on 2016/12/31.
 */
public class ImageUtil {

    public static void setBitmap(final ImageView iv, final String tag, String loadUrl) {
        Bitmap bm = (Bitmap) GlobalResource.imgCache.get(tag);

        if (bm == null) {

            new AsyncTask<String, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(String... params) {
                    try {

                        Bitmap bp = BitmapFactory.decodeStream(new URL(params[0]).openStream());

//                        Log.i("bp:", bp.getWidth() + ":" + bp.getHeight());

                        return bp;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (bitmap != null) {
                        GlobalResource.imgCache.put(tag, bitmap);
//                            View v = .findViewByPosition(position);
//                            if (v != null) {
//                                ImageView iv = (ImageView) v.findViewWithTag(position);
//                                if (iv != null) iv.setImageBitmap(bitmap);
//                            }
                        iv.setImageBitmap(bitmap);
                    }

                }
            }.execute(loadUrl);
        } else {
            iv.setImageBitmap(bm);
        }


    }

}
