package com.example.mycloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.classes.objects.Utente;
import com.classes.utility.DB;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SigninActivity extends AppCompatActivity {
    GoogleSignInClient client;
    private static final int RC_SIGN_IN = 9001;

    FirebaseAuth auth;

    EditText inputName, inputSurname, inputUsername, inputEmail, inputPassword, inputConfirmPassword;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        auth = FirebaseAuth.getInstance();

        inputName = findViewById(R.id.ev_name);
        inputSurname = findViewById(R.id.ev_surname);
        inputUsername = findViewById(R.id.ev_username);
        inputEmail = findViewById(R.id.ev_email);
        inputPassword = findViewById(R.id.ev_password);
        inputConfirmPassword = findViewById(R.id.ev_passwordConfirm);

        loadingBar = new ProgressDialog(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        client = GoogleSignIn.getClient(this, gso);
    }

    public void registrationCheck(View v) {
        String name, surname, username, email, password, confirmPassword;

        int minUsernameChar = 6;
        int minPasswordChar = 8;

        name = inputName.getText().toString();
        surname = inputSurname.getText().toString();
        username = inputUsername.getText().toString();
        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();
        confirmPassword = inputConfirmPassword.getText().toString();

        boolean error = false;

        if(name.isEmpty()) {
            MyCloset.inputError(inputName, getString(R.string.invalid_name));
            error = true;
        }
        if(surname.isEmpty()) {
            MyCloset.inputError(inputSurname, getString(R.string.invalid_surname));
            error = true;
        }
        if(username.isEmpty() || username.length() < minUsernameChar) {
            MyCloset.inputError(inputUsername, getString(R.string.invalid_username));
            error = true;
        }
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            MyCloset.inputError(inputEmail, getString(R.string.invalid_email));
            error = true;
        }
        if(password.isEmpty() || password.length() < minPasswordChar) {
            MyCloset.inputError(inputPassword, getString(R.string.invalid_password));
            error = true;
        }
        if(confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
            MyCloset.inputError(inputConfirmPassword, getString(R.string.invalid_confirmPassword));
            error = true;
        }

        if(!error) {
            loadingBar.setTitle(getString(R.string.registration_in_progress));
            loadingBar.setMessage(getString((R.string.please_wait)));
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(SigninActivity.this, getString(R.string.registration_succeed), Toast.LENGTH_LONG).show();
                    // create the user wardrobe
                    // get the previous page of the user
                    Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    loadingBar.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SigninActivity.this, getString(R.string.registration_failed) + ": " + e, Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            });
        }
    }

    public void pageLogin(View v) {
        Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        //GoogleSignInAccount user = GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser user = auth.getCurrentUser();
        updateUI(user);
    }

    private void revokeAccess() {
        client.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(SigninActivity.this, getString(R.string.revoked_access), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void authButton(View v) {
        signIn();
    }

    private void signIn() {
        Intent signInIntent = client.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        client.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // alert to notify the user
                Log.d("DEBUG", "Utente disconnesso");
            }
        });
    }

    public void updateUI(FirebaseUser user) {
        if(user == null) {
            Toast.makeText(SigninActivity.this, getString(R.string.login_failed), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SigninActivity.this, getString(R.string.login_succeed) + "\n" + getString(R.string.welcome) + " " + user.getDisplayName(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(SigninActivity.this, getString(R.string.registration_failed) + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
            updateUI(null);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SigninActivity.this, getString(R.string.registration_succeed), Toast.LENGTH_LONG).show();
                FirebaseUser user = auth.getCurrentUser();
                updateUI(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SigninActivity.this, getString(R.string.registration_failed) + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
                updateUI(null);
            }
        });
    }
}