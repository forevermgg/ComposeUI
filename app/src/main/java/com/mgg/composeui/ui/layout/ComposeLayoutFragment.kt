package com.mgg.composeui.ui.layout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mgg.baselib.argument
import com.mgg.composeui.R
import com.mgg.composeui.ui.theme.Purple200
import com.mgg.composeui.ui.theme.Purple500


class ComposeLayoutFragment : Fragment() {
    private var param1: Int by argument()
    private var param2: String by argument()
    private lateinit var viewModel: ComposeLayoutModel

    companion object {
        fun newInstance() = ComposeLayoutFragment()
        fun newInstance(param1: Int, param2: String): ComposeLayoutFragment =
            ComposeLayoutFragment().apply {
                this.param1 = param1
                this.param2 = param2
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // return inflater.inflate(R.layout.compose_layout_fragment, container, false)
        return ComposeView(requireContext()).apply {
            setContent {
                Column(modifier = Modifier.padding(10.dp)) {
                    ArtistCard()
                    ArtistCard2()
                    FlowerCard()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(ComposeLayoutModel::class.java)
    }


    @Preview(showBackground = true)
    @Composable
    fun ArtistCard() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.mipmap.header),
                contentDescription = null,
                modifier = Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text("forever")
                Text("forevermeng")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ArtistCard2() {
        val padding = 16.dp
        Column(
            Modifier
                // .padding(padding)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.mipmap.header),
                    contentDescription = null,
                    modifier = Modifier
                        .width(72.dp)
                        .height(72.dp)
                        .clip(shape = RoundedCornerShape(36.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("forever")
                    Text("forevermeng")
                }
            }
            Spacer(Modifier.height(padding))
            Card(elevation = 4.dp) {
                Image(
                    painter = painterResource(R.mipmap.header),
                    contentDescription = null,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun FlowerCard() {
        Card(
            shape = RoundedCornerShape(14.dp),
            backgroundColor = Color.White,
            modifier = Modifier.padding(10.dp).width(100.dp)
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        text = "name",
                        style = TextStyle(
                            color = Purple200,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = "price",
                        style = TextStyle(
                            color = Purple500,
                            fontSize = 16.sp
                        )
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(
                        color = Purple500,
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }
        }
    }
}