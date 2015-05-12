package kr.jroad.touchout.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import kr.jroad.touchout.activity.PaymentActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.adapter.CartListAdapter;
import kr.jroad.touchout.adapter.CartListAdapter.OnCountAdapterListener;
import kr.jroad.touchout.data.CartItem;
import kr.jroad.touchout.data.CartResult;
import kr.jroad.touchout.data.OrderResult;
import kr.jroad.touchout.data.OrderSendItem;
import kr.jroad.touchout.data.OrderSendResult;
import kr.jroad.touchout.data.PostResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager;
import kr.jroad.touchout.manager.PropertyManager.OnCartChangeListener;
import kr.jroad.touchout.view.CartListChildView;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CartFragment extends Fragment{

	ExpandableListView cartList;
	CartListAdapter cartAdapter;
	TextView resultPriceView;
	TextView numberView;
	int cartItemNumber;
	DecimalFormat thousandFormat = new DecimalFormat("#,###");
	
	public interface OnGetCart {
		public void onGetCart();
	}
	
	public OnGetCart getCart;
	
	public void setOnGetCart(OnGetCart cart) {
		getCart = cart;
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_cart, container, false);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		PropertyManager.getInstance().removeCartChangeListener(listener);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// setCartItemNumber 했을 때의 리스너 add
		PropertyManager.getInstance().addOnCartChangeListener(listener);

		cartList = (ExpandableListView) view.findViewById(R.id.cart_list);
		cartAdapter = new CartListAdapter(getActivity());
		numberView = (TextView) view.findViewById(R.id.cart_number_view);
		resultPriceView = (TextView) view
				.findViewById(R.id.cart_result_price_txt);

		Button btnPayment = (Button) view.findViewById(R.id.cart_payment_btn);
		btnPayment.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 카트에 담긴 항목들
				if (PropertyManager.getInstance().getCartItemNumber() != 0) {
					ArrayList<CartItem> cartItem = cartAdapter.getChildList(0);
					goPayment(cartItem);
				} else {
					Toast.makeText(getActivity(), "장바구니에 상품을 넣어주세요!",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		// 다른 매장의 상품이 담겨 초기화 되었을 경우 불리는 리스너
//		CartResetDialog dialog = new CartResetDialog();
//		dialog.setOnClearCartListener(new OnClearCartListener() {
//			
//			@Override
//			public void onClearCartListener() {
//				cartAdapter.clear();
//				getCartData();
//			}
//		});

		// 펼치기
		cartList.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				cartList.expandGroup(groupPosition);
			}
		});

		// 더하기 눌리면 더하기 연산 실행
		cartAdapter.setOnCountAdapterListener(new OnCountAdapterListener() {

			@Override
			public void onCountPlusListener(CartListChildView view,
					CartItem data) {
				data.count++;
				NetworkManager.getInstance().editCartData(getActivity(),
						data.item_id, data.cartitem_id, data.count,
						new OnResultListener<PostResult>() {

							@Override
							public void onSuccess(PostResult result) {
								if (result.success == 1) {
									getCartData();
								}
							}

							@Override
							public void onFail(int code) {
								Toast.makeText(getActivity(), "수량 변경 실패",
										Toast.LENGTH_SHORT).show();
							}
						});
				cartAdapter.notifyDataSetChanged();
			}

			// 빼기 누르면 더하기 빼야함
			@Override
			public void onCountMinusListener(CartListChildView view,
					CartItem data) {
				data.count--;
				// 0이면 삭제
				if (data.count < 1) {
					NetworkManager.getInstance().deleteCartData(getActivity(),
							data.item_id, data.cartitem_id,
							new OnResultListener<PostResult>() {

								@Override
								public void onSuccess(PostResult result) {
									if (result.success == 1) {
										getCartData();
										PropertyManager.getInstance()
												.setCartItemNumber(
														result.result.item_cnt);
									}
								}

								@Override
								public void onFail(int code) {
									Toast.makeText(getActivity(),
											"장바구니 항목 삭제 실패", Toast.LENGTH_SHORT)
											.show();
								}
							});
				} else {
					// 0이 아니면 수정
					NetworkManager.getInstance().editCartData(getActivity(),
							data.item_id, data.cartitem_id, data.count,
							new OnResultListener<PostResult>() {

								@Override
								public void onSuccess(PostResult result) {
									if (result.success == 1) {
										getCartData();
									}
								}

								@Override
								public void onFail(int code) {
									Toast.makeText(getActivity(), "수량 수정 실패",
											Toast.LENGTH_SHORT).show();
								}
							});
				}
				cartAdapter.notifyDataSetChanged();
			}
		});
		

		cartList.setAdapter(cartAdapter);

	}

	OnCartChangeListener listener = new OnCartChangeListener() {

		@Override
		public void onCartChangeListener() {
			if (PropertyManager.getInstance().getCartItemNumber() != 0) {
				numberView.setVisibility(ImageView.VISIBLE);
				numberView.setText(PropertyManager.getInstance()
						.getCartItemNumber() + "");
				// cartAboveNumberView.setText(PropertyManager
				// .getInstance().getCartItemNumber() + "");
			} else {
				numberView.setVisibility(ImageView.GONE);
				// cartAboveNumberView.setVisibility(ImageView.GONE);
			}
		}
	};

	private void getCartData() {
		NetworkManager.getInstance().getCartItem(getActivity(),
				new OnResultListener<CartResult>() {

					@Override
					public void onSuccess(CartResult result) {
						if (result.success == 1) {
							cartAdapter.clear();
							if (result.result.item_cnt != 0) {
								for (CartItem item : result.result.items) {
									cartAdapter.add(item.storeName, item);
								}
								resultPriceView.setText(thousandFormat
										.format(result.result.total_amount));

								for (int i = 0; i < cartAdapter.getGroupCount(); i++) {
									cartList.expandGroup(i);
								}
							} else {
								resultPriceView.setText(0 + "");
							}
							// 장바구니 갯수 저장
							PropertyManager.getInstance().setCartItemNumber(
									result.result.item_cnt);
						}
					}

					@Override
					public void onFail(int code) {
						Log.e("cart", "failed to get cart list");
					}
				});
	}

	private void goPayment(ArrayList<CartItem> items) {
		// json규격으로 보내줄 결제 내역 객체 생성
		OrderSendResult sendPaymentResult = new OrderSendResult();
		sendPaymentResult.store_id = items.get(0).store_id;
		try {
			sendPaymentResult.store_name = URLEncoder.encode(
					items.get(0).storeName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		for (CartItem c : items) {
			OrderSendItem payItem = new OrderSendItem();
			payItem.item_id = c.item_id;
			payItem.item_name = c.menuName;

			payItem.count = c.count;

			payItem.is_whipping = c.is_whipping;
			payItem.size = c.size;

			sendPaymentResult.items.add(payItem);
		}

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

					}
				});

	}

	@Override
	public void onResume() {
		super.onResume();
		getCartData();

		// NetworkManager.getInstance().getCartItem(getActivity(),
		// new OnResultListener<CartResult>() {
		//
		// @Override
		// public void onSuccess(CartResult result) {
		// if (result.success == 1) {
		// cartAdapter.clear();
		// if(result.result.items != null) {
		// for (CartItem item : result.result.items) {
		// cartAdapter.add(item.storeName, item);
		// }
		// resultPriceView.setText(thousandFormat.format(result.result.total_amount));
		//
		// for (int i = 0; i < cartAdapter.getGroupCount(); i++) {
		// cartList.expandGroup(i);
		// }
		// }
		// // 장바구니 갯수 저장
		// PropertyManager.getInstance().setCartItemNumber(
		// result.result.item_cnt);
		//
		// //0이면 안보여줌
		// if(result.result.item_cnt == 0) {
		// numberView.setVisibility(ImageView.GONE);
		// } else {
		// numberView.setVisibility(ImageView.VISIBLE);
		// numberView.setText(result.result.item_cnt + "");
		// }
		//
		// }
		//
		// }
		//
		// @Override
		// public void onFail(int code) {
		// // TODO Auto-generated method stub
		//
		// }
		// });
	}
	
}
