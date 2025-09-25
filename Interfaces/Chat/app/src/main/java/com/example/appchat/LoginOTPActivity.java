package com.example.appchat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appchat.utils.AndroidUtil;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginOTPActivity extends AppCompatActivity {

    String phoneNumber;
    Long timeoutSeconds = 60L;

    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    EditText otpInput;
    Button nextBtn;
    ProgressBar progressBar;
    TextView resendOtpTextView;
    FirebaseAuth mAtuh = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_otpactivity);

        otpInput = findViewById(R.id.login_otp);
        nextBtn = findViewById(R.id.login_next_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        resendOtpTextView = findViewById(R.id.resend_otp_textView);


        phoneNumber = getIntent().getExtras().getString("phone");

        sendOtp(phoneNumber, false);
    }

   void sendOtp(String phoneNumber, boolean isResend){

        setInProgress(true);

       PhoneAuthOptions.Builder builder =
               PhoneAuthOptions.newBuilder(mAtuh)
                       .setPhoneNumber(phoneNumber)
                       .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
                       .setActivity(this)
                       .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                           @Override
                           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                               signIn(phoneAuthCredential);
                               setInProgress(false);
                           }

                           @Override
                           public void onVerificationFailed(@NonNull FirebaseException e) {

                               AndroidUtil.showToast(getApplicationContext(),"El codigo de verificacion no se envio");
                               setInProgress(false);


                           }

                           @Override
                           public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               super.onCodeSent(s, forceResendingToken);
                               verificationCode = s;
                               resendingToken = forceResendingToken;
                               AndroidUtil.showToast(getApplicationContext(),"El codigo de verificacion se envio correctamente");
                               setInProgress(false);

                           }
                       });

       if(isResend){
           PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
       } else{
           PhoneAuthProvider.verifyPhoneNumber(builder.build());

       }

    }

    void setInProgress(boolean inProgress){

        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.GONE);
        } else{
            progressBar.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        }
    }

    void signIn(PhoneAuthCredential phoneAuthCredential){

    }
}