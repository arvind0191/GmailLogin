package com.infojini.gmaillogin.Myinterface;

import com.infojini.gmaillogin.DataClass.UserInformation;

/**
 * Created by nishit on 15/02/16.
 */
public interface OngmailDataListener {
    void OnErrorListener(String Message);
    void OnSuccessListener(UserInformation userInformation);
}
