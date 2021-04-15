package com.aakash.fithub.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.*
import com.aakash.fithub.R
import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.entity.User
import com.aakash.fithub.repository.UserRepository
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ProfileEditActivity : AppCompatActivity() {
    private val REQUEST_GALLERY_CODE=0;
    private val REQUEST_CAMERA_CODE=1;
    private lateinit var imgAdd: ImageView;
    private lateinit var etFirstname: EditText;
    private lateinit var etLastname: EditText;
    private lateinit var etemail: EditText;
    var imageUrl:String?=null
    private lateinit var btnDone: Button;
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        etFirstname=findViewById(R.id.etFirstname)
        etLastname=findViewById(R.id.etLastname)
        etemail=findViewById(R.id.etemail)
        btnDone=findViewById(R.id.btnDone)
        imgAdd=findViewById(R.id.imgAdd)
        CoroutineScope(Dispatchers.IO).launch {
            val repository= UserRepository()
            val response=repository.getUser(ServiceBuilder.id!!)
            if(response.success==true){
                val data=response.data
                val listdata= data?.get(0)
                withContext(Dispatchers.Main){
                    val imageUrl=listdata!!.image
                    val imagePath = ServiceBuilder.loadImagepath() +imageUrl
                    if (!imageUrl.equals("noimg")) {
                        Glide.with(applicationContext)
                                .load(imagePath)
                                .into(imgAdd)
                    }
                    etFirstname.setText("${listdata.fname}")
                    etLastname.setText("${listdata.lname}")
                    etemail.setText("${listdata.email}")
                }
            }
        }
        imgAdd.setOnClickListener(){
            popup()
        }
        btnDone.setOnClickListener(){
            val user = User(_id= ServiceBuilder.id!!,fname = etFirstname.text.toString(), lname = etLastname.text.toString(),email = etemail.text.toString())
            CoroutineScope(Dispatchers.IO).launch {
                val repository = UserRepository()
                val response = repository.updateUser(user)
                val suc=response
                if (response.success == true) {
                    val id= ServiceBuilder.id!!
                    if(imageUrl != null){
                        uploadImage(id!!)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@ProfileEditActivity, "Student Added Successfully", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProfileEditActivity, "error occours", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun popup(){
        val popupMenu= PopupMenu(this,imgAdd)
        popupMenu.menuInflater.inflate(R.menu.gallery_camera_menu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuGallery -> {
                    openGallery()
                    true
                }
                R.id.menuCamera -> {
                    openCamera()
                    true
                }
                else -> false
            }
        })
        popupMenu.show()
    }
    private fun openGallery(){
        val intent= Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,REQUEST_GALLERY_CODE)
    }
    private fun openCamera(){
        val cameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent,REQUEST_CAMERA_CODE)
    }
    private fun bitmapToFile(
            bitmap: Bitmap,
            fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                            .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
    private fun uploadImage(studentId: String) {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                    RequestBody.create(MediaType.parse("image/${file.extension}"), file)
            val body =
                    MultipartBody.Part.createFormData("file", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                val userRepository = UserRepository()
                val response = userRepository.uploadImage(studentId, body)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProfileEditActivity, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProfileEditActivity, "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            if(requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                        contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                imgAdd.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            }
            else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                imgAdd.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }
}