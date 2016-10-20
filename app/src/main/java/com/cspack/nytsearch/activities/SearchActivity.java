package com.cspack.nytsearch.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.cspack.nytsearch.DatePickerFragment;
import com.cspack.nytsearch.R;
import com.cspack.nytsearch.adapters.ArticleSearchAdapter;
import com.cspack.nytsearch.databinding.ActivitySearchBinding;
import com.cspack.nytsearch.models.articlesearch.ArticleSearch;
import com.cspack.nytsearch.models.articlesearch.Doc;
import com.cspack.nytsearch.network.NewYorkTimesClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ActivitySearchBinding binding;

    private List<Doc> docs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        setSupportActionBar(binding.tbMain);

        NewYorkTimesClient.MakeArticleSearch(new Callback<ArticleSearch>() {
            @Override
            public void onResponse(Call<ArticleSearch> call, Response<ArticleSearch> response) {
                if (response.isSuccessful()) {
                    docs = response.body().getResponse().getDocs();
                    setupArticleView();
                }
            }

            @Override
            public void onFailure(Call<ArticleSearch> call, Throwable t) {
                showErrorSnackbar();
            }
        });
    }

    public void setupArticleView() {
        binding.rvSearchResults.setAdapter(new ArticleSearchAdapter(docs, cardClickListener));
        binding.rvSearchResults.setLayoutManager(new StaggeredGridLayoutManager(
                isLandscape() ? 4 : 2, StaggeredGridLayoutManager.VERTICAL));
        binding.rvSearchResults.setHasFixedSize(true);
    }

    public ArticleSearchAdapter.OnArticleClickListener cardClickListener =
            new ArticleSearchAdapter.OnArticleClickListener() {
                @Override
                public void OnClick(Doc article) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getWebUrl()));
                    startActivity(intent);
                }
            };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public void showErrorSnackbar() {
        Snackbar.make(findViewById(R.id.mainLayout), R.string.snackbar_network,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this, "hello", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(); // Don’t forget to show!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                    // Closes the keyboard, very helpful in landscape mode.
                    searchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        MenuItem filter = menu.findItem(R.id.action_filter).setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getSupportFragmentManager(), "date");
                return true;
            }
        });

        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(this, "lol date set", Toast.LENGTH_SHORT).show();
    }
}
