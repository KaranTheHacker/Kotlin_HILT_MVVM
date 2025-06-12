package com.example.myapplication.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.navigation.BottomNavGraph
import com.example.myapplication.ui.screens.UserListScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

//Compose
@AndroidEntryPoint
class MainActivity : ComponentActivity() { //an activity represent a single screen with a user interface

//    private var userViewModel: UserViewModel by viewModels()
//    private lateinit var userViewModel: UserViewModelSF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //lifecycle method
        setContent {
            MyApplicationTheme {
                Surface (modifier = Modifier.fillMaxSize()){
                    BottomNavGraph()
                }
            }
//            UserListScreen()
        }
    }
}
//
//@Composable
//fun UserListScreen(viewModel: UserViewModel){
//    val users by viewModel.allUsersLiveData.observeAsState(init)
//}



// view Binding
//@AndroidEntryPoint
//class MainActivity : AppCompatActivity() { //an activity represent a single screen with a user interface
//
//    private lateinit var userViewModel: UserViewModel
////    private lateinit var userViewModel: UserViewModelSF
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) { //lifecycle method
//        super.onCreate(savedInstanceState)
////        binding = ActivityMainBindin
//        enableEdgeToEdge()
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root) // Make sure you have this layout
//        val textView = binding.textView
//        val userListTextView = binding.userListTextView
//        val addUserButton = binding.addUserButton
//        val clearButton = binding.clearButton
//
//        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
//
//        //Observe LiveModel
//        userViewModel.allUsersLiveData.observe(this){
//            users ->
//            val userList = users.joinToString("\n"){
//                "${it.firstName} ${it.lastName}:- ${(it.email ?: "No Email" )}"
//            }
//            binding.userListTextView.text = buildString {
//                append("Users: \n ")
//                append(userList)
//            }
//        }
//
//        //Observer Stateflow
//        lifecycleScope.launch {
//            userViewModel.allUsersStateFlow.collect { users ->
//                val userList = users.joinToString("\n"){"${it.firstName} ${it.lastName} ${(it.email ?: "No Email" )}"}
//                userListTextView.text = buildString {
//                                                    append("Users: \n ")
//                                                    append(userList)
//                                                }
//            }
//        }

        //Observe LiveData
//        userViewModel.allUsersLiveData.observe(this) {
//                users ->
//                val userList = users.joinToString("\n"){"${it.firstName} ${it.lastName} ${(it.email ?: "No Email" )}"}
//                userListTextView.text ="Users: \n $userList"
//        }
//        addUserButton.setOnClickListener{
//            val firstName = "New" + Random.nextInt(100)
//            val lastName = "User" + Random.nextInt(100)
//            val email = "user${Random.nextInt(100)}@happy.com"
//            userViewModel.addUser(firstName,lastName,email)
//        }
//        clearButton.setOnClickListener{
//            userViewModel.clearUsers()
//        }
//    }
//
//
//}
//
//@composable
//fun MAinScreen(viewModel: UserViewModel){
//    val state
//    column{
//        Text(text = "")
//        Button(onclcik = {}){
//            text("Add user button")
//        }
//    }
//}

//Jetpack Compose is declarative UI toolkit
/* Declarative UI = "What to show" not "How to build"
->Remembers the UI state
->Observe changes in that state(via State, LiveData or Flow)
->Recompose the UI whenever the state changes

textView.text = "Welcome"
Text("Welcome")
Text(var)

compose- > built in kotlin functions
 */
//@Composable
//fun Greeting(name:String){
//    Text("Hello, $name") //TExtView -> linearLayout ->ConstraintLayout
//    Column {  }
//    Row {  }
//    Box()
//}

// composable and statically typed (Directly with it in code)
//@Preview
//@Composable
//fun GreetingPreview(){
//    Greeting("John")
//}

/*what happens hood?
Compose -> compiler plugin
 -> track which composable function were called
 -> observer
 -> recompose only part of the UI that changed
*
UI tree in memory - not XML views
Type Safe, compiler checked

*/