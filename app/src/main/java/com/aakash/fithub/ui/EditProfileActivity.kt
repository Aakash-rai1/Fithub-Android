package com.aakash.fithub.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import com.aakash.fithub.R
import com.aakash.fithub.entity.User
import com.aakash.fithub.repository.UserRepository
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

class EditProfileActivity : AppCompatActivity() {

    private lateinit var etFirstname: EditText
    private lateinit var etAge: EditText
    private lateinit var etLastname: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var radioOther: RadioButton
    private lateinit var btnDone: Button
    private lateinit var imgAdd: ImageView

    var usergender = ""
    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        etFirstname= findViewById(R.id.etFirstname)
        etAge= findViewById(R.id.etAge)
        etLastname= findViewById(R.id.etLastname)
        radioGroup= findViewById(R.id.radioGroup)
        radioMale= findViewById(R.id.radioMale)
        radioFemale= findViewById(R.id.radioFemale)
        radioOther= findViewById(R.id.radioOther)
        btnDone= findViewById(R.id.btnDone)
        imgAdd= findViewById(R.id.imgAdd)

        selectGender()
        btnDone.setOnClickListener {

            editprofilebtn()
        }

        imgAdd.setOnClickListener {
            loadPopUpMenu()
        }

    }

    fun editprofilebtn() {

        val firstname = etFirstname.text.toString()
        val lastname = etLastname.text.toString()
        val age = etAge.text.toString().toInt()
        val image = imgAdd.toString()



        val gender = usergender

        val userData =
            User(fname = firstname, age = age, lname = lastname, gender = gender)

        CoroutineScope(Dispatchers.IO).launch {

            val repository = UserRepository()
            val response = repository.updateUser(userData)
            if (response.success == true) {
                withContext(Dispatchers.Main) {
                    if(imageUrl != null){
                        uploadImage(response.data!!._id.toString())
                    }
                    Toast.makeText(
                        this@EditProfileActivity,
                        "User updated Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@EditProfileActivity,
                        "Error  While updating user",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            clear()
        }

    }

    fun clear() {
        etFirstname.setText("")
        etAge.setText("")
        etLastname.setText("")
        radioMale.isChecked = false
        radioFemale.isChecked = false
        radioOther.isChecked = false
    }

    private fun selectGender() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioMale -> {
                    usergender = radioMale.text.toString()
                }
                R.id.radioFemale -> {
                    usergender = radioFemale.text.toString()
                }
                R.id.radioOther -> {
                    usergender = radioOther.text.toString()
                }
            }
        }
    }

    // Load pop up menu
    private fun loadPopUpMenu() {
        val popupMenu = PopupMenu(this, imgAdd)
        popupMenu.menuInflater.inflate(R.menu.gallery_camera_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera ->
                    openCamera()
                //   Toast.makeText(this, "Camera Accessed", Toast.LENGTH_SHORT).show()
                R.id.menuGallery ->
                    openGallery()
                //  Toast.makeText(this, "Gallery Accessed", Toast.LENGTH_SHORT).show()
            }
            true
        }
        popupMenu.show()
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
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



    ///code for accessing the image by giving url to the image that is captured because it is not stored in any physical state and which can be extracted by using url and bitmap to file
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

    private fun uploadImage(userId: String) {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body =
                MultipartBody.Part.createFormData("file", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.uploadImage(userId, body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@EditProfileActivity, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Error uploading image ", ex.localizedMessage)
                        Toast.makeText(
                            this@EditProfileActivity,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}