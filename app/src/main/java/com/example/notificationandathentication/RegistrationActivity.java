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


public class RegistrationActivity extends AppCompatActivity {

    EditText ed_username,ed_email,ed_password;
    String str_name,str_email,str_password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ed_email = findViewById(R.id.ed_email);
        ed_username = findViewById(R.id.ed_username);
        ed_password = findViewById(R.id.ed_password);
        mAuth=FirebaseAuth.getInstance();

    }

    public void moveToLogin(View view) {

        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }

    public void Register(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if(ed_username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();
            CreateUser();



        }

    }
    private void CreateUser(){
        str_name = ed_username.getText().toString().trim();
        str_email = ed_email.getText().toString().trim();
        str_password = ed_password.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(str_email,str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Registration Error"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
