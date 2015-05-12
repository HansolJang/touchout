package kr.jroad.touchout.activity;

import kr.jroad.touchout.fragment.AccountInfoFragment;
import kr.jroad.touchout.fragment.AccountWithdrawalFragment;
import kr.jroad.touchout.view.CenterTextActionBarView;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AccountInfoAcitivity extends ActionBarActivity {
	
	ActionBar actionBar;
	CenterTextActionBarView actionBarView;
	TextView actionBarTitleView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_info);
		setActionBar();
		
		AccountInfoFragment f = new AccountInfoFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.account_info_container, f);
		ft.commit();
		
	}
	
	public void setActionBar() {
		actionBar = getSupportActionBar();
		actionBar = getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.main_orange_color));
		actionBarView = new CenterTextActionBarView(AccountInfoAcitivity.this);
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.main_orange_color));
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(
				Gravity.CENTER));
		actionBar.setHomeAsUpIndicator(R.drawable.back_btn_selector);
		actionBarTitleView = (TextView) actionBarView
				.findViewById(R.id.actionbar_title_txt);
		actionBarTitleView.setText("계정 정보");
	}
	
	public void goWithdrawalFragment() {
		AccountWithdrawalFragment f = new AccountWithdrawalFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.account_info_container, f);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void popWithdrawalFragment() {
		FragmentManager fm = getSupportFragmentManager();
		fm.popBackStack();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home) {
			finish();
			return true;
		} else {
			return false;
		}
	}
}
