package com.cspack.nytsearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.cspack.nytsearch.R;
import com.cspack.nytsearch.models.FilterConfig;
import com.cspack.nytsearch.models.articlesearch.Values;
import com.cspack.nytsearch.util.EndlessScrollListener;

import java.util.ArrayList;

/**
 * Created by chris on 10/22/16.
 */

public class NewsDeskArrayAdapter extends ArrayAdapter {
    public interface OnNewsDeskCheckChange {
        void onNewsDeskChanged(String newsDesk, boolean isChecked);
    };
    public class ViewHolder {
        public CheckedTextView checkText;
        public ViewHolder(View view) {
            checkText = (CheckedTextView) view.findViewById(R.id.checkText);
        }
    }

    private Context context;
    private boolean[] newsCheck;
    private OnNewsDeskCheckChange listener;
    private View.OnClickListener checkListener = v -> {
        CheckedTextView cv = (CheckedTextView) v;
        if (cv != null && listener != null) {
            final String filterName = cv.getText().toString();
            cv.toggle();
            int index = Values.NEWS_DESK_VALUES.indexOf(filterName);
            if (index >= 0) {
                newsCheck[index] = cv.isChecked();
            }
            listener.onNewsDeskChanged(filterName, cv.isChecked());
        }
    };
    public NewsDeskArrayAdapter(Context context, FilterConfig config, OnNewsDeskCheckChange listener) {
        super(context, 0);
        this.context = context;
        this.listener = listener;
        newsCheck = new boolean[Values.NEWS_DESK_VALUES.size()];
        if (config.getNewsDeskFilters() != null) {
            for (String filter : config.getNewsDeskFilters()) {
                int index = Values.NEWS_DESK_VALUES.indexOf(filter);
                if (index >= 0) {
                    newsCheck[index] = true;
                }
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.list_view_check_text, parent, false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.checkText.setOnClickListener(null);
        holder.checkText.setChecked(newsCheck[position]);
        holder.checkText.setText(Values.NEWS_DESK_VALUES.get(position));
        holder.checkText.setOnClickListener(checkListener);
        return view;
    }

    @Override
    public int getCount() {
        return Values.NEWS_DESK_VALUES.size();
    }
}