package com.example.cafeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {
	private static final String EXTRA_USER_NAME = "userName";

	private TextView textViewHello;
	private TextView textViewAdditives;

	private RadioGroup radioGroupDrinks;
	private RadioButton radioButtonTea;
	private RadioButton radioButtonCoffee;

	private CheckBox checkBoxSugar;
	private CheckBox checkBoxMilk;
	private CheckBox checkBoxLemon;

	private Spinner spinnerTypeOfCoffee;
	private Spinner spinnerTypeOfTea;

	private Button buttonMakeOrder;

	private String drink;
	private String userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_order);

		initViews();
		setUserName();
		radioGroupDrinks.setOnCheckedChangeListener((group, checkedId) -> {
			if (checkedId == radioButtonTea.getId()) {
				onUserCheckTea();
			} else {
				onUserCheckCoffee();
			}

		});

		radioButtonTea.setChecked(true);

		buttonMakeOrder.setOnClickListener(v -> {
			makeOrder();
		});

	}

	public static Intent newIntent(Context context, String userName) {
		Intent intent = new Intent(context, MakeOrderActivity.class);
		intent.putExtra(EXTRA_USER_NAME, userName);
		return intent;
	}

	private void initViews() {
		textViewHello = findViewById(R.id.textViewHello);
		textViewAdditives = findViewById(R.id.textViewAdditives);
		radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
		radioButtonTea = findViewById(R.id.radioButtonTea);
		radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
		checkBoxSugar = findViewById(R.id.checkBoxSugar);
		checkBoxMilk = findViewById(R.id.checkBoxMilk);
		checkBoxLemon = findViewById(R.id.checkBoxLemon);
		spinnerTypeOfCoffee = findViewById(R.id.spinnerTypeOfCoffee);
		spinnerTypeOfTea = findViewById(R.id.spinnerTypeOfTea);
		buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
	}

	private void setUserName() {
		userName = getIntent().getStringExtra(EXTRA_USER_NAME);
		String greetings = getString(R.string.hello_s_what_do_you_prefer, userName);
		textViewHello.setText(greetings);
	}

	private void onUserCheckTea() {
		drink = getString(R.string.tea);
		String additivesLabel = getString(R.string.what_add_to_your_s, drink);
		textViewAdditives.setText(additivesLabel);
		checkBoxLemon.setVisibility(View.VISIBLE);
		spinnerTypeOfTea.setVisibility(View.VISIBLE);
		spinnerTypeOfCoffee.setVisibility(View.INVISIBLE);
	}

	private void onUserCheckCoffee() {
		drink = getString(R.string.coffee);
		String additivesLabel = getString(R.string.what_add_to_your_s, drink);
		textViewAdditives.setText(additivesLabel);
		checkBoxLemon.setVisibility(View.INVISIBLE);
		spinnerTypeOfTea.setVisibility(View.INVISIBLE);
		spinnerTypeOfCoffee.setVisibility(View.VISIBLE);
	}

	private void makeOrder() {
		ArrayList<String> additives = new ArrayList<>();
		if (checkBoxSugar.isChecked()) {
			additives.add(checkBoxSugar.getText().toString());
		}
		if (checkBoxMilk.isChecked()) {
			additives.add(checkBoxMilk.getText().toString());
		}
		if (radioButtonTea.isChecked() && checkBoxLemon.isChecked()) {
			additives.add(checkBoxLemon.getText().toString());
		}
		String drinkType = "";
		if (radioButtonTea.isChecked()) {
			drinkType = spinnerTypeOfTea.getSelectedItem().toString();
		} else if (radioButtonCoffee.isChecked()) {
			drinkType = spinnerTypeOfCoffee.getSelectedItem().toString();
		}

		Intent intent = OrderDetailsActivity.newIntent(this, userName, drink, drinkType, additives.toString());
		startActivity(intent);
	}

}