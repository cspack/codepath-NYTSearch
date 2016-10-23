package com.cspack.nytsearch.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cspack.nytsearch.R;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    private WebView myWebView;
    private Intent shareIntent;
    private String title;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        if (title == null || title.isEmpty() || url == null || url.isEmpty()) {
            Log.e(TAG, "Encountered an improbable intent state!");
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);

        myWebView = (WebView) findViewById(R.id.webview);
        // Configure related browser settings
        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        myWebView.setWebViewClient(new MyBrowser());
        // Load the initial URL
        myWebView.loadUrl(url);

        prepareShareIntent();
    }

    private class MyBrowser extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
    private void prepareShareIntent() {
        String shareText = "Check out \"" + title + "\" at " + url;
        shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        shareIntent.setType("text/plain");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_webview, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        ShareActionProvider miShare = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        miShare.setShareIntent(shareIntent);
        return super.onCreateOptionsMenu(menu);
    }
}
