package hu.proha.ilvoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import hu.proha.ilvoto.adapters.ViewsPagerAdapter
import hu.proha.ilvoto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val pagerAdapter = ViewsPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
            tab.text = when(position){
                0 -> "Profil"
                1 -> "Események"
                else -> ""
            }
        }.attach()
    }

}