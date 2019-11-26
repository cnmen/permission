package com.cmonbaby.permission.demo;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.cmonbaby.permission.annotation.NeedsPermission;
import com.cmonbaby.permission.annotation.OnNeverAskAgain;
import com.cmonbaby.permission.annotation.OnPermissionDenied;
import com.cmonbaby.permission.annotation.OnShowRationale;
import com.cmonbaby.permission.core.PermissionManager;
import com.cmonbaby.permission.core.listener.PermissionSetting;
import com.cmonbaby.permission.core.listener.ProceedPermission;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "simon >>> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Click event
    public void permissions(View view) {
        PermissionManager.requestPermissions(this, new String[]{Manifest.permission.CAMERA});
    }

    // When All permission granted, invoke this method
    @NeedsPermission()
    public void permissionGranted() {
        Log.e(TAG, "permissionGranted()");
    }

    @OnShowRationale()
    public void showRationale(final ProceedPermission request) {
        Log.e(TAG, "showRationale()");
        new AlertDialog.Builder(this)
                .setMessage("Why need these permissions")
                .setPositiveButton("Know", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // Try request permission again
                        request.proceed();
                    }
                })
                .show();
    }

    @OnPermissionDenied()
    public void permissionDenied() {
        Log.e(TAG, "OnPermissionDenied()");
    }

    @OnNeverAskAgain()
    public void neverAskAgain(final PermissionSetting setting) {
        Log.e(TAG, "OnNeverAskAgain()");
        new AlertDialog.Builder(this)
                .setMessage("Never ask again")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Log.e(TAG, "showNeverAskForCamera() >>> Dialog");
                        // Enter application settings
                        setting.setting(666);
                    }
                })
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TAG, "onRequestPermissionsResult()");
        PermissionManager.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
