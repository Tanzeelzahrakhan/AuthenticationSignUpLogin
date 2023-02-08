package com.example.notificationandathentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText ed_email,ed_password;

    String str_email,str_password;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        mAuth=FirebaseAuth.getInstance();
    }

    public void Login(View view) {

        if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{


            str_email = ed_email.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Error"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
        finish();
    }
}
