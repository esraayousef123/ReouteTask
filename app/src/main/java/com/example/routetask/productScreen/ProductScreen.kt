package com.example.routetask.productScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.routetask.R

class ProductScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val productViewModel: ProductViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
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
                value = productViewModel.text.value,
                onValueChange = { textInSearchBar ->
                    productViewModel.text.value = textInSearchBar
                },
                placeholder = { Text(stringResource(R.string.what_do_you_search_for),color = Color.Gray) },
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
            Image(painter =painterResource(id = R.drawable.ic_cart) , contentDescription ="Cart Icon",
                modifier = Modifier.padding(10.dp) )


        }
        LazyColumn(content = )
    }}
