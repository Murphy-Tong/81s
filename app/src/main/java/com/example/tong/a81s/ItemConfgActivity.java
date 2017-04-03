package com.example.tong.a81s;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import util.HtmlPaser;
import util.ImageUtil;
import util.ItemConfg;

public class ItemConfgActivity extends AppCompatActivity implements View.OnClickListener {

    private String loadUrl;
    private Toolbar toolbar;
    private ClipboardManager clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tem_confg);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clip = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        loadUrl = getIntent().getStringExtra("loadUrl");

        initView();
        loadContent();


    }

    private TextView tv_content;
    private ImageView iv_head;

    private void initView() {
        tv_content = (TextView) findViewById(R.id.tv_content);
        iv_head = (ImageView) findViewById(R.id.iv_head);

        Button cp1 = (Button) findViewById(R.id.cp1);
        Button cp2 = (Button) findViewById(R.id.cp2);
        cp1.setOnClickListener(this);
        cp2.setOnClickListener(this);
        cp2.setVisibility(View.GONE);
    }

    private void loadContent() {

        HtmlPaser.parseInfo(loadUrl, this);
    }


    private String dl1;

    public void fillContent(ItemConfg itemConfg) {
        toolbar.setTitle(itemConfg.getTitle());
        tv_content.setText(itemConfg.getMovie_content());

        dl1 = itemConfg.getDl1();
        ImageUtil.setBitmap(iv_head, itemConfg.getImgUrl(), itemConfg.getImgUrl());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cp1:
                clip.setText(dl1);
                Toast.makeText(this, "copy ok", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cp2:

                break;
        }
    }
}
