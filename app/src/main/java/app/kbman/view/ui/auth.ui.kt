package app.kbman.view.ui

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.kbman.R
import app.kbman.view.activity.SignupActivity
import app.kbman.view.ui.common.NoticeAlertDialog
import app.kbman.view.ui.common.NotoSansFontFamily

@Composable
fun AccountBox(activity: ComponentActivity) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val loginAlertState = remember { mutableStateOf(false) }
            val loginAlertText = remember { mutableStateOf("") }

            if (loginAlertState.value) {
                NoticeAlertDialog(loginAlertText.value) {
                    loginAlertState.value = false
                }
            }

            Text(
                text = "로그인",
                fontSize = 32.sp,
                fontFamily = NotoSansFontFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 80.dp)
            )

            EmailPasswordBox()

            Spacer(modifier = Modifier.padding(16.dp))

            LoginRegisterButton(
                onLoginClick = {
                    loginAlertText.value = "로그인에 실패했습니다."
                    loginAlertState.value = true
                },
                onRegisterClick = {
//                    loginAlertText.value = "회원가입에 실패했습니다."
//                    loginAlertState.value = true
                    val intent = Intent(activity, SignupActivity::class.java)
                    activity.startActivity(intent)

                    activity.finish()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailPasswordBox() {
    TextField(
        value = "",
        onValueChange = {},
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        label = {
            Text(
                text = "아이디",
                fontSize = 16.sp,
                fontFamily = NotoSansFontFamily,
                textAlign = TextAlign.Center,
            )
        },
        modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    )

    Spacer(modifier = Modifier.padding(16.dp))

    TextField(
        value = "",
        onValueChange = {},
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
                fontFamily = NotoSansFontFamily,
                textAlign = TextAlign.Center,
            )
        },
        modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
    )
}

@Composable
fun LoginRegisterButton(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Row {
        Button(
            onClick = {
                onLoginClick()
            },
        ) {
            Text(
                text = "로그인",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.noto_sans)),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = {
                onRegisterClick()
            },
        ) {
            Text(
                text = "회원가입",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.noto_sans)),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}