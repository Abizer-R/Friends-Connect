package com.abizer_r.friendsconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abizer_r.friendsconnect.MainActivity.Destinations.SIGN_UP_ROUTE
import com.abizer_r.friendsconnect.MainActivity.Destinations.TIMELINE_ROUTE
import com.abizer_r.friendsconnect.signup.SignUp
import com.abizer_r.friendsconnect.timeline.Timeline
import com.abizer_r.friendsconnect.ui.theme.FriendsConnectTheme

class MainActivity : ComponentActivity() {

    object Destinations {
        const val SIGN_UP_ROUTE = "signUp_route"
        const val TIMELINE_ROUTE = "timeline_route"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FriendsConnectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    val onSignedUpLambda = remember<() -> Unit> {{
                        navController.navigate(TIMELINE_ROUTE)
                    }}
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = SIGN_UP_ROUTE
                    ) {
                        composable(SIGN_UP_ROUTE) {
                            SignUp(
                                onSignedUp = onSignedUpLambda
                            )
                        }
                        composable(TIMELINE_ROUTE) {
                            Timeline()
                        }
                    }

                }
            }
        }
    }
}