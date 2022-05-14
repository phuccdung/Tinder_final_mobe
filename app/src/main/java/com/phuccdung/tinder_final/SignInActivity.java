package com.phuccdung.tinder_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private EditText mEmail,mPassword;
    private Button mSignIn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initUi();
        initListener();
    }

    private void initListener() {

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                if(email==null ||password==null)
                {
                    Toast.makeText(SignInActivity.this, "Email or Password is Empty ",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    if(email.isEmpty()||password.isEmpty()){
                        Toast.makeText(SignInActivity.this, "Email or Password is Empty ",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        onClickSignIn();

                    }
                }
            }
        });
    }

    private void onClickSignIn() {
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        String email=mEmail.getText().toString().trim();
        String password=mPassword.getText().toString().trim();
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void initUi() {

        linearLayout=findViewById(R.id.layout_sign_up);
        mEmail=findViewById(R.id.edt_email);
        mPassword=findViewById(R.id.edt_password);
        mSignIn=findViewById(R.id.btn_sign_in);
        progressDialog=new ProgressDialog(this);
    }


}