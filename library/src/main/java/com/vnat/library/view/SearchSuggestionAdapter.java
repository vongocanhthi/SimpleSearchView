package com.vnat.library.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vnat.library.databinding.ItemSearchSuggestionBinding;
import com.vnat.library.listeners.SearchSuggestion;
import com.vnat.library.listeners.OnItemSuggestionListener;

import java.util.List;

public class SearchSuggestionAdapter extends ListAdapter<SearchSuggestion, SearchSuggestionAdapter.SearchSuggestionViewHolder> {
    private OnItemSuggestionListener onItemSuggestionListener;

    public SearchSuggestionAdapter(@NonNull DiffUtil.ItemCallback<SearchSuggestion> diffCallback) {
        super(diffCallback);
    }

    public void setOnItemSuggestionListener(OnItemSuggestionListener onItemSuggestionListener) {
        this.onItemSuggestionListener = onItemSuggestionListener;
    }

    @Override
    public void submitList(@Nullable List<SearchSuggestion> list) {
        super.submitList(list);
    }

    @NonNull
    @Override
    public SearchSuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchSuggestionViewHolder(ItemSearchSuggestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSuggestionViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class SearchSuggestionViewHolder extends RecyclerView.ViewHolder {
        private final ItemSearchSuggestionBinding binding;

        public SearchSuggestionViewHolder(@NonNull ItemSearchSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SearchSuggestion obj) {
            binding.setSearchSuggestion(obj);
            binding.layoutParent.setOnClickListener(v -> onItemSuggestionListener.onItemSuggestionClick(getAdapterPosition()));
            binding.layoutParent.setOnLongClickListener(v -> {
                onItemSuggestionListener.onItemSuggestionLongClick(getAdapterPosition());
                return true;
            });

        }
    }

}