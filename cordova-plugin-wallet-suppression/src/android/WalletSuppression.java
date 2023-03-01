package org.apache.cordova.walletsuppression;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.util.Log;

import java.io.Console;


public class WalletSuppression extends CordovaPlugin {

  private static final String TAG = "WalletSuppression";

  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      if (action.equals("enableWallet")) {
          NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this.cordova.getActivity());
          if (nfcAdapter != null) {
            nfcAdapter.setNdefPushMessageCallback(null, this.cordova.getActivity());
            callbackContext.success();
            return true;
          }
          else{

            Log.d(TAG, "No NFC Adapter found! ");
          }
          return true;
      }

      if (action.equals("disableWallet")) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this.cordova.getActivity());
        if (nfcAdapter != null) {
        nfcAdapter.setNdefPushMessageCallback(new NfcAdapter.CreateNdefMessageCallback() {
            @Override
            public NdefMessage createNdefMessage(NfcEvent event) {
                return null;
            }
        }, this.cordova.getActivity());
        callbackContext.success();
        return true;}
        else{
          Log.d(TAG, "No NFC Adapter found! ");

        }
    }
    return false;
}
  }

