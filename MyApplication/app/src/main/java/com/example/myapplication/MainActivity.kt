package com.example.myapplication

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application()

class MyService{
    fun doSomething() = "Service doing something"
}

@AndroidEntryPoint // HILT will generate all the necessary code to perform DI into the class
class MainActivity : AppCompatActivity() { //an activity represent a single screen with a user interface
//    @Inject
//    lateinit var myService: MyService
    @Inject
    lateinit var singletonCounter: ScopedCounter

    override fun onCreate(savedInstanceState: Bundle?) { //lifecycle method
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        singletonCounter.increment()
        Log.d("MainActivity","Singleton counter in main activity:${singletonCounter.getCounter()} -$singletonCounter")
//        println(myService.doSomething())
    }
}

@AndroidEntryPoint
class SecondActivity: AppCompatActivity(){
    @Inject
    lateinit var singletonCounter: ScopedCounter

    override fun onCreate(savedInstanceState: Bundle?) { //lifecycle method
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        singletonCounter.increment()
        Log.d("MainActivity","Singleton counter in main activity:${singletonCounter.getCounter()} -$singletonCounter")
//        println(myService.doSomething())
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}
