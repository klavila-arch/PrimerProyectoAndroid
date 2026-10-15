
package notes.app.ebc.karen.muffinshop.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import notes.app.ebc.karen.muffinshop.viewmodel.PedidoViewModel

@Composable
fun MuffinShopApp(
    viewModel: PedidoViewModel = viewModel()
) {
    val navController = rememberNavController()

    val backEntry by navController.currentBackStackEntryAsState()
}
