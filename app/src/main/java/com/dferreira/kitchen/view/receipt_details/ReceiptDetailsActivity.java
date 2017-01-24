package com.dferreira.kitchen.view.receipt_details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dferreira.kitchen.R;
import com.dferreira.kitchen.view.GenericActivity;

/**
 * Activity where the user is going to see the details of
 * a receipt
 */
public class ReceiptDetailsActivity extends GenericActivity {

    public static final String TITLE = "title";
    public static final String URL = "url";

    private WebView webView;

    /**
     * Find the views that is going to use in the activity
     */
    private void bindViews() {
        this.webView = (WebView)this.findViewById(R.id.web_view_receipt_details);
    }

    /**
     * Called during the creation of the activity
     *
     * @param savedInstanceState the bundle previously saved to restore the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_details_activity);

        super.configureToolBar();

        this.bindViews();
    }

    /**
     *  Start the loading of the data of the activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        String url = getIntent().getExtras().getString(URL);
        String title = getIntent().getExtras().getString(TITLE);

        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl(url);
        setTitle(title);
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
