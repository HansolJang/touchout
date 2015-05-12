package kr.jroad.touchout.activity;

import java.io.IOException;

import kr.jroad.touchout.data.CartResult;
import kr.jroad.touchout.data.PostResult;
import kr.jroad.touchout.data.ProfileResult;
import kr.jroad.touchout.manager.NetworkManager;
import kr.jroad.touchout.manager.NetworkManager.OnResultListener;
import kr.jroad.touchout.manager.PropertyManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GreetingActivity extends Activity {

	public static final int MESSAGE_MOVE_ACTIVITY = 0;
	public static final int TIMEOUT_ACTIVITY = 2000;

	boolean isStartActivity = false;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_MOVE_ACTIVITY:
				if (!isStartActivity) {
					isStartActivity = true;
					Intent i = new Intent(GreetingActivity.this,
							MainActivity.class);
					startActivity(i);
					finish();
				}
				break;
			}
		}
	};

	String provider;
	LocationManager lm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_greeting);

	}

	@Override
	protected void onResume() {
		super.onResume();

		// gcm 아이디 얻어오기
		getGcmRegId();
		// 위치정보 세팅
		setLocationService();
		// 자동로그인
		autoLogin();
		// mHandler.sendMessageDelayed(
		// mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY), TIMEOUT_ACTIVITY);

	}

	@Override
	protected void onPause() {
		super.onPause();
		mHandler.removeMessages(MESSAGE_MOVE_ACTIVITY);
	}

	// 페이스북 사용에 필수!
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (Session.getActiveSession() != null) {
			Session.getActiveSession().onActivityResult(this, requestCode,
					resultCode, data);
		}
	}

	GoogleCloudMessaging gcm;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private static final String SENDER_ID = "453067806975";

	private void registerInBackground() {
		new AsyncTask<Void, Integer, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging
								.getInstance(GreetingActivity.this);
					}
					String regid = gcm.register(SENDER_ID);

					PropertyManager.getInstance().setRegistrationId(regid);

				} catch (IOException ex) {
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				// runOnUiThread(nextAction);
			}
		}.execute(null, null, null);
	}

	private void setRegId() {
		String regId = PropertyManager.getInstance().getRegistrationId();
		if (regId != null && !regId.equals("")) {
			NetworkManager.getInstance().setRegId(this, regId,
					new OnResultListener<PostResult>() {

						@Override
						public void onSuccess(PostResult result) {
							if (result.success == 1) {
//								Toast.makeText(GreetingActivity.this,
//										"gcm id 설정 성공", Toast.LENGTH_SHORT)
//										.show();
							}
						}

						@Override
						public void onFail(int code) {
							// TODO Auto-generated method stub

						}
					});
		}
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
						resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST);
				dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {

					}
				});
				dialog.show();
			} else {
				// To Do...
				finish();
			}
			return false;
		}
		return true;
	}

	private void setCartItemNumber() {
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

	private void autoLogin() {
		if (PropertyManager.getInstance().isFacebookLogin()) {
			Session.openActiveSession(this, true, new StatusCallback() {

				@Override
				public void call(Session session, SessionState state,
						Exception exception) {
					if (session.isOpened()) {
						String token = session.getAccessToken();

						// 터치아웃에 계정생성
						NetworkManager.getInstance().doLogin(
								GreetingActivity.this, token,
								new OnResultListener<PostResult>() {

									@Override
									public void onSuccess(PostResult result) {
										if (result.success == 1) {
											if (result.result.is_loggedin == 1) {
												if (result.result.is_new == 1) {
													PropertyManager
															.getInstance()
															.setFacebookLogin(
																	true);
													// 약관 동의
													Intent i = new Intent(
															GreetingActivity.this,
															AgreementActivity.class);
													startActivity(i);
												} else {
													// 로그인 성공
													PropertyManager
															.getInstance()
															.setFacebookLogin(
																	true);

													setCartItemNumber();
													setRegId();
													setUserName();
													
													mHandler.sendMessageDelayed(
															mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY),
															TIMEOUT_ACTIVITY);
												}
											} else {

											}

										}
									}

									@Override
									public void onFail(int code) {
										// 자동로그인 실패
										PropertyManager.getInstance()
												.setFacebookLogin(false);
										mHandler.sendMessageDelayed(
												mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY),
												TIMEOUT_ACTIVITY);
									}

								}); // doLogin()
					}
				} // call()
			});
		} else {
			// 자동로그인이 안될경우 튜토리얼로 이동
			mHandler.sendMessageDelayed(
					mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY),
					TIMEOUT_ACTIVITY);
		}
	}

	private void setLocationService() {
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// provider = LocationManager.GPS_PROVIDER;

			/****
			 * *** 일단 네트워크로 고정
			 * *****/
			provider = LocationManager.NETWORK_PROVIDER;

			// mHandler.sendMessageDelayed(
			// mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY),
			// TIMEOUT_ACTIVITY);
		} else if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
			// mHandler.sendMessageDelayed(
			// mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY),
			// TIMEOUT_ACTIVITY);
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					GreetingActivity.this);
			builder.setIcon(R.drawable.appicon);
			builder.setTitle("위치정보 사용 동의");
			builder.setMessage("위치정보 사용을 동의하고 gps 사용을 활성화하러 설정페이지로 이동하시겠습니까?");
			builder.setPositiveButton("이동",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent i = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(i);
						}
					});
			builder.setNegativeButton("취소",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							mHandler.sendMessageDelayed(mHandler
									.obtainMessage(MESSAGE_MOVE_ACTIVITY),
									TIMEOUT_ACTIVITY);
						}
					});
			builder.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					mHandler.sendMessageDelayed(
							mHandler.obtainMessage(MESSAGE_MOVE_ACTIVITY),
							TIMEOUT_ACTIVITY);
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();

		}
	}

	private void getGcmRegId() {
		// gcm 사용가능한 플레이 서비스 버전인지 체크
		if (checkPlayServices()) {
			// property manager에 저장
			String regId = PropertyManager.getInstance().getRegistrationId();
			if (!regId.equals("")) {
				// runOnUiThread(nextAction);
			} else {
				// regid 발급
				registerInBackground();
			}
		} else {
		}
	}

	private void setUserName() {
		NetworkManager.getInstance().getUserProfile(GreetingActivity.this,
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
