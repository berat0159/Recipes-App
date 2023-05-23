package com.beraterdem2.tarifkupu2.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beraterdem2.tarifkupu2.modul.Meals
import com.beraterdem2.tarifkupu2.databinding.ActivityDetailsBinding

@Suppress("DEPRECATION")
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent:Intent=getIntent()

        val meals= intent.getSerializableExtra("push") as Meals?
        if (meals!=null){
            binding.imageDetail.setImageResource(meals.mealImage)
            binding.detailTxt.setImageResource(meals.mealDetail)
        }

        val desserts=intent.getSerializableExtra("pushDessert")as Meals?
        if (desserts!=null){
            binding.imageDetail.setImageResource(desserts.mealImage)
            binding.detailTxt.setImageResource(desserts.mealDetail)

        }



        val actionBar=supportActionBar
        actionBar?.title="Tarif Detayı"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)


    }
}