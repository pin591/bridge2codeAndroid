package com.example.code2bridge_app.models

import com.example.code2bridge_app.R

public class Course (val imageRes: Int,
              val sesiones: Int,
              val duration: String,
              val title: String,
              val description: String,
              val progressBar: Float)
{
    companion object
    {
        fun initializeDebugObjects() : List<Course>
        {
            return listOf(
                Course(R.drawable.ic_launcher_background, 12, "1Hr 30Min", "iOS Development Basic", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 25f),
                Course(R.drawable.ic_launcher_background, 24, "2Hr 30Min", "Android Development Basic", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 100f),
                Course(R.drawable.ic_launcher_background, 20, "2Hr 15Min", "iOS Development Intermediate", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 0f),
                Course(R.drawable.ic_launcher_background, 250, "150Hr 00Min", "Android Development Intermediate", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 75f),
                Course(R.drawable.ic_launcher_background, 10, "0Hr 45Min", "iOS Development Advance", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 0f),
                Course(R.drawable.ic_launcher_background, 15, "1Hr 10Min", "Android Development Advance", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 0f),
                Course(R.drawable.ic_launcher_background, 8, "0Hr 30Min", "Cybersecurity Mobile", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 55f),
                Course(R.drawable.ic_launcher_background, 4, "0Hr 15Min", "Maintenance Mobile", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut", 30f)
            )
        }
    }
}