package com.example.composeproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.composeproject.data.Dog
import com.example.composeproject.ui.theme.MyTheme
import com.example.composeproject.ui.theme.ProfileScreen


class ProfileActivity : AppCompatActivity() {

    private val puppy: Dog by lazy {
        intent?.getSerializableExtra(PUPPY_ID) as Dog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ProfileScreen(puppy)
            }
        }
    }

    companion object {
        private const val PUPPY_ID = "puppy_id"
        fun newIntent(context: Context, puppy: Dog) =
            Intent(context, ProfileActivity::class.java).apply {
                putExtra(PUPPY_ID, puppy)
            }
    }
}