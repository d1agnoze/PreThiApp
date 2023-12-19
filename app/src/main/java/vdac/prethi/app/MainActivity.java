package vdac.prethi.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vdac.prethi.app.api.RetroContri;
import vdac.prethi.app.api.RetrofitClient;
import vdac.prethi.app.databinding.ActivityMainBinding;
import vdac.prethi.app.models.Countries;
import vdac.prethi.app.models.Geo;
import vdac.prethi.app.models.Mft;
import vdac.prethi.app.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel vm;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = new ViewModelProvider(this).get(MainViewModel.class);
        vm.getUiState().observe(this, geo -> {
            try {
                binding.location.setText(geo.getLocation().getName() + " " + geo.getLocation().getCountry());
                binding.tempature.setText(geo.getCurrent().getTemp_c() + "℃");
            } catch (Exception ignored) {
            }
        });
        getData();
        getCountries();
        ActivityResultLauncher launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
            int resCode = o.getResultCode();
            Intent data = o.getData();
            if (resCode == RESULT_OK && data != null) {
                Bundle extas = data.getExtras();
                Toast.makeText(this, extas.getString("yeet"), Toast.LENGTH_SHORT).show();
            }
        });
        binding.tempature.setOnClickListener(v -> {
            Intent intent = new Intent(this, ScrollingActivity.class);
            launcher.launch(intent);
        });

        binding.acc.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void getData() {
        String location = "Hanoi";
        Call<Geo> call = RetrofitClient.getInstance().getMyApi().getGeo(location);
        call.enqueue(new Callback<Geo>() {
            @Override
            public void onResponse(Call<Geo> call, Response<Geo> response) {
                Toast.makeText(MainActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                Geo obj = response.body();
                vm.getUiState().setValue(obj);
            }

            @Override
            public void onFailure(Call<Geo> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCountries() {
        rv = binding.recycleView;
        ArrayList<Countries> countries = new ArrayList<>();
        ConAdapter adapter = new ConAdapter(countries);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Call<Mft> call = RetroContri.getInstance().getWft().getCountries();
        call.enqueue(new Callback<Mft>() {
            @Override
            public void onResponse(Call<Mft> call, Response<Mft> response) {
                Toast.makeText(MainActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                Mft res = response.body();
                adapter.dataSet = res.getData();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Mft> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}