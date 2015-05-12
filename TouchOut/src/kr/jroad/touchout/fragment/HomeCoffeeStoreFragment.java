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

public class HomeCoffeeStoreFragment extends Fragment implements
		HomeSortingStoreFragment.OnLocationReceiver {

	ListView cafeStoreList;
	SortingStoreListAdapter cafeStoreAdapter;

	int searchResult;
	TextView resultView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home_coffee_store_list,
				container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		cafeStoreList = (ListView) view.findViewById(R.id.coffee_store_list);
		SortingStoreListHeaderResultView resultHeaderView = new SortingStoreListHeaderResultView(
				getActivity());

		resultView = (TextView) resultHeaderView
				.findViewById(R.id.sorting_store_result_txt);
		cafeStoreList.addHeaderView(resultHeaderView, "searchResultHeader",
				false);

		cafeStoreList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(MyApplication.getContext(),
						StoreMenuActivity.class);

				// parcelable로 전달
				SortingStore d = (SortingStore) cafeStoreList
						.getItemAtPosition(position);
				i.putExtra(HomeAllStoreFragment.SELECTED_STORE, d);
				getActivity().startActivity(i);
				getActivity().overridePendingTransition(0, 0);
			}
		});

		cafeStoreAdapter = new SortingStoreListAdapter(getActivity());

		cafeStoreList.setAdapter(cafeStoreAdapter);

	}

	// int 1 => coffee
	// 현재 위치정보 필요!
	private void initData() {
		HomeSortingStoreFragment parentFragment = (HomeSortingStoreFragment) getParentFragment();
		Location location = parentFragment.getFixedLocation();
		String orderby = parentFragment.getOrderby();

		if (location != null) {
			NetworkManager.getInstance().getSortingStore(getActivity(), 1,
					37.466, 126.959,1 , orderby,
					new OnResultListener<SortingStoreResult>() {

						@Override
						public void onSuccess(SortingStoreResult result) {
							if (result.success == 1) {
								searchResult = result.result.item_cnt;
								resultView.setText("검색결과는 총 " + searchResult
										+ "개 입니다.");
								cafeStoreAdapter.clear();
								for (SortingStore d : result.result.items) {
									cafeStoreAdapter.add(d);
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
