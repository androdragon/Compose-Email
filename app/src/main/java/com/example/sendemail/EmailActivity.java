package com.example.sendemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.sendemail.databinding.ActivityEmailBinding;

public class EmailActivity extends AppCompatActivity {
    private ActivityEmailBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityEmailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.sendEmail.setOnClickListener(view -> {
            if (validate()){
                composeEmail(
                        mBinding.email.getText().toString(),
                        mBinding.subject.getText().toString(),
                        mBinding.message.getText().toString()
                );
            }
        });

    }

    private boolean validate(){
        if (mBinding.email.getText().toString().isEmpty()){
            mBinding.email.setError("Enter email");
            mBinding.email.requestFocus();
            return false;
        }else if(mBinding.email.getText().toString().length() < 5 ||
                !mBinding.email.getText().toString().contains(".") ||
                !mBinding.email.getText().toString().contains("@")
        ){
            mBinding.email.setError("Enter valid email");
            mBinding.email.requestFocus();
            return false;
        }
        return true;
    }

    private void composeEmail(String emailTo,String subject, String message){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);

    }
}