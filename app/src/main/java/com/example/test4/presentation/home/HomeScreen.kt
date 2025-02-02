package com.example.test4.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.test3.presentation.common.CommonText
import com.example.test4.R
import com.example.test4.presentation.cat_pop_favour.ListScreen
import com.example.test4.presentation.cat_pop_favour.ScreenType
import com.example.test4.presentation.common.CommonMain
import com.example.test4.presentation.common.CommonSearchBar
import com.example.test4.presentation.common.CommonShoesCard
import com.example.test4.presentation.common.CommonShopButton
import com.example.test4.presentation.ui.theme.Accent
import com.example.test4.presentation.ui.theme.Background
import com.example.test4.presentation.ui.theme.Block
import com.example.test4.presentation.ui.theme.Red
import com.example.test4.presentation.ui.theme.TextColor

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { HomeViewModel() }
        Home(viewModel)

    }
    @Composable
    fun Home(viewModel: HomeViewModel) {
        val homeState = viewModel.homeState.collectAsState().value
        val navigator = LocalNavigator.currentOrThrow
        val navigationList = listOf(R.drawable.home_navigation, R.drawable.heart,R.drawable.bag_splash , R.drawable.notif, R.drawable.profile)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Background)

        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)

            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 48.dp)
                ) {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.hamburger),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                    CommonMain()
                    CommonShopButton(
                        state = false
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 21.dp)
                ) {
                    CommonSearchBar(
                        inputText = "",
                        onValue = {},
                        placeholder = "Поиск",
                        modifier = Modifier,
                        state = false
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 14.dp)
                            .size(52.dp)
                            .background(color = Accent, RoundedCornerShape(14.dp))
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.sliders),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                }
                CommonText(
                    text = "Категории",
                    modifier = Modifier.padding(top = 22.dp),
                    state = true,
                    onTextClick = {

                    }
                )
                LazyRow(
                    modifier = Modifier
                        .padding(top = 19.dp)
                        .fillMaxWidth()

                ) {
                    itemsIndexed(homeState.category) { index, category ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(108.dp, 40.dp)
                                    .background(
                                        color = Block,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        navigator.push(ListScreen(categoryScreen = category, screen = ScreenType.CATEGORY))
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    category.name,
                                    fontWeight = FontWeight.W400,
                                    fontSize = 12.sp,
                                    lineHeight = 12.28.sp,
                                    color = TextColor
                                )
                            }
                            if (index < homeState.category.size - 1) {
                                Spacer(Modifier.width(16.dp))
                            }
                        }
                    }
                }
                CommonText(
                    text = "Популярное",
                    state = true,
                    modifier = Modifier.padding(top = 24.dp),
                    onTextClick = {
                        navigator.push(ListScreen(screen = ScreenType.POPULAR))
                    }
                )
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(182.dp)
                ) {
                    itemsIndexed(viewModel.shoesList) { index, shoes ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            CommonShoesCard(
                                products = shoes,
                                onAdd = {
                                },
                                onFavourite = {
                                    viewModel.inFavourite(index, !shoes.isFavourite)
                                },
                            )
                            if (index < viewModel.shoesList.size - 1) {
                                Spacer(Modifier.width(15.dp))
                            }
                        }
                    }
                }
                CommonText(
                    text = "Акции",
                    state = true,
                    modifier = Modifier.padding(top = 29.dp),
                    onTextClick = {

                    }
                )
                LazyRow(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    itemsIndexed(homeState.sales) { index, sale ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(95.dp)
                                .background(Red)
                        ) {
                            AsyncImage(
                                model = sale.sales_url,
                                contentDescription = "",
                                contentScale = ContentScale.FillWidth
                            )
                        }
                        if (index < homeState.sales.size-1) {
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
            }
            NavigationBar(
                containerColor = Color.Transparent,

                modifier = Modifier
                    .fillMaxWidth()
                    .paint(
                        painter = painterResource(R.drawable.navigation_bar),
                        contentScale = ContentScale.FillWidth
                    ),
                windowInsets = WindowInsets(top = 40.dp)
            ){
                navigationList.forEachIndexed{index, i ->
                    if(index == 2){
                        NavigationBarItem(
                            selected = false,
                            onClick = {},
                            icon = {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.size(56.dp).background(Accent, RoundedCornerShape(60.dp)
                                    )
                                ) {
                                    Icon(
                                        painter = painterResource(i),
                                        contentDescription = "",
                                        tint = Block,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                            modifier =  Modifier.offset(y = (-45).dp).wrapContentSize()
                        )
                        return@forEachIndexed
                    }
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                painter = painterResource(i),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp)

                            )
                        }
                    )
                }
            }
        }
    }
}