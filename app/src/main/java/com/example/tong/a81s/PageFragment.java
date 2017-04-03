package com.example.tong.a81s;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import util.GlobalResource;
import util.HtmlPaser;
import util.ImageUtil;
import util.Item;

/**
 * Created by TONG on 2016/12/31.
 */
public class PageFragment extends Fragment {

    private String loadUrl;


    public PageFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.loadUrl = getArguments().getString("loadUrl");


    }

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.page_frag, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        fromScroll = false;
        initView();

        if (!GlobalResource.isLoading){
            GlobalResource.HANDLER.sendEmptyMessage(GlobalResource.LOADING);
            GlobalResource.isLoading = true;
        }


        HtmlPaser.paserContent(loadUrl, 1, this);
        return v;
    }

    private boolean fromScroll = false;
    private MyRecyclerAdapter adapter;

    private void initView() {
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            private int position;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (position + 1) == adapter.getItemCount()) {
                    fromScroll = true;
                    getMore();

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                position = layoutManager.findLastVisibleItemPosition();
            }
        });

        adapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }


    private boolean isLoading = false;
    private int currentPage = 1;

    private void getMore() {
        if (fromScroll)
            GlobalResource.HANDLER.sendEmptyMessage(GlobalResource.LOADING);
        if (!isLoading) {
            isLoading = true;
            HtmlPaser.paserContent(loadUrl, ++currentPage, this);
        }
    }


    private ArrayList<Item> items = new ArrayList<>();

    public void fillContent(ArrayList arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            if (fromScroll)
                GlobalResource.HANDLER.sendEmptyMessage(GlobalResource.NOMORE);
        } else {
            Message msg = Message.obtain();
            msg.what = GlobalResource.GET;
            msg.obj = arrayList.size();
            if (fromScroll)
                GlobalResource.HANDLER.sendMessage(msg);
        }

        items.addAll(arrayList);

        adapter.notifyDataSetChanged();
        isLoading = false;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        TextView title, confg;
        ImageView iv;
        View self;
        Drawable drawable;

        public MyHolder(View itemView) {
            super(itemView);

            self = itemView;
            title = (TextView) itemView.findViewById(R.id.item_title);
            confg = (TextView) itemView.findViewById(R.id.item_confg);
            iv = (ImageView) itemView.findViewById(R.id.iv);
//            drawable = iv.getBackground();

        }
    }


    private class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
        private MyHolder myHolder;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = View.inflate(getActivity(), R.layout.item, null);
            v.setOnClickListener(this);

            return new MyHolder(v);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            myHolder = (MyHolder) holder;
            myHolder.iv.setImageResource(android.R.color.darker_gray);
            final Item item = items.get(position);
            myHolder.title.setText(item.getTitle());
            myHolder.confg.setText(item.getConfg());
            myHolder.self.setTag(item.getLink());

            ImageUtil.setBitmap(myHolder.iv, item.getLink(), item.getImgUrl());


        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        @Override
        public void onClick(View v) {
            String url = v.getTag().toString();
            Intent i = new Intent(getContext(), ItemConfgActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("loadUrl", url);
            // Log.i("link",url);
            i.putExtra("loadUrl", url);
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i, bundle);
        }
    }


}

