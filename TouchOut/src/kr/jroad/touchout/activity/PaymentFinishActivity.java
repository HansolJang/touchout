package kr.jroad.touchout.activity;

import java.text.DecimalFormat;

import kr.jroad.touchout.data.PaymentList;
import kr.jroad.touchout.view.CenterTextActionBarView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentFinishActivity extends ActionBarActivity {
	
	ActionBar actionBar;
	CenterTextActionBarView actionBarView;
	TextView actionBarTitleView;
	PaymentList paymentResult;
	
	TextView menuView;
	TextView storeNameView;
	TextView totalPriceView;
	TextView payTypeView;
	
	DecimalFormat thousandFormat = new DecimalFormat("#,###");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment_finish);
		actionBar = getSupportActionBar();
		actionBarView = new CenterTextActionBarView(this);
		actionBarTitleView = (TextView) actionBarView.findViewById(R.id.actionbar_title_txt);
		
		menuView = (TextView)findViewById(R.id.payment_f_menu_txt);
		storeNameView = (TextView)findViewById(R.id.payment_f_store_txt);
		totalPriceView = (TextView)findViewById(R.id.payment_f_price);
		payTypeView = (TextView)findViewById(R.id.way_to_payment_f);
		
		StringBuilder sb = new StringBuilder();
		
		Intent payIntent = getIntent();
		paymentResult = payIntent.getParcelableExtra(PaymentActivity.GO_PAYMENT_FINISH);
		if(paymentResult != null) {
			for(int i = 0; i < paymentResult.items.size(); i++) {
				sb.append(paymentResult.items.get(i).menuName);
				if(i != paymentResult.items.size()-1) {
					sb.append(",");
				}
			}
			menuView.setText(sb.toString());
			storeNameView.setText(paymentResult.store_name);

			totalPriceView.setText(thousandFormat.format(paymentResult.total_amount));
			if(paymentResult.pay_type == 1) {
				payTypeView.setText("신용카드");
			}else {
				payTypeView.setText("휴대폰");
			}
		}
		
		Button btnCancel = (Button)findViewById(R.id.payment_f_cancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(PaymentFinishActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		});
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(PaymentFinishActivity.this, MainActivity.class);
		startActivity(i);
		finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setLogo(R.drawable.null_image);
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.main_orange_color));
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(Gravity.CENTER));
		actionBar.setLogo(R.drawable.null_image);
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.main_orange_color));
		actionBar.setCustomView(actionBarView);
		actionBarTitleView.setText("결제 완료");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
