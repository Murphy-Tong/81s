package util;

import android.graphics.Bitmap;
import android.os.Handler;

/**
 * Created by TONG on 2016/12/31.
 */
public class GlobalResource {
    public static final int LOADING = 1;
    public static final int GET = 2;
    public static final int NOMORE = 3;
    public static Handler HANDLER;
    public static boolean isLoading = false;
    public static SoftReferenceMap imgCache = new SoftReferenceMap<Object,Bitmap>();
    public static String HOST = "http://www.80s.tw";

}
