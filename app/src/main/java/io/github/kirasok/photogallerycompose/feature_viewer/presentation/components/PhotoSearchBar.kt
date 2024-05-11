package io.github.kirasok.photogallerycompose.feature_viewer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import io.github.kirasok.photogallerycompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoSearchBar(
  modifier: Modifier = Modifier,
  query: String,
  onQueryChange: (String) -> Unit,
  onSearch: () -> Unit,
  active: Boolean,
  onActiveChange: (Boolean) -> Unit,
) {
  val focusManager = LocalFocusManager.current

  TextField(
    modifier = modifier
      .fillMaxWidth()
      .onFocusChanged {
        if (it.isFocused)
          onActiveChange(true)
      },
    value = query,
    onValueChange = onQueryChange,
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(onSearch = {
      focusManager.clearFocus()
      onSearch()
    }),
    singleLine = true,
    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
    trailingIcon = {
      if (active) {
        Icon(
          Icons.Default.Close,
          contentDescription = stringResource(R.string.close_search),
          modifier = Modifier.clickable {
            onActiveChange(false)
            focusManager.clearFocus()
          },
        )
      }
    },
    placeholder = { Text(stringResource(R.string.search)) },
  )
}

@Preview
@Composable
private fun PhotoSearchBarInactive() {
  PhotoSearchBar(query = "", onQueryChange = {}, onSearch = {}, active = false, onActiveChange = {})
}

@Preview
@Composable
private fun PhotoSearchBarActive() {
  PhotoSearchBar(
    query = "Photo of tree",
    onQueryChange = {},
    onSearch = {},
    active = true,
    onActiveChange = {})
}