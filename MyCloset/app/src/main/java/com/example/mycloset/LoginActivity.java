package com.example.mycloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    FirebaseAuth auth;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.ev_email);
        inputPassword = findViewById(R.id.ev_password);

        loadingBar = new ProgressDialog(this);
    }

    public void loginCheck(View v) {
        String email, password;

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        Boolean error = false;
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            MyCloset.inputError(inputEmail, getString(R.string.incorrect_email));
            error = true;
        }
        if(password.isEmpty()) {
            MyCloset.inputError(inputPassword, getString(R.string.incorrect_password));
            error = true;
        }

        if(!error) {
            loadingBar.setTitle(getString(R.string.login_in_progress));
            loadingBar.setMessage(getString((R.string.please_wait)));
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_succeed), Toast.LENGTH_LONG).show();
                    // call the previous page
                    loadingBar.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_failed) + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            });
        }
    }

    public void pageSignup(View v) {
        Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void forgotPassword(View v) {
        Toast.makeText(this, "Password dimenticata", Toast.LENGTH_LONG).show();
    }
}