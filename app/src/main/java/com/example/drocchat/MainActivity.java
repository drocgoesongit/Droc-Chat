package com.example.drocchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.drocchat.Adapters.FragmentAdapters;
import com.example.drocchat.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null){
            Intent intent = new Intent(MainActivity.this,SignupActivity.class);
            startActivity(intent);
        }

        binding.viewPager.setAdapter(new FragmentAdapters(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case (R.id.settings):
                Intent intent2 = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent2);
                break;

            case (R.id.logout):
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
                break;
            case (R.id.groupChat):
                Intent intent21 = new Intent(MainActivity.this, GroupChatActivity.class);
                startActivity(intent21);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}