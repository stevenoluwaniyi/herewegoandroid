package com.example.herewego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.login_fragment.view.password_edit_text
import kotlinx.android.synthetic.main.login_fragment.view.password_text_input
import kotlinx.android.synthetic.main.login_fragment.view.username_text_input
import kotlinx.android.synthetic.main.navigate_fragments.view.*
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.lang.Exception

class NavigateFragment : Fragment() {

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.navigate_fragments, container, false)

        view.registeredevents.setOnClickListener{
            doAsync {
                (activity as NavigationHost).navigateTo(RegisteredEvents(), true)
            }
        }

        view.searchevents.setOnClickListener{
            doAsync {
                try {
                    (activity as NavigationHost).navigateTo(SearchEvent(), true)
                }
                catch (e: Exception){
                    println(e)
                }
            }
        }
        view.joinevents.setOnClickListener {
            doAsync {
                try {
                    (activity as NavigationHost).navigateTo(JoinEvent(), true)
                }
                catch (e: Exception){
                    println(e)
                }


            }
        }
        return view
    }
}