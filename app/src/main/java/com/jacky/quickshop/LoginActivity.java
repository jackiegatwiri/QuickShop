package com.jacky.quickshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.email) EditText mEmail;
    @BindView(R.id.password) EditText mPassword;
    @BindView(R.id.rememberme) TextView mRememberMe;
    @BindView(R.id.forgotPassword) TextView mForgotPassword;
    @BindView(R.id.loginbutton) Button mLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLogin.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }
    public boolean validation() {
        boolean valid = false;

        if (mEmail.getText().toString().isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches())) {
            mEmail.setError("Invalid email address");
        } else if (mPassword.getText().toString().isEmpty() || mPassword.getText().toString().length() < 8) {   //  !(isValidPassword(mpassword.getText().toString().trim()))  ){
            mPassword.setError("Invalid Password!");
        } else {
            valid = true;

        }
        return valid;
    }

    @Override
    public void onClick(View v) {

        if (v == mLogin) {
            if (validation()) {
                loginUser();

            }
        }
    }

    private void loginUser() {

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                String emails = mEmail.getText().toString();
                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("emails", emails);
                startActivity(intent);
            }
            else {
                Toast.makeText(LoginActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
