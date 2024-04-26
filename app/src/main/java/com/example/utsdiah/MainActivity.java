package com.example.utsdiah;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.utsdiah.Adapters.ListUsersAdapter;
import com.example.utsdiah.Config.ApiConfig;
import com.example.utsdiah.databinding.ActivityMainBinding;
import com.example.utsdiah.Models.GitHubResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListUsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adapter = new ListUsersAdapter();
        String nama = "Diah";

        Call<GitHubResponse> api = ApiConfig.service().getUsers(nama);
        api.enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(@NonNull Call<GitHubResponse> call, @NonNull Response<GitHubResponse> response) {
                binding.RvList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.RvList.setAdapter(adapter);
                if (response.isSuccessful()){
                    adapter.submitList(response.body().getItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GitHubResponse> call, @NonNull Throwable t) {
                Log.e("error", Objects.requireNonNull(t.getMessage()));
            }
        });

    }
}