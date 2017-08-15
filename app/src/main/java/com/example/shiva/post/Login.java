package com.example.shiva.post;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StreamDownloadTask;

public class Login extends AppCompatActivity {
Button mLoginButton;
    EditText mLoginId,mLoginPass;
    TextView toSignUp;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(Login.this,MainActivity.class));
        }
        toSignUp=(TextView)findViewById(R.id.tosignup);
        mLoginButton=(Button)findViewById(R.id.loginButton);
        mLoginId=(EditText)findViewById(R.id.loginid);
        mLoginPass=(EditText)findViewById(R.id.passlogin);

        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mLoginId.getText().toString();
                final String password= mLoginPass.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Dei enter the email da  ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Goyyala password podu da", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                        Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(Login.this, "Machi edho error irukku", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Intent intent=new Intent(Login.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                );
            }
        });


    }
}
