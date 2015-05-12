package kr.jroad.touchout.activity;

import kr.jroad.touchout.dialog.ExtraDialogFragment;
import kr.jroad.touchout.dialog.TutorialDialogFragment;
import kr.jroad.touchout.fragment.FavoriteMainFragment;
import kr.jroad.touchout.fragment.HomeAllStoreFragment;
import kr.jroad.touchout.fragment.HomeFragment;
import kr.jroad.touchout.fragment.NowSaleFragment;
import kr.jroad.touchout.fragment.SearchMainFragment;
import kr.jroad.touchout.manager.PropertyManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static final String TUTORIAL_TAG = "tutorialDialog";

	private static final String MAIN_TAB1_ID = "tab1Home";
	private static final String MAIN_TAB2_ID = "tab2Favorite";
	private static final String MAIN_TAB3_ID = "tab3Search";
	private static final String MAIN_TAB4_ID = "tab4NowSale";
	private static final String MAIN_TAB5_ID = "tab5Extra";
	FragmentTabHost tabHost;
	ActionBar actionBar;

	public static final int MESSAGE_BACK_PRESSED_TIME_OUT = 0;
	public static final int TIMEOUT_BACK_PRESSED = 2000;
	boolean isBackPressed = false;
	boolean isFacebookLogin; // shared preference로 이동해야함
	boolean extraTabSelected = false;

	// back key twice pressed => destroy
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_BACK_PRESSED_TIME_OUT:
				isBackPressed = false;
				break;
			}
		}
	};

	@Override
	public void onBackPressed() {
		if (!isBackPressed) {
			isBackPressed = true;
			Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT)
					.show();
			mHandler.sendMessageDelayed(
					mHandler.obtainMessage(MESSAGE_BACK_PRESSED_TIME_OUT),
					TIMEOUT_BACK_PRESSED);
		} else {
			mHandler.removeMessages(MESSAGE_BACK_PRESSED_TIME_OUT);
			super.onBackPressed();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View tab1View = LayoutInflater.from(this).inflate(
				R.layout.main_tab1_home, null);
		View tab2View = LayoutInflater.from(this).inflate(
				R.layout.main_tab2_home, null);
		View tab3View = LayoutInflater.from(this).inflate(
				R.layout.main_tab3_home, null);
		View tab4View = LayoutInflater.from(this).inflate(
				R.layout.main_tab4_home, null);

		// tab setting
		tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.container);
		tabHost.addTab(tabHost.newTabSpec(MAIN_TAB1_ID).setIndicator(tab1View),
				HomeFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec(MAIN_TAB2_ID).setIndicator(tab2View),
				FavoriteMainFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec(MAIN_TAB3_ID).setIndicator(tab3View),
				SearchMainFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec(MAIN_TAB4_ID).setIndicator(tab4View),
				NowSaleFragment.class, null);

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals(MAIN_TAB1_ID)) {

				} else if (tabId.equals(MAIN_TAB2_ID)) {

				} else if (tabId.equals(MAIN_TAB3_ID)) {

				} else if (tabId.equals(MAIN_TAB4_ID)) {

				} else {
					// ExtraDialogFragment extraDialog = new
					// ExtraDialogFragment();
					// extraDialog.show(getSupportFragmentManager(),
					// MAIN_TAB5_ID);
					// tabHost.setCurrentTab(0);
				}
			}
		});

		Intent i = getIntent();
		if (i != null) {
			int requestCode = i.getIntExtra(
					HomeAllStoreFragment.REQUEST_SEARCH_TAB, 0);
			if (requestCode == HomeAllStoreFragment.REQUEST_SEARCH_CODE) {
				tabHost.setCurrentTabByTag(MAIN_TAB3_ID);
			}
		}

		Button mainExtraBtn = (Button) findViewById(R.id.main_extra_btn);
		mainExtraBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				Button btn = (Button) v;
//				btn.setSelected(!btn.isSelected());
				ExtraDialogFragment extraDialog = new ExtraDialogFragment();
				extraDialog.show(getSupportFragmentManager(), MAIN_TAB5_ID);
			}
		});

		// 로그인x || 자동로그인 x 일때
//		isFacebookLogin = PropertyManager.getInstance().isFacebookLogin();
//		if (!isFacebookLogin) {
//			TutorialDialogFragment tutorialDialog = new TutorialDialogFragment();
//			tutorialDialog.show(getSupportFragmentManager(), TUTORIAL_TAG);
//		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("MainActivity" , "called onStop");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i("MainActivity" , "called onSavedInstanceState");
	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Log.i("MainActivity", "called onAttachedToWindow");
		
		isFacebookLogin = PropertyManager.getInstance().isFacebookLogin();
		if (!isFacebookLogin) {
			TutorialDialogFragment tutorialDialog = new TutorialDialogFragment();
			tutorialDialog.show(getSupportFragmentManager(), TUTORIAL_TAG);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		// else if (id == android.R.id.home) {
		// toggle();
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("MainActivity" , "called onResume");		
	}
}
