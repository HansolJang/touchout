package kr.jroad.touchout.data;

import java.util.ArrayList;

public class OrderSendResult {
	public int store_id;
	public String store_name;
	public ArrayList<OrderSendItem> items = new ArrayList<OrderSendItem>();
}
