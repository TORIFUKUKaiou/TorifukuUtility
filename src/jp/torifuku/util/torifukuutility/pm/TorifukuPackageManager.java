package jp.torifuku.util.torifukuutility.pm;

import java.util.List;

import jp.torifuku.util.torifukuutility.log.TorifukuLog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

/**
 * TorifukuPackageManager
 * @author torifuku.kaiou@gmail.com
 *
 */
public class TorifukuPackageManager {
	private PackageManager mPackageManager = null;
	
	
	/**
	 * Constructor
	 * @param context
	 */
	public TorifukuPackageManager(Context context) {
		if (context != null) {
			mPackageManager = context.getPackageManager();
		}
	}
	
	/**
	 * isInstalled
	 * @param packageName
	 * @return True if packageName found, otherwise false.
	 */
	public boolean isInstalled(String packageName) {
		TorifukuLog.methodIn();
		if (mPackageManager == null) {
			return false;
		}
		if (packageName == null) {
			return false;
		}
		
		List<PackageInfo> list = mPackageManager.getInstalledPackages(0);
		if (list == null) {
			return false;
		}
		
		boolean ret = false;
		for (PackageInfo info : list) {
			if (info == null) {
				continue;
			}
			if (packageName.equals(info.packageName)) {
				ret = true;
				break;
			}
		}
		TorifukuLog.methodOut();
		return ret;
	}
	
	/**
	 * @param activity
	 * @param packageName
	 * @param msg
	 */
	public void showInstallDialog(final Activity activity, final String packageName, String msg) {
		TorifukuLog.methodIn();
		if (activity == null) {
			return;
		}
		String threadName = Thread.currentThread().getName();
		if (!"main".equals(threadName)) {
			// Please call on main thread.
			Log.w("Torifuku", "Please call on main thread");
			return;
		}
		
		AlertDialog.Builder builder = null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			builder = new AlertDialog.Builder(activity, AlertDialog.THEME_HOLO_LIGHT);
		} else {
			builder = new AlertDialog.Builder(activity);
		}
		builder.setMessage(msg);
		builder.setNegativeButton(android.R.string.no, null);
		builder.setPositiveButton(android.R.string.yes, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				TorifukuLog.methodIn();
				if (which != DialogInterface.BUTTON_POSITIVE) {
					return;
				}
				
				// launch google play app
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("market://details?id=" + packageName));
				activity.startActivity(intent);
				
				TorifukuLog.methodOut();
			}});
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		
		TorifukuLog.methodOut();
	}
}
