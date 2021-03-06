package lads.contancsharing.www.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.jaeger.library.StatusBarUtil
import lads.contancsharing.www.utils.Helper
import lads.contancsharing.www.utils.SPManager.getInstance

import lads.contancsharing.www.utils.SessionManager

open class BaseActivity : AppCompatActivity() {
    var isLoggedIn = false
    lateinit var sessionManager: SessionManager
    lateinit var mContext: Context
    open val TAG: String = "com.lads.contactsharing"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        sessionManager = SessionManager.getInstance(mContext.applicationContext)
        //   isLoggedIn = sessionManager.isLoggedIn
        setStatusBarMode(true)
        setStatusBarTransparent(this)
    }

    var loadingLayout: RelativeLayout? = null
    fun showLoading() {
        loadingLayout?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        loadingLayout?.visibility = View.GONE
    }
    open fun setStatusBarMode(enableDarkMode: Boolean) {
        if (enableDarkMode) {
            StatusBarUtil.setDarkMode(this)
        } else {
            StatusBarUtil.setLightMode(this)
        }
    }


    open fun setStatusBarTransparent(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            activity.window.statusBarColor = Color.TRANSPARENT
        } else {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }


    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount <= 1) {
            super.onBackPressed()
        } else {
            fragmentManager.popBackStack()
        }
    }
    fun printLog(string: String) {
        Log.d(TAG, string+"\n")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            Amplify.Auth.handleWebUISignInResponse(data)
        }
    }
}