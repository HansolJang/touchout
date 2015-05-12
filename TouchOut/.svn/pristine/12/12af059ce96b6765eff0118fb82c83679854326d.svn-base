package kr.jroad.touchout.view;

import java.text.DecimalFormat;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.PaymentHistory;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PaymentHistoryChildOrderView extends FrameLayout {

	public PaymentHistoryChildOrderView(Context context) {
		super(context);
		init();
	}
	
	DecimalFormat thousandFormat = new DecimalFormat("#,###");
	TextView menuNameView;
	TextView menuAmountView;
	TextView menuPriceView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.payment_history_child_order_item, this);
		
		menuNameView = (TextView)findViewById(R.id.payment_menu_name_txt);
		menuAmountView = (TextView)findViewById(R.id.payment_menu_amount_txt);
		menuPriceView = (TextView)findViewById(R.id.payment_menu_price_txt);
	}
	
	public void setData(PaymentHistory data) {
		menuNameView.setText(data.name);
		menuAmountView.setText(data.count + " 개");
		
		String thousandPrice = thousandFormat.format(data.amount);
		menuPriceView.setText(thousandPrice + "원");
	}

}
