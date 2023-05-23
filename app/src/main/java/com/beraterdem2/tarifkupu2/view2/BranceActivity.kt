package com.beraterdem2.tarifkupu2.view2

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.beraterdem2.tarifkupu2.adapter.BranceAdapter
import com.beraterdem2.tarifkupu2.databinding.ActivityBranceBinding
import com.beraterdem2.tarifkupu2.modul.Brance

class BranceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBranceBinding
    private lateinit var arrayList: ArrayList<Brance>
    private lateinit var adapter: BranceAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayList=ArrayList()

        val actionBar=supportActionBar
        actionBar?.title="Anılar"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        getData()

        binding.recyclerView2.layoutManager=GridLayoutManager(this,2)
        adapter= BranceAdapter(arrayList)
        binding.recyclerView2.adapter=adapter





    }

    fun getData(){
        try {

            val database=this.openOrCreateDatabase("Meals", MODE_PRIVATE, null)
            val cursor=database.rawQuery("SELECT * FROM meals",null)

            val branceNameIx=cursor.getColumnIndex("mealName")
            val idIx=cursor.getColumnIndex("id")
            val branceImageIx=cursor.getColumnIndex("image")
            while (cursor.moveToNext()){

                val name=cursor.getString(branceNameIx)
                val id=cursor.getInt(idIx)
                val byteArray=cursor.getBlob(branceImageIx)
                val bitmap=BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
                val brance= Brance(id,name,bitmap)

                arrayList.add(brance)
            }

            adapter.notifyDataSetChanged()

            cursor.close()

        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }


    }
}