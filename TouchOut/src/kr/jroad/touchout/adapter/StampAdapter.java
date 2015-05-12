package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.StampData;
import kr.jroad.touchout.view.StampView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StampAdapter extends BaseAdapter {

	Context mContext;
	ArrayList<StampData> items = new ArrayList<StampData>();
	
	public StampAdapter(Context context) {
		mContext = context;
	}
	
	public void add(StampData data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void clear(){
		items.clear();
		notifyDataSetChanged();
	}
	
	public void addAll(ArrayList<StampData> list) {
		for(StampData d : list) {
			items.add(d);
		}
		notifyDataSetChanged();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		StampView view;
		if(convertView == null) {
			view = new StampView(mContext);
		} else {
			view = (StampView) convertView;
		}
		view.setData(items.get(position));
		return view;
	}

}
