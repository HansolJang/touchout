package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.OrderbyDropdownData;
import kr.jroad.touchout.view.OrderbyDropdownView;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OrderbySpinnerAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<OrderbyDropdownData> items = new ArrayList<OrderbyDropdownData>();
	
	private void init() {
		OrderbyDropdownData data = new OrderbyDropdownData("거리순", true);
		OrderbyDropdownData data1 = new OrderbyDropdownData("별점순", false);
		items.add(data);
		items.add(data1);
		notifyDataSetChanged();
	}
	
	public void setSelected(int position) {
		for(int i = 0; i < items.size(); i++) {
			if(i == position) {
				items.get(i).selected = true;
			} else {
				items.get(i).selected = false;
			}
		}
		notifyDataSetChanged();
	}
	
	public OrderbySpinnerAdapter(Context context) {
		mContext = context;
		init();
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		TextView view;
		if(convertView == null) {
			view = new TextView(mContext);
		} else {
			view = (TextView) convertView;
		}
		view.setTextSize(12);
		view.setTextColor(Color.WHITE);
		view.setText(items.get(position).text);
		return view;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		OrderbyDropdownView tv;
		if (convertView == null) {
			tv = new OrderbyDropdownView(mContext);
		} else {
			tv = (OrderbyDropdownView)convertView;
		}
		tv.setData(items.get(position));
		return tv;
	}

}
