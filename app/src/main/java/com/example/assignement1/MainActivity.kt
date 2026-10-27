package com.example.assignement1

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun PizzApp() {
    var showPrice by remember { mutableStateOf(0)}
    var custName by remember { mutableStateOf("") }

    val sizes = listOf("Small", "Medium", "Large")
    var selectedSize by remember { mutableStateOf("Medium") }

    var cheese by remember { mutableStateOf(false) }
    var mushrooms by remember { mutableStateOf(false) }
    var sausages by remember { mutableStateOf(false) }
    var olives by remember { mutableStateOf(false) }

    var delivery by remember { mutableStateOf(false) }

    var validation by remember { mutableStateOf(false) }

    fun payerCommande() {
        custName = ""
        selectedSize = "Medium"
        cheese = false
        mushrooms = false
        sausages = false
        olives = false
        delivery = false
    }

    Column(
        modifier = Modifier
            .size(width = 480.dp, height = 770.dp)
            .padding(25.dp),
    )
    {
        Text(
            text = "Pizza Order",
            fontSize = 30.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(15.dp))
        Canvas(modifier = Modifier.fillMaxWidth().height(3.dp)) {
            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                strokeWidth = 3f
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = custName,
            onValueChange = { newText -> custName = newText ; validation = false},
            singleLine = true,
            placeholder = {
                Text(
                    text = "Customer name",
                    color = Color.Gray,
                    fontSize = 20.sp
                )
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Choose pizza size",
            fontSize = 16.sp,
            color = Color.Black
        )
        Column {
            sizes.forEach { size ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (selectedSize == size),
                        onClick = { selectedSize = size; validation = false}
                    )
                    Text(text = size)
                }
            }
        }
        Canvas(modifier = Modifier.fillMaxWidth().height(3.dp)) {
            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                strokeWidth = 3f
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Extra toppings (+15,000 VDN each)",
            fontSize = 16.sp,
            color = Color.Black
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = cheese,
                onCheckedChange = { isChecked -> cheese = isChecked; validation = false }
            )
            Text(text = "Cheese")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = mushrooms,
                onCheckedChange = { isChecked -> mushrooms = isChecked; validation = false }
            )
            Text(text = "Mushrooms")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = sausages,
                onCheckedChange = { isChecked -> sausages = isChecked; validation = false }
            )
            Text(text = "Sausages")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = olives,
                onCheckedChange = { isChecked -> olives = isChecked; validation = false }
            )
            Text(text = "Olives")
        }
        Canvas(modifier = Modifier.fillMaxWidth().height(3.dp)) {
            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                strokeWidth = 3f
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(){
                Text(
                    text = "Home delivery",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "Delivery free : 20,000 VDN",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = delivery,
                onCheckedChange = { newState -> delivery = newState; validation = false },
            )
        }
        Canvas(modifier = Modifier.fillMaxWidth().height(3.dp)) {
            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                strokeWidth = 3f
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Total price",
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            showPrice = calculPrice (selectedSize, cheese, mushrooms, sausages, olives, delivery)
            Text(
                text = "${showPrice},000 VDN",
                fontSize = 24.sp,
                color = Color.Blue
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        val isFormValid = custName.isNotBlank()
        Button(
            onClick = { payerCommande(); validation = true },
            shape = RoundedCornerShape(12.dp),
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = "Place Order",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if(validation){
            Text(
                text = "Order well Placed",
                fontSize = 15.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun calculPrice (selectedSize : String, cheese : Boolean, mushrooms : Boolean, sausages : Boolean, olives : Boolean, delivery : Boolean) : Int {
    var price:Int = when {
        selectedSize == "Small" -> if(!delivery) 90 else 110
        selectedSize == "Medium" -> if(!delivery) 120 else 140
        selectedSize == "Large" -> if(!delivery) 150 else 170
        else -> 0
    }
    if(cheese){price += 15}
    if(mushrooms){price += 15}
    if(sausages){price += 15}
    if(olives){price += 15}
    return price
}