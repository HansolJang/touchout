package kr.jroad.touchout.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import kr.jroad.touchout.activity.PaymentActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.activity.StoreMenuActivity;
import kr.jroad.touchout.adapter.FavoriteMenuAdapter;
import kr.jroad.touchout.adapter.FavoriteMenuAdapter.OnAdapterEditListener;
import kr.jroad.touchout.adapter.FavoriteMenuAdapter.OnAdapterRemoveListener;
import kr.jroad.touchout.data.FavoriteItem;
import kr.jroad.touchout.data.FavoriteResult;
import kr.jroad.touchout.data.OrderResult;
import kr.jroad.touchout.data.OrderSendItem;
import kr.jroad.touchout.data.OrderSendResult;
import kr.jroad.touchout.data.PostResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager;
import kr.jroad.touchout.manager.PropertyManager.OnCartChangeListener;
import kr.jroad.touchout.view.ListEmptyView;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class FavoriteFragment extends Fragment {

	ListView favoriteMenuList;
	FavoriteMenuAdapter favoriteAdapter;
	ImageView cartStrapView;
	boolean isCartSelected = false;
	FrameLayout cartLayout;
	ListEmptyView emptyView;

	TextView cartNumberView;

	//수정될 객체
	public static final String GO_EDIT_MENU = "editSelectedMenu";
	
	//바로 결제할 객체
	public static final String PAYMENT_FAVORITE_MENU = "paySelectedMenu";

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		PropertyManager.getInstance().removeCartChangeListener(listener);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_favorite, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		PropertyManager.getInstance().addOnCartChangeListener(listener);

		favoriteMenuList = (ListView) view.findViewById(R.id.favorite_list);
		favoriteAdapter = new FavoriteMenuAdapter(getActivity());

		cartNumberView = (TextView) view
				.findViewById(R.id.favorite_cart_number_view);
		cartStrapView = (ImageView) view
				.findViewById(R.id.favorite_cart_strap_view);
		cartStrapView.setImageResource(R.drawable.cart_strap);
		cartLayout = (FrameLayout) view
				.findViewById(R.id.favorite_cart_container);
		emptyView = (ListEmptyView) view.findViewById(R.id.favorite_empty_view);
		emptyView.setData(R.drawable.favorite_emptyview_img, "즐겨찾기 내역이 없습니다  "," 즐겨찾기로 테이크아웃을 더 간편하게 하세요!");
		emptyView.setVisibility(ImageView.GONE);

		CartFragment f = new CartFragment();
		FragmentTransaction ft = getChildFragmentManager()
				.beginTransaction();
		ft.replace(R.id.favorite_cart_container, f);
		ft.commit();
		
		// 누르면 그 하나만 바로 결제! 결제 버튼 눌릴 시 결제 내역 json으로 보내주어야함
		favoriteMenuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FavoriteItem selectedFavorite = (FavoriteItem) favoriteMenuList
						.getItemAtPosition(position);
				goPayment(selectedFavorite);
			}
		});

		// 편집 -> 수정버튼이 눌렸을 경우
		favoriteAdapter.setOnAdapterEditListener(new OnAdapterEditListener() {

			@Override
			public void onAdapterEditListener(View v, FavoriteItem data) {
				Intent i = new Intent(getActivity(), StoreMenuActivity.class);
				i.putExtra(GO_EDIT_MENU, data);
				startActivity(i);
				getActivity().overridePendingTransition(0, 0);
			}
		});

		// 편집 -> 삭제 버튼이 눌렸을 경우
		favoriteAdapter
				.setOnAdapterRemoveListener(new OnAdapterRemoveListener() {

					@Override
					public void onAdapterRemoveListener(View v,
							FavoriteItem data) {
						NetworkManager.getInstance().deleteFavoriteData(
								getActivity(), data.favorite_id,
								new OnResultListener<PostResult>() {

									@Override
									public void onSuccess(PostResult result) {
										if (result.success == 1) {
											getFavoriteMenuData();
											favoriteAdapter.setEditable(true);
										}
									}

									@Override
									public void onFail(int code) {

									}
								});
						favoriteAdapter.remove(data);
						favoriteAdapter.notifyDataSetChanged();

					}
				});

		favoriteMenuList.setAdapter(favoriteAdapter);

		initCartNumber();
		toggleCart();
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

	private void getFavoriteMenuData() {
		NetworkManager.getInstance().getFavoriteData(getActivity(),
				new OnResultListener<FavoriteResult>() {

					@Override
					public void onSuccess(FavoriteResult result) {
						if (result.success == 1) {
							favoriteAdapter.clear();
							if(result.result.item_cnt != 0) {
								for (FavoriteItem d : result.result.items) {
									favoriteAdapter.add(d);
								}
							}
							favoriteMenuList.setEmptyView(emptyView);
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
		getFavoriteMenuData();
	};

	MenuItem menuEdit, menuFinish;

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.favorite_menu, menu);

		menuEdit = menu.findItem(R.id.favorite_menu_edit);
		menuFinish = menu.findItem(R.id.favorite_menu_finish);
		menuFinish.setVisible(false);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.favorite_menu_edit) {
			favoriteAdapter.setEditable(true);
			menuEdit.setVisible(false);
			menuFinish.setVisible(true);
		} else if (item.getItemId() == R.id.favorite_menu_finish) {
			favoriteAdapter.setEditable(false);
			menuEdit.setVisible(true);
			menuFinish.setVisible(false);
		}
		return super.onOptionsItemSelected(item);
	}

	// payment 버튼 누르면 즐겨찾기아이템 + store_id로 검색한 매장 객체 
	private void goPayment(FavoriteItem favoriteMenu) {
		
		//json규격으로 보내줄 결제 내역 객체 생성
		OrderSendResult sendPaymentResult = new OrderSendResult();
		sendPaymentResult.store_id = favoriteMenu.store_id;
		try {
			sendPaymentResult.store_name = URLEncoder.encode(favoriteMenu.storeName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		OrderSendItem payItem = new OrderSendItem();
		payItem.item_id = favoriteMenu.item_id;
		payItem.item_name = favoriteMenu.menuName;
		
		//즐겨찾기는 수량에 관계없으므로 무조건 1로 고정
		payItem.count = 1;
		
		payItem.is_whipping = favoriteMenu.is_whipping;
		payItem.size = favoriteMenu.size;
				
		sendPaymentResult.items.add(payItem);
		
		//결제 내역을 서버에 올려주고 payment activity에 뿌려줄 정보를 담은 객체를 받음
		NetworkManager.getInstance().createOrder(getActivity(), sendPaymentResult, new OnResultListener<OrderResult>() {

			@Override
			public void onSuccess(OrderResult result) {
				if(result.success == 1) {
					Intent i = new Intent(getActivity(),
							PaymentActivity.class);
					// 받아온 객체 보내줘야함 
					i.putExtra(PaymentActivity.PAYMENT_ORDER_DETAILS, result.result);
					startActivity(i);
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
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
//					CartFragment f = new CartFragment();
//					FragmentTransaction ft = getChildFragmentManager()
//							.beginTransaction();
//					ft.replace(R.id.favorite_cart_container, f);
//					ft.commit();
					cartStrapView.setVisibility(ImageView.GONE);
					cartNumberView.setVisibility(ImageView.GONE);

					Animation anim = AnimationUtils.loadAnimation(
							getActivity(), R.anim.cart_slide_in);
					cartLayout.startAnimation(anim);
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