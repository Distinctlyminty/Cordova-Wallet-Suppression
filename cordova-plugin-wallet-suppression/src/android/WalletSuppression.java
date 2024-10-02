package org.apache.cordova.walletsuppression;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class WalletSuppression extends CordovaPlugin {

    private static final String TAG = "WalletSuppression";
    private NfcAdapter nfcAdapter;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this.cordova.getActivity());

        if (nfcAdapter == null) {
            Log.d(TAG, "No NFC Adapter found!");
            return false;
        }

        if (action.equals("enableWallet")) {
            disableReaderMode();
            callbackContext.success("Wallet enabled.");
            return true;
        }

        if (action.equals("disableWallet")) {
            enableReaderMode();
            callbackContext.success("Wallet disabled.");
            return true;
        }

        return false;
    }

    private void enableReaderMode() {
        if (nfcAdapter != null) {
            nfcAdapter.enableReaderMode(
                this.cordova.getActivity(),
                new NfcAdapter.ReaderCallback() {
                    @Override
                    public void onTagDiscovered(Tag tag) {
                        // Do nothing here, since we just want to prevent the wallet from opening.
                    }
                },
                NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_NFC_B,
                null
            );
            Log.d(TAG, "Reader mode enabled. Wallet suppressed.");
        }
    }

    private void disableReaderMode() {
        if (nfcAdapter != null) {
            nfcAdapter.disableReaderMode(this.cordova.getActivity());
            Log.d(TAG, "Reader mode disabled. Wallet behavior restored.");
        }
    }
}