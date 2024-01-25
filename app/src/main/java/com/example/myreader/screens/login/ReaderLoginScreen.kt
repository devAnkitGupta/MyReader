package com.example.myreader.screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myreader.components.EmailInput
import com.example.myreader.components.ReaderLogo

@Composable
fun ReaderLoginScreen(navController: NavController){

 Surface (modifier = Modifier.fillMaxSize()) {
  Column(horizontalAlignment = Alignment.CenterHorizontally,
   verticalArrangement = Arrangement.Top,
   ) {
   ReaderLogo()
   UserForm(
    loading = false,
    isCreateAccount = false,
    ){email,password ->
    Log.d("Form","ReaderLoginScreen: $email password" )
   }
  }
 }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun UserForm(
 loading: Boolean = false,
 isCreateAccount: Boolean = false,
 onDone: (String,String) -> Unit = {
  email, pwd ->
 }

){
 val email = rememberSaveable { mutableStateOf("") }
 val password = rememberSaveable{ mutableStateOf("") }
 val passwordVisibility = rememberSaveable{ mutableStateOf(false) }
 val passwordFocusRequest = FocusRequester.Default
 val keyboardController = LocalSoftwareKeyboardController.current
 val valid = remember(email.value, password.value){
  email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
 }

 val modifier = Modifier
  .height(250.dp)
  .background(MaterialTheme.colorScheme.background)
  .verticalScroll(rememberScrollState())

 Column (modifier, horizontalAlignment = Alignment.CenterHorizontally) {
  EmailInput(emailState = email,
   enabled = !loading,
   onAction = KeyboardActions {
    passwordFocusRequest.requestFocus()
   }
  )
  PasswordInput(
   modifier = Modifier.padding(8.dp).focusRequester(passwordFocusRequest),
   passwordState = password,
   labelId = "Password",
   enabled = !loading,
   passwordVisibility = passwordVisibility,
   onAction = KeyboardActions{
    if (!valid) return@KeyboardActions
    onDone(email.value.trim(),password.value.trim())
   }
  )
 }
}

@Composable
fun PasswordInput(
 modifier: Any,
 passwordState: MutableState<String>,
 labelId: String, enabled: Boolean,
 passwordVisibility: MutableState<Boolean>,
 imeAction: ImeAction = ImeAction.Done,
 onAction: KeyboardActions = KeyboardActions.Default
) {

   val visualTransformation = if(passwordVisibility.value) VisualTransformation.None else
    PasswordVisualTransformation()
  OutlinedTextField(
   value = passwordState.value,
   onValueChange = {
    passwordState.value = it
   },
   label = { Text(text = labelId)},
   singleLine = true,
   textStyle = TextStyle(fontSize = 18.sp,
    color = MaterialTheme.colorScheme.onBackground
    ),
    modifier = Modifier
     .padding(
      bottom = 10.dp

     )
     .fillMaxWidth(),
   enabled = enabled,
   keyboardOptions = KeyboardOptions(
    keyboardType = KeyboardType.Password,
    imeAction = imeAction
   ),
   visualTransformation = visualTransformation,
   trailingIcon = {
    PasswordVisibility(passwordVisibility=passwordVisibility)
   }
  )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
  val visible = passwordVisibility.value
  IconButton(onClick = {passwordVisibility.value = !visible}){
   Icons.Default.AcUnit
  }
}
