package com.jacky.quickshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.name)
    EditText mName;
    @BindView(R.id.emailid)
    EditText mEmail;
    @BindView(R.id.passwordRegister)
    EditText mPassword;
    @BindView(R.id.passwordConfirm)
    EditText mConfirmPass;
    @BindView(R.id.signUpbutton)
    Button mSignUp;
    private ProgressDialog loading;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        loading = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mSignUp.setOnClickListener(this);
    }
    public boolean validation(){
        boolean valid = false;

        if(mName.getText().toString().isEmpty()){
            mName.setError("Invalid name");
        }
        else if(mEmail.getText().toString().isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches())){
            mEmail.setError("Invalid email address");
        }
        else if(mPassword.getText().toString().isEmpty() || mPassword.getText().toString().length() < 8 ){   //  !(isValidPassword(mpassword.getText().toString().trim()))  ){
            mPassword.setError("Email should be at least 8 characters");
        }
        else if(!mConfirmPass.getText().toString().equals(mPassword.getText().toString())){
            mConfirmPass.setError("Password does not match");
        }
        else
        {
            valid = true;

        }
        return valid;
    }


    private void registerUser(){
        loading.setTitle("Create Account");
        loading.setMessage("Verification");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(RegisterActivity.this, CategoryActivity.class);
                    startActivity(intent);

                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                    Map newMap = new HashMap();
                    newMap.put("name", name);
                    newMap.put("email", email);
                    newMap.put("password", password);

                    currentUser.setValue(newMap);

                }
                else{
                    loading.dismiss();
                    Toast.makeText(RegisterActivity.this, "Could not register.. please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mSignUp){
            if (validation()) {
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                registerUser();
            }

        }
    }

}
