package com.cspack.nytsearch.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;

import com.cspack.nytsearch.R;
import com.cspack.nytsearch.adapters.ArticleSearchAdapter;
import com.cspack.nytsearch.databinding.ActivitySearchBinding;
import com.cspack.nytsearch.fragments.ArticleFilterFragment;
import com.cspack.nytsearch.models.FilterConfig;
import com.cspack.nytsearch.models.articlesearch.ArticleSearch;
import com.cspack.nytsearch.models.articlesearch.Doc;
import com.cspack.nytsearch.network.NewYorkTimesClient;
import com.cspack.nytsearch.util.EndlessScrollListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchActivity
        extends AppCompatActivity
        implements ArticleFilterFragment.OnFragmentInteractionListener {
    @Override
    public void onFilterChanged(FilterConfig config) {
        this.filterConfig = config;
        startArticleSearch(/*append=*/false);
    }

    private ActivitySearchBinding binding;
    private FilterConfig filterConfig;

    private List<Doc> docs;
    private ArticleSearch result;
    private Snackbar errorSnackbar;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        setSupportActionBar(binding.tbMain);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        docs = new ArrayList<>();
        setupArticleView();
        filterConfig = new FilterConfig();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Backup search result.
        outState.putParcelable("result", Parcels.wrap(result));
        outState.putParcelable("filterConfig", Parcels.wrap(filterConfig));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore search result.
        Parcelable result = savedInstanceState.getParcelable("result");
        if (result != null) {
            this.result = Parcels.unwrap(result);
            docs.clear();
            docs.addAll(this.result.getResponse().getDocs());
            binding.rvSearchResults.getAdapter().notifyDataSetChanged();
        }
        Parcelable config = savedInstanceState.getParcelable("filterConfig");
        if (config != null) {
            this.filterConfig = Parcels.unwrap(config);
        }
    }

    public void startArticleSearch(final boolean append) {
        if (append == false) {
            // Reset page if not appending.
            filterConfig.setPage(0);
        }
        NewYorkTimesClient.MakeArticleSearch(filterConfig,
                new Callback<ArticleSearch>() {
                    @Override
                    public void onResponse(Call<ArticleSearch> call,
                                           Response<ArticleSearch> response) {
                        if (response.isSuccessful()) {
                            result = response.body();
                            // erase if not appending.
                            if (append == false) {
                                docs.clear();
                            }
                            docs.addAll(result.getResponse().getDocs());
                            binding.rvSearchResults.getAdapter().notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleSearch> call, Throwable t) {
                        showErrorSnackbar(SnackbarErrorType.NETWORK);
                    }
                });
    }

    public void setupArticleView() {
        binding.rvSearchResults.setAdapter(new ArticleSearchAdapter(docs, cardClickListener));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                isLandscape() ? 4 : 2, StaggeredGridLayoutManager.VERTICAL);
        binding.rvSearchResults.setLayoutManager(layoutManager);
        binding.rvSearchResults.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // limiting factors to control whether more data is available.
                if (totalItemsCount < (page * 10) || page >= 1000) {
                    return;
                }
                filterConfig.setPage(page);
                startArticleSearch(/*append=*/true);
            }
        });
    }

    public ArticleSearchAdapter.OnArticleClickListener cardClickListener =
            article -> {
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", article.getWebUrl());
                intent.putExtra("title", article.getHeadline().getMain());
                startActivity(intent);
            };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public enum SnackbarErrorType {
        NETWORK,
        INTERNAL
    }

    public void showErrorSnackbar(SnackbarErrorType errorType) {
        // don't show again if already showing.
        if (errorSnackbar != null && errorSnackbar.isShownOrQueued()) {
            return;
        }

        int snackbarStrResId = R.string.snackbar_network;
        switch (errorType) {
            case INTERNAL:
                snackbarStrResId = R.string.snackbar_internal;
                break;
            case NETWORK:
                snackbarStrResId = R.string.snackbar_network;
                break;
        }
        errorSnackbar = Snackbar.make(findViewById(R.id.mainLayout),
                snackbarStrResId,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.snackbar_retry, v -> startArticleSearch(/*append=*/false));
        errorSnackbar.show(); // Don’t forget to show!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (query.equals("")) {
                        // Erase query restriction.
                        filterConfig.setQuery(null);
                    } else {
                        filterConfig.setQuery(query);
                    }
                    startArticleSearch(/*append=*/false);
                    // Closes the keyboard, very helpful in landscape mode.
                    searchView.clearFocus();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // Handle empty text to clear query.
                    if (newText.equals("")) {
                        return this.onQueryTextSubmit(newText);
                    }
                    return false;
                }
            });
        }
        menu.findItem(R.id.action_filter).setOnMenuItemClickListener(
                item -> {
                    ArticleFilterFragment fragment = ArticleFilterFragment.newInstance(
                            filterConfig);
                    fragment.show(getSupportFragmentManager(), "FilterFragment");
                    return true;
                });
        return true;
    }
}
