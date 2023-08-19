package com.example.creditcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.creditcardapp.ui.theme.SpaceGrotesk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreditCardUI()
        }
    }
}

@Composable
fun CreditCardUI() {

    var isCompleted by remember {
        mutableStateOf(false)
    }
    var displayName by remember{ mutableStateOf("Jane Appleseed") }
    var displayCardNumber by remember{ mutableStateOf("0000000000000000") }
    var displayMonth by remember{ mutableStateOf("00") }
    var displayYear by remember{ mutableStateOf("00") }
    var displayCvv by remember{ mutableStateOf("000") }

    var name by remember{ mutableStateOf("") }
    var cardNumber by remember{ mutableStateOf("") }
    var month by remember{ mutableStateOf("") }
    var year by remember{ mutableStateOf("") }
    var cvv by remember{ mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DisplayCard(
            month = displayMonth,
            year = displayYear,
            cvv = displayCvv,
            name =displayName,
            cardNumber = displayCardNumber
        )
        if( isCompleted  ) {
            CompletedState(onButtonClicked = {
                name = ""
                cardNumber = ""
                month = ""
                year = ""
                cvv = ""
                isCompleted = false
            })
        }else{
            CreditCardInfo(
                name = name,
                cardNumber = cardNumber,
                month = month,
                year = year,
                cvv = cvv,
                onNameChanged = { name = it},
                onCardNumberChanged = { cardNumber = it},
                onMonthChanged = {month = it},
                onYearChanged = { year = it},
                onCvvChanged = { cvv = it},
                onButtonClicked = {
                    displayName = name
                    displayCardNumber = cardNumber
                    displayMonth = month
                    displayYear = year
                    displayCvv = cvv
                    isCompleted = true
                }
            )
        }
    }
}

@Composable
fun DisplayCard(
    modifier: Modifier = Modifier,
    month: String ,
    year: String ,
    cvv: String ,
    name: String ,
    cardNumber: String
) {
    Box(
        modifier = modifier
            .height(300.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.bg_main_mobile),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.bg_card_back),
            contentDescription = null,
            modifier = Modifier
                .scale(1.75f)
                .padding(top = 60.dp, start = 100.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.bg_card_front),
            contentDescription = null,
            modifier = Modifier
                .scale(1.75f)
                .padding(top = 125.dp, start = 60.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.card_logo),
            contentDescription = null,
            modifier = Modifier
                .scale(1.75f)
                .padding(top = 120.dp, start = 40.dp)
        )
        Text(
            text = name,
            color = Color.White,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 260.dp, start = 40.dp)
        )
        Text(
            text = cvv,
            color = Color.White,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 118.dp, start = 305.dp)
        )
        Text(
            text = "${month}/${year}",
            color = Color.White,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 260.dp, start = 255.dp)
        )
        Text(
            text = "${cardNumber.slice(0..3)}  ${cardNumber.slice(4..7)}  ${cardNumber.slice(8..11)}  ${cardNumber.slice(12..15)}",
            color = Color.White,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            letterSpacing = 1.5.sp,
            modifier = Modifier
                .padding(top = 220.dp, start = 40.dp)
        )
    }
}

@Composable
fun CreditCardInfo(
    modifier: Modifier = Modifier,
    name: String,
    cardNumber: String,
    month: String,
    year: String,
    cvv: String,
    onNameChanged: (String) -> Unit,
    onCardNumberChanged: (String) -> Unit,
    onMonthChanged: (String) -> Unit,
    onYearChanged: (String) -> Unit,
    onCvvChanged: (String) -> Unit,
    onButtonClicked: () -> Unit
) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            Column() {
                Text(
                    text = "CARDHOLDER NAME",
                    fontFamily = SpaceGrotesk,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = name,
                    onValueChange = onNameChanged,
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text(
                            text = "e.g. Jane Appleseed",
                            fontFamily = SpaceGrotesk,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.White,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
            Column() {
                Text(
                    text = "CARD NUMBER",
                    fontFamily = SpaceGrotesk,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    value = cardNumber,
                    onValueChange = onCardNumberChanged,
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text(
                            text = "e.g. 1234 5678 9123 0000",
                            fontFamily = SpaceGrotesk,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.White,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text = "EXP. DATE",
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        modifier = Modifier
                            .width(75.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                        value = month,
                        onValueChange = onMonthChanged,
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                text = "MM",
                                fontFamily = SpaceGrotesk,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            backgroundColor = Color.White,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
                Column() {
                    Text(
                        text = "(MM/YY)",
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = year,
                        onValueChange = onYearChanged,
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                text = "YY",
                                fontFamily = SpaceGrotesk,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        },
                        modifier = Modifier
                            .width(75.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            backgroundColor = Color.White,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
                Column() {
                    Text(
                        text = "CVV",
                        fontFamily = SpaceGrotesk,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = cvv,
                        onValueChange = onCvvChanged,
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                text = "e.g.123",
                                fontFamily = SpaceGrotesk,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        },
                        modifier = Modifier
                            .width(150.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            backgroundColor = Color.White,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            }
            Button(
                onClick = onButtonClicked,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.button_color),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Confirm",
                    fontFamily = SpaceGrotesk,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    fontSize = 18.sp
                )
            }
        }
    }

@Composable
fun CompletedState(
    onButtonClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.icon_complete),
            contentDescription = "Complete Icon",
            modifier = Modifier
                .size(100.dp)
        )
        Text(
            text = "THANK YOU!",
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            letterSpacing = 2.sp,
            color = colorResource(id = R.color.button_color)
        )
        Text(
            text = "We've added your card details",
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = Color.Gray
        )
        Button(
            onClick = onButtonClicked ,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_color),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Continue",
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowContent() {
    CreditCardUI()
}