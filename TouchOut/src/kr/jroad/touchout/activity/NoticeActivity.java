package kr.jroad.touchout.activity;

import kr.jroad.touchout.adapter.NoticeListAdapter;
import kr.jroad.touchout.data.NoticeItem;
import kr.jroad.touchout.data.NoticeResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.view.CenterTextActionBarView;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class NoticeActivity extends ActionBarActivity {

	ExpandableListView noticeList;
	NoticeListAdapter noticeAdapter;
	ActionBar actionBar;
	View actionBarView;
	TextView actionBarTitleView;
	
	TextView titleView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		setActionBar();
		
		noticeList = (ExpandableListView) findViewById(R.id.notice_list);
		noticeAdapter = new NoticeListAdapter(this);
		noticeList.setAdapter(noticeAdapter);
		
		
	}

	private void initNoticeData() {
		NetworkManager.getInstance().getNoticeData(this, new OnResultListener<NoticeResult>() {
			
			@Override
			public void onSuccess(NoticeResult result) {
				noticeAdapter.clear();
				for(NoticeItem data : result.result.items) {
					noticeAdapter.add(data.title, data);
				}
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initNoticeData();
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
		actionBarView = new CenterTextActionBarView(NoticeActivity.this);
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.main_orange_color));
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(
				Gravity.CENTER));
		actionBar.setHomeAsUpIndicator(R.drawable.back_btn_selector);
		actionBarTitleView = (TextView) actionBarView
				.findViewById(R.id.actionbar_title_txt);
		actionBarTitleView.setText("공지사항");
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
