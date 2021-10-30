package com.mgg.composeui.ui.text

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mgg.composeui.R

class ComposeTextFragment : Fragment() {

    companion object {
        fun newInstance() = ComposeTextFragment()
    }

    // private lateinit var viewModel: ComposeTextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // return inflater.inflate(R.layout.compose_text_fragment, container, false)
        /*return ComposeView(requireContext()).apply {
            setContent {
                Text(text = "Hello world.")
            }
        }*/
        return inflater.inflate(R.layout.compose_text_fragment, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {
                LazyColumn(modifier = Modifier.padding(Dp(16.0F))) {
                    item {
                        Text(text = "forevermeng test")
                    }
                    item {
                        ContentView()
                    }
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // viewModel = ViewModelProvider(this).get(ComposeTextViewModel::class.java)
        // TODO: Use the ViewModel
    }


    @Preview(showBackground = true)
    @Composable
    fun ContentView() {
        Column {
            Text("A day in Shark Fin Cove")
            Text("Davenport, California")
            Text("December 2018")
            SimpleText()
            StringResourceText()
            BlueText()
            BigText()
            ItalicText()
            BoldText()
            CenterText()
            LongText()
            OverflowedText()
            SimpleClickableText()
            AnnotatedClickableText()
            SimpleFilledTextFieldSampleTest()
            SimpleFilledTextFieldSample()
            HelloContent()
            StyledTextField()
            PasswordTextField()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun RichContentView() {
        Column() {
            Image(
                painter = painterResource(R.mipmap.header),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Text("A day in Shark Fin Cove")
            Text("Davenport, California")
            Text("December 2018")
            Text(
                "A day wandering through the sandhills " +
                        "in Shark Fin Cove, and a few of the " +
                        "sights I saw",
                style = typography.h6,
                maxLines = 2,
                modifier = Modifier
                    .alpha(0.5F)
                    .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp)
                    .clickable(onClick = {
                        Log.e("onClick", "onClick")
                    }),
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style = typography.body2
            )
            Text(
                "December 2018",
                style = typography.body2
            )
        }
    }

    @Composable
    fun SharedPrefsToggle(
        text: String,
        value: Boolean,
        onValueChanged: (Boolean) -> Unit
    ) {
        Row {
            Text(text)
            Checkbox(checked = value, onCheckedChange = onValueChanged)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SimpleText() {
        Text(text = "SimpleText")
    }

    @Preview(showBackground = true)
    @Composable
    fun StringResourceText() {
        Text(text = stringResource(id = R.string.app_name))
    }

    @Preview(showBackground = true)
    @Composable
    fun BlueText() {
        Text(text = "Hello World !!!", color = Color.Blue)
    }

    @Preview(showBackground = true)
    @Composable
    fun BigText() {
        Text(text = "Hello World !!!", fontSize = 30.sp)
    }

    @Preview(showBackground = true)
    @Composable
    fun ItalicText() {
        Text(text = "Hello World !!!", fontStyle = FontStyle.Italic)
    }

    @Preview(showBackground = true)
    @Composable
    fun BoldText() {
        Text("Hello World", fontWeight = FontWeight.Bold)
    }

    @Preview(showBackground = true)
    @Composable
    fun CenterText() {
        Text(
            "Hello World", textAlign = TextAlign.Left,
            modifier = Modifier.width(150.dp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun LongText() {
        Text("hello ".repeat(50), maxLines = 2)
    }

    @Preview(showBackground = true)
    @Composable
    fun OverflowedText() {
        val viewModel: ComposeTextViewModel = viewModel()
        Text("Hello Compose ".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis)
    }

    @Preview(showBackground = true)
    @Composable
    fun SimpleClickableText() {
        ClickableText(
            text = AnnotatedString("Click Me"),
            onClick = { offset ->
                Log.d("ClickableText", "$offset -th character is clicked.")
            }
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun AnnotatedClickableText() {
        val annotatedText = buildAnnotatedString {
            append("Click ")

            // We attach this *URL* annotation to the following content
            // until `pop()` is called
            pushStringAnnotation(
                tag = "URL",
                annotation = "https://developer.android.com"
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("here")
            }

            pop()
        }

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                // We check if there is an *URL* annotation attached to the text
                // at the clicked position
                annotatedText.getStringAnnotations(
                    tag = "URL", start = offset,
                    end = offset
                )
                    .firstOrNull()?.let { annotation ->
                        // If yes, we log its value
                        Log.d("Clicked URL", annotation.item)
                    }
            }
        )
    }

    @Composable
    fun SimpleFilledTextFieldSampleTest() {
        var text by remember { mutableStateOf("Hello") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") }
        )
    }


    @Composable
    fun SimpleFilledTextFieldSample() {
        var text by remember { mutableStateOf("Hello") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") }
        )
    }

    @Composable
    fun HelloContent() {
        Column(modifier = Modifier.padding(16.dp)) {
            var name by remember { mutableStateOf("") }
            Text(
                text = "Hello",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
        }
    }

    @Composable
    fun StyledTextField() {
        var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }

        TextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Enter text") },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(20.dp)
        )
    }

    @Composable
    fun PasswordTextField() {
        var password by rememberSaveable { mutableStateOf("") }

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}