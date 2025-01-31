package com.example.test4.presentation.onboard

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.test3.presentation.common.CommonButton
import com.example.test4.R
import com.example.test4.presentation.ui.theme.Block
import com.example.test4.presentation.ui.theme.Disable
import com.example.test4.presentation.ui.theme.Red
import com.example.test4.presentation.ui.theme.SubTextLight
import com.example.test4.presentation.ui.theme.TextColor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.log

class OnBoardScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { OnBoardingViewModel() }
        OnBoarding(viewModel)
    }
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun OnBoarding(viewModel: OnBoardingViewModel){
        val pagerState = rememberPagerState(pageCount = {3})

        var visible by remember { mutableStateOf(true)}

        val coroutineScope = rememberCoroutineScope()

        val pagerImage = listOf(0,R.drawable.image_2, R.drawable.image_3)
        val pagerLabel = listOf("","Начнем\nпутешествие", "У вас есть сила,\nчтобы")
        val pagerDesc = listOf("","Умная, великолепная и модная\nколлекция Изучите сейчас", "В вашей комнате много красивых и\nпривлекательных растений")

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.isScrollInProgress }.collect{page ->
                visible = !page
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF48B2E7),
                            Color(0xFF44A9DC),
                            Color(0xFF2B6B8B)
                        )
                    )
                )
        ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.75f)
        ){  page ->
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    initialAlpha = 0.1f
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 100)
                )
            ) {
                if (page == 0) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(top = 70.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "ДОБРО\nПОЖАЛОВАТЬ",
                                textAlign = TextAlign.Center,
                                color = Block
                            )
                        }
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.image_1),
                            contentDescription = "image",
                            modifier = Modifier.size(375.dp, 302.dp).padding(bottom = 26.dp),
                            contentScale = ContentScale.Crop
                        )
                    }


                } else {
                    Page(
                        image = pagerImage[page],
                        label = pagerLabel[page],
                        description = pagerDesc[page]
                    )
                }
            }
        }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(start = 20.dp, end = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val current = pagerState.currentPage == iteration
                        Box(
                            modifier = Modifier
                                .width(if (current) 48.dp else 23.dp)
                                .height(4.dp)
                                .background(
                                    color = (if (current) Block else Disable),
                                    shape = RoundedCornerShape(5.dp)
                                )
                                .padding(horizontal = 4.dp),

                            )
                        if (pagerState.pageCount > 0) {
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                }

                CommonButton(
                    modifier = Modifier.padding(bottom = 36.dp),
                    onClick = {
                        coroutineScope.launch {
                            println(pagerState.currentPage)
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    buttonColor = Block,
                    textColor = TextColor,
                    buttonText = if (pagerState.currentPage == 0) "Начать" else "Далее"
                )
            }
        }
    }
    @Composable
    fun Page(@DrawableRes image: Int, label: String, description: String){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
                .padding(start = 30.dp, end = 30.dp, top = 80.dp)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(image),
                contentDescription = "image",
                modifier = Modifier.size(375.dp, 302.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(top = 60.dp)
            ) {
                Text(
                    label,
                    textAlign = TextAlign.Center,
                    color = Block
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
            ) {
                Text(
                    description,
                    color = SubTextLight,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}