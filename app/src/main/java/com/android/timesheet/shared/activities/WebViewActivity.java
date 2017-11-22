package com.android.timesheet.shared.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;


import com.android.timesheet.R;
import com.android.timesheet.shared.Constant;
import com.android.timesheet.shared.util.FontUtils;
import com.android.timesheet.shared.views.BaseViewBehavior;
import com.android.timesheet.shared.widget.CircularProgressBar;
import com.android.timesheet.shared.widget.CustomFontTextView;

import butterknife.BindView;


/**
 * Created by vamsikonanki on 7/26/16.
 */

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends BaseActivity implements BaseViewBehavior {

    @BindView(R.id.general_web_view)
    WebView webView;

    @BindView(R.id.overlay)
    View overlay;

    @BindView(R.id.circular_progress_bar)
    CircularProgressBar progressBar;

    @BindView(R.id.toolbarTitleTv)
    CustomFontTextView textViewToolbarTitle;

    private String mTitle ="";

    private String mUrl;

    @Override
    protected int layoutRestID() {
        return R.layout.activity_web_view;
    }

    @Override
    protected boolean showBackButton() {
        return true;
    }

    @Override
    protected String title() {
        return mTitle;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Bundle bundle = savedInstanceState;
        if (bundle == null) {
            bundle = getIntent().getExtras();
        }

        mUrl = bundle.getString(Constant.KEYS.WEB_VIEW_URL);
        mTitle = bundle.getString(Constant.KEYS.WEB_VIEW_TITLE);

        textViewToolbarTitle.setText(mTitle);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) textViewToolbarTitle.getLayoutParams();
        lp.setMargins(0, 0, 75, 0);
        textViewToolbarTitle.setPadding(0, 0, 75, 0);
        textViewToolbarTitle.setLayoutParams(lp);

        webView.loadUrl(mUrl);
//        webView.set
        WebSettings settings = webView.getSettings();
        //settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                onLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                onComplete();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(Constant.KEYS.WEB_VIEW_TITLE, mTitle);
        outState.putString(Constant.KEYS.WEB_VIEW_URL, mUrl);
    }

    @Override
    public void onLoading() {
        //overlay.setVisibility(View.VISIBLE);
        if (progressBar != null) {
            progressBar.start();
        }
    }

    @Override
    public void onComplete() {
        //overlay.setVisibility(View.GONE);
        if (progressBar != null) {
            progressBar.stop();
        }
    }

    @Override
    public void onSuccess(Object data) {
        onComplete();
    }

    @Override
    public void onFailed(Throwable e) {
        onComplete();
    }


    public static Intent newIntent(Context context, String titleResId, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constant.KEYS.WEB_VIEW_TITLE, titleResId);
        intent.putExtra(Constant.KEYS.WEB_VIEW_URL, url);

        return intent;
    }
}
