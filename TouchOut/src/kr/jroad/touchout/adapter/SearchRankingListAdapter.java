package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.RankingWord;
import kr.jroad.touchout.view.SearchRankingListView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SearchRankingListAdapter extends BaseAdapter {
	
	Context mContext;
	ArrayList<RankingWord> items = new ArrayList<RankingWord>();
	
	public SearchRankingListAdapter(Context context) {
		mContext = context;
	}

	public void add(RankingWord data) {
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
		SearchRankingListView view;
		if(convertView == null) {
			view = new SearchRankingListView(mContext);
		}else {
			view = (SearchRankingListView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

}
