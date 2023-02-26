package com.snhu.eventsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Button;
import android.text.TextWatcher;
import android.widget.Toast;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button login;
    Button register;

    // Instance of the app database
    EventDatabase eventDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        // Listen to any text changes on these fields
        username.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if ((username.getText().toString().isEmpty()) || (password.getText().toString().isEmpty())) {
                login.setEnabled(false);
                register.setEnabled(false);
            } else {
                login.setEnabled(true);
                register.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void login(View view) {
        toastMessage(view.getContext().getResources().getString(R.string.login_message));
        String usernameVal = username.getText().toString();
        String passwordVal = password.getText().toString();

        try {
            eventDatabase.addUser(usernameVal, passwordVal);
        } catch (Exception e) {
            toastMessage("Unable to login");
        } finally {
            Intent intent = new Intent(getApplicationContext(), EventListActivity.class);
            startActivity(intent);
        }
    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}