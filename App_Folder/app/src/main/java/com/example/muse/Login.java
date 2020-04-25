package com.example.muse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }


    public void buttonClicked(View v) { //login button
        EditText userView = (EditText) findViewById(R.id.user);
        String username = userView.getText().toString();
        EditText passView = (EditText) findViewById(R.id.pass);
        String password = passView.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            validate(username, password);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please fill out all the fields",Toast.LENGTH_SHORT).show();
        }
    }

    public void signupButton(View v) {
        System.out.println("test");
            openActivityCreate();
        }

        public void openActivityCreate(){
            Intent intent = new Intent (this,Signup.class);
            startActivity(intent);
        }

    public void openMain(){     //questionaire
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }



    private void validate (String username, String password){   //validate user, pass

        progressDialog.setMessage("Loading...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                    openMain();
                }
                else{

                    Toast.makeText(getApplicationContext(),"Login failed, try again",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }


}
