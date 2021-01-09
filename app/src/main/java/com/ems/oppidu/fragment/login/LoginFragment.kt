package com.ems.oppidu.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ds.kerching.fragment.login.LoginViewModel
import com.ems.oppidu.base.BaseActivity
import com.ems.oppidu.base.BaseFragment
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.oppidu.oppidu.databinding.FragmentLoginBinding
import java.util.concurrent.TimeUnit
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : BaseFragment()  {

    lateinit var mBinding: FragmentLoginBinding
    val mViewModel by viewModels<LoginViewModel> { getViewModelProvider() }
    private lateinit var mActivity: BaseActivity
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var verificationInProgress = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity
        auth = Firebase.auth

        setupObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mBinding = FragmentLoginBinding.inflate(inflater, container, false)
            .apply {
            viewModel = mViewModel
            clickHandler = ClickHandler()

        }

        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity = activity as BaseActivity

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.

                // [START_EXCLUDE silent]
                verificationInProgress = false
                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
                //updateUI(STATE_VERIFY_SUCCESS, credential)
                // [END_EXCLUDE]
                //signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                TODO("Not yet implemented")
                verificationInProgress = false
                if (e is FirebaseAuthInvalidCredentialsException) {
                   // binding.fieldPhoneNumber.error = "Invalid phone number."
                }else if (e is FirebaseTooManyRequestsException) {
//                    Snackbar.make("findViewById(android.R.id.content)", "Quota exceeded.",
//                        Snackbar.LENGTH_SHORT).show()
                }
                //updateUI(STATE_VERIFY_FAILED)

            }

/*
            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)
                // [START_EXCLUDE silent]
                verificationInProgress = false
                // [END_EXCLUDE]

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    binding.fieldPhoneNumber.error = "Invalid phone number."
                    // [END_EXCLUDE]
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_SHORT).show()
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                updateUI(STATE_VERIFY_FAILED)
                // [END_EXCLUDE]
            }
*/

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                //storedVerificationId = verificationId
                //resendToken = token

                // [START_EXCLUDE]
                // Update UI
                //updateUI(STATE_CODE_SENT)
                // [END_EXCLUDE]
            }
        }


    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(mActivity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        // [END start_phone_auth]

        verificationInProgress = true
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
    }

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(mActivity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information


                    val user = task.result?.user
                    // [START_EXCLUDE]
                   // updateUI(STATE_SIGNIN_SUCCESS, user)
                    // [END_EXCLUDE]
                } else {
                    // Sign in failed, display a message and update the UI

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        // [START_EXCLUDE silent]
                       // binding.fieldVerificationCode.error = "Invalid code."
                        // [END_EXCLUDE]
                    }
                    // [START_EXCLUDE silent]
                    // Update UI
                   // updateUI(STATE_SIGNIN_FAILED)
                    // [END_EXCLUDE]
                }
            }
    }

    private fun updateUI(uiState: Int, cred: PhoneAuthCredential) {
       // updateUI(uiState, null, cred)
    }

    private fun setupObserver() {



    }

    /*
    * Use for
    * all click event
    * */
    inner class ClickHandler {


        fun onClickLogin() {
            commonCallbacks?.hideKeyboard()
            if (mViewModel.isLoginDataValid()) {
                showProgress()
                mViewModel.callLoginApi()
            }
        }





    }
}