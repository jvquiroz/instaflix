package com.instaleap.instaflix.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.instaleap.instaflix.R
import com.instaleap.instaflix.ui.navigation.NavigationItem
import com.instaleap.instaflix.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    title: String,
    navigationItems: List<NavigationItem>,
    onMenuClick: () -> Unit = {}
) {
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = stringResource(id = R.string.menu))
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navigationItems.forEach { item ->
                    NavigationBarItem(
                        selected = navBackStackEntry?.destination?.route == item.route,
                        onClick = {
                            rootNavController.navigate(item.route) {
                                popUpTo(rootNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(text = item.title) },
                        icon = {
                            Icon(
                                contentDescription = item.title,
                                imageVector = if (navBackStackEntry?.destination?.route == item.route)
                                    item.selectedIcon else item.unselectedIcon
                            )
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = rootNavController,
            startDestination = navigationItems.getOrNull(0)?.route ?: Screen.Default.route,
            modifier = modifier.padding(contentPadding),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(700)
                )
            }
        ) {
            navigationItems.forEach { navigationItem ->
                composable(route = navigationItem.route) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = navigationItem.title)
                    }
                }
            }

            composable(Screen.Default.route) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(id = R.string.something_went_wrong))
                }
            }
        }
    }
}