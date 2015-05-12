package kr.jroad.touchout.adapter;

import java.util.ArrayList;

import kr.jroad.touchout.data.ReviewItem;
import kr.jroad.touchout.view.ReviewListView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ReviewListAdapter extends BaseAdapter implements ReviewListView.OnReviewDeleteListener{

	public interface OnReviewDeleteAdapterListener {
		public void onDeleteAdapterListener(ReviewListView view, ReviewItem data);
	}
	
	OnReviewDeleteAdapterListener deleteListener;
	
	public void setOnDeleteAdapterListener(OnReviewDeleteAdapterListener listener) {
		deleteListener = listener;
	}
	
	Context mContext;
	ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
	
	public ReviewListAdapter(Context context) {
		mContext = context;
	}
	
	public void add(ReviewItem data) {
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
		ReviewListView view;
		if(convertView == null) {
			view = new ReviewListView(mContext);
			view.setOnReviewDeleteListener(this);
		} else {
			view = (ReviewListView)convertView;
		}
		view.setData(items.get(position));
		return view;
	}

	@Override
	public void onReviewDeleteListener(ReviewListView view, ReviewItem data) {
		if(deleteListener != null) {
			deleteListener.onDeleteAdapterListener(view, data);
		}
	}

}
