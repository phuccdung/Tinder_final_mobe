package com.phuccdung.tinder_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText eEmail, ePassword, eConfirm, mName;
    private RadioGroup mRadioSex;
    private Button btnSignUp;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intiUi();

        initListener();
    }

    private void initListener() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = ePassword.getText().toString();
                String confirm = eConfirm.getText().toString();
                if (password.equals(confirm)) {
                    onClickSignUp();


                } else {

                    Toast.makeText(SignUpActivity.this, "Password don't same!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClickSignUp() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String email = eEmail.getText().toString().trim();
        final String password = ePassword.getText().toString().trim();
        final String name = mName.getText().toString();

        int selectId = mRadioSex.getCheckedRadioButtonId();
        final RadioButton radioButton = (RadioButton) findViewById(selectId);
        if (radioButton.getText() == null) {
            return;
        }

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            String userId = mAuth.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(radioButton.getText().toString()).child(userId).child("name");
                            currentUserDb.setValue(name);
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void intiUi() {
        progressDialog = new ProgressDialog(this);
        eEmail = findViewById(R.id.edit_email);
        ePassword = findViewById(R.id.edit_password);
        eConfirm = findViewById(R.id.edit_confirm);
        btnSignUp = findViewById(R.id.btn_sign_up);
        mName = findViewById(R.id.name);
        mRadioSex = findViewById(R.id.radio_sex);
    }
}