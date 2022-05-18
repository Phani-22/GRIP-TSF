package com.phani.socialmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.phani.socialmedia.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mBinding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser

        mBinding.namePlaceHolder.text = currentUser?.displayName
        mBinding.emailPlaceHolder.text = currentUser?.email

        Glide.with(this).load(currentUser?.photoUrl).into(mBinding.profileImageView)

        mBinding.signOutBtn.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}
