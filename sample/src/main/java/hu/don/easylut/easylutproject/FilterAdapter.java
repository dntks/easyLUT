package hu.don.easylut.easylutproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    public interface OnFilterSelected {
        void onFilterClicked(FilterSelection filterSelection);
    }

    private List<FilterSelection> filters;
    private final OnFilterSelected listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public FilterAdapter(List<FilterSelection> filters, OnFilterSelected listener) {
        this.filters = filters;
        this.listener = listener;
    }

    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        TextView v = (TextView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final FilterSelection filterSelection = filters.get(position);
        holder.mTextView.setText(filterSelection.name);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFilterClicked(filterSelection);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

}
