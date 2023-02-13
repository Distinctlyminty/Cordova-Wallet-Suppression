
package com.ald.WalletSupression;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.nfc.NfcAdapter;


public class WalletSuppression extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
      if (action.equals("enableWallet")) {
          NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(cordova.getActivity());
          nfcAdapter.setNdefPushMessageCallback(null, cordova.getActivity());
          callbackContext.success();
          return true;
      }
     
      if (action.equals("disableWallet")) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(cordova.getActivity());
        nfcAdapter.setNdefPushMessageCallback(new NfcAdapter.CreateNdefMessageCallback() {
            @Override
            public NdefMessage createNdefMessage(NfcEvent event) {
                return null;
            }
        }, cordova.getActivity());
        callbackContext.success();
        return true;
    }
    return false;
}
  }

