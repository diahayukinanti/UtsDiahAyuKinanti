package com.example.utsdiah;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.utsdiah.Config.ApiConfig;
import com.example.utsdiah.databinding.ActivityDetailBinding;
import com.example.utsdiah.Models.ItemUser;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String nama = getIntent().getStringExtra("nama");

        Call<ItemUser> api = ApiConfig.service().getDetailUser(nama);
        api.enqueue(new Callback<ItemUser>() {
            @Override
            public void onResponse(@NonNull Call<ItemUser> call, @NonNull Response<ItemUser> response) {
                if (response.isSuccessful()){
                    binding.tvNama.setText(response.body().getLogin());
                    binding.tvBio.setText(response.body().getBio());
                    Glide.with(DetailActivity.this)
                            .load(response.body().getAvatarUrl())
                            .into(binding.ivProfil);

                }
            }

            @Override
            public void onFailure(@NonNull Call<ItemUser> call, @NonNull Throwable t) {
                Log.e("error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
