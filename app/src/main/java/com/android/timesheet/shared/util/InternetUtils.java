package com.android.timesheet.shared.util;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.timesheet.R;

/**
 * Created by vamsikonanki on 8/18/2017.
 */

public class InternetUtils {

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        //boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    static MaterialDialog mLoadingDialog;

    public static void initLoadingDialog(Context context) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.backgroundColor(Color.WHITE);
        builder.titleColor(context.getResources().getColor(R.color.black));
        builder.contentColor(context.getResources().getColor(R.color.colorPrimaryTransparentPopup));
        mLoadingDialog = builder
                .content(R.string.prompt_progress_title)
                .title(R.string.error_no_network)
                .progress(true, 0)
                .autoDismiss(false)
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }

    public static void hideLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    public static void showLoadingDialog(Context context) {
        if (mLoadingDialog == null)
            initLoadingDialog(context);
    }

    public static boolean showingDialog() {
        return mLoadingDialog != null && mLoadingDialog.isShowing();

    }
}
