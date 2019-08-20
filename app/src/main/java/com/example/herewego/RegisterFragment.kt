package com.example.herewego

import androidx.fragment.app.Fragment
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import com.squareup.okhttp.MediaType
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_fragment.username_text_input
import kotlinx.android.synthetic.main.login_fragment.view.register_button
import kotlinx.android.synthetic.main.register_fragment.*
import kotlinx.android.synthetic.main.register_fragment.view.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception


class RegisterFragment : Fragment() {


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.register_fragment, container, false)
        view.register_button.setOnClickListener ({
            doAsync {
                try {

                    val username = username_text_input.text.toString()
                    val firstname = first_name.text.toString()
                    val lastname = last_name.text.toString()
                    var age: Int = age.text.toString().toInt()
                    val email = email.text.toString()
                    var password = password.text!!
                    var confirmPassword = confirm_password.text!!
                    val phone = phone_number.text.toString()
                        if (confirmPassword(password, confirmPassword)) {
                            val response = registerUser(username, firstname, lastname, age, email, password, phone)
                            val register_user = JSONObject(response)
                            getActivity()?.runOnUiThread() {
                            if (register_user.get("register_status").toString() == "success") {
                                password_text.error = null
                                (activity as NavigationHost).navigateTo(LoginFragment(), false)
                            } else {

                                error_render.error = getString(R.string.unsuccessful_registration)


                            }
                            }
                        } else {
                            getActivity()?.runOnUiThread() {
                                password_text.error = getString(R.string.password_match)
                            }



                        }


                }

                catch (e: Exception){
                    println(e)
                }
            }
        })



            view.password.setOnKeyListener({ _, _, _ ->
                if (confirmPassword(password.text!!, confirm_password.text!!)) {
                    // Clear the error.
                    getActivity()?.runOnUiThread() {
                        password_text.error = null
                    }

                }
                false
            })



        return view
    }


    fun registerUser(
        username: String?,
        firstname: String?,
        lastname: String?,
        age: Int?,
        email: String?,
        password: Editable?,
        phone: String?
    ): String {
        val url = "https://herewegoadi.appspot.com/apiregister"

        val password_string = password.toString()
        val json = """
    {
        "username":"${username}",
        "firstname":"${firstname}",
        "lastname":"${lastname}",
        "age":"${age}",
        "email":"${email}",
        "password":"${password_string}",
        "user_phone":"${phone}"
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

    fun confirmPassword(password1: Editable?, password2: Editable?): Boolean {
        return (password1.toString() != null && password2.toString() != null) && password1.toString() == password2.toString()
    }

}