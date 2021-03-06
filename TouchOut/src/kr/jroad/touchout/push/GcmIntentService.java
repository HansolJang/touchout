package kr.jroad.touchout.push;

import kr.jroad.touchout.activity.MainActivity;
import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.manager.PropertyManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmIntentService extends IntentService {
	private static final String TAG = "GcmIntengService";
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	public static final String NOTI_STORE_NAME = "store";

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();

		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) { // has effect of unparcelling Bundle

			// if
			// (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
			// // This loop represents the service doing some work.
			// String time = intent.getStringExtra("time");
			// sendNotification("Received: " + extras.toString());
			// }
			/*
			 * 주문 호출 order_id 받아야함
			 */
			if (messageType.equals("gcm")) {
				// 주문 완료후 최초로 푸시 왔을때
				PropertyManager.getInstance().setIsPickUp(false);
				// 매장이름 빼내기
				String storeName = intent.getStringExtra(NOTI_STORE_NAME);
				
				// 서비스 시작
				Intent i = new Intent(GcmIntentService.this, CallService.class);
				i.putExtra(NOTI_STORE_NAME, storeName);
				startService(i);
			}

		}
		// Release the wake lock provided by the WakefulBroadcastReceiver.
		GcmBroadcastReceiver.completeWakefulIntent(intent);
	}

	// Put the message into a notification and post it.
	// This is just one simple example of what you might choose to do with
	// a GCM message.
	private void sendNotification(String msg) {
		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("GCM Notification")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}
}
