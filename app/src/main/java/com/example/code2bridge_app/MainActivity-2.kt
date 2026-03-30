package com.example.code2bridge_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.ProgressIndicatorDefaults.drawStopIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.code2bridge.R
import com.example.code2bridge_app.ui.theme.Code2bridgeappTheme
import com.example.code2bridge_app.models.Course

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Code2bridgeappTheme {
                CoursesScreen()
            }
        }
    }
}

@Composable
fun CoursesScreen() {
    val tabs = listOf("Todos", "iOS", "Android", "Otros")
    var selectedTab by remember { mutableStateOf(0) }

    val courses = Course.initializeDebugObjects()

    val coursesiOs = listOf(
        courses.get(0),
        courses.get(2),
        courses.get(4)
    )

    val coursesAndroid = listOf(
        courses.get(1),
        courses.get(3),
        courses.get(5)
    )

    val coursesAnother = listOf(
        courses.get(6),
        courses.get(7)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Cursos", modifier = Modifier.padding(top = 15.dp), fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ) {
            tabs.forEachIndexed { index, text ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedTab == 0)
        {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(courses) { course ->
                    CourseItem(course)
                }
            }
        }
        else if (selectedTab == 1)
        {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(coursesiOs) { course ->
                    CourseItem(course)
                }
            }
        }
        else if (selectedTab == 2)
        {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(coursesAndroid) { course ->
                    CourseItem(course)
                }
            }
        }
        else if (selectedTab == 3)
        {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(coursesAnother) { course ->
                    CourseItem(course)
                }
            }
        }
    }
}

// TODO: Tratar que la imagen se pegue a la izquierda, (se crezca arriba y abajo tambien)
@Composable
fun CourseItem(course: Course) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFBEB5))
            .clickable { }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column ()
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Text("${course.sesiones} Sesiones", fontSize = 13.sp, color = Color.Gray)
                Text("•", color = Color.Gray)
                Text(course.duration, fontSize = 13.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                course.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                course.description,
                fontSize = 12.sp
            )

            LinearProgressIndicator(
                progress = { course.progressBar / 100 },
                modifier = Modifier
                    .border(2.dp, ProgressIndicatorDefaults.linearTrackColor, shape = CircleShape)
                    .fillMaxWidth()
                    .height(12.dp),
                color = ProgressIndicatorDefaults.linearColor,
                trackColor = ProgressIndicatorDefaults.linearTrackColor,
                strokeCap = StrokeCap.Round,
                gapSize = -15.dp,
                drawStopIndicator = {
                    drawStopIndicator(
                        stopSize = 0.dp,
                        color = Color.Transparent,
                        strokeCap = StrokeCap.Round,
                        drawScope = this
                    )
                }
            )
        }
    }
}