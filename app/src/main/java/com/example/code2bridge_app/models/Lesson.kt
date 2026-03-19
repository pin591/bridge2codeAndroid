package com.example.code2bridge_app.models


//noinspection SuspiciousImport
import android.R

class Lesson (
    val title : String,
    val imageRes : Int,
    val progress : Float, // 0 to 100
)
{
    companion object
    {
        fun initializeDebugObjects() : List<Lesson>
        {
            return listOf(
                Lesson(
                    title = "Intro to Videography",
                    imageRes = R.drawable.star_on,
                    progress = 100f
                ),
                Lesson(
                    title = "Learning Basic Tools",
                    imageRes = R.drawable.star_on,
                    progress = 85f
                ),
                Lesson(
                    title = "Working with Timelines",
                    imageRes = R.drawable.star_on,
                    progress = 60f
                ),
                Lesson(
                    title = "Color Correction Essentials",
                    imageRes = R.drawable.star_on,
                    progress = 45f
                ),
                Lesson(
                    title = "Audio Editing and Mixing",
                    imageRes = R.drawable.star_on,
                    progress = 30f
                ),
                Lesson(
                    title = "Transitions and Effects",
                    imageRes = R.drawable.star_on,
                    progress = 20f
                ),
                Lesson(
                    title = "Export Settings and Codecs",
                    imageRes = R.drawable.star_on,
                    progress = 10f
                ),
                Lesson(
                    title = "Final Project: Edit a Short Film",
                    imageRes = R.drawable.star_on,
                    progress = 0f
                )
            )
        }
    }
}