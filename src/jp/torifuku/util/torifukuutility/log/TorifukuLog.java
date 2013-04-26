package jp.torifuku.util.torifukuutility.log;

import android.util.Log;

/**
 * TorifukuLog
 * 
 * output log.
 * 
 * <How to use>
 * 1. Please call jp.torifuku.util.torifukuutility.log.TorifukuLog.setDebugable() or setDebugable(boolean) first
 * at android.app.Activity.onCreate(Bundle).
 * 2. Please call at method head.
 * jp.torifuku.util.torifukuutility.log.TorifukuLog.methodIn()
 * 3. method tail at method end.
 * jp.torifuku.util.torifukuutility.log.TorifukuLog.methodOut()
 * You do not need to write Tag & msg.
 * 
 * @author torifuku.kaiou@gmail.com
 *
 */
public class TorifukuLog {
	static private boolean sDebugable = false;
	static private String sTag = "XXXX"; // TODO

	/**
	 * setDebugable
	 * 
	 * Set manifest debuggable
	 */
	static public void setDebugable() {
		// TODO read manifest debuggable
		sDebugable = true;
	}
	
	/**
	 * setDebugable
	 * @param on if true output log
	 */
	static public void setDebugable(boolean on) {
		sDebugable = on;
	}
	
	static public void methodIn() {
		if (!sDebugable) {
			return;
		}
	}
	
	static public void methodOut() {
		if (!sDebugable) {
			return;
		}
	}
	
	static public void d(String msg) {
		if (!sDebugable) {
			return;
		}
	}
	
	static public void e(String msg) {
		if (!sDebugable) {
			return;
		}
	}
	
	static public void i(String msg) {
		if (!sDebugable) {
			return;
		}
	}
	
	static public void w(String msg) {
		if (!sDebugable) {
			return;
		}
	}
}
