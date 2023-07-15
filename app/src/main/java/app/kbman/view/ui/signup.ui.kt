package app.kbman.view.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kbman.R
import app.kbman.view.activity.AuthActivity
import app.kbman.view.ui.common.NotoSansFontFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseSignup(onClick: () -> Unit, composable: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onClick()
                        }
                    )
                }
            )
        }
    ) {
        composable()
    }
}

@Composable
fun SignupUI(activity: ComponentActivity) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "init") {
        composable("init") {
            BaseSignup(onClick = {
                val intent = Intent(activity, AuthActivity::class.java)
                activity.startActivity(intent)

                activity.finish()
            }) {
                SignupInitUI(navController)
            }
        }

        composable("email") {
            BaseSignup(onClick = {
                navController.navigate("init")
            }) {
                EmailInput(navController)
            }
        }

        composable("password") {
            BaseSignup(onClick = {
                navController.navigate("email")
            }) {
                PasswordInput(navController)
            }
        }

        composable("success") {
            // 중복 회원가입을 막기 위해 뒤로가기 버튼 제거
            BackHandler { }
            SuccessSignupPage(navController)
        }
    }
}

@Composable
fun SignupInitUI(navController: NavController) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val personalCheck = remember {
                mutableStateOf(false)
            }

            Text(
                text = "반가워요!",
                fontSize = 40.sp,
                fontFamily = NotoSansFontFamily,
                modifier = Modifier.padding(top = 100.dp),
            )

            Text(
                text = "회원가입을 위해\n간단한 약관에 동의해주세요.",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp),
                fontFamily = NotoSansFontFamily,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                CheckBoxRow("당신의 개인정보가 개발자에게 제공, 개발자에 의해 열람, 제 3자에게 이용될 수 있음에 동의합니다. (필수)", personalCheck.value) {
                    personalCheck.value = !personalCheck.value
                }
            }

            Spacer(modifier = Modifier.padding(top = 50.dp))

            Button(
                onClick = {
                    navController.navigate("email")
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(50.dp),
                enabled = personalCheck.value,
            ) {
                Text(
                    text = "다음",
                    fontSize = 16.sp,
                    fontFamily = NotoSansFontFamily,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 15.dp)
                        .requiredHeight(30.dp)
                )
            }
        }
    }
}

@Composable
fun CheckBoxRow(text: String, value: Boolean, onClick: (Any) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = value, onCheckedChange = onClick)
        ClickableText(
            text = AnnotatedString(text), onClick = onClick, modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = NotoSansFontFamily
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(navController: NavController) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val email = remember {
                mutableStateOf("")
            }

            Text(
                text = "기본정보 입력",
                fontSize = 40.sp,
                fontFamily = NotoSansFontFamily,
                modifier = Modifier.padding(top = 100.dp),
            )

            Text(
                text = "회원가입을 위해\n이메일을 입력해주세요.",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp),
                fontFamily = NotoSansFontFamily,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            TextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                label = {
                    Text(
                        text = "이메일",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.noto_sans)),
                        textAlign = TextAlign.Center,
                    )
                },
                modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            Button(
                onClick = {
                    navController.navigate("password")
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(50.dp),
                enabled = email.value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
            ) {
                Text(
                    text = "다음",
                    fontSize = 16.sp,
                    fontFamily = NotoSansFontFamily,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 15.dp)
                        .requiredHeight(30.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(navController: NavController) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val password = remember {
                mutableStateOf("")
            }
            val passwordVisibility = remember {
                mutableStateOf(false)
            }

            Text(
                text = "기본정보 입력",
                fontSize = 40.sp,
                fontFamily = NotoSansFontFamily,
                modifier = Modifier.padding(top = 100.dp),
            )

            Text(
                text = "회원가입을 위해\n비밀번호를 입력해주세요.",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp),
                fontFamily = NotoSansFontFamily,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            TextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                label = {
                    Text(
                        text = "비밀번호",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.noto_sans)),
                        textAlign = TextAlign.Center,
                    )
                },
                modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(
                            imageVector = if (passwordVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "password visibility",
                            tint = Color.Black
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            Button(
                onClick = {
                    navController.navigate("success")
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(50.dp),
                enabled = password.value.isNotEmpty()
            ) {
                Text(
                    text = "다음",
                    fontSize = 16.sp,
                    fontFamily = NotoSansFontFamily,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 15.dp)
                        .requiredHeight(30.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessSignupPage(navController: NavController) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "check",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 100.dp),
            )

            Text(
                text = "회원가입을 축하드립니다.",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp),
                fontFamily = NotoSansFontFamily,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            Button(
                onClick = {

                },
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(50.dp),
                enabled = true
            ) {
                Text(
                    text = "다음",
                    fontSize = 16.sp,
                    fontFamily = NotoSansFontFamily,
                    color = Color.Black,
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 15.dp)
                        .requiredHeight(30.dp)
                )
            }
        }
    }
}