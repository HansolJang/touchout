package kr.jroad.touchout.extra;

import kr.jroad.touchout.activity.R;
import kr.jroad.touchout.manager.NetworkManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.HttpClientImageDownloader;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	private static Context mContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		initImageLoader(this);
	}
	
	public static Context getContext(){
		return mContext;
	}
	
	public static void initImageLoader(Context context){
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.loading_img)
		.showImageForEmptyUri(R.drawable.loading_img)
		.showImageOnFail(R.drawable.loading_img)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.build();
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.defaultDisplayImageOptions(options)
				.imageDownloader(new HttpClientImageDownloader(context, NetworkManager.getInstance().getHttpClient()))
				.build();
		ImageLoader.getInstance().init(config);
	}
	
}
