package com.example.singupandloginapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
class Details : AppCompatActivity() {

    lateinit var displayUN : TextView
    lateinit var displayinfo : TextView
    lateinit var signOut : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = ""


        displayUN=findViewById(R.id.displyN)
        displayinfo=findViewById(R.id.displyinfo)
        signOut=findViewById(R.id.btnSignout)

        var DB = DbHlpr(this)
        var usrnam = intent.getStringExtra("User")
        var info= usrnam?.let { a1 -> DB.rtrvinfo(a1) }
        displayUN.text="Welcome "+usrnam
        displayinfo.text="Your details are,\n \n " + info



        signOut.setOnClickListener {

            var signOut = Intent(this, MainActivity::class.java)
            startActivity(signOut)
        }
    }
}