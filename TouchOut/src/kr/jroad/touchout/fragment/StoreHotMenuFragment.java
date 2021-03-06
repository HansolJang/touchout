package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.PaymentActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.MenuListAdapter;
import kr.jroad.touchout.adapter.MenuListAdapter.OnMenuAdapterClickListner;
import kr.jroad.touchout.data.FavoriteItem;
import kr.jroad.touchout.data.OrderResult;
import kr.jroad.touchout.data.OrderSendItem;
import kr.jroad.touchout.data.OrderSendResult;
import kr.jroad.touchout.data.PostResult;
import kr.jroad.touchout.data.SortingStore;
import kr.jroad.touchout.data.StampItem;
import kr.jroad.touchout.data.StoreMenuItem;
import kr.jroad.touchout.data.StoreMenuResult;
import kr.jroad.touchout.dialog.CartResetDialog;
import kr.jroad.touchout.dialog.ClearCartDialog;
import kr.jroad.touchout.dialog.StampDialogFragment;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager;
import kr.jroad.touchout.view.MenuListView;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class StoreHotMenuFragment extends Fragment {

	public static final String DIALOG_CART_RESET_TAG = "dialogCartResetTag";
	public static final String CART_RESET_ITEM = "cartResetItem";

	ListView menuList;
	MenuListAdapter menuAdapter;
	int storeId;
	StampItem storeStamp;
	FavoriteItem editFavoriteMenu;
	SortingStore selectedStore;
	int isWhipping;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		Bundle b = getArguments();
		if (b != null) {
			// 즐찾에서 넘어왔으면 즐찾에서 아이디로 매장 검색후 매장 객체 넘겨주도록 고쳐야함
			editFavoriteMenu = b.getParcelable(FavoriteFragment.GO_EDIT_MENU);
			// 매장 선택해서 바로 들어왔을 경우
			selectedStore = b
					.getParcelable(HomeAllStoreFragment.SELECTED_STORE);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_hot_menu, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// 메뉴 검색에 쓸 store_id 세팅
		if (selectedStore != null) {
			storeId = selectedStore.store_id;
		} else if (editFavoriteMenu != null) {
			storeId = editFavoriteMenu.store_id;
		}

		menuList = (ListView) view.findViewById(R.id.store_hot_menu_list);
		menuAdapter = new MenuListAdapter(getActivity());

		menuAdapter
				.setOnMenuAdapterClickListner(new OnMenuAdapterClickListner() {

					@Override
					public void onWhippingSelectListener(MenuListView view,
							StoreMenuItem data) {
						data.whippingSelected = !data.whippingSelected;
						menuAdapter.notifyDataSetChanged();
					}

					@Override
					public void onPaymentListener(MenuListView view,
							StoreMenuItem data) {
						goPayment(data);
					}

					@Override
					public void onFavoriteSelectListener(MenuListView view,
							StoreMenuItem data) {
						data.isFavorite = !data.isFavorite;
						menuAdapter.notifyDataSetChanged();

						if (data.whippingSelected) {
							data.is_whipping = 1;
						} else {
							data.is_whipping = 0;
						}

						if (data.isFavorite) {
							// /////////////즐겨찾기 선택 안되어있어서 클릭하여 선택됨!

							// !null -> edit
							if (editFavoriteMenu != null) {
								NetworkManager.getInstance().editFavoriteData(
										getActivity(),
										editFavoriteMenu.favorite_id,
										data.item_id, data.is_whipping,
										data.selectedSize,
										new OnResultListener<PostResult>() {

											// 완료하면 창닫고 즐찾 목록 다시 get
											@Override
											public void onSuccess(
													PostResult result) {
												if (result.success == 1) {
													getActivity().finish();
													Toast.makeText(
															getActivity(),
															"즐겨찾기가 수정되었습니다!",
															Toast.LENGTH_SHORT)
															.show();
												}
											}

											@Override
											public void onFail(int code) {
												Toast.makeText(getActivity(),
														"즐겨찾기 수정에 실패하였습니다.",
														Toast.LENGTH_SHORT)
														.show();
											}
										});
							} else {
								// 수정할 객체가 null이면 새로 add favorite
								NetworkManager.getInstance().addFavoriteData(
										getActivity(), data.item_id,
										data.whippingSelected,
										data.selectedSize,
										new OnResultListener<PostResult>() {

											@Override
											public void onSuccess(
													PostResult result) {
												if (result.success == 1) {
													Toast.makeText(
															getActivity(),
															"즐겨찾기에 추가되었습니다!",
															Toast.LENGTH_SHORT)
															.show();
												}
											}

											@Override
											public void onFail(int code) {
												Toast.makeText(getActivity(),
														"즐겨찾기 추가에 실패하였습니다.",
														Toast.LENGTH_SHORT)
														.show();
											}

										});
							}

						} else {
							// /////////isFavorite = false
							// 한 페이지에서 한번 추가후 취소한 데이터 삭제
							NetworkManager.getInstance().cancelFavoriteData(
									getActivity(), data.item_id,
									data.whippingSelected, data.selectedSize,
									new OnResultListener<PostResult>() {

										@Override
										public void onSuccess(PostResult result) {
											if (result.success == 1) {
												Toast.makeText(getActivity(),
														"즐겨찾기 추가가 취소되었습니다",
														Toast.LENGTH_SHORT)
														.show();
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
					public void onCountPlusListener(MenuListView view,
							StoreMenuItem data) {

						data.count++;
						// 가격 세팅
						if (data.size.equals("R")) {
							data.menuPrice = data.oneRegularPrice * data.count;
						} else {
							data.menuPrice = data.oneLargePrice * data.count;
						}
						menuAdapter.notifyDataSetChanged();
					}

					@Override
					public void onCountMinusListener(MenuListView view,
							StoreMenuItem data) {
						if (data.count < 2) {
							Toast.makeText(getActivity(), "수량을 올려주세요!",
									Toast.LENGTH_SHORT).show();
						} else {
							data.count--;
							// 가격 세팅
							if (data.size.equals("R")) {
								data.menuPrice = data.oneRegularPrice * data.count;
							} else {
								data.menuPrice = data.oneLargePrice * data.count;
							}
							menuAdapter.notifyDataSetChanged();
						}
					}

					/*******
					 * 장바구니에 이미 같은게 있으면 cartResetDialog
					 * *******/
					@Override
					public void onAddCartListener(MenuListView view,
							final StoreMenuItem data) {

						if (data.whippingSelected) {
							isWhipping = 1;
						} else {
							isWhipping = 0;
						}

						NetworkManager.getInstance().addCartItem(getActivity(),
								data.item_id, data.count, isWhipping,
								data.selectedSize,
								new OnResultListener<PostResult>() {

									// add cart 성공하면 다시 setCartNumber
									@Override
									public void onSuccess(PostResult result) {
										if (result.success == 1) {
											// 다른 매장이면 다이얼로그 띄우고 ok 하면 초기화
											if (result.result.is_same_store == 0) {
//												Bundle b = new Bundle();
//												b.putParcelable(
//														CART_RESET_ITEM, data);
//												CartResetDialog cartResetDialog = new CartResetDialog();
//												cartResetDialog.setArguments(b);
//												cartResetDialog.show(
//														getFragmentManager(),
//														DIALOG_CART_RESET_TAG);
												
												ClearCartDialog dialog = new ClearCartDialog();
												dialog.show(getFragmentManager(), null);
												// 정상 추가됨!끝
											} else {
												// 리스너 호출되고 숫자 바뀜
												PropertyManager
														.getInstance()
														.setCartItemNumber(
																result.result.item_cnt);
												Toast.makeText(getActivity(),
														"장바구니에 추가되었습니다!",
														Toast.LENGTH_SHORT)
														.show();
											}
										}
									}

									@Override
									public void onFail(int code) {
										/*
										 * * 오류 상태 메세지에 따라 처리해야함!
										 */
										// if(code == 1222222) {
										// Toast.makeText(getActivity(),
										// "같은 상품이 이미 담겨있습니다!",
										// Toast.LENGTH_SHORT).show();
										// }
									}
								});
					}

					// large = +500
					@Override
					public void onSizeRegularListener(MenuListView view,
							StoreMenuItem data) {
						if (data.selectedSize.equalsIgnoreCase("L")) {
							data.selectedSize = "R";
							data.menuPrice = data.oneRegularPrice * data.count;
							menuAdapter.notifyDataSetChanged();
						} else {
							data.selectedSize = "R";
							data.menuPrice = data.oneRegularPrice * data.count;
							menuAdapter.notifyDataSetChanged();
						}
					}

					@Override
					public void onSizeLargeListener(MenuListView view,
							StoreMenuItem data) {
						if (data.selectedSize.equalsIgnoreCase("R")) {
							data.selectedSize = "L";
							data.menuPrice = data.oneLargePrice * data.count;
							menuAdapter.notifyDataSetChanged();
						} else {
							data.selectedSize = "L";
							data.menuPrice = data.oneLargePrice * data.count;
							menuAdapter.notifyDataSetChanged();
						}
					}
				});

		/*
		 * 
		 * 나중에 싱글 초이스로 바꿔야함
		 */
		// 메뉴 디테일 보이기
		menuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				menuAdapter.clearSelected();
				StoreMenuItem selectedMenuData = (StoreMenuItem) menuList
						.getItemAtPosition(position);
				selectedMenuData.menuSelected = true;
				menuAdapter.notifyDataSetChanged();
			}
		});

		menuList.setAdapter(menuAdapter);

	}

	@Override
	public void onResume() {
		super.onResume();
		getMenuData();
	}

	private void goPayment(StoreMenuItem menuItem) {
		// json규격으로 보내줄 결제 내역 객체 생성
		OrderSendResult sendPaymentResult = new OrderSendResult();
		if (selectedStore != null) {
			sendPaymentResult.store_id = selectedStore.store_id;
			sendPaymentResult.store_name = selectedStore.name;
		} else {
			sendPaymentResult.store_id = editFavoriteMenu.store_id;
			sendPaymentResult.store_name = editFavoriteMenu.storeName;
		}

		OrderSendItem payItem = new OrderSendItem();
		payItem.item_id = menuItem.item_id;
		payItem.item_name = menuItem.menuName;

		payItem.count = menuItem.count;
		if (menuItem.whippingSelected) {
			payItem.is_whipping = 1;
		} else {
			payItem.is_whipping = 0;
		}
		payItem.size = menuItem.selectedSize;

		sendPaymentResult.items.add(payItem);

		// 결제 내역을 서버에 올려주고 payment activity에 뿌려줄 정보를 담은 객체를 받음
		NetworkManager.getInstance().createOrder(getActivity(),
				sendPaymentResult, new OnResultListener<OrderResult>() {

					@Override
					public void onSuccess(OrderResult result) {
						if (result.success == 1) {
							Intent i = new Intent(getActivity(),
									PaymentActivity.class);
							// 받아온 객체 보내줘야함
							i.putExtra(PaymentActivity.PAYMENT_ORDER_DETAILS,
									result.result);
							startActivity(i);
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void getMenuData() {
		NetworkManager.getInstance().getMenuData(getActivity(), storeId,
				new OnResultListener<StoreMenuResult>() {

					@Override
					public void onSuccess(StoreMenuResult result) {
						if (result.success == 1) {
							if (result.result.items != null) {
								for (StoreMenuItem menu : result.result.items) {
									// if hot
									if (menu.is_hot == 1) {
										menu.oneLargePrice = menu.menuPrice + 500;
										menu.oneRegularPrice = menu.menuPrice;
										menuAdapter.add(menu);
									}
								}
							}

							if (result.result.stamp.stampCount != 0) {
								storeStamp = result.result.stamp;
								menuStamp.setVisible(true);
							} else {
								menuStamp.setVisible(false);
							}
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub

					}
				});
	}

	public static final String STORE_STAMP_KEY = "storeStampKey";

	MenuItem menuStamp;

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.store_menu_info_menu, menu);
		menuStamp = menu.findItem(R.id.store_menu_stamp);
		menuStamp.setVisible(false);
	}

	public static final String STORE_STAMP_DIALOG_KEY = "storeStampDialogKey";

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.store_menu_stamp) {
			// dialog!!
			Bundle b = new Bundle();
			if (storeStamp != null) {
				b.putParcelable(STORE_STAMP_KEY, storeStamp);
				// stamp 객체 보내주기
				StampDialogFragment stampDialog = new StampDialogFragment();
				stampDialog.setArguments(b);
				stampDialog.show(getChildFragmentManager(),
						STORE_STAMP_DIALOG_KEY);

			}

		}

		return super.onOptionsItemSelected(item);
	}

}
