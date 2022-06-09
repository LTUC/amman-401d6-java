package com.edy.buymorestuff;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.geo.location.AWSLocationGeoPlugin;
import com.amplifyframework.predictions.aws.AWSPredictionsPlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class BuyMoreStuffAmplifyApplication extends Application
{
  public static final String TAG = "buymorestufffapplication";

  @Override
  public void onCreate() {
    super.onCreate();
    try
    {
      Amplify.addPlugin(new AWSApiPlugin());
      Amplify.addPlugin(new AWSCognitoAuthPlugin());
      Amplify.addPlugin(new AWSS3StoragePlugin());
      Amplify.addPlugin(new AWSLocationGeoPlugin());
      Amplify.addPlugin(new AWSPinpointAnalyticsPlugin(this));
      Amplify.addPlugin(new AWSPredictionsPlugin());
      Amplify.configure(getApplicationContext());
    } catch (AmplifyException ae)
    {
      Log.e(TAG, "Error initializing Amplify: " + ae.getMessage(), ae);
    }
  }
}
