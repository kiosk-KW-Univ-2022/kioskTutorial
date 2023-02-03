package com.example.kiosktutorial.Screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun residentForm(context: Context) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var username = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }

        Text(text = "Login Form", fontWeight = FontWeight.Bold, fontSize = 35.sp)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text(text = "Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (username.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Username", Toast.LENGTH_SHORT).show()
                } else if (password.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Validation Successfully Completed", Toast.LENGTH_SHORT)
                        .show()
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

    }

}

/*@Composable
fun RoundedCornerTextField() {
    var textState = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textState.value,
        onValueChange ={textState.value = it},
        shape = RoundedCornerShape(16.dp),
        placeholder = {
            Text(text = "주민번호 앞자리를 여기에 입력하세요")
        },
        singleLine = true)
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    KioskTutorialTheme {
        RoundedCornerTextField()
    }
}*/
