package kr.jroad.touchout.view;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.data.RankingWord;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class SearchRankingListView extends FrameLayout {

	public int[] icon = new int[] { R.drawable.search_icon1,
			R.drawable.search_icon2, R.drawable.search_icon3,
			R.drawable.search_icon4, R.drawable.search_icon5,
			R.drawable.search_icon6, R.drawable.search_icon7,
			R.drawable.search_icon8, R.drawable.search_icon9, R.drawable.search_icon9 };
	
	public SearchRankingListView(Context context) {
		super(context);
		init();
	}
	
	ImageLoader imgLoader;
	ImageView iconView;
	TextView keywordView;
	
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.search_ranking_list_item, this);
		imgLoader = ImageLoader.getInstance();
		
		iconView = (ImageView)findViewById(R.id.search_icon_img);
		keywordView = (TextView)findViewById(R.id.search_keyword_txt);
	}
	
	int position;
	
	public void setData(RankingWord data) {
		position = data.rank-1;
		iconView.setImageResource(icon[position]);
		keywordView.setText(data.keyword);
	}

}
