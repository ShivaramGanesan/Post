package com.example.shiva.post;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button signUpButton;
    EditText emailSign,passwordSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        signUpButton=(Button)findViewById(R.id.signupbutton);
        emailSign=(EditText)findViewById(R.id.signupemail);
        passwordSign=(EditText)findViewById(R.id.signuppassword);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailSign.getText().toString().trim();
                String password = passwordSign.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this, "Enter Email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUp.this, "CreateUserWithEmailAndPassword:"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if(!task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Auth failed", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignUp.this, "Name "+mAuth.getInstance().getCurrentUser(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this,MainActivity.class));
                        }
                    }
                });
            }
        });

    }
}
