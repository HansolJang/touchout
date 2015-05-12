package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SearchRankingListHeaderView extends FrameLayout {

	public SearchRankingListHeaderView(Context context) {
		super(context);
		init();
	}

	TextView rankingCategoryView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.search_ranking_list_header, this);
	
		rankingCategoryView =(TextView)findViewById(R.id.search_ranking_category);
	}
	
	public void setData(String text) {
		rankingCategoryView.setText(text);
	}
}
