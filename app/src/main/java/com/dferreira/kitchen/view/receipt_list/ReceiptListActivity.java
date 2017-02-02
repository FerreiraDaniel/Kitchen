package com.dferreira.kitchen.view.receipt_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.dferreira.kitchen.R;
import com.dferreira.kitchen.model.database.ReceiptRepository;
import com.dferreira.kitchen.presenter.receipt_list.ReceiptsAdapter;
import com.dferreira.kitchen.view.GenericRecycleViewActivity;

/**
 * Activity used to show content to sell near from the user
 */
public class ReceiptListActivity extends GenericRecycleViewActivity<ReceiptsAdapter> {


    /**
     * Find the views that is going to use in the activity
     */
    private void bindViews() {
        this.lstResults = (RecyclerView) this.findViewById(R.id.near_by_list);
        this.loadProgress = (ProgressBar) this.findViewById(R.id.load_progress);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        lstResults.setHasFixedSize(false);

        // use a linear layout manager
        this.lstLayoutManager = new LinearLayoutManager(this);
        lstResults.setLayoutManager(lstLayoutManager);

        // specify an adapter (see also next example)
        this.lstAdapter = new ReceiptsAdapter(this, lstResults, loadProgress);
        lstResults.setAdapter(lstAdapter);
    }

    /**
     * Called during the creation of the activity
     *
     * @param savedInstanceState the bundle previously saved to restore the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_list_activity);

        super.configureToolBar();

        this.bindViews();

        ReceiptRepository receiptRepository = new ReceiptRepository(this.getApplicationContext());
        try {

        } finally {
            receiptRepository.close();
        }


    }

    /**
     * Start the loading of the data of the activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        lstAdapter.startLoading();
    }

    /**
     * Called when activity is paused
     */
    @Override
    protected void onPause() {
        super.onPause();
        lstAdapter.stopLoading();
    }


    /**
     * Called when of the elements of the navigation menu is pressed
     *
     * @param item The selected item
     * @return If was possible to treat the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.search:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
