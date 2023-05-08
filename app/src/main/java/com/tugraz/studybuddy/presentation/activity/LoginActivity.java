package com.tugraz.studybuddy.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.tugraz.studybuddy.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int MIN_PASSWORD_LENGTH = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startPostSignInActivity();
        }

        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (!emailAndPasswordValid(email, password)) {
                return;
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    startPostSignInActivity();
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), getString(R.string.authentication_error), Toast.LENGTH_SHORT).show();
                }
            });
        });

        findViewById(R.id.buttonRegister).setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (!emailAndPasswordValid(email, password)) {
                return;
            }

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signUpWithEmail:success");
                    startPostSignInActivity();
                } else {
                    Log.w(TAG, "signUpWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), getString(R.string.email_already_in_use), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean emailAndPasswordValid(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.trim().length() < MIN_PASSWORD_LENGTH) {
            Toast.makeText(getApplicationContext(), getString(R.string.password_too_short), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void startPostSignInActivity() {
        startActivity(new Intent(this, OverviewActivity.class));
    }
}
