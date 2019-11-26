### Hey, Do you really know about Permission ?
#### If you not sure, please click here : [Permission](https://www.cmonbaby.com)

## Permission ![Build Status](https://travis-ci.org/greenrobot/EventBus.svg?branch=master)

## About coding
```java
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
```

## Add permission to your project
<a href="https://www.cmonbaby.com">
<img src="https://img.shields.io/bintray/v/cmonbaby/simon/permission_annotation?label=maven-central"></a>

Via Gradle:
```gradle
implementation 'com.cmonbaby.permission.core:core:1.0.0'
implementation 'com.cmonbaby.permission.annotation:annotation:1.0.0'
annotationProcessor 'com.cmonbaby.permission.compiler:compiler:1.0.0'
```

Via Maven:
```xml
<dependency>
    <groupId>com.cmonbaby.permission.annotation</groupId>
    <artifactId>annotation</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>com.cmonbaby.permission.compiler</groupId>
    <artifactId>compiler</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>com.cmonbaby.permission.core</groupId>
    <artifactId>core</artifactId>
    <version>1.0.0</version>
</dependency>
```

## License

Copyright (C) 2013-2020 Markus Junginger, Simon (https://www.cmonbaby.com)  
Permission binaries and source code can be used according to the [Apache License, Version 2.0](LICENSE).
