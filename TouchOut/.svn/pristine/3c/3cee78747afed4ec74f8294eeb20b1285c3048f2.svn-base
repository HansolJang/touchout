package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.CartItemGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CartListGroupView extends FrameLayout {

	public CartListGroupView(Context context) {
		super(context);
		init();
	}
	
	TextView storeName;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.cart_list_group_item, this);
		
		storeName = (TextView)findViewById(R.id.cart_list_store_name_txt);
	}

	public void setData(CartItemGroup data) {
		storeName.setText(data.storeName);
	}
}
