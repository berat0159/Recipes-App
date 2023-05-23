package com.beraterdem2.tarifkupu2.view

import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.beraterdem2.tarifkupu2.modul.Brance
import com.beraterdem2.tarifkupu2.view2.BranceActivity
import com.beraterdem2.tarifkupu2.databinding.ActivityBranceDetailsBinding

class BranceDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBranceDetailsBinding
    private lateinit var arrayList: ArrayList<Brance>
    private lateinit var dataBase:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBranceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayList=ArrayList()

        val actionBar=supportActionBar
        actionBar?.title="Anı Detayı"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        readData()

    }

    fun readData(){
        val intent=intent
        val branceId=intent.getIntExtra("id",1)
        dataBase=this.openOrCreateDatabase("Meals", MODE_PRIVATE,null)

        binding.save.visibility= View.INVISIBLE


        try {


            val cursor=dataBase.rawQuery("SELECT * FROM meals WHERE id=?", arrayOf(branceId.toString()))

            val mealNameIx=cursor.getColumnIndex("mealName")
            val cookerNameIx=cursor.getColumnIndex("cookerName")
            val dateTxtIx=cursor.getColumnIndex("dateTxt")
            val imageIx=cursor.getColumnIndex("image")

            while (cursor.moveToNext()){

                binding.yournameText.setText(cursor.getString(mealNameIx))
                binding.cookernameText.setText(cursor.getString(cookerNameIx))
                binding.addDataText.text = cursor.getString(dateTxtIx)


                val byteArray=cursor.getBlob(imageIx)
                val bitmap= BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)

                binding.addImageView.setImageBitmap(bitmap)

            }

            cursor.close()

        }catch (e:java.lang.Exception){
            print(e.message)
        }


    }

    fun deleteData(view: View) {

        val alertDialog=AlertDialog.Builder(this)
        alertDialog.setTitle("Dikkat!")
        alertDialog.setMessage("Silmek İstiyormusun ?")
        alertDialog.setPositiveButton("EVET",DialogInterface.OnClickListener { dialogInterface, i ->
            val getIntent=intent
            val brandeId=getIntent.getIntExtra("id",1)

             dataBase=this.openOrCreateDatabase("Meals", MODE_PRIVATE,null)
             dataBase.execSQL("DELETE FROM meals WHERE id=?", arrayOf(brandeId.toString()))

            val intent=Intent(this, BranceActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        })
            .setNegativeButton("HAYIR",DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this,"silinmedi",Toast.LENGTH_LONG).show()

            })
            alertDialog.show()



    }
}