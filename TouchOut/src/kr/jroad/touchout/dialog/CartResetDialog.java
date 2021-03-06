package kr.jroad.touchout.dialog;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.PostResult;
import kr.jroad.touchout.data.StoreMenuItem;
import kr.jroad.touchout.fragment.StoreHotMenuFragment;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CartResetDialog extends DialogFragment {

	StoreMenuItem cartItem;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, R.style.DialogTheme);
		Bundle b = getArguments();
		cartItem = b.getParcelable(StoreHotMenuFragment.CART_RESET_ITEM);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.dialog_cart_reset, container, false);
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		
		
		Button btnCancel = (Button)view.findViewById(R.id.cart_reset_cancel_btn);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		Button btnOK = (Button)view.findViewById(R.id.cart_reset_ok_btn);
		btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cartItem != null) {
					resetCart(cartItem);
				}
			}
		});
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
//		getDialog().getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.dialog_background);
	}
	
	private void resetCart(StoreMenuItem data) {
		int whipping;
		if (data.whippingSelected) {
			whipping = 1;
		} else {
			whipping = 0;
		}
		NetworkManager.getInstance().resetCartItem(getActivity(), data.item_id,
				data.count, whipping, data.selectedSize,
				new OnResultListener<PostResult>() {

					@Override
					public void onSuccess(PostResult result) {
						if (result.success == 1) {
//							Toast.makeText(getActivity(), "장바구니 초기화 성공",
//									Toast.LENGTH_SHORT).show();
							dismiss();
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub

					}
				});
	}
}
