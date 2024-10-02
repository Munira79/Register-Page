package com.example.regpage;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword, etMobile;
    private Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etUsername = findViewById(R.id.et_register_username);
        etEmail = findViewById(R.id.et_register_email);
        etPassword = findViewById(R.id.et_register_password);
        etConfirmPassword = findViewById(R.id.et_register_confirm_password);
        etMobile = findViewById(R.id.et_register_mobile);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(v -> {
            if (validateInputs()) {
                // Proceed with registration logic here
                Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
            }
        });


        btnLogin.setOnClickListener(v -> {
            // Handle login redirection or action here
            Toast.makeText(MainActivity.this, "Redirect to Login", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean validateInputs() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();


        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Username is required");
            etUsername.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email address");
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return false;
        } else if (password.length() < 6) {
            etPassword.setError("Password should be at least 6 characters");
            etPassword.requestFocus();
            return false;
        } else if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            etPassword.setError("Password must contain at least one uppercase letter");
            etPassword.requestFocus();
            return false;
        } else if (!Pattern.compile("[a-z]").matcher(password).find()) {
            etPassword.setError("Password must contain at least one lowercase letter");
            etPassword.requestFocus();
            return false;
        }


        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Please confirm your password");
            etConfirmPassword.requestFocus();
            return false;
        } else if (!confirmPassword.equals(password)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mobile)) {
            etMobile.setError("Mobile number is required");
            etMobile.requestFocus();
            return false;
        } else if (!Pattern.compile("^01[0-9]{9}$").matcher(mobile).matches()) {
            etMobile.setError("Mobile number must be 11 digits and start with '01'");
            etMobile.requestFocus();
            return false;
        }

        return true;
    }
}
