package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.activity.StoreMenuActivity;
import kr.jroad.touchout.adapter.SortingStoreListAdapter;
import kr.jroad.touchout.data.SearchResult;
import kr.jroad.touchout.data.SortingStore;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager;
import kr.jroad.touchout.manager.PropertyManager.OnCartChangeListener;
import kr.jroad.touchout.view.ListEmptyView;
import kr.jroad.touchout.view.SortingStoreListHeaderResultView;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResultFragment extends Fragment {

	String keyword;
	ListView resultList;
	SortingStoreListAdapter resultAdapter;
	float latitude;
	float longitude;
	String provider;
	LocationManager lm;
	
	int searchResult;
	TextView resultView;
	
	ListEmptyView emptyView;
	ImageView cartStrapView;
	FrameLayout cartLayout;
	boolean isCartSelected = false;
	FrameLayout searchContainer;
	TextView cartNumberView;
	int cartNumber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		Bundle b = getArguments();
		if (b != null) {
			keyword = b.getString(SearchMainFragment.SEARCH_KEYWORD);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search_result, container,
				false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		PropertyManager.getInstance().addOnCartChangeListener(listener);

		resultList = (ListView) view.findViewById(R.id.search_result_list);
		resultAdapter = new SortingStoreListAdapter(getActivity());
		
		SortingStoreListHeaderResultView resultHeaderView = new SortingStoreListHeaderResultView(
				getActivity());
		resultView = (TextView) resultHeaderView
				.findViewById(R.id.sorting_store_result_txt);
		resultList.addHeaderView(resultHeaderView, "searchResultHeader",
				false);
		
		resultList.setAdapter(resultAdapter);
		
		resultList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent i = new Intent(getActivity(), StoreMenuActivity.class);
				// parcelable sorting store
				SortingStore d = (SortingStore) resultList
						.getItemAtPosition(position);
				i.putExtra(HomeAllStoreFragment.SELECTED_STORE, d);
				getActivity().startActivity(i);
				getActivity().overridePendingTransition(0, 0);
			}
		});
		
		cartStrapView = (ImageView)view.findViewById(R.id.search_result_cart_strap_view);
		cartStrapView.setImageResource(R.drawable.cart_strap);
		cartLayout = (FrameLayout)view.findViewById(R.id.search_result_cart_container);
		
		CartFragment f = new CartFragment();
		FragmentTransaction ft = getChildFragmentManager()
				.beginTransaction();
		ft.replace(R.id.search_result_cart_container, f);
		ft.commit();
		
		cartNumberView = (TextView)view.findViewById(R.id.search_result_cart_number_view);
		
		emptyView = (ListEmptyView)view.findViewById(R.id.search_empty_view);
		emptyView.setData(R.drawable.search_noresult, "검색 결과가 없습니다",
				"다른 검색어로 검색해주세요!");
		emptyView.setVisibility(ImageView.GONE);
		
		initCartNumber();
		toggleCart();

	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		PropertyManager.getInstance().removeCartChangeListener(listener);
	}

	// 사용자 위치정보 필요!!
	private void getResultData(float lat, float lng) {
		NetworkManager.getInstance().getSearchResult(getActivity(), keyword,
				lat, lng, 1, new OnResultListener<SearchResult>() {

					@Override
					public void onSuccess(SearchResult result) {
						resultAdapter.clear();
						if(result.result.item_cnt != 0) {
							searchResult = result.result.item_cnt;
							resultView.setText("검색결과는 총 " + searchResult
									+ "개 입니다.");
							for (SortingStore data : result.result.items) {
								resultAdapter.add(data);
							}
						} else {
							searchResult = result.result.item_cnt;
							resultList.setEmptyView(emptyView);
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub

					}
				});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		lm = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);

		// 지금 연결되어 있는 provider 확인
		if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// provider = LocationManager.GPS_PROVIDER;

			/*******
			 * *** 일단 네트워크로 고정
			 * ********/
			provider = LocationManager.NETWORK_PROVIDER;
		} else if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle("위치정보 사용 동의");
			builder.setMessage("위치정보 사용을 동의하고 gps 사용을 활성화하러 설정페이지로 이동하시겠습니까?");
			builder.setPositiveButton("이동",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent i = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(i);
						}
					});
			builder.setNegativeButton("취소",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			AlertDialog dialog = builder.create();
			dialog.show();
		}

		// 이전에 지정된 위치가 있다면 처리
		if (provider != null) {
			Location location = lm.getLastKnownLocation(provider);
			if (location != null) {
				locationListener.onLocationChanged(location);
			}
		}

		if (provider != null) {
			// 위치정보 가져오기 request
			lm.requestSingleUpdate(provider, locationListener, null);
		}
		
	}
	
	LocationListener locationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// singleUpdate
		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onProviderDisabled(String provider) {

		}

		@Override
		public void onLocationChanged(Location location) {
			// 장소 받아와서 세팅
			latitude = (float) location.getLatitude();
			longitude = (float) location.getLongitude();

//			Toast.makeText(getActivity(),
//					"latitude : " + latitude + ", longtitude : " + longitude,
//					Toast.LENGTH_SHORT).show();
			if(latitude != 0) {
				getResultData(latitude, longitude);
			}
				
		}
	};

	@Override
	public void onStop() {
		super.onStop();
		lm.removeUpdates(locationListener);
	};
	
	private void initCartNumber() {
		if (PropertyManager.getInstance().getCartItemNumber() != 0) {
			cartNumberView.setText(PropertyManager.getInstance()
					.getCartItemNumber() + "");
		} else {
			cartNumberView.setVisibility(ImageView.GONE);
		}
	}

	OnCartChangeListener listener = new OnCartChangeListener() {

		@Override
		public void onCartChangeListener() {
			if(cartStrapView.getVisibility() == ImageView.VISIBLE) {
				if (PropertyManager.getInstance().getCartItemNumber() != 0) {
					cartNumberView.setVisibility(ImageView.VISIBLE);
					cartNumberView.setText(PropertyManager
							.getInstance().getCartItemNumber() + "");
					// cartAboveNumberView.setText(PropertyManager
					// .getInstance().getCartItemNumber() + "");
				} else {
					cartNumberView.setVisibility(ImageView.GONE);
					// cartAboveNumberView.setVisibility(ImageView.GONE);
				}
			}else {
				if (PropertyManager.getInstance().getCartItemNumber() != 0) {
					cartNumberView.setText(PropertyManager.getInstance()
							.getCartItemNumber() + "");
					// cartAboveNumberView.setText(PropertyManager
					// .getInstance().getCartItemNumber() + "");
				} else {
					cartNumberView.setVisibility(ImageView.GONE);
					// cartAboveNumberView.setVisibility(ImageView.GONE);
				}
			}
		}
	};

	private void toggleCart() {
		cartStrapView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				cartLayout.setVisibility(ImageView.VISIBLE);
				if (!isCartSelected) {
					cartStrapView.setVisibility(ImageView.GONE);
					cartNumberView.setVisibility(ImageView.GONE);

					Animation anim = AnimationUtils.loadAnimation(
							getActivity(), R.anim.cart_slide_in);
					cartLayout.startAnimation(anim);
					
//					CartFragment f = new CartFragment();
//					FragmentTransaction ft = getChildFragmentManager()
//							.beginTransaction();
//					ft.replace(R.id.search_result_cart_container, f);
//					ft.commit();
				}

			}
		});

		cartLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cartStrapView.getVisibility() == ImageView.GONE) {

					// anim
					Animation anim = AnimationUtils.loadAnimation(
							getActivity(), R.anim.cart_slide_out);
					cartLayout.startAnimation(anim);
					anim.setAnimationListener(new AnimationListener() {

						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation animation) {
							cartLayout.setVisibility(ImageView.GONE);
							if (PropertyManager.getInstance()
									.getCartItemNumber() != 0) {
								cartNumberView.setVisibility(ImageView.VISIBLE);
								cartStrapView.setVisibility(ImageView.VISIBLE);
							} else {
								cartNumberView.setVisibility(ImageView.GONE);
								cartStrapView.setVisibility(ImageView.VISIBLE);
							}
						}
					}); // animation listener
				}
			}
		});
	}

}
