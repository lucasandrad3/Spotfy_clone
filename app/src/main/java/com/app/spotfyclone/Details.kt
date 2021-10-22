package com.app.spotfyclone

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.spotfyclone.Model.Album
import com.app.spotfyclone.Model.titulos
import com.app.spotfyclone.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        intent.extras?.let {
            var img = it.getString("album")
            var positionTitle = it.getInt("position")
            Picasso.get().load(img).into(binding.imageView)
            binding.tile.text = titulos[positionTitle-1]
        }

        binding.tollbar.navigationIcon = getDrawable(R.drawable.ic_baseline_arrow_back)

        window.statusBarColor = Color.LTGRAY
        binding.tollbar.setNavigationOnClickListener {
            finish()
        }

    }
}