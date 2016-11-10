package com.rntemplate.imagepicker;

import com.facebook.react.bridge.ReactContextBaseJavaModule;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ActivityEventListener;


import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

// include this by edmund 

// include this edmund 
import com.facebook.react.bridge.ReactMethod;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;


public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public ImagePickerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);
    }

    private static final int PICK_IMAGE = 1;

    private Callback pickerSuccessCallback;
    private Callback pickerCancelCallback;

    @ReactMethod
    public void openSelectDialog(ReadableMap config, Callback successCallback, Callback cancelCallback) {
        Activity currentActivity = getCurrentActivity();
    
        if (currentActivity == null) {
            cancelCallback.invoke("Activity doesn't exist");
            return;
        }
        
        pickerSuccessCallback = successCallback;
        pickerCancelCallback = cancelCallback;

        try {
            final Intent galleryIntent = new Intent();

            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Pick an image");

            currentActivity.startActivityForResult(chooserIntent, PICK_IMAGE);
        } catch (Exception e) {
            cancelCallback.invoke(e);
        }
    }

    @Override
    public String getName() {
        return "ImagePicker";
    }


    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
        if (pickerSuccessCallback != null) {
            if (resultCode == Activity.RESULT_CANCELED) {
                pickerCancelCallback.invoke("ImagePicker was cancelled");
            } else if (resultCode == Activity.RESULT_OK) {
                Uri uri = intent.getData();

                if (uri == null) {
                    pickerCancelCallback.invoke("No image data found");
                } else {
                    try {
                        pickerSuccessCallback.invoke(uri);
                    } catch (Exception e) {
                        pickerCancelCallback.invoke("No image data found");
                    }
                }
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}


