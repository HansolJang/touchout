package kr.jroad.touchout.push;

import kr.jroad.touchout.activity.CallActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.manager.PropertyManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class CallService extends Service {

	public static final int NOTIFICATION_CALL_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	String storeName;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		setAlarm();
		storeName = intent.getStringExtra(GcmIntentService.NOTI_STORE_NAME);
		return Service.START_NOT_STICKY;
	}

	private void setAlarm() {
		//픽업완료 일때는 sharedPreference 값 초기화
		if (PropertyManager.getInstance().isPickUp()) {
			stopSelf();
		} else {
			// pickup 안했을 경우 1분마다 서비스 재실행 -> 노티 띄우기
			// 이름 받아온 후 노티
			sendNotification();
			long time = System.currentTimeMillis() + 60000;

			AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			PendingIntent pi = PendingIntent.getService(this, 0, new Intent(
					CallService.this, CallService.class), 0);
			am.set(AlarmManager.RTC, time, pi);
		}

	}

	private void sendNotification() {
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent i = new Intent(this, CallActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pi = PendingIntent.getActivity(this, 0,
				i, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(CallService.this);
		builder.setSmallIcon(R.drawable.appicon);
		builder.setTicker("TOUCH OUT 주문하신 음료가 완성되었습니다!");
		
		// 널체크후 상점 이름과 유저이름 세팅
		if(storeName != null && !storeName.equals("")) {
			builder.setContentTitle("TOUCHOUT(" + storeName + ")");
		} else {
			builder.setContentTitle("TOUCHOUT");
		}
		String userName = PropertyManager.getInstance().getUserName();
		if(userName != null && !userName.equals("")) {
			builder.setContentText( userName + "님! 픽업하기를 눌러주세요!");
		} else {
			builder.setContentText("테이크아웃을 하고 픽업하기를 눌러주세요!");
		}
		builder.setWhen(System.currentTimeMillis());
		
		builder.setContentIntent(pi);
		
		builder.setDefaults(Notification.DEFAULT_ALL);
		
		builder.setAutoCancel(true);
		
		mNotificationManager.notify(NOTIFICATION_CALL_ID, builder.build());
	}

}
