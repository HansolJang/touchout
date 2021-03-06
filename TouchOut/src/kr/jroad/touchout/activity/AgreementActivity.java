package kr.jroad.touchout.activity;

import kr.jroad.touchout.adapter.TermsListAdapter;
import kr.jroad.touchout.data.CartResult;
import kr.jroad.touchout.data.PostResult;
import kr.jroad.touchout.data.ProfileResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager;
import kr.jroad.touchout.view.CenterTextActionBarView;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class AgreementActivity extends ActionBarActivity {

	ExpandableListView termsList;
	TermsListAdapter termsAdapter;
	
	ActionBar actionBar;
	CenterTextActionBarView actionBarView;
	TextView actionBarTitleView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agreement);
		actionBar = getSupportActionBar();
		actionBarView = new CenterTextActionBarView(this);
		actionBarTitleView = (TextView)actionBarView.findViewById(R.id.actionbar_title_txt);
		
		termsList = (ExpandableListView) findViewById(R.id.agreement_terms_list);
		termsAdapter = new TermsListAdapter(AgreementActivity.this);

		termsList.setAdapter(termsAdapter);

		termsAdapter.add("Touch Out 이용약관", getResources().getString(R.string.readme1));
		termsAdapter.add("개인정보 수집 및 이용약관", getResources().getString(R.string.readme2));
		termsAdapter.add("위치기반서비스 이용약관", getResources().getString(R.string.readme3));
		termsAdapter.add("개인정보 제3자 제공 약관", getResources().getString(R.string.readme4));

		Button btnAgree = (Button) findViewById(R.id.agreement_btn);
		btnAgree.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//카트아이템 수 받아오고 gcm 아이디 설정
				setCartNumber();
				setRegId();
				setUserName();
				finish();
			}
		});
	}
	
	@Override
	protected void onResume() {
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
		actionBarTitleView.setText("이용약관");
	}
	
	//백버튼 누르면 로그인 안한걸론 간주하고 다시 튜토리얼을 띄움
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		deleteUser();
		//메인으로 돌아가 다시 튜토리얼 -> 로그인 하도록
		Intent i = new Intent(AgreementActivity.this, MainActivity.class);
		startActivity(i);
	}
	
	//이용약관에 동의하지 않으면 만들었던 계정 삭제
	private void deleteUser() {
		NetworkManager.getInstance().deleteUserAccount(this, new OnResultListener<PostResult>() {

			@Override
			public void onSuccess(PostResult result) {
				if(result.success == 1) {
					PropertyManager.getInstance().setFacebookLogin(false);
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void setCartNumber() {
		// 장바구니 숫자 받아오기
		NetworkManager.getInstance().getCartItem(this,
				new OnResultListener<CartResult>() {

					@Override
					public void onSuccess(CartResult result) {
						if (result.success == 1) {
							PropertyManager.getInstance().setCartItemNumber(
									result.result.item_cnt);
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub

					}
				});
	}
	
	private void setRegId() {
		String regId = PropertyManager.getInstance().getRegistrationId();
		if(regId != null && !regId.equals("")) {
			NetworkManager.getInstance().setRegId(this, regId, new OnResultListener<PostResult>() {

				@Override
				public void onSuccess(PostResult result) {
					if(result.success == 1) {
//						Toast.makeText(AgreementActivity.this, "gcm id 설정 성공", Toast.LENGTH_SHORT).show();
					}
				}

				@Override
				public void onFail(int code) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
	private void setUserName() {
		NetworkManager.getInstance().getUserProfile(AgreementActivity.this,
				new OnResultListener<ProfileResult>() {

					@Override
					public void onSuccess(ProfileResult result) {
						if (result.success == 1) {
							PropertyManager.getInstance().setUserName(
									result.result.items.name);
						}
					}

					@Override
					public void onFail(int code) {
						// TODO Auto-generated method stub

					}
				});
	}

}
