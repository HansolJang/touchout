package kr.jroad.touchout.activity;

import kr.jroad.touchout.fragment.AppInfoDetailFragment;
import kr.jroad.touchout.fragment.AppInfoMainFragment;
import kr.jroad.touchout.view.CenterTextActionBarView;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

public class AppInfoActivity extends ActionBarActivity {
	
	public static final String APP_INFO_DETAIL_CONTENT = "detailContent";
	public static final String APP_INFO_DETAIL_POSITION = "detailPosition";
	
	ActionBar actionBar;
	CenterTextActionBarView actionBarView;
	TextView actionBarTitleView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_info);
		setActionBar();
		
		AppInfoMainFragment f = new AppInfoMainFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.app_info_container, f);
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
		actionBarView = new CenterTextActionBarView(AppInfoActivity.this);
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.main_orange_color));
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(
				Gravity.CENTER));
		actionBar.setHomeAsUpIndicator(R.drawable.back_btn_selector);
		actionBarTitleView = (TextView) actionBarView
				.findViewById(R.id.actionbar_title_txt);
		actionBarTitleView.setText("앱 정보");
	}
	
	public void changeDetail(int position) {
		
		AppInfoDetailFragment detailFragment = new AppInfoDetailFragment();
		Bundle b = new Bundle();
		
		switch(position) {
		case 0 :
			b.putString(APP_INFO_DETAIL_CONTENT, getResources().getString(R.string.readme1));
			break;
		case 1 :
			b.putString(APP_INFO_DETAIL_CONTENT, getResources().getString(R.string.readme2));
			break;
		case 2 :
			b.putString(APP_INFO_DETAIL_CONTENT, getResources().getString(R.string.readme3));
			break;
		case 3 :
			b.putString(APP_INFO_DETAIL_CONTENT, getResources().getString(R.string.readme4));
			break;
		}
		
		b.putInt(APP_INFO_DETAIL_POSITION, position);
		
		detailFragment.setArguments(b);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.app_info_container, detailFragment);
		//백스텍 안됨
		ft.addToBackStack(null);
		ft.commit();
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
