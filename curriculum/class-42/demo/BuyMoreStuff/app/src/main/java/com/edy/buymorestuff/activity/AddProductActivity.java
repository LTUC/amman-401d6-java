package com.edy.buymorestuff.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.edy.buymorestuff.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddProductActivity extends AppCompatActivity
{
  public static final String TAG = "AddProductActivity";
  Spinner productCategorySpinner = null;
  Spinner contactSpinner = null;
  // WARNING: Be careful of using CompletableFuture in runOnUiThread()! Sometimes it seems to break
  // Also, I recommend using a future only in a single Activity, not between activities
  CompletableFuture<List<Contact>> contactsFuture = null;
  FusedLocationProviderClient locationProviderClient = null;
  Geocoder geocoder;

  static final int LOCATION_POLLING_INTERVAL = 5 * 1000;  // 5 seconds, in milliseconds

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    contactsFuture = new CompletableFuture<>();

    // Step 2: Actually request the location permission in onCreate()
    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);  // hardcoded to 1; you can pick anything between 1 and 65535
    // Step 3: Set up a FusedLocationProviderClient
    locationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
    locationProviderClient.flushLocations();

    setUpSpinners();
    setUpSaveButton();

    // After this point is not in your lab today!

    // getCurrentLocation() example
    /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      Log.e(TAG, "Application does not have access to either ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION!");
      return;
    }

    locationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, new CancellationToken()
    {
      @NonNull
      @Override
      public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener)
      {
        return null;
      }

      @Override
      public boolean isCancellationRequested()
      {
        return false;
      }
    }).addOnSuccessListener(location ->
      {
        if (location == null)
        {
          Log.e(TAG, "Location callback was null!");
        }
        String currentLatitude = Double.toString(location.getLatitude());
        String currentLongitude = Double.toString(location.getLongitude());
        Log.i(TAG, "Our current latitude: " + currentLatitude);
        Log.i(TAG, "Our current longitude: " + currentLongitude);
      }
    );

    // Subscription example for many updates

    geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
    LocationRequest locationRequest = LocationRequest.create();
    locationRequest.setInterval(LOCATION_POLLING_INTERVAL);
    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    LocationCallback locationCallback = new LocationCallback()
    {
      @Override
      public void onLocationResult(@NonNull LocationResult locationResult)
      {
        super.onLocationResult(locationResult);

        try
        {
          String address = geocoder.getFromLocation(
              locationResult.getLastLocation().getLatitude(),
              locationResult.getLastLocation().getLongitude(),
              1)  // give us only 1 best guess
            .get(0)  // grab that best guess
            .getAddressLine(0);  // get the first address line

          Log.i(TAG, "Repeating current location is: " + address);
        } catch (IOException ioe)
        {
          Log.e(TAG, "Could not get subscribed location: " + ioe.getMessage(), ioe);
        }
      }
    };

    locationProviderClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
   */
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    Intent callingIntent = getIntent();
    if((callingIntent != null) && (callingIntent.getType() != null)
      && (callingIntent.getType().equals("text/plain")))  // we got called from outside the app with an implicit intent
    {
      String callingText = callingIntent.getStringExtra(Intent.EXTRA_TEXT);
      if (callingText != null)
      {
        ((EditText) findViewById(R.id.addProductProductNameEditText)).setText(callingText);  // TODO: See why this doesn't update on subsequent calls
      }
    }
  }

  private void setUpSpinners()
  {
    contactSpinner = (Spinner) findViewById(R.id.addProductContactSpinner);

    Amplify.API.query(
      ModelQuery.list(Contact.class),
      success ->
      {
        Log.i(TAG, "Read contacts successfully!");
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : success.getData())
        {
          contacts.add(contact);
          contactNames.add(contact.getFullName());
        }
        contactsFuture.complete(contacts);

        runOnUiThread(() ->
        {
          contactSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            contactNames));
        });
      },
      failure -> {
        contactsFuture.complete(null);
        Log.i(TAG, "Did not read contacts successfully!");
      }
    );

    productCategorySpinner = (Spinner) findViewById(R.id.addProductProductCategorySpinner);
    productCategorySpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      ProductCategoryEnum.values()));
  }

  private void setUpSaveButton()
  {
    Button saveButton = (Button) findViewById(R.id.saveProductButton);
    saveButton.setOnClickListener(v ->
    {
      // Step 4: Return if we don't have access to the appropriate location permissions
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
      {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        Log.e(TAG, "Application does not have access to either ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION!");
        return;
      }

      String name = ((EditText) findViewById(R.id.addProductProductNameEditText)).getText().toString();
      String description = ((EditText) findViewById(R.id.addProductProductDescriptionEditText)).getText().toString();
      //String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
      String selectedContactString = contactSpinner.getSelectedItem().toString();

      List<Contact> contacts = null;
      try
      {
        contacts = contactsFuture.get();
      } catch (InterruptedException ie)
      {
        Log.e(TAG, "InterruptedException while getting contacts");
        Thread.currentThread().interrupt();
      } catch (ExecutionException ee)
      {
        Log.e(TAG, "ExecutionException while getting contacts");
      }
      Contact selectedContact = contacts.stream().filter(c -> c.getFullName().equals(selectedContactString)).findAny().orElseThrow(RuntimeException::new);

      // Step 5: Grab the location in an OnSuccessListener
      locationProviderClient.getLastLocation().addOnSuccessListener(location ->  // "location" here could be null if no one else has request a location prior!
          // Try running Google Maps first if you have a null callback here!
        {
          if (location == null)
          {
            Log.e(TAG, "Location callback was null!");
          }
          String currentLatitude = Double.toString(location.getLatitude());
          String currentLongitude = Double.toString(location.getLongitude());
          Log.i(TAG, "Our latitude: " + location.getLatitude());
          Log.i(TAG, "Our longitude: " + location.getLongitude());
          saveProduct(name, description, currentLatitude, currentLongitude, selectedContact);
        }
      ).addOnCanceledListener(() ->
      {
        Log.e(TAG, "Location request was canceled!");
      })
        .addOnFailureListener(failure ->
        {
          Log.e(TAG, "Location request failed! Error was: " + failure.getMessage(), failure.getCause());
        })
        .addOnCompleteListener(complete ->
        {
          Log.e(TAG, "Location request completed!");
        });
    });
  }

  private void saveProduct(String name, String description, String latitude, String longitude, Contact selectedContact)
  {
    Product newProduct = Product.builder()
      .name(name)
      .description(description)
      //.dateCreated(new Temporal.DateTime(currentDateString))
      .dateCreated(new Temporal.DateTime(new Date(), 0))
      .productCategory((ProductCategoryEnum) productCategorySpinner.getSelectedItem())
      .productLatitude(latitude)
      .productLongitude(longitude)
      .contactPerson(selectedContact)
      .productImageS3Key("")
      .build();

    Amplify.API.mutate(
      ModelMutation.create(newProduct),  // making a GraphQL request to the cloud
      successResponse -> Log.i(TAG, "AddProductActivity.onCreate(): made a product successfully"),  // success callback
      failureResponse -> Log.i(TAG, "AddProductActivity.onCreate(): failed with this response: " + failureResponse)  // failure callback
    );
    //Toast.makeText(getApplicationContext(), "Product saved!", Toast.LENGTH_SHORT).show();
    Snackbar.make(findViewById(R.id.addProductActivity), "Product saved!", Snackbar.LENGTH_SHORT).show();
  }
}
