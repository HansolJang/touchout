package kr.jroad.touchout.data;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class PaymentHistoryItem {
	public int payment_id;
	
	public int item_cnt;
	
	@SerializedName("payment_date")
    public String orderedTime;
	
	@SerializedName("pay_amount")
    public int resultPrice;
    
    @SerializedName("store_name")
    public String storeName;
    
    public ArrayList<PaymentHistory> items = new ArrayList<PaymentHistory>();
	
}
