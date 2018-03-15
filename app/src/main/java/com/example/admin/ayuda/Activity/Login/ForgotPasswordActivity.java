package com.example.admin.ayuda.Activity.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.ayuda.R;


public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText forgotPassEmailTextBox;
    private Button forgotPassEnterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        forgotPassEmailTextBox = findViewById(R.id.ForgotPassEmailTextBox);
        forgotPassEnterButton = findViewById(R.id.ForgotPassEnterButton);

    }
}
