package in.pathri.officeprofile;

import java.lang.reflect.Method;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class DataConnectionHelper {
  private static String TAG = "OfficeProfile";
  public static void setMobileDataState(boolean mobileDataEnabled, Context ctx)
{
    try
    {
        TelephonyManager telephonyService = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

        Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

        if (null != setMobileDataEnabledMethod)
        {
            setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
        }
    }
    catch (Exception ex)
    {
        Log.e(TAG, "Error setting mobile data state", ex);
    }
}

public static boolean getMobileDataState(Context ctx)
{
    try
    {
        TelephonyManager telephonyService = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

        Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");

        if (null != getMobileDataEnabledMethod)
        {
            boolean mobileDataEnabled = (Boolean) getMobileDataEnabledMethod.invoke(telephonyService);

            return mobileDataEnabled;
        }
    }
    catch (Exception ex)
    {
        Log.e(TAG, "Error getting mobile data state", ex);
    }

    return false;
}
}
