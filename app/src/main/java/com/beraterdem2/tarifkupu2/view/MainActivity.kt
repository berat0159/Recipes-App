package com.beraterdem2.tarifkupu2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.beraterdem2.tarifkupu2.R
import com.beraterdem2.tarifkupu2.adapter.DessertAdapter
import com.beraterdem2.tarifkupu2.adapter.MealAdapter
import com.beraterdem2.tarifkupu2.databinding.ActivityMainBinding
import com.beraterdem2.tarifkupu2.modul.Meals
import com.beraterdem2.tarifkupu2.view2.AddBranceActivity
import com.beraterdem2.tarifkupu2.view2.BranceActivity




class MainActivity : AppCompatActivity() {
    private lateinit var arrayList: ArrayList<Meals>
    private lateinit var arrayList2: ArrayList<Meals>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        arrayList=ArrayList()
        arrayList2=ArrayList()

        mealDate()

        val anim=AnimationUtils.loadAnimation(this,R.anim.recyclerview_anim)
        binding.recyclerView.animation=anim

        binding.recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val adapter= MealAdapter(arrayList)
        binding.recyclerView.adapter=adapter


        binding.recyclerView2.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val desAdapter= DessertAdapter(arrayList2)
        binding.recyclerView2.adapter=desAdapter


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=MenuInflater(this)
        menuInflater.inflate(R.menu.brance_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(R.id.brance_id==item.itemId){
            val intent=Intent(this@MainActivity, AddBranceActivity::class.java)
            startActivity(intent)
        }
        if(R.id.mybrance_id==item.itemId){
            val intent=Intent(this@MainActivity, BranceActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    fun mealDate(){

        val brownie= Meals("Brownie",R.drawable.brownie,R.drawable.browniekurabiyetxt)
        val elmali= Meals("Elmalı Kurabiye",R.drawable.elmalikurabiye,R.drawable.elmalikurabiyetxt)
        val islakKek= Meals("İslak Kek",R.drawable.islakkek,R.drawable.islakektxt)
        val kadinBudu= Meals("Kadin Budu",R.drawable.kadinbudu,R.drawable.kadinbudutxt)
        val pizza= Meals("Pizza",R.drawable.pizza,R.drawable.pizzatxt)
        val pogaca= Meals("Pogaca",R.drawable.pogaca,R.drawable.pogacatxt)
        val profiterol= Meals("Profiterol",R.drawable.profiterol,R.drawable.profiteroltxt)
        val sultanKebabi= Meals("Sultan Kebabı",R.drawable.sultankebabi,R.drawable.sultankebabitxt)
        val sutlac= Meals("Sütlac",R.drawable.sutlac,R.drawable.sutlactxt)
        val tavukSote= Meals("Tavuk Sote",R.drawable.tavuksote,R.drawable.tavuksotetxt)

        arrayList2.add(sutlac)
        arrayList2.add(profiterol)
        arrayList2.add(elmali)
        arrayList2.add(islakKek)
        arrayList2.add(brownie)

        arrayList.add(kadinBudu)
        arrayList.add(pizza)
        arrayList.add(pogaca)
        arrayList.add(sultanKebabi)
        arrayList.add(tavukSote)

    }
}