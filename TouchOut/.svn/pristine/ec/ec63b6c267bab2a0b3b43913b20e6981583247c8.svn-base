package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.CartItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CartListChildView extends FrameLayout {
	
	public interface OnCountListener{
		public void onCountPlusListener(CartListChildView view, CartItem data);
		public void onCountMinusListener(CartListChildView view, CartItem data);
	}
	
	OnCountListener plusListener;
	OnCountListener minusListener;
	
	public void setOnCountListener(OnCountListener listener) {
		plusListener = listener;
		minusListener = listener;
	}

	public CartListChildView(Context context) {
		super(context);
		init();
	}

	CartItem data;
	TextView menuName;
	TextView menuAmount;

	private void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.cart_list_child_item, this);

		menuName = (TextView) findViewById(R.id.cart_list_menu_name);
		menuAmount = (TextView) findViewById(R.id.cart_list_menu_amount);

		Button btnCountMinus = (Button) findViewById(R.id.cart_amount_minus_btn);
		btnCountMinus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(minusListener != null) {
					minusListener.onCountMinusListener(CartListChildView.this, data);
				}
			}
		});

		Button btnCountPlus = (Button) findViewById(R.id.cart_amount_plus_btn);
		btnCountPlus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (plusListener != null) {
					plusListener.onCountPlusListener(CartListChildView.this, data);
				}
			}
		});
	}

	public void setData(CartItem data) {
		this.data = data;
		if(data.is_whipping == 0) {
			data.whippingSelected = false;
		} else {
			data.whippingSelected = true;
		}
		menuName.setText(data.menuName + " (" + data.size + ")");
		menuAmount.setText(""+data.count);

	}

}
