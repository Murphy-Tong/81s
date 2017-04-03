package com.example.tong.a81s;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TONG on 2016/12/31.
 */
public class ItemConfgFragment extends Fragment {
    private ItemConfgFragment() {
    }

    ;

    private static String loadUrl;
    private static ItemConfgFragment itemConfgFragment = new ItemConfgFragment();

    public static ItemConfgFragment Instance(String loadUrl) {
        ItemConfgFragment.loadUrl = loadUrl;
        return itemConfgFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.itemconfg, container, false);

        return v;
    }
}
