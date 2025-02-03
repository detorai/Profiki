package com.example.test4.presentation.cat_pop_favour

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.test4.domain.category.Category
import com.example.test4.presentation.common.CommonShoesCard
import com.example.test4.presentation.common.CommonTopBar
import com.example.test4.presentation.ui.theme.Accent
import com.example.test4.presentation.ui.theme.Block
import com.example.test4.presentation.ui.theme.TextColor

data class ListScreen(
    var screen: ScreenType,
    val categoryScreen: Category? = null,
    private val context: Context
): Screen {
    @Composable
    override fun Content() {
    val viewModel = rememberScreenModel { CatPopViewModel(screen, categoryScreen, context) }
        LaunchedEffect(screen) {
            viewModel.updateScreen(screen)
        }
        key(screen){
           ProductList(viewModel)
        }
    }
    @Composable
    fun ProductList(viewModel: CatPopViewModel){
        val state = viewModel.screenState.collectAsState().value
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
            topBar = {
                CommonTopBar(
                    onBack = {navigator.pop()},
                    label = state.label,
                    modifier = Modifier.padding(top = 48.dp),
                    onFavourite = {
                        navigator.push(
                            ListScreen(screen = ScreenType.FAVOURITE, context = context)
                        )
                    },
                    screenType = screen
                )
            },
            content = { padding ->
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.padding(padding)
                ) {
                    if (screen == ScreenType.CATEGORY) {
                        LazyRow(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()

                        ) {
                            itemsIndexed(state.category) { index, category ->
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(108.dp, 40.dp)
                                            .background(
                                                color = if (state.selectedCategory == category) Accent else Block,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .clickable {
                                                viewModel.selectedCategory(category)
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
                                    if (index < state.category.size - 1) {
                                        Spacer(Modifier.width(16.dp))
                                    }
                                }
                            }
                        }
                    }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxSize()
                            .height(182.dp),
                        contentPadding = PaddingValues(vertical = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        itemsIndexed(viewModel.shoesList) { index, shoes ->
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                CommonShoesCard(
                                    shoes = shoes,
                                    onAdd = {
                                        viewModel.inBucket(index, !shoes.inBucket)
                                    },
                                    onFavourite = {
                                        viewModel.inFavourite(index, !shoes.isFavourite )
                                    },
                                )
                                if (index < viewModel.shoesList.size - 1) {
                                    Spacer(Modifier.width(15.dp))
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}
enum class ScreenType {
    POPULAR,
    CATEGORY,
    FAVOURITE
}