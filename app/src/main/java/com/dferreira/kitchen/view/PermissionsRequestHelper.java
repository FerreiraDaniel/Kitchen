package com.dferreira.kitchen.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.dferreira.kitchen.R;

/**
 * Helper used to require permissions to the user in run time
 */
public class PermissionsRequestHelper {


    /**
     * Show a dialog to the user why the permission is request
     *
     * @param activity              Context where is to create the dialog
     * @param title                 The title of the dialog to show to the user
     * @param message               Message that is to show to the user
     * @param permission            Permission that is necessary
     * @param permissionRequestCode the code of the permission required
     */
    private static void showExplanation(@NonNull final Activity activity,
                                        String title,
                                        String message,
                                        final String permission,
                                        final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        pRequestPermission(activity, permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    /**
     * Request the permission to the user
     *
     * @param activity              Context where is to create the dialog
     * @param permissionName        The name of the permission to request
     * @param permissionRequestCode The code that is going to be return to the activity when a user decides give or not permission
     */
    private static void pRequestPermission(@NonNull Activity activity, String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(activity,
                new String[]{permissionName}, permissionRequestCode);
    }

    /**
     * Checks if has permission to do something and if not requests to the user
     *
     * @param activity    Context where is to create the dialog
     * @param permission  The name of permission to request
     * @param requestCode Application specific request code to match with a result
     * @return False ->    Was not possible to the the demand permission
     * True ->     Everything went alright
     */
    public static boolean requestPermission(@NonNull final Activity activity, @NonNull String permission, int requestCode) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showExplanation(activity, activity.getString(R.string.permission_needed), activity.getString(R.string.rationale), permission, requestCode);
            } else {
                pRequestPermission(activity, permission, requestCode);
            }
            return false;
        }
    }
}
