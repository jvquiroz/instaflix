package com.instaleap.instaflix.ui.navigation


import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.instaleap.instaflix.ui.components.NavigationDrawer
import com.instaleap.instaflix.ui.components.SecondLevelNavigation
import com.instaleap.instaflix.ui.components.navigationItems
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(modifier: Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val items = navigationItems()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                NavigationDrawer(
                    items = items,
                    navController = navController,
                    onDrawerItemClicked = {
                        scope.launch { drawerState.close() }
                    }
                )
            }
        ) {
            SecondLevelNavigation(
                navController = navController,
                items = items,
                onMenuClick = {
                    scope.launch {
                        drawerState.apply { if (isClosed) open() else close() }
                    }
                }
            )
        }
    }
}
