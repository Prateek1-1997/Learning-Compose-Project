package com.example.composeproject.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeproject.data.DataProvider
import com.example.composeproject.data.Dog

@Composable
fun ProfileScreen(puppy: Dog) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ProfileHeader(
                        scrollState,
                        puppy,
                        this@BoxWithConstraints.maxHeight
                    )
                    Divider()
                    ProfileContent(puppy, this@BoxWithConstraints.maxHeight)
                    AdoptButton()
                }
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    puppy: Dog,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = painterResource(id = puppy.puppyImaageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun ProfileContent(puppy: Dog, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))

        Name(puppy)


//        ProfileProperty(stringResource(R.string.personality), puppy.description)


       // Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(
    puppy: Dog
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            puppy = puppy,
            modifier = Modifier.baselineHeight(32.dp)
        )
    }
}

@Composable
private fun Name(puppy: Dog, modifier: Modifier = Modifier) {
    Text(
        text = puppy.title,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
            )
        Text(
            text = value,
            style = MaterialTheme.typography.caption,
        )
    }
}
@Composable
fun AdoptButton() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val context = LocalContext.current
        var selected by remember { mutableStateOf(false) }
        val color = if (selected) Color.LightGray else Color.Blue
        Button(
            onClick = {
                selected = !selected
                Toast.makeText(context, "Thank you for adopting me ! you are nice human being", Toast.LENGTH_LONG).show()


            },
            colors= ButtonDefaults.buttonColors(backgroundColor = color),
            modifier = Modifier.padding(all = Dp(10F))

        ) {

            if(!selected){
                Text(text = "Adopt Me", color = Color.White)
            }else{
                Text(text="Adopted", color = Color.White)
            }

        }
    }
}
@Preview
@Composable
fun ProfilePreview() {
    val puppy = DataProvider.puppyList[0]
    ProfileScreen(puppy = puppy)
}