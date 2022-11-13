package com.darkedges.webauthn;

import static com.google.android.gms.fido.Fido.getFido2ApiClient;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import org.apache.commons.beanutils.BeanUtils;

@CapacitorPlugin(name = "WebAuthn")
public class WebAuthnPlugin extends Plugin {
    private WebAuthn implementation = new WebAuthn();

    @Override
    public void load() {
        super.load();
        // Obtain the Fido2ApiClient instance.
        implementation.setFido2APICLient(getFido2ApiClient(bridge.getActivity()));
    }

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void isWebAuthnAvailable(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.isWebAuthnAvailable());
        call.resolve(ret);
    }

    @PluginMethod
    public void isWebAuthnAutoFillAvailable(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", implementation.isWebAuthnAutoFillAvailable());
        call.resolve(ret);
    }

    @PluginMethod
    public void startRegistration(PluginCall call) {
        try {
            PublicKeyCredentialCreationOptions.Builder builder = new PublicKeyCredentialCreationOptions.Builder()
                    .setUser(WebAuthnUtil.parseUser(call))
                    .setChallenge(WebAuthnUtil.parseChallenge(call))
                    .setParameters(WebAuthnUtil.parseParameters(call))
                    .setTimeoutSeconds(WebAuthnUtil.parseTimeoutSeconds(call))
                    .setAttestationConveyancePreference(WebAuthnUtil.parseAttestation(call))
                    .setExcludeList(WebAuthnUtil.parseExcludeList(call))
                    //.setAuthenticatorSelection(WebAuthnUtil.parseAuthenticatorSelection(call))
                    .setRp(WebAuthnUtil.parseRp(call));
            PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = builder.build();
            Log.d("intent", publicKeyCredentialCreationOptions.getAttestationConveyancePreference().toString());
            Log.d("intent", new String(publicKeyCredentialCreationOptions.getChallenge()));
            Log.d("intent", new String(publicKeyCredentialCreationOptions.getUser().getId()));
            Log.d("intent", publicKeyCredentialCreationOptions.getUser().getName());
            Log.d("intent", publicKeyCredentialCreationOptions.getUser().getDisplayName());
            Log.d("intent", publicKeyCredentialCreationOptions.getRp().getId());
            Log.d("intent", publicKeyCredentialCreationOptions.getRp().getName());
            Log.d("intent",""+publicKeyCredentialCreationOptions.getParameters().get(0).getType());
            Log.d("intent",""+publicKeyCredentialCreationOptions.getParameters().get(0).getAlgorithm().describeContents());
            Log.d("intent",""+publicKeyCredentialCreationOptions.getParameters().get(1).getType());
            Log.d("intent",""+publicKeyCredentialCreationOptions.getParameters().get(1).getAlgorithm().describeContents());
            Log.i("intent", "1");
            Task result = implementation.getIntent(publicKeyCredentialCreationOptions);
            Log.i("Intent", "2");
            result.addOnSuccessListener(
                    new OnSuccessListener() {

                        @Override
                        public void onSuccess(Object o) {
                            if (o != null) {
                                Log.i("Intent", "All good");
                                PendingIntent pendingIntent = (PendingIntent) o;
                                int SIGN_REQUEST_CODE = 0;
                                try {
                                    getActivity().startIntentSenderForResult(
                                            pendingIntent.getIntentSender(),
                                            0,
                                            null,
                                            0,0,0,null
                                    );
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
            result.addOnFailureListener(
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Intent", "All bad");
                        }
                    });
//            bridge.saveCall(call);
//            startActivityForResult(call, intent.getResult(), "verifyResult");

            JSObject ret = new JSObject();
            ret.put("value", false);
            call.resolve(ret);
        } catch (Exception e) {
            JSObject ret = new JSObject();
            ret.put("value", "Failed");
            call.resolve(ret);
        }
    }

    @ActivityCallback
    private void verifyResult(PluginCall call, ActivityResult result){
        Log.i("Intent","verifyResult");
    }
}
