package com.cspack.nytsearch.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
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

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static android.databinding.DataBindingUtil.bind;

/**
 * Created by chris on 10/20/16.
 */

public class ArticleSearchAdapter extends RecyclerView.Adapter<ArticleSearchAdapter.ViewHolder> {
    public static final String TAG = ArticleSearchAdapter.class.getSimpleName();

    // Multiple types don't work well with Data binding, so don't split views to ease it.
    public enum ArticleSearchCardType {
        THUMBNAIL_AND_TEXT,
        TEXT_ONLY
    };

    final List<Doc> docs;
    final OnArticleClickListener cardClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardArticleBinding binding;
        private ArticleSearchCardType viewType;
        public ViewHolder(View itemView, ArticleSearchCardType viewType) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            this.viewType = viewType;
        }
        public CardArticleBinding getBinding() {
            return binding;
        }

        public ArticleSearchCardType getViewType() {
            return viewType;
        }
    }

    public interface OnArticleClickListener {
        void OnClick(Doc article);
    }

    public ArticleSearchAdapter(List<Doc> docs, OnArticleClickListener cardClickListener) {
        this.docs = docs;
        this.cardClickListener = cardClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (getThumbnail(docs.get(position).getMultimedia()) == null) {
            return ArticleSearchCardType.TEXT_ONLY.ordinal();
        }
        return ArticleSearchCardType.THUMBNAIL_AND_TEXT.ordinal();
    }

    public static Multimedia getThumbnail(List<Multimedia> multimedia) {
        // Pick first image that is not a thumbnail.
        for (Multimedia media : multimedia) {
            // Thumbnail is only 75x75, the other types are at least reasonably sized.
            if (media.getType().equals("image") &&
                    !media.getSubtype().equalsIgnoreCase("thumbnail")) {
                return media;
            }
        }
        return null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArticleSearchCardType cardType = ArticleSearchCardType.values()[viewType];
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_article, parent, false);
        return new ViewHolder(view, cardType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Doc doc = docs.get(position);
        Multimedia media = getThumbnail(doc.getMultimedia());
        holder.getBinding().setDoc(doc);
        holder.getBinding().setMedia(media);
        holder.getBinding().card.setOnClickListener(
                v -> cardClickListener.OnClick(docs.get(position)));
        if (holder.getViewType() == ArticleSearchCardType.TEXT_ONLY) {
            holder.getBinding().ivThumbnail.setVisibility(View.GONE);
            holder.getBinding().tvTitle.setMaxLines(4);
        } else {
            holder.getBinding().ivThumbnail.setVisibility(View.VISIBLE);
            holder.getBinding().tvTitle.setMaxLines(2);

            Context context = holder.getBinding().card.getContext();
            int cardBackground = ContextCompat.getColor(context, R.color.cardBackground);
            float[] cardColor = new float[]{Color.red(cardBackground) / 256f,
                    Color.green(cardBackground) / 256f, Color.blue(cardBackground) / 256f};
            // Color.colorToHSV(Color.parseColor("#00CCFF"), cardColor);
            Glide.with(context)
                    .load("http://www.nytimes.com/" + media.getUrl())
                    .placeholder(R.drawable.error_black_24dp)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .bitmapTransform(new VignetteFilterTransformation(
                            context, new PointF(0.5f, 0.5f), cardColor, 0.0f, 1.5f))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFirstResource) {

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model,
                                                       Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache,
                                                       boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(holder.getBinding().ivThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return docs.size();
    }
}