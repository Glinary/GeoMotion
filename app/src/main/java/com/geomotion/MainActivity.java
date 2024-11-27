package com.geomotion;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.geomotion.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationControl {

    ActivityMainBinding binding;
    private MyDatabaseHelper myDB;
    private boolean allowNavigation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        replaceFragment(new HomeFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (!allowNavigation) { // Check if navigation is disabled
                Toast.makeText(this, "Navigation is disabled during recording.", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (item.getItemId() == R.id.mn_home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.mn_recordings) {
                replaceFragment(new RecordingsFragment());
            } else if (item.getItemId() == R.id.mn_settings) {
                replaceFragment(new SettingsFragment());
            }

            return true;
        });


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainConstraintLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setAllowNavigation(boolean allow) {
        this.allowNavigation = allow;
    }
}