package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.StampItem;
import kr.jroad.touchout.view.StampListView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StampListAdapter extends BaseAdapter {
	
	Context mContext;
	ArrayList<StampItem> items = new ArrayList<StampItem>();
	
	public StampListAdapter(Context context) {
		mContext = context;
	}

	public void add(StampItem data) {
		items.add(data);
		notifyDataSetChanged();
	}
	
	public void clear() {
		items.clear();
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
		StampListView view;
		if(convertView == null) {
			view = new StampListView(mContext);
		} else {
			view = (StampListView) convertView;
		}
		view.setData(items.get(position));
		return view;
	}

}
