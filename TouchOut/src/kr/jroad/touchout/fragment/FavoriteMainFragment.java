package kr.jroad.touchout.fragment;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.view.CenterTextActionBarView;
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
import android.widget.TextView;

public class FavoriteMainFragment extends Fragment {
	
	ActionBar actionBar;
	TextView tabTitleView;
	CenterTextActionBarView actionBarView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
		actionBarView = new CenterTextActionBarView(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_favorite_main, container, false);
		
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		tabTitleView = (TextView)actionBarView.findViewById(R.id.actionbar_title_txt);
		tabTitleView.setText("즐겨찾기");
		
		FavoriteFragment f = new FavoriteFragment();
		FragmentTransaction ft = getChildFragmentManager().beginTransaction();
		ft.replace(R.id.favorite_container, f);
		ft.commit();
	
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setLogo(R.drawable.null_image);
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.main_orange_color));
		actionBar.setCustomView(actionBarView, new ActionBar.LayoutParams(Gravity.CENTER));
		actionBar.setLogo(R.drawable.null_image);
		actionBar.setTitle("");
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.main_orange_color));
		actionBar.setCustomView(actionBarView);
		tabTitleView.setText("즐겨찾기");
	}

}
