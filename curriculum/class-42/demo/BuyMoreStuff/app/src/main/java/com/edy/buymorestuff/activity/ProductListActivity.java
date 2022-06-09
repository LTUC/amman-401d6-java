package com.edy.buymorestuff.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.edy.buymorestuff.R;
import com.edy.buymorestuff.adapter.ProductListRecyclerViewAdapter;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ProductListActivity extends AppCompatActivity
{
  public final String TAG = "ProductListActivity";
  public static final String PRODUCT_ID_TAG = "Product ID Tag";
  SharedPreferences preferences;
  ProductListRecyclerViewAdapter adapter;

  List<Product> products = null;
  private InterstitialAd mInterstitialAd = null;
  private RewardedAd mRewardedAd = null;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_list);

    init();

    /*Contact contact1 =
      Contact.builder()
        .email("contact1@example.com")
        .fullName("Ed Younskevicius")
        .build();
    Amplify.API.mutate(
      ModelMutation.create(contact1),
      successResponse -> Log.i(TAG, "ProductListActivity.onCreate(): made a contact successfully"),  // success callback
      failureResponse -> Log.i(TAG, "ProductListActivity.onCreate(): contact failed with this response: " + failureResponse)  // failure callback
    ); */

    setUpAddProductButton();
    setUpSettingsImageView();
    setUpProductListRecyclerView();
    setUpLoginAndLogoutButton();
    setUpAds();
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    AnalyticsEvent event = AnalyticsEvent.builder()
      .name("resumedApp")
      .addProperty("timeResumed", Long.toString(new Date().getTime()))
      .addProperty("eventDescription", "Resumed ProductListActivity")
      .build();

    Amplify.Analytics.recordEvent(event);

    //String userNickname = preferences.getString(UserSettingsActivity.USER_NICKNAME_TAG, "No nickname");
    AuthUser authUser = Amplify.Auth.getCurrentUser();
    String username = "";
    if (authUser == null)
    {
      Button loginButton = (Button) findViewById(R.id.productListLoginButton);
      loginButton.setVisibility(View.VISIBLE);
      Button logoutButton = (Button) findViewById(R.id.productListLogoutButton);
      logoutButton.setVisibility(View.INVISIBLE);
    }
    else  // authUser is not null
    {
      Log.i(TAG, "Username is: " + username);
      Button loginButton = (Button) findViewById(R.id.productListLoginButton);
      loginButton.setVisibility(View.INVISIBLE);
      Button logoutButton = (Button) findViewById(R.id.productListLogoutButton);
      logoutButton.setVisibility(View.VISIBLE);

      // Not strictly required for your lab, but useful for your project
      Amplify.Auth.fetchUserAttributes(
        success ->
        {
          Log.i(TAG, "Fetch user attributes succeeded for username: " + username);

          for (AuthUserAttribute userAttribute : success)
          {
            if (userAttribute.getKey().getKeyString().equals("nickname"))
            {
              String userNickname = userAttribute.getValue();
              runOnUiThread(() ->
                {
                  ((TextView) findViewById(R.id.productListNicknameTextView)).setText(userNickname);
                }
              );
            }
          }
        },
        failure ->
        {
          Log.i(TAG, "Fetch user attributes failed: " + failure.toString());
        }
      );
    }

    Amplify.API.query(
      ModelQuery.list(Product.class),
      success ->
      {
        Log.i(TAG, "Read products successfully!");
        products.clear();

        for (Product databaseProduct : success.getData())
        {
          products.add(databaseProduct);
        }

        runOnUiThread(() ->
        {
          adapter.notifyDataSetChanged();
        });
      },
      failure -> Log.i(TAG, "Did not read products successfully!")
    );
  }

  private void init()
  {
    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    products = new ArrayList<>();

    // Analytics portion
    AnalyticsEvent event = AnalyticsEvent.builder()
      .name("openedApp")
      .addProperty("timeOpened", Long.toString(new Date().getTime()))
      .addProperty("eventDescription", "Opened ProductListActivity")
      .build();

    Amplify.Analytics.recordEvent(event);
  }

  private void setUpAds(){
    // Ads portion

    MobileAds.initialize(this, new OnInitializationCompleteListener() {
      @Override
      public void onInitializationComplete(InitializationStatus initializationStatus) {
      }
    });

    // Banner ad
    AdView bannerAdView = findViewById(R.id.bannerAdView);
    AdRequest bannerAdRequest = new AdRequest.Builder().build();
    bannerAdView.loadAd(bannerAdRequest);

    // Interstitial Ad
    AdRequest adRequestInterstitial = new AdRequest.Builder().build();

    InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequestInterstitial,
      new InterstitialAdLoadCallback()
      {
        @Override
        public void onAdLoaded(@NonNull InterstitialAd interstitialAd)
        {
          // The mInterstitialAd reference will be null until
          // an ad is loaded.
          mInterstitialAd = interstitialAd;
          Log.i(TAG, "onAdLoaded");

          // This FullScreenContentCallback is not required for your lab for the interstitial ad!
          mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdDismissedFullScreenContent() {
              // Called when fullscreen content is dismissed.
              Log.d(TAG, "The ad was dismissed.");
              Intent goToAddProductActivityIntent = new Intent(ProductListActivity.this, AddProductActivity.class);
              startActivity(goToAddProductActivityIntent);
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
              // Called when fullscreen content failed to show.
              Log.d(TAG, "The ad failed to show.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
              // Called when fullscreen content is shown.
              // Make sure to set your reference to null so you don't
              // show it a second time.
              mInterstitialAd = null;
              Log.d(TAG, "The ad was shown.");
            }
          });
        }

        @Override
        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError)
        {
          // Handle the error
          Log.i(TAG, loadAdError.getMessage());
          mInterstitialAd = null;
        }
      });

    Button interstitialAdButton = (Button)findViewById(R.id.interstitialAdButton);
    interstitialAdButton.setOnClickListener(b ->
    {
      if (mInterstitialAd != null)
      {
        mInterstitialAd.show(ProductListActivity.this);
      }
      else
      {
        Log.d(TAG, "The interstitial ad wasn't ready yet, but we tried to show it.");
      }
    });

    // Rewarded ad

    AdRequest rewardedAdRequest = new AdRequest.Builder().build();

    RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
      rewardedAdRequest, new RewardedAdLoadCallback()
      {
        @Override
        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError)
        {
          // Handle the error.
          Log.d(TAG, loadAdError.getMessage());
          mRewardedAd = null;
        }

        @Override
        public void onAdLoaded(@NonNull RewardedAd rewardedAd)
        {
          mRewardedAd = rewardedAd;
          Log.d(TAG, "Ad was loaded.");

          mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdShowedFullScreenContent() {
              // Called when ad is shown.
              Log.d(TAG, "Ad was shown.");
              runOnUiThread(() -> Toast.makeText(ProductListActivity.this, "Ad was shown!", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
              // Called when ad fails to show.
              Log.d(TAG, "Ad failed to show.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
              // Called when ad is dismissed.
              // Set the ad reference to null so you don't show the ad a second time.
              Log.d(TAG, "Ad was dismissed.");
              mRewardedAd = null;
            }
          });
        }
      });

    Button rewardedAdButton = (Button)findViewById(R.id.rewardedAdButton);
    rewardedAdButton.setOnClickListener(b -> {
      if (mRewardedAd != null) {
        mRewardedAd.show(ProductListActivity.this, new OnUserEarnedRewardListener() {
          @Override
          public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            // Handle the reward.
            int rewardAmount = rewardItem.getAmount();
            String rewardType = rewardItem.getType();
            Log.d(TAG, "The user earned the reward. Amount is: " + rewardAmount + ", and type is: " + rewardType);
          }
        });
      } else {
        Log.d(TAG, "The rewarded ad wasn't ready yet.");
      }
    });
  }

  private void setUpAddProductButton()
  {
    Button addProductButton = findViewById(R.id.addProductButton);
    addProductButton.setOnClickListener(v ->
    {
      Intent goToAddProductActivity = new Intent(ProductListActivity.this, AddProductActivity.class);
      startActivity(goToAddProductActivity);
    });
  }

  private void setUpSettingsImageView()
  {
    ImageView userSettingsImageView = (ImageView) findViewById(R.id.userSettingsImageView);

    userSettingsImageView.setOnClickListener(v ->
    {
      Intent goToUserSettingsIntent = new Intent(ProductListActivity.this, UserSettingsActivity.class);
      startActivity(goToUserSettingsIntent);
    });
  }

  private void setUpProductListRecyclerView()
  {
    RecyclerView productListRecyclerView = (RecyclerView) findViewById(R.id.productListRecyclerView);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    productListRecyclerView.setLayoutManager(layoutManager);

    adapter = new ProductListRecyclerViewAdapter(products, this);  // NOTE: don't need data items in 1-series of steps

    productListRecyclerView.setAdapter(adapter);
  }

  private void setUpLoginAndLogoutButton()
  {
    Button loginButton = (Button) findViewById(R.id.productListLoginButton);
    loginButton.setOnClickListener(v ->
    {
      Intent goToLogInIntent = new Intent(ProductListActivity.this, LoginActivity.class);
      startActivity(goToLogInIntent);
    });

    Button logoutButton = (Button) findViewById(R.id.productListLogoutButton);
    logoutButton.setOnClickListener(v ->
    {
      Amplify.Auth.signOut(
        () ->
        {
          Log.i(TAG, "Logout succeeded!");
          runOnUiThread(() ->
            {
              ((TextView) findViewById(R.id.productListNicknameTextView)).setText("");
            }
          );
          Intent goToLogInIntent = new Intent(ProductListActivity.this, LoginActivity.class);
          startActivity(goToLogInIntent);
        },
        failure ->
        {
          Log.i(TAG, "Logout failed: " + failure.toString());
          runOnUiThread(() ->
          {
            Toast.makeText(ProductListActivity.this, "Log out failed!", Toast.LENGTH_SHORT).show();
          });
        }
      );
    });
  }
}
