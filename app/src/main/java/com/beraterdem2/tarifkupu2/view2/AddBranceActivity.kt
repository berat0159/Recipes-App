package com.beraterdem2.tarifkupu2.view2

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import android.Manifest
import android.app.DatePickerDialog
import android.database.sqlite.SQLiteDatabase
import com.beraterdem2.tarifkupu2.databinding.ActivityAddBranceBinding


import java.util.Calendar

@Suppress("DEPRECATION")
class AddBranceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBranceBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var activityPerissionLauncher: ActivityResultLauncher<String>
    private var imageBitmap: Bitmap?=null
    private lateinit var calendar: Calendar
    private lateinit var datePickerDialog: DatePickerDialog
    var imageDecoder:ImageDecoder.Source?=null
    private lateinit var dataBase: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddBranceBinding.inflate(layoutInflater)
        setContentView(binding.root)



        registerLauncher()


        val actionBar=supportActionBar
        actionBar?.title="Anı Ekle"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        binding.addDataText.setOnClickListener {

            calendar=Calendar.getInstance()

            val year=calendar.get(Calendar.YEAR)
            val month=calendar.get(Calendar.MONTH)
            val day=calendar.get(Calendar.DAY_OF_MONTH)

            datePickerDialog=DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                binding.addDataText.setText("$i3/$i2/$i")

            },year,month,day)
            datePickerDialog.show()


        }





    }





    fun selectImage(view: View){

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

                Snackbar.make(view,"Foto Yüklemek İçin İzin Gerekli",Snackbar.LENGTH_INDEFINITE).setAction("İzin Ver",
                    View.OnClickListener {
                        //request permission
                        activityPerissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

                    }).show()

            }
            else{
                //request permission
                activityPerissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)

            }

        }
        else{

            //go to gallery
            val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intent)

        }



    }

    fun makeSmallerImage(image:Bitmap,maximumSize:Int):Bitmap{
        var height=image.height
        var widht=image.height

        var bitmapRadio=widht/height

        if (bitmapRadio>1){
            widht=maximumSize
            height=widht/bitmapRadio
        }else{
            height=maximumSize
            widht=height*bitmapRadio
        }

        return Bitmap.createScaledBitmap(image,widht,height,true)


    }

    fun registerLauncher(){

        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->

                if (result.resultCode== RESULT_OK){

                    val intent:Intent?=result.data

                    if (intent!=null){
                        val uriData=intent.data

                        try {
                            if (Build.VERSION.SDK_INT>=28){
                                imageDecoder=ImageDecoder.createSource(contentResolver,
                                    uriData!!
                                )
                                imageBitmap=ImageDecoder.decodeBitmap(imageDecoder!!)
                                binding.addImageView.setImageBitmap(imageBitmap)
                            }else{

                                imageBitmap=MediaStore.Images.Media.getBitmap(contentResolver,uriData)
                                binding.addImageView.setImageBitmap(imageBitmap)


                            }



                        }
                        catch (e:java.lang.Exception){
                                e.printStackTrace()
                        }


                    }

                }

            })


        activityPerissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission(),
            ActivityResultCallback { result ->

                if (result){

                    val intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intent)
                }else{
                    Toast.makeText(this,"İzin Gerekiyor",Toast.LENGTH_LONG).show()
                }

            })

    }


    fun save(view:View){



        if (imageBitmap!=null){

            val mealName=binding.yournameText.text.toString()
            val cookerName=binding.cookernameText.text.toString()
            val dateTxt=binding.addDataText.text.toString()

            var smallImage=makeSmallerImage(imageBitmap!!,300)
            val outputStream=ByteArrayOutputStream()
            smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArray=outputStream.toByteArray()

            try {

                dataBase=this.openOrCreateDatabase("Meals", MODE_PRIVATE,null)
                dataBase.execSQL("CREATE TABLE IF NOT EXISTS meals(id INTEGER PRIMARY KEY,mealName VARCHAR,cookerName VARCHAR,dateTxt VARCHAR,image BLOB)")
                val stringSql="INSERT INTO meals(mealName,cookerName,dateTxt,image) VALUES(?,?,?,?)"
                val statment=dataBase.compileStatement(stringSql)
                statment.bindString(1,mealName)
                statment.bindString(2,cookerName)
                statment.bindString(3,dateTxt)
                statment.bindBlob(4,byteArray)
                statment.execute()




            }catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }

        val intent=Intent(this, BranceActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)



    }
}