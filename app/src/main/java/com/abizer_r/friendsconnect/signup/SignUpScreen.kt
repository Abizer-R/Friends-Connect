package com.abizer_r.friendsconnect.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abizer_r.friendsconnect.R
import com.abizer_r.friendsconnect.signup.state.SignUpState
import com.abizer_r.friendsconnect.ui.theme.FriendsConnectTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    signUpViewModel: SignUpViewModel = koinViewModel(),
    onSignedUp: () -> Unit,
) {
    val signUpState by signUpViewModel.signUpState.observeAsState()
    SignUpView(
        modifier = modifier,
        signUpState = signUpState ?: SignUpState.Default,
        onSignedUp = onSignedUp,
        onCreateAccountClicked = { email, password, about ->
            signUpViewModel.createAccount(email, password, "empty about")
        }
    )
}

@Composable
fun SignUpView(
    modifier: Modifier = Modifier,
    signUpState: SignUpState,
    onSignedUp: () -> Unit,
    onCreateAccountClicked: (email: String, password: String, about: String) -> Unit,
) {

    if (signUpState is SignUpState.SignedUp) {
        onSignedUp()
    }

    Box(modifier = modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle(stringResource(R.string.create_an_account))
            Spacer(modifier = Modifier.height(16.dp))
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var about by remember { mutableStateOf("") }
            EmailField(
                value = email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                value = password,
                onValueChange = { password = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            AboutField(
                value = about,
                onValueChange = { about = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onCreateAccountClicked(email, password, about)
                }
            ) {
                Text(text = stringResource(R.string.signUp))
            }
        }

        if (signUpState is SignUpState.DuplicateAccount) {
            ErrorMessageBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                errorText = stringResource(R.string.duplicateAccountError)
            )
        }
    }
}

@Composable
fun ErrorMessageBox(
    modifier: Modifier = Modifier,
    errorText: String
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.errorContainer)
                .align(Alignment.Center),
            text = errorText,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        label = { Text(text = stringResource(R.string.email)) },
        onValueChange = onValueChange
    )
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    val visualTransformation = if (isVisible) {
        VisualTransformation.None
    } else PasswordVisualTransformation()
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        trailingIcon = {
            ToggleVisibilityIcon(isVisible) {
                isVisible = isVisible.not()
            }
        },
        visualTransformation = visualTransformation,
        label = { Text(text = stringResource(R.string.password)) },
        onValueChange = onValueChange
    )
}

@Composable
fun ToggleVisibilityIcon(
    isVisible: Boolean,
    onToggle: () -> Unit
) {
    val imageVector = ImageVector.vectorResource(
        if (isVisible) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off
    )
    IconButton(
        onClick = { onToggle() }
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = stringResource(R.string.toggleVisiblity)
        )
    }
}

@Composable
fun AboutField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    val visualTransformation = if (isVisible) {
        VisualTransformation.None
    } else PasswordVisualTransformation()
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        trailingIcon = {
            ToggleVisibilityIcon(isVisible) {
                isVisible = isVisible.not()
            }
        },
        visualTransformation = visualTransformation,
        label = { Text(text = stringResource(R.string.about)) },
        onValueChange = onValueChange
    )
}

@Composable
fun ScreenTitle(
    titleString: String
) {
    Text(
        text = titleString,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
@Preview(device = Devices.PIXEL_4, showBackground = true)
fun SignUpPreview() {
    FriendsConnectTheme {
        SignUpView(
            signUpState = SignUpState.Default,
            onSignedUp = {},
            onCreateAccountClicked = {_, _, _ -> }
        )
    }
}

@Composable
@Preview(device = Devices.PIXEL_4, showBackground = true)
fun DuplicateAccountInfoPreview() {
    FriendsConnectTheme {
        SignUpView(
            signUpState = SignUpState.DuplicateAccount,
            onSignedUp = {},
            onCreateAccountClicked = {_, _, _ -> }
        )
    }
}