package com.binkes.countryflagemoji.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.binkes.countryflagemoji.ui.MainScreen
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex


fun NavController.popBackStackSafely() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavGraph(
    navController: NavHostController,
) {


    val navMutex = remember { Mutex() }

    val scope = rememberCoroutineScope()


    NavHost(
        navController = navController,
        startDestination = NavScreens.Main.route
    ) {


        composable(route = NavScreens.Main.route) {

            MainScreen(
                onGoToNativeTextEmojiScreen = {
                    scope.launch {
                        /*
                        navMutex.withLock {
                            try {
                                navController.navigate(NavScreens.NativeTextEmoji.route)
                            } catch (_: Exception) {
                                navMutex.unlock()
                            }
                        }

                         */
                    }
                },
                onGoToSharedTextEmojiScreen = {
                    scope.launch {
                        /*
                        navMutex.withLock {
                            try {
                                navController.navigate(NavScreens.SharedTextEmoji.route)
                            } catch (_: Exception) {
                                navMutex.unlock()
                            }
                        }

                         */
                    }
                },
            )

        }
        



        /*
        composable(route = NavScreens.NativeTextEmoji.route) {

            NativeTextEmojiScreen()

        }


        composable(route = NavScreens.SharedTextEmoji.route) {

            SharedTextEmojiScreen()

        }

         */



    }


}


