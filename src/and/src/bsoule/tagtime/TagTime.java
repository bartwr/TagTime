package bsoule.tagtime;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

public class TagTime extends Application {
	private final static String TAG = "TagTime";

	public final static boolean DISABLE_LOGV = true;

	private static Context sContext = null;

	public void onCreate() {
		super.onCreate();

		sContext = getApplicationContext();

		String pkgname = sContext.getPackageName(), version;
		try {
			version = sContext.getPackageManager().getPackageInfo(pkgname, 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			Log.w(TAG, "getPackageInfo() failed! Msg:" + e.getMessage());
			version = "???";
		}
		Log.v(TAG, "Starting TagTime. Package=" + pkgname + ", Version=" + version);
	}

	public static boolean checkBeeminder() {
		if (sContext == null) return false;
		PackageManager pm = sContext.getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo("com.beeminder.beeminder", PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}
}
