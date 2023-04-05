package com.tugraz.studybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUsername = (EditText) findViewById(R.id.editTextUsernameLogin);
                String email = editTextUsername.getText().toString();

                EditText editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogin);
                String password = editTextPassword.getText().toString();

                if(email.isEmpty())
                {
                    editTextUsername.setError(getString(R.string.authentication_error));
                    return;
                }

                if(password.isEmpty())
                {
                    editTextPassword.setError(getString(R.string.authentication_error));
                    return;
                }

                //TODO implement proper login
                bypassLogin();
            }
        });

        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }
    private void openRegisterActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void bypassLogin() {
        Intent intent = new Intent(this, StudyOverview.class);
        startActivity(intent);
    }
}