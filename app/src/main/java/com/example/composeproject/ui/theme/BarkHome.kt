package com.example.composeproject.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeproject.PuppyListItem
import com.example.composeproject.data.DataProvider
import com.example.composeproject.data.Dog

@Composable
fun BarkHomeContent(navigateToProfile: (Dog) -> Unit){
val puppies= remember{DataProvider.puppyList}
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ){
        items(
            items=puppies,
            itemContent={
               PuppyListItem(puppy=it,navigateToProfile)
            }
        )
    }

}