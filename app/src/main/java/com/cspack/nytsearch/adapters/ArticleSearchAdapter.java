package com.cspack.nytsearch.adapters;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cspack.nytsearch.R;
import com.cspack.nytsearch.activities.SearchActivity;
import com.cspack.nytsearch.databinding.CardArticleBinding;
import com.cspack.nytsearch.models.articlesearch.ArticleSearch;
import com.cspack.nytsearch.models.articlesearch.Doc;
import com.cspack.nytsearch.models.articlesearch.Multimedia;

import java.util.List;
import java.util.Random;

/**
 * Created by chris on 10/20/16.
 */

public class ArticleSearchAdapter extends RecyclerView.Adapter<ArticleSearchAdapter.ViewHolder> {
    public static final String TAG = ArticleSearchAdapter.class.getSimpleName();

    final List<Doc> docs;
    final OnArticleClickListener cardClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardArticleBinding binding;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
        public CardArticleBinding getBinding() {
            return binding;
        }
    }

    public interface OnArticleClickListener {
        public void OnClick(Doc article);
    }

    public ArticleSearchAdapter(List<Doc> docs, OnArticleClickListener cardClickListener) {
        this.docs = docs;
        this.cardClickListener = cardClickListener;
    }

    @BindingAdapter("multimedia")
    public static void bindMultimedia(ImageView view, List<Multimedia> multimedia) {
        if (multimedia.isEmpty()) {
            return;
        }
        Multimedia media = multimedia.get(new Random().nextInt(multimedia.size()));
        Glide.with(view.getContext())
                .load("http://www.nytimes.com/" + media.getUrl())
                .placeholder(R.drawable.ic_settings_black_24dp)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        e.printStackTrace();
                        Log.i("GLIDE", "FAIL!!");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.i("GLIDE", "READY!!");
                        return false;
                    }
                })
                .into(view);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Doc doc = docs.get(position);
        if (doc.getMultimedia().isEmpty()) {
            // Do something?
        }
        holder.getBinding().setDoc(doc);
        holder.getBinding().card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClickListener.OnClick(docs.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return docs.size();
    }
}