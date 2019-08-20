package com.example.herewego

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.okhttp.MediaType
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class LoginFragment : Fragment() {

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        view.register_button.setOnClickListener{
            doAsync {
                (activity as NavigationHost).navigateTo(RegisterFragment(), false)
            }
        }

        view.cancel_button.setOnClickListener{
            doAsync {
                try {
                    (activity as NavigationHost).navigateTo(LoginFragment(), false)
                }
                catch (e:Exception){
                    println(e)
                }
            }
        }
        view.login_button.setOnClickListener {
            doAsync {
                try {
                    val username = username_text_input.text.toString()
                    val password = password_edit_text.text!!
                    val response = validateLogin(username, password)
                    val check_user = JSONObject(response)
                    if (check_user.get("user_authenticated").toString() == "Success") {
                        val gotresponse = registeredEvents(username)
                        Users.setResponse(gotresponse)
                        Users.setUsername(username)
                        (activity as NavigationHost).navigateTo(NavigateFragment(), true)
                    } else {

                        getActivity()?.runOnUiThread() {
                            password_text_input.error = getString(R.string.error_password)
                        }


                    }
                }

                catch (e: Exception){
                    println(e)
                }
            }
        }
        return view
    }

     fun validateLogin(username:String?, password:Editable?): String {
        val url = "https://herewegoadi.appspot.com/apilogin/" + username + "/" + password
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()

        val response = client.newCall(request).execute()
        val bodystr =  response.body().string() // this can be consumed only once

        println(bodystr)

        return bodystr
    }


    fun registeredEvents(
        username: String?
    ): String {
        val url = "https://herewegoadi.appspot.com/userpagejson"
        val json = """
{
    "user":"${username}"
}
""".trimIndent()

        val client = OkHttpClient()
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .header("User-Agent", "Android")
            .build()
        val response = client.newCall(request).execute()
        val bodystr = response.body().string() // this can be consumed only once

        return bodystr
    }

}
