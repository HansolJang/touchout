package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.ExtraMyWalletActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.StampListAdapter;
import kr.jroad.touchout.data.StampItem;
import kr.jroad.touchout.data.StampResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.view.ListEmptyView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ExtraStampFragment extends PagerFragment {

	ListView stampList;
	StampListAdapter stampAdapter;
	ActionBar actionBar;
	View actionBarView;
	TextView titleView;
	ListEmptyView emptyView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBarView = LayoutInflater.from(getActivity()).inflate(
				R.layout.actionbar_center_text, null);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.main_orange_color));
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_stamp, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		titleView = (TextView) actionBarView
				.findViewById(R.id.actionbar_title_txt);
		stampList = (ListView) view.findViewById(R.id.wallet_stamp_list);
		stampAdapter = new StampListAdapter(getActivity());
		stampList.setAdapter(stampAdapter);
		emptyView = (ListEmptyView)view.findViewById(R.id.wallet_stamp_empty_view);
		emptyView.setData(R.drawable.mywallet_stamp_noresult, "보유중인 스탬프 내역이 없습니다!", "스탬프를 모아 할인혜택을 받아보세요");
	}

	private void initStampData() {
		NetworkManager.getInstance().getStampData(getActivity(), 1,
				new OnResultListener<StampResult>() {

					@Override
					public void onSuccess(StampResult result) {
						stampAdapter.clear();
						if(result.result.item_cnt != 0) {
							for (StampItem data : result.result.items) {
								stampAdapter.add(data);
							}
						} else {
//							empty뷰 해야함
							stampList.setEmptyView(emptyView);
						}
						
						//액티비티 상단의 스탬프 개수 세팅
						((ExtraMyWalletActivity)getActivity()).setStampStoreView(result.result.item_cnt);
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onPageCurrent() {
		super.onPageCurrent();
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(
				Gravity.CENTER));
		titleView.setText("스탬프");
		initStampData();
	}

}
