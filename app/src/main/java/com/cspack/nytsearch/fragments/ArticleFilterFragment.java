package com.cspack.nytsearch.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cspack.nytsearch.R;
import com.cspack.nytsearch.adapters.NewsDeskArrayAdapter;
import com.cspack.nytsearch.databinding.FragmentArticleFilterBinding;
import com.cspack.nytsearch.models.FilterConfig;
import com.cspack.nytsearch.models.articlesearch.ArticleSearch;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ArticleFilterFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener, NewsDeskArrayAdapter.OnNewsDeskCheckChange {
    public static final String TAG = ArticleFilterFragment.class.getSimpleName();
    private static final String ARG_FILTER_CONFIG = "filterConfig";

    private FilterConfig filterConfig;
    private FragmentArticleFilterBinding binding;
    private OnFragmentInteractionListener listener;

    public ArticleFilterFragment() {}

    @Override
    public void onNewsDeskChanged(String newsDesk, boolean isChecked) {
        if (filterConfig.getNewsDeskFilters() == null) {
            filterConfig.setNewsDeskFilters(new ArrayList<>());
        }
        if (isChecked) {
            filterConfig.getNewsDeskFilters().add(newsDesk);
        } else {
            filterConfig.getNewsDeskFilters().remove(newsDesk);
        }
    }

    public static ArticleFilterFragment newInstance(FilterConfig config) {
        ArticleFilterFragment fragment = new ArticleFilterFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FILTER_CONFIG, Parcels.wrap(config));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filterConfig = Parcels.unwrap(getArguments().getParcelable(ARG_FILTER_CONFIG));
            prepareFilterConfig();
        }
    }

    public void prepareFilterConfig() {
        if (filterConfig != null && binding != null) {
            // Dependencies on filterConfig happen here:
            binding.lvNewsDesk.setAdapter(new NewsDeskArrayAdapter(getContext(), filterConfig,
                    this));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = getActivity().getLayoutInflater().inflate(
                R.layout.fragment_article_filter, null);
        binding = DataBindingUtil.bind(view);
        if (filterConfig.getBeginDateMicros() > 0) {
            binding.etDatePicker.setText(DateFormat.getDateInstance().format(
                    filterConfig.getBeginDateMicros()));
        } else {
            binding.etDatePicker.setText(R.string.enter_date);
        }
        binding.etDatePicker.setFocusable(false);
        binding.etDatePicker.setClickable(true);
        binding.etDatePicker.setOnClickListener(text -> {
            DatePickerFragment fragment = DatePickerFragment.newInstance(
                    filterConfig.getBeginDateMicros(), this);
            fragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
        });

        // Dirty spinner code. Needs improved.
        List<String> sortTypes = Arrays.asList("newest", "oldest");
        binding.spinnerSort.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, sortTypes));
        binding.spinnerSort.setOnItemSelectedListener(null);
        if (filterConfig.getSortOrder() != null && filterConfig.getSortOrder().equals("oldest")) {
            // select oldest.
            binding.spinnerSort.setSelection(1);
        } else {
            // select newest.
            binding.spinnerSort.setSelection(0);
        }
        binding.spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (filterConfig != null && view != null && view instanceof TextView) {
                    TextView tv = (TextView) view;
                    filterConfig.setSortOrder(tv.getText().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e(TAG, "Somehow sort order onNothingSelected fired.");
            }
        });

        // filterConfig is probably null, but adding just in case:
        prepareFilterConfig();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.filter_dialog_title);
        builder.setView(view);
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            if (listener != null) listener.onFilterChanged(filterConfig);
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFilterChanged(FilterConfig config);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);
        binding.etDatePicker.setText(DateFormat.getDateInstance().format(cal.getTime()));
        filterConfig.setBeginDateMicros(cal.getTimeInMillis());
    }
}
