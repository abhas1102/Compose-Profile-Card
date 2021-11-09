package com.example.profilecardlayout

data class UserProfile(val name:String, val status:Boolean, val drawableId:Int)

val userProfileList = arrayListOf<UserProfile>(
    UserProfile("John Doe", true,R.drawable.neweasyday),
    UserProfile("Anna Johns", false,R.drawable.neweasyday),
    UserProfile("John Doe", true,R.drawable.neweasyday),
    UserProfile("Anna Johns", false,R.drawable.neweasyday),
    UserProfile("John Doe", true,R.drawable.neweasyday),
    UserProfile("Anna Johns", false,R.drawable.neweasyday),
    UserProfile("John Doe", true,R.drawable.neweasyday),
    UserProfile("Anna Johns", false,R.drawable.neweasyday),
    UserProfile("John Doe", true,R.drawable.neweasyday),
    UserProfile("Anna Johns", false,R.drawable.neweasyday),

)