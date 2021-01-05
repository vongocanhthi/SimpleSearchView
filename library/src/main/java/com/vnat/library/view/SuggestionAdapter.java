package com.vnat.library.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vnat.library.databinding.ItemSearchSuggestionBinding;
import com.vnat.library.listener.OnSuggestionIconChangeListener;
import com.vnat.library.listener.OnSuggestionListener;
import com.vnat.library.util.Constant;

import java.util.List;

public class SuggestionAdapter extends ListAdapter<String, SuggestionAdapter.SimpleSearchViewViewHolder> {
    private OnSuggestionListener onSuggestionListener;
    private OnSuggestionIconChangeListener onSuggestionIconChangeListener;

//    public static int LIMIT = 10;


    public SuggestionAdapter(@NonNull DiffUtil.ItemCallback<String> diffCallback) {
        super(diffCallback);
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.onSuggestionListener = onSuggestionListener;
    }

    public void setOnSuggestionIconChangeListener(OnSuggestionIconChangeListener onSuggestionIconChangeListener) {
        this.onSuggestionIconChangeListener = onSuggestionIconChangeListener;
    }

    @Override
    public void submitList(@Nullable List<String> list) {
        super.submitList(list);
    }

    @Override
    public int getItemCount() {
        return Math.min(getCurrentList().size(), Constant.LIMIT);
    }

    @NonNull
    @Override
    public SimpleSearchViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleSearchViewViewHolder(ItemSearchSuggestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleSearchViewViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class SimpleSearchViewViewHolder extends RecyclerView.ViewHolder {
        private final ItemSearchSuggestionBinding binding;

        public SimpleSearchViewViewHolder(@NonNull ItemSearchSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(String s) {
            binding.txtSuggestion.setText(s.toLowerCase());

            binding.layoutParent.setOnClickListener(v -> onSuggestionListener.onSuggestionClick(getAdapterPosition()));
            binding.layoutParent.setOnLongClickListener(v -> {
                onSuggestionListener.onSuggestionLongClick(getAdapterPosition());
                return false;
            });

            binding.imgRightIcon.setOnClickListener(v -> onSuggestionListener.onSuggestionRightIconClick(getAdapterPosition()));

            onSuggestionIconChangeListener.onLeftSuggestionIconChange(binding.imgLeftIcon);
            onSuggestionIconChangeListener.onRightSuggestionIconChange(binding.imgRightIcon);


        }

    }
}