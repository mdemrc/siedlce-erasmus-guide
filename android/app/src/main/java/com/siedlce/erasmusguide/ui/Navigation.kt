package com.siedlce.erasmusguide.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.siedlce.erasmusguide.ui.checklist.ChecklistScreen
import com.siedlce.erasmusguide.ui.map.MapScreen

@Composable
fun ErasmusGuideNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "map") {
        composable("map") {
            MapScreen(onNavigateToChecklist = { navController.navigate("checklist") })
        }
        composable("checklist") {
            ChecklistScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
