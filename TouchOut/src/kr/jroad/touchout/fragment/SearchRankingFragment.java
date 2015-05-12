package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.SearchRankingListAdapter;
import kr.jroad.touchout.data.RankingResult;
import kr.jroad.touchout.data.RankingWord;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.PropertyManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager.OnCartChangeListener;
import kr.jroad.touchout.view.SearchRankingListHeaderView;
import android.os.Bundle;
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

public class SearchRankingFragment extends Fragment {

	ListView searchRankingList;
	SearchRankingListAdapter searchAdapter;
	SearchRankingListHeaderView searchHeaderView;
	String keyword;
	
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
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_search_ranking_list, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		PropertyManager.getInstance().addOnCartChangeListener(listener);
		
		searchRankingList = (ListView)view.findViewById(R.id.search_ranking_list);
		searchHeaderView = new SearchRankingListHeaderView(getActivity());
		
		searchRankingList.addHeaderView(searchHeaderView, null, true);
		
		searchAdapter = new SearchRankingListAdapter(getActivity());
		searchRankingList.setAdapter(searchAdapter);
		
		searchRankingList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				//추천 검색어 눌리면 그 결과 보여줌
				RankingWord keyword = (RankingWord) searchRankingList.getItemAtPosition(position);
				SearchMainFragment parent = (SearchMainFragment)getParentFragment();
				parent.goRecommendResult(keyword.keyword);
				
			}
		});
		
		cartStrapView = (ImageView)view.findViewById(R.id.search_cart_strap_view);
		cartStrapView.setImageResource(R.drawable.cart_strap);
		cartLayout = (FrameLayout)view.findViewById(R.id.search_cart_container);
		
		CartFragment f = new CartFragment();
		FragmentTransaction ft = getChildFragmentManager()
				.beginTransaction();
		ft.replace(R.id.search_cart_container, f);
		ft.commit();
		
		cartNumberView = (TextView)view.findViewById(R.id.search_cart_number_view);
		
		initCartNumber();
		toggleCart();
			
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		PropertyManager.getInstance().removeCartChangeListener(listener);
	};
	
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
	
	
	@Override
	public void onResume() {
		super.onResume();
		initSearchRankingData();
	}
	
	private void initSearchRankingData() {
		NetworkManager.getInstance().getRankingWord(getActivity(), new OnResultListener<RankingResult>() {

			@Override
			public void onSuccess(RankingResult result) {
				if(result.success == 1) {
					searchAdapter.clear();
					for(int i = 0; i < result.result.items.size(); i++) {
						searchAdapter.add(result.result.items.get(i));
					}
				}
			}

			@Override
			public void onFail(int code) {
				
			}
		});
	}
	
	private void initCartNumber() {
		if (PropertyManager.getInstance().getCartItemNumber() != 0) {
			cartNumberView.setText(PropertyManager.getInstance()
					.getCartItemNumber() + "");
		} else {
			cartNumberView.setVisibility(ImageView.GONE);
		}
	}


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
//					ft.replace(R.id.search_cart_container, f);
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
