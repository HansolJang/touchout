package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.view.CenterTextActionBarView;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchMainFragment extends Fragment {

	public static final String SEARCH_KEYWORD = "keyword";
	
	ActionBar actionBar;
	CenterTextActionBarView actionBarView;
	TextView tabTitleView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		setHasOptionsMenu(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBarView = new CenterTextActionBarView(getActivity());
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.main_orange_color));
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(Gravity.CENTER));
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_search_main, container, false);
	}

	String keyword;
	EditText keywordEdit;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		tabTitleView = (TextView)actionBarView.findViewById(R.id.actionbar_title_txt);
		tabTitleView.setText("더 찾기");
		
		SearchRankingFragment f = new SearchRankingFragment();
		FragmentTransaction ft = getChildFragmentManager().beginTransaction();
		ft.replace(R.id.search_container, f);
		ft.commit();
		
		keywordEdit = (EditText) view.findViewById(R.id.search_keyword_edit);

		Button btnSearch = (Button) view.findViewById(R.id.search_btn);
		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyword = keywordEdit.getText().toString();
				if (keyword != null && !keyword.equals("")) {

					// edit창 저절로 없어지게
					InputMethodManager imm = (InputMethodManager) getActivity()
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(keywordEdit.getWindowToken(), 0);

					// 검색어가 있으면 검색 결과
					SearchResultFragment f = new SearchResultFragment();
					Bundle b = new Bundle();
					b.putString(SEARCH_KEYWORD, keyword);
					f.setArguments(b);
					FragmentTransaction ft = getChildFragmentManager()
							.beginTransaction();
					ft.replace(R.id.search_container, f);
					ft.commit();

				} else {
					// 검색어가 없으면 추천검색어 리스트
					Toast.makeText(getActivity(), "검색어를 입력해주세요!",
							Toast.LENGTH_SHORT).show();
					SearchRankingFragment f = new SearchRankingFragment();
					FragmentTransaction ft = getChildFragmentManager()
							.beginTransaction();
					ft.replace(R.id.search_container, f);
					ft.commit();
				}
			}
		});

	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.main_orange_color));
		actionBar.setCustomView(actionBarView);
		
		tabTitleView.setText("더 찾기");
	}
	
	// 추천검색어 누르면 그 눌린 키워드의 검색결과로 이동
	public void goRecommendResult(String keyword) {
		SearchResultFragment f = new SearchResultFragment();
		Bundle b = new Bundle();
		b.putString(SEARCH_KEYWORD, keyword);
		f.setArguments(b);
		FragmentTransaction ft = getChildFragmentManager()
				.beginTransaction();
		ft.replace(R.id.search_container, f);
		ft.commit();
	}

}
