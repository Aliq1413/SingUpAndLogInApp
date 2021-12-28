package com.example.singupandloginapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception
class SignUp : AppCompatActivity() {
    lateinit var edtName1 : EditText
    lateinit var edtMobnum : EditText
    lateinit var edtLoc : EditText
    lateinit var edPassword : EditText
    lateinit var submtBtn : Button

    lateinit var DB : DbHlpr
    var username = ""
    var umobile = ""
    var ulocation = ""
    var upasswrd = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        title = ""

        DB = DbHlpr(applicationContext)
        // init UI
        initsignUp()

        submtBtn.setOnClickListener {
            submitdata()
        }
    }

    // init Function
    fun initsignUp(){
        edtName1=findViewById(R.id.edtName1)
        edtMobnum=findViewById(R.id.edtMob1)
        edtLoc=findViewById(R.id.edtLoc1)
        edPassword=findViewById(R.id.edPass)
        submtBtn=findViewById(R.id.btnSub)
    }

    // submited Function
    fun submitdata(){

        try {
            username=edtName1.text.toString()
            umobile=edtMobnum.text.toString()
            ulocation=edtLoc.text.toString()
            upasswrd=edPassword.text.toString()
            // save data in DB
            var svdata =DB.save(username,umobile,ulocation,upasswrd)

            if (username.isNotEmpty() &&
                umobile.isNotEmpty() &&
                ulocation.isNotEmpty() &&
                upasswrd.isNotEmpty()){

                Toast.makeText(applicationContext, "data submitted successfully! "+ svdata, Toast.LENGTH_LONG).show()
            }

            // after save data in DB go to details activity
            var intent = Intent(this,Details::class.java)
            intent.putExtra("User",username)
            startActivity(intent)

        }catch (e:Exception){
            Toast.makeText(applicationContext, "data not submitted!", Toast.LENGTH_LONG).show()
        }
    }
}