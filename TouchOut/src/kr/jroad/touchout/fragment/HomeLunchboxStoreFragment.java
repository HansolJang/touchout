package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.activity.StoreMenuActivity;
import kr.jroad.touchout.adapter.SortingStoreListAdapter;
import kr.jroad.touchout.data.SortingStore;
import kr.jroad.touchout.data.SortingStoreResult;
import kr.jroad.touchout.extra.MyApplication;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.view.SortingStoreListHeaderResultView;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class HomeLunchboxStoreFragment extends Fragment implements HomeSortingStoreFragment.OnLocationReceiver{

	ListView lunchboxStoreList;
	SortingStoreListAdapter lunchboxStoreAdapter;

	int searchResult;
	TextView resultView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home_lunchbox_store_list,
				container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		lunchboxStoreList = (ListView) view
				.findViewById(R.id.lunchbox_store_list);
		SortingStoreListHeaderResultView resultHeaderView = new SortingStoreListHeaderResultView(
				getActivity());

		resultView = (TextView) resultHeaderView
				.findViewById(R.id.sorting_store_result_txt);
		lunchboxStoreList.addHeaderView(resultHeaderView, "searchResultHeader",
				false);

		lunchboxStoreList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(MyApplication.getContext(),
						StoreMenuActivity.class);

				// parcelable로 전달
				SortingStore d = (SortingStore) lunchboxStoreList
						.getItemAtPosition(position);
				i.putExtra(HomeAllStoreFragment.SELECTED_STORE, d);
				getActivity().startActivity(i);
				getActivity().overridePendingTransition(0, 0);
			}
		});

		lunchboxStoreAdapter = new SortingStoreListAdapter(getActivity());

		lunchboxStoreList.setAdapter(lunchboxStoreAdapter);

	}

	// int 2 => lunchbox
	// 현재 위치정보 필요!
	private void initData() {
		HomeSortingStoreFragment parentFragment = (HomeSortingStoreFragment)getParentFragment();
		Location location = parentFragment.getFixedLocation();
		String orderby = parentFragment.getOrderby();
		
		if(location != null) {
			NetworkManager.getInstance().getSortingStore(getActivity(), 2,
					location.getLatitude(), location.getLongitude(), 1, orderby,
					new OnResultListener<SortingStoreResult>() {

						@Override
						public void onSuccess(SortingStoreResult result) {
							if (result.success == 1) {
								searchResult = result.result.item_cnt;
								resultView.setText("총 검색결과는 " + searchResult
										+ "개 입니다.");
								lunchboxStoreAdapter.clear();
								for (SortingStore d : result.result.items) {
									lunchboxStoreAdapter.add(d);
								}
							}
						}

						@Override
						public void onFail(int code) {
							// TODO Auto-generated method stub

						}
					});
		}
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		initData();
	}

	@Override
	public void onLocationReceived(Location location) {
		initData();
	}

	@Override
	public void onOrderbyChanged(String orderby) {
		initData();
	}

}
