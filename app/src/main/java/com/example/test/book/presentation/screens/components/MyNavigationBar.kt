package com.example.test.book.presentation.screens.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.test.R


@Preview
@Composable
fun MyNavigationBar(
    navController: NavController
) {
    val selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val items = createBottomNavigationItems()
    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        painter = painterResource(
                            id =
                            if (selectedItemIndex == index)
                                item.selectedIcon
                            else
                                item.unselectedIcon
                        ),
                        contentDescription = item.title
                    )
                })
        }
    }
}

@Composable
fun createBottomNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            title = "Book",
            selectedIcon = R.drawable.ic_book_sel,
            unselectedIcon = R.drawable.ic_book
        ),
        BottomNavigationItem(
            title = "Misc",
            selectedIcon = R.drawable.ic_misc_sel,
            unselectedIcon = R.drawable.ic_misc
        ),
        BottomNavigationItem(
            title = "Play",
            selectedIcon = R.drawable.btn_play,
            unselectedIcon = R.drawable.btn_play
        ),
        BottomNavigationItem(
            title = "Link",
            selectedIcon = R.drawable.ic_link_sel,
            unselectedIcon = R.drawable.ic_link
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = R.drawable.ic_settings_sel,
            unselectedIcon = R.drawable.ic_settings
        ),
    )
}