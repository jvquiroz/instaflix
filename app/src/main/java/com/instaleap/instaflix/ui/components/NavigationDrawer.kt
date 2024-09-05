package com.instaleap.instaflix.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.instaleap.instaflix.R
import com.instaleap.instaflix.ui.navigation.NavigationItem

@Composable
fun NavigationDrawer(
    items: List<NavigationItem>,
    navController: NavHostController,
    onDrawerItemClicked: () -> Unit = {}
) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val spacerHeight = 16.dp

    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(spacerHeight))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.app_name)
        )
        Spacer(modifier = Modifier.height(spacerHeight))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(spacerHeight))

        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                selected = index == selectedItemIndex,
                label = { Text(text = item.title) },
                icon = {
                    Icon(
                        contentDescription = item.title,
                        imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedItemIndex = index
                    onDrawerItemClicked()
                }
            )
        }
    }
}
