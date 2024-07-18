package com.example.routetask.productScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.routetask.Model.Api.ProductsItem
import com.example.routetask.R
import coil.compose.AsyncImage
import com.example.routetask.ui.theme.DerkBlue
import com.example.routetask.ui.theme.FadedStrokeColor
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class ProductScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //val productViewModel: ProductViewModel by viewModels()

        super.onCreate(savedInstanceState)
        setContent {
            val productViewModel : ProductViewModel=  hiltViewModel()
            productViewModel.getProduct()
            ProductScreen(productViewModel)



        }
    }

}

@Composable
fun ProductScreen(productViewModel: ProductViewModel) {
    Column(modifier = Modifier.padding(10.dp)) {
        Image(
            painter = painterResource(id = R.drawable.route_image),
            contentDescription = stringResource(
                R.string.routeimage
            ),
            modifier = Modifier
                .padding(10.dp)

                .size(66.dp, 26.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {

            TextField(
                value = productViewModel.textInSearchBar.value,
                onValueChange = { textInSearchBar ->
                    productViewModel.textInSearchBar.value = textInSearchBar
                },
                placeholder = {
                    Text(
                        stringResource(R.string.what_do_you_search_for),
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .weight(0.9f)


                    .border(BorderStroke(2.dp, Color.Blue), shape = RoundedCornerShape(25.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    // to make background color transparent
                    focusedContainerColor = Color.White,

                    unfocusedContainerColor = Color.White,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search Icon"


                    )
                })
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = "Cart Icon",
                modifier = Modifier.padding(10.dp)
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            if (productViewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)
                )}
            else{
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(10.dp),
                    content = {
                        items(productViewModel.productList) { product ->
                            ProductCard(product,productViewModel)
                        }

                    })



            }

        }






    }
}

@Composable
fun ProductCard(product: ProductsItem?,productViewModel: ProductViewModel) {
    Card(
        modifier = Modifier
            .padding(5.dp)

            .padding(5.dp)
            .border(2.dp, FadedStrokeColor, RoundedCornerShape(8.dp))
        ,
        shape = RoundedCornerShape(15.dp)
    ) {
        Box {
            AsyncImage(
                model = product?.thumbnail,
                contentDescription = stringResource(R.string.product_picture),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )


            Box(
                modifier = Modifier
                    .padding(9.dp)
                    .size(36.dp)
                    .background(Color.White, shape = CircleShape)
                    .border(1.dp, Color.Gray, shape = CircleShape)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(24.dp),
                    tint = DerkBlue
                )
            }
        }
        Column(modifier = Modifier.padding(start = 7.dp)) {
            Text(
                text = product?.title ?: "Product Title",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = product?.description ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Text(text = "EGP",color = Color.Black)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = productViewModel.priceAfterDiscount(product?.discountPercentage,product?.price),
                    color = Color.Black)

                Text(modifier = Modifier.padding(start = 10.dp),
                    text = product?.price.toString(),
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.LineThrough,
                    color = DerkBlue

                )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Row {
                Text(text = "Review",color = Color.Black)
                Text(text = "( " +product?.rating   +" )", modifier = Modifier.padding(start= 4.dp),color = Color.Black)
                Image(painter = painterResource(id = R.drawable.rate_icon), contentDescription = stringResource(
                    R.string.rate_icon
                ), modifier = Modifier

                    .padding(start = 5.dp,top= 2.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(painter =painterResource(id = R.drawable.__icon_plus_circle_) , contentDescription = stringResource(
                    R.string.add_item_icon)
                    , modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(bottom = 8.dp, end = 3.dp)
                )
            }


        }




    }

}


