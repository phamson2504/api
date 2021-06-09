package com.example.dangnhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhap extends AppCompatActivity {

    TextView editUser,editPass;
    Button dangNhap;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        editUser = findViewById(R.id.userdn);
        editPass = findViewById(R.id.passworddn);
        dangNhap = findViewById(R.id.loggin);
        mAuth = FirebaseAuth.getInstance();
        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangnhap();
                Intent intent2 = new Intent(DangNhap.this,GiaoDien.class);
                startActivity(intent2);
            }
        });
    }
    public void dangnhap(){
        String email = editUser.getText().toString();
        String password = editPass.getText().toString();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DangNhap.this,"thanh cong",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DangNhap.this,"ko thanh cong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}