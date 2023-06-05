package com.example.zennexapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zennexapp.presentation.NewsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	companion object {

		const val INSTANCE_KEY = "INSTANCE_KEY"
		const val INSTANCE_VALUE = 1
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		setTheme(R.style.SplashScreen)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val instance = savedInstanceState?.getInt(INSTANCE_KEY)
		if (instance == null) {
			supportFragmentManager
				.beginTransaction()
				.replace(R.id.fragmentContainer, NewsListFragment())
				.commit()
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putInt(INSTANCE_KEY, INSTANCE_VALUE)
	}
}