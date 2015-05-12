package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.activity.StoreMenuActivity;
import kr.jroad.touchout.adapter.SortingStoreListAdapter;
import kr.jroad.touchout.data.SortingStore;
import kr.jroad.touchout.data.SortingStoreResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.view.SortingStoreListHeaderAdsView;
import kr.jroad.touchout.view.SortingStoreListHeaderResultView;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class HomeAllStoreFragment extends Fragment implements HomeSortingStoreFragment.OnLocationReceiver {

	ListView allStoreList;
	SortingStoreListAdapter allStoreAdapter;
	String storeName;
	int searchResult;
	TextView resultView;
	ActionBar actionBar;
	String orderby;

	public static final String SELECTED_STORE = "selectedStore";
	public static final String SELECTED_PLACE_TAG = "choiceDialogTag";
	public static final String REQUEST_SEARCH_TAB = "requestSearchTab";
	public static final int REQUEST_SEARCH_CODE = 333;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home_all_store_list,
				container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		allStoreList = (ListView) view.findViewById(R.id.sorting_store_list);
		SortingStoreListHeaderAdsView adsHeaderView = new SortingStoreListHeaderAdsView(
				getActivity());
		SortingStoreListHeaderResultView resultHeaderView = new SortingStoreListHeaderResultView(
				getActivity());

		adsHeaderView.setClickable(false);
		resultHeaderView.setClickable(false);

		// 결과에 따라 set해줘야함
		resultView = (TextView) resultHeaderView
				.findViewById(R.id.sorting_store_result_txt);

		allStoreList.addHeaderView(adsHeaderView, "adsHeader", false);
		allStoreList.addHeaderView(resultHeaderView, "searchResultHeader",
				false);

		allStoreAdapter = new SortingStoreListAdapter(getActivity());

		allStoreList.setAdapter(allStoreAdapter);

		allStoreList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getActivity(), StoreMenuActivity.class);
				// parcelable sorting store
				SortingStore d = (SortingStore) allStoreList
						.getItemAtPosition(position);
				i.putExtra(SELECTED_STORE, d);
				getActivity().startActivity(i);
				getActivity().overridePendingTransition(0, 0);
			}
		});
	}

	// int 0 => all
	
	//location.getLatitude(), location.getLongitude()
	
	
	// 현재 위치정보 필요!
	private void initData() {
		HomeSortingStoreFragment parentFragment = (HomeSortingStoreFragment) getParentFragment();
		Location location = parentFragment.getFixedLocation();
		orderby = parentFragment.getOrderby();
		//위도경도가 0이 아니면
		if(location != null ) {
			NetworkManager.getInstance().getSortingStore(getActivity(), 1,
					37.466, 126.959,  1, orderby,
					new OnResultListener<SortingStoreResult>() {

						@Override
						public void onSuccess(SortingStoreResult result) {
							if (result.success == 1) {
								searchResult = result.result.item_cnt;
								resultView.setText("검색결과는 총 " + searchResult
										+ "개 입니다.");
								allStoreAdapter.clear();
								for (SortingStore d : result.result.items) {
									allStoreAdapter.add(d);
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
