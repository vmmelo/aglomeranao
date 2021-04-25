package br.ufpe.cin.aglomerano

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.*
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class AuthActivity : AppCompatActivity() {

    lateinit var providers:List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //TODO remover chamada da main activity
        return startActivity(Intent(this@AuthActivity, MainActivity::class.java))

        init()
        themeAndLogo()
    }

    private fun init() {
        providers = arrayListOf(
                EmailBuilder().build(),
                GoogleBuilder().build()
        )
    }

    private fun createSignInIntent() {
        // [START auth_fui_create_intent]

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN
        )
        // [END auth_fui_create_intent]
    }

    // [START auth_fui_result]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                if(user != null) {
                    Toast.makeText(this@AuthActivity, "Bem vindo, ${user.displayName}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                }
                // ...
            } else {
                Toast.makeText(this@AuthActivity, response?.error?.message, Toast.LENGTH_SHORT).show()
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
    // [END auth_fui_result]

    private fun signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                // ...
            }
        // [END auth_fui_signout]
    }

    private fun delete() {
        // [START auth_fui_delete]
        AuthUI.getInstance()
            .delete(this)
            .addOnCompleteListener {
                // ...
            }
        // [END auth_fui_delete]
    }

    private fun themeAndLogo() {
        // [START auth_fui_theme_logo]
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.common_google_signin_btn_icon_light) // Set logo drawable
//                .setTheme(R.style.ThemeOverlay_AppCompat_Dark) // Set theme
                        .build(),
                RC_SIGN_IN
        )
        // [END auth_fui_theme_logo]
    }

    companion object {

        private const val RC_SIGN_IN = 8002
    }
}