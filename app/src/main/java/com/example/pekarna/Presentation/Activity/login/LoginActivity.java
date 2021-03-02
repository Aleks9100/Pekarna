package com.example.pekarna.Presentation.Activity.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.pekarna.Database.Data;
import com.example.pekarna.Presentation.Activity.Main.MainActivity;
import com.example.pekarna.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data = Data.getInstance(this);
        NumberPicker userTypePicker = binding.userNP;
        userTypePicker.setDisplayedValues(new String[]{"Администратор","Клиент"});
        userTypePicker.setMaxValue(1);
        userTypePicker.setMinValue(0);
        binding.floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                data.admin = binding.userNP.getValue() == 0;
                startActivity(intent);
            }
        });
    }
}