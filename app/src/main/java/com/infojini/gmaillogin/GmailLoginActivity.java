package com.infojini.gmaillogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.infojini.gmaillogin.DataClass.UserInformation;
import com.infojini.gmaillogin.Myinterface.OngmailDataListener;

/**
 * Created by nishit on 15/02/16.
 */
public class GmailLoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,OngmailDataListener


{
    GoogleApiClient mGoogleApiClient;
    int RC_SIGN_IN = 100;
    public String TAG = "MAinActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this  , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            UserInformation userInformation = new UserInformation();
            userInformation.Name = acct.getDisplayName();
            userInformation.emaiId = acct.getEmail();
            userInformation.googleId = acct.getId();
            userInformation.picUrl = acct.getPhotoUrl();
            OnSuccessListener(userInformation);

        } else {

            // Signed out, show unauthenticated UI.
            OnErrorListener("Login failed!!please try again!");
        }
    }
    @Override
    public void OnErrorListener(String Message) {

    }

    @Override
    public void OnSuccessListener(UserInformation userInformation) {

    }
}
