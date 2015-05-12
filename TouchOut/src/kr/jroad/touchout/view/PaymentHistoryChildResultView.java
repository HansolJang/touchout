package kr.jroad.touchout.view;

import java.text.DecimalFormat;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.PaymentHistoryItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class PaymentHistoryChildResultView extends FrameLayout {

	public PaymentHistoryChildResultView(Context context) {
		super(context);
		init();
	}
	
	DecimalFormat thousandFormat = new DecimalFormat("#,###");
	TextView resultPriceView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.payment_history_child_result_item, this);
		
		resultPriceView = (TextView)findViewById(R.id.payment_result_price_txt);
	}
	
	public void setData(PaymentHistoryItem data) {
		String thousandPrice = thousandFormat.format(data.resultPrice);
		resultPriceView.setText(thousandPrice + "");
	}

}
