package com.example.utsdiah.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.utsdiah.DetailActivity;
import com.example.utsdiah.Models.ItemUser;
import com.example.utsdiah.databinding.ListUsersBinding;

public class ListUsersAdapter extends ListAdapter<ItemUser, ListUsersAdapter.ViewHolder> {

    public ListUsersAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ListUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListUsersBinding binding = ListUsersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUsersAdapter.ViewHolder holder, int position) {
        ItemUser item = getItem(position);
        holder.bind(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListUsersBinding binding;

        public ViewHolder(ListUsersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemUser usersItem) {
            binding.namaProfile.setText(usersItem.getLogin());
            Glide.with(itemView.getContext())
                    .load(usersItem.getAvatarUrl())
                    .into(binding.PhotoProfile);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("nama", usersItem.getLogin());
                view.getContext().startActivity(intent);
            });
        }
    }

    private static final DiffUtil.ItemCallback<ItemUser> DIFF_CALLBACK = new DiffUtil.ItemCallback<ItemUser>() {
        @Override
        public boolean areItemsTheSame(ItemUser oldItem, ItemUser newItem) {
            return oldItem.getLogin().equals(newItem.getLogin());
        }

        @Override
        public boolean areContentsTheSame(ItemUser oldItem, ItemUser newItem) {
            return oldItem.getLogin().equals(newItem.getLogin()) &&
                    oldItem.getAvatarUrl().equals(newItem.getAvatarUrl()) &&
                    (oldItem.getBio() != null ? oldItem.getBio().equals(newItem.getBio()) : newItem.getBio() == null);
        }
    };
}
