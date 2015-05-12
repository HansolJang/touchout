package kr.jroad.touchout.manager;

import java.util.ArrayList;

import kr.jroad.touchout.extra.MyApplication;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;


public class PropertyManager {
	
	public interface OnCartChangeListener {
		public void onCartChangeListener();
	}
	
	ArrayList<OnCartChangeListener> cartChangeListenerList = new ArrayList<OnCartChangeListener>();
	
//	public void setOnCartChangeListener(OnCartChangeListener listener) {
//		cartChangeListener = listener;
//	}
	
	public void addOnCartChangeListener(OnCartChangeListener listener) {
		cartChangeListenerList.add(listener);
	}
	
	public void removeCartChangeListener(OnCartChangeListener listener) {
		cartChangeListenerList.remove(listener);
	}
	
	Handler mHandler = new Handler(Looper.getMainLooper());
	
	public void notifyCartChangeListener() {
		mHandler.removeCallbacks(notifyRunnable);
		mHandler.post(notifyRunnable);
	}
	
	// array에 담긴 모든 리스너 호출
	Runnable notifyRunnable = new Runnable() {
		
		@Override
		public void run() {
			for (OnCartChangeListener l : cartChangeListenerList) {
				l.onCartChangeListener();
			}
		}
	};

	private static PropertyManager instance;

	public static PropertyManager getInstance() {
		if (instance == null) {
			instance = new PropertyManager();
		}
		return instance;
	}

	SharedPreferences mPrefs;
	SharedPreferences.Editor mEditor;

	private static final String PREF_NAME = "my_prefs";

	private PropertyManager() {
		mPrefs = MyApplication.getContext().getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		mEditor = mPrefs.edit();
	}
	
	public int cartItemNumber;
	public static final String FIELD_CART_ITEM_NUMBER = "cartItemNumber";
	
	public int getCartItemNumber() {
		return cartItemNumber;
	}
	public void setCartItemNumber(int count) {
		this.cartItemNumber = count;
		mEditor.putInt(FIELD_CART_ITEM_NUMBER, count);
		mEditor.commit();
		
		notifyCartChangeListener();
	}
	
	//facebookLogin이 true면 
	public boolean facebookLogin;
	public static final String FIELD_FACEBOOK_LOGIN = "facebookLogin";

	public boolean isFacebookLogin() {
		return facebookLogin;
	}
	
	public void setFacebookLogin(boolean isLogin) {
		this.facebookLogin = isLogin;
		mEditor.putBoolean(FIELD_FACEBOOK_LOGIN, isLogin);
		mEditor.commit();
	}

	//gcm regid
	private String regId;
	public static final String FIELD_REG_ID = "regid";
	public void setRegistrationId(String regid) {
		this.regId = regid;
		mEditor.putString(FIELD_REG_ID, regid);
		mEditor.commit();
	}
	
	public String getRegistrationId() {
		if (regId == null) {
			regId = mPrefs.getString(FIELD_REG_ID, "");
		}
		return regId;
	}
	
	//유저가 픽업완료
	private boolean isPickUp;
	public static final String FIELD_IS_PICKUP = "isPickUp";
	public boolean isPickUp() {
		return mPrefs.getBoolean(FIELD_IS_PICKUP, false);
	}
	
	public void setIsPickUp(boolean isPickUp) {
		this.isPickUp = isPickUp;
		mEditor.putBoolean(FIELD_IS_PICKUP, isPickUp);
		mEditor.commit();
	}
	
	private String userName;
	public static final String FIELD_USER_NAME = "userName";
	public String getUserName() {
		if(userName == null) {
			userName = mPrefs.getString(FIELD_USER_NAME, "");
		}
		return userName;
	}
	public void setUserName(String name) {
		this.userName = name;
		mEditor.putString(FIELD_USER_NAME, name);
		mEditor.commit();
	}
	
	
}
