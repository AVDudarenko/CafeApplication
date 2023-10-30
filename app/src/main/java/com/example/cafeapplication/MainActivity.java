package com.example.cafeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private EditText editTextName;
	private EditText editTextPassword;
	private Button buttonSignIn;
	private String name;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();

		buttonSignIn.setOnClickListener(v -> {

			name = editTextName.getText().toString().trim();
			password = editTextPassword.getText().toString().trim();
			if (name.isEmpty() && password.isEmpty()) {
				Toast.makeText(MainActivity.this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
			} else {
				openOrderActivity(name);
			}

		});
	}

	private void initViews() {
		editTextName = findViewById(R.id.editTextName);
		editTextPassword = findViewById(R.id.editTextPassword);
		buttonSignIn = findViewById(R.id.buttonSignIn);
	}

	private void openOrderActivity(String userName) {
		Intent intent = MakeOrderActivity.newIntent(this, userName);
		startActivity(intent);
	}
}