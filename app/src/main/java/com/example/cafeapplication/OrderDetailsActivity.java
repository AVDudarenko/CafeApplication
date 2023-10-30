package com.example.cafeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

	private static final String EXTRA_USER_NAME = "userName";
	private static final String EXTRA_DRINK = "drink";
	private static final String EXTRA_TYPE_OF_DRINK = "typeOfDrink";
	private static final String EXTRA_ADDITIVES = "additives";

	private TextView textViewName;
	private TextView textViewDrink;
	private TextView textViewTypeOfDrink;
	private TextView textViewAdditives;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);

		initViews();
		setName();
		setDrink();
		setDrinkType();
		setAdditives();
	}

	public static Intent newIntent(Context context, String userName, String drink, String typeOfDrink, String additives) {
		Intent intent = new Intent(context, OrderDetailsActivity.class);
		intent.putExtra(EXTRA_USER_NAME, userName);
		intent.putExtra(EXTRA_DRINK, drink);
		intent.putExtra(EXTRA_TYPE_OF_DRINK, typeOfDrink);
		intent.putExtra(EXTRA_ADDITIVES, additives);

		return intent;
	}

	private void initViews() {
		textViewName = findViewById(R.id.textViewName);
		textViewDrink = findViewById(R.id.textViewDrink);
		textViewTypeOfDrink = findViewById(R.id.textViewTypeOfDrink);
		textViewAdditives = findViewById(R.id.textViewAdditives);
	}

	private void setName() {
		textViewName.setText(getIntent().getStringExtra(EXTRA_USER_NAME));
	}

	private void setDrink() {
		textViewDrink.setText(getIntent().getStringExtra(EXTRA_DRINK));
	}

	private void setDrinkType() {
		textViewTypeOfDrink.setText(getIntent().getStringExtra(EXTRA_TYPE_OF_DRINK));
	}

	private void setAdditives() {
		textViewAdditives.setText(getIntent().getStringExtra(EXTRA_ADDITIVES));
	}
}