package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.PaymentHistoryListAdapter;
import kr.jroad.touchout.data.PaymentHistoryResult;
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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.TextView;

public class ExtraPaymentHistoryFragment extends PagerFragment {

	ExpandableListView paymentHistoryList;
	PaymentHistoryListAdapter paymentHistoryAdapter;
	ActionBar actionBar;
	View actionBarView;
	TextView titleView;
	ListEmptyView emptyView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setHasOptionsMenu(true);
		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.main_orange_color));
		actionBarView = LayoutInflater.from(getActivity()).inflate(
				R.layout.actionbar_center_text, null);

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_payment_history, container,
				false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		titleView = (TextView) actionBarView
				.findViewById(R.id.actionbar_title_txt);
		paymentHistoryList = (ExpandableListView) view
				.findViewById(R.id.payment_history_list);
		paymentHistoryAdapter = new PaymentHistoryListAdapter(getActivity());

		paymentHistoryList.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			
			@Override
			public void onGroupCollapse(int groupPosition) {
				paymentHistoryList.expandGroup(groupPosition);
			}
		});
		
		paymentHistoryList.setAdapter(paymentHistoryAdapter);
		
		emptyView = (ListEmptyView)view.findViewById(R.id.payment_history_empty_view);
		emptyView.setData(R.drawable.mywallet_payment_noresult, "결제 내역이 없습니다", "지금 바로 터치아웃을 즐겨보세요!");

		initPaymentData();

	}

	private void initPaymentData() {
		NetworkManager.getInstance().getPaymentHistoryData(getActivity(), 1,
				new OnResultListener<PaymentHistoryResult>() {

					@Override
					public void onSuccess(PaymentHistoryResult result) {
						if (result.success == 1) {
							paymentHistoryAdapter.clearAll();
							paymentHistoryAdapter
									.setPaymentHistoryList(result.result);
							paymentHistoryList.setEmptyView(emptyView);
						}

						for (int i = 0; i < paymentHistoryAdapter
								.getGroupCount(); i++) {
							paymentHistoryList.expandGroup(i);
						}
					}

					@Override
					public void onFail(int code) {

					}
				});

	}

	@Override
	public void onPageCurrent() {
		super.onPageCurrent();
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(
				Gravity.CENTER));
		titleView.setText("결제 내역");
		initPaymentData();
	}

}
