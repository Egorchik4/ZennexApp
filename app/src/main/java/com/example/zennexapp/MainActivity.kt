package com.example.zennexapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zennexapp.presentation.NewsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		setTheme(R.style.SplashScreen)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.fragmentContainer, NewsListFragment())
			.commit()
	}
}