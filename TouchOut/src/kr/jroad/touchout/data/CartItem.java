package kr.jroad.touchout.data;

import com.google.gson.annotations.SerializedName;

public class CartItem {
	///////////cart child data
	public int item_id;
	public int store_id;
	public int cartitem_id;
	
	@SerializedName("store_name")
	public String storeName;   //group에 들어갈 이름
	
	@SerializedName("item_name")
	public String menuName;  //child의 메뉴이름
	public int count; //품목 개수 이메뉴의 수량, child
	public int amount; //price
	
//장바구니 리스트에 보이지 않으나 주문할때 필요
	public int is_whipping; //사용자가 휘핑 선택했는지 안헀는지
	public boolean whippingSelected;
	
	public int is_hot;
	public String size;
}
