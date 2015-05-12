package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.AppInfoActivity;
import kr.jroad.touchout.activity.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AppInfoDetailFragment extends Fragment {
	
	String content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		Bundle b = getArguments();
		if( b != null) {
			content = b.getString(AppInfoActivity.APP_INFO_DETAIL_CONTENT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_app_info_detail, container, false);
	}
	
	TextView tv;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		tv = (TextView)view.findViewById(R.id.notice_detail_txt);
		tv.setText(Html.fromHtml(content));
	}
	
}
