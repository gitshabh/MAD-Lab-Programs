package com.example.sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText textUsername,textPassword;
    Button signup;

    int f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUIElement();
        String np=".*\\d.*";
        String up=".*[A-Z].*";
        String lp=".*[a-z].*";
        String sp=".*[@#$%^&+=].*";
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=0;
                String username = textUsername.getText().toString();
                String password = textPassword.getText().toString();
                if(username.isEmpty()||password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter username and password.", Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(password.length()<8){
                    Toast.makeText(MainActivity.this, "Enter password of length 8.", Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(!password.matches(np)){
                    Toast.makeText(MainActivity.this, "Password should contain numbers.", Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(!password.matches(lp)){
                    Toast.makeText(MainActivity.this, "Password should lowercase characters.", Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(!password.matches(up)){
                    Toast.makeText(MainActivity.this, "Password should uppercase characters.", Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(!password.matches(sp)){
                    Toast.makeText(MainActivity.this, "Password should speical characters.", Toast.LENGTH_SHORT).show();
                    f=1;
                }
                if(f==0){
                    Bundle bundle= new Bundle();
                    bundle.putString("Username",username);
                    bundle.putString("Password",password);

                    Intent intent=new Intent(MainActivity.this,login.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void bindUIElement(){
        textUsername = (EditText) findViewById(R.id.sign_up_username);
        textPassword = (EditText) findViewById(R.id.sign_up_password);
        signup = (Button) findViewById(R.id.sign_up_btn);
    }
}