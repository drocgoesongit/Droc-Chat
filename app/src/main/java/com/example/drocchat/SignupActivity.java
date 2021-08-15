package com.example.drocchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.drocchat.Models.Users;
import com.example.drocchat.databinding.ActivityMainBinding;
import com.example.drocchat.databinding.ActivitySignupBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("We're creating your account");



        binding.twAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                            Users user =new Users(binding.etUsername.getText().toString(), binding.etEmail.getText().toString(),
                                    binding.etPassword.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            // practicing user info getting and setting from model to database. :D
                            Users userEmail = new Users(binding.etEmail.getText().toString());
                            String userName= binding.etUsername.getText().toString();
                            database.getReference().child(userName).child(id).setValue(userEmail);
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

}