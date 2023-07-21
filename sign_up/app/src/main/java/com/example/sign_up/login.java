package com.example.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText textUsername, textPassword;
    Button login_btn;
    Integer clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUIElement();
        Bundle bundle = getIntent().getExtras();
        String sign_user = bundle.getString("Username");
        String sign_pass = bundle.getString("Password");
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();
                if (username.equals(sign_user) && password.equals(sign_pass)) {
                     Toast.makeText(login.this, "Successful login", Toast.LENGTH_SHORT).show();
                } else {
                    if (clickCount < 2) {
                        Toast.makeText(login.this, "Failed login Attempt", Toast.LENGTH_SHORT).show();
                        clickCount++;
                    } else {
                        Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        login_btn.setEnabled(false);
                        login_btn.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    private void bindUIElement() {
        textUsername = (EditText) findViewById(R.id.login_username);
        textPassword = (EditText) findViewById(R.id.login_password);
        login_btn = (Button) findViewById(R.id.login_btn);
    }
}