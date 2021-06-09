package com.example.dangnhap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button dangky,dangnhap;
    EditText editpassword,edituser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editpassword =findViewById(R.id.password);
        edituser = findViewById(R.id.user);
        mAuth = FirebaseAuth.getInstance();
        dangky= findViewById(R.id.dangky);
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky();

            }

        });
        dangnhap = findViewById(R.id.logindk);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,DangNhap.class);
                startActivity(intent2);
            }
        });

    }
    public void dangky(){
        String email = edituser.getText().toString();
        String password = editpassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user=mAuth.getCurrentUser();
                            String userId= user.getUid();
                            mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                            HashMap<String,String> hashMap =new HashMap<>();
                            hashMap.put("userId",userId);
                            hashMap.put("email",email);
                            hashMap.put("password",password);
                             mDatabaseReference.setValue(hashMap);
                            Toast.makeText(MainActivity.this,"thanh cong",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"ko thanh cong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}