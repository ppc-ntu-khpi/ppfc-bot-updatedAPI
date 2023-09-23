/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.workingsaturdays

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coreui.compose.ButtonWithLoader
import coreui.compose.OutlinedButton
import coreui.compose.OutlinedMultilineTextField
import coreui.compose.Text
import coreui.compose.base.*
import coreui.theme.AppTheme
import coreui.theme.Typography
import coreui.util.rememberGet
import org.jetbrains.compose.web.css.*
import tables.domain.model.WorkingSaturdays

@Composable
fun ShowWorkingSaturdaysDialog(
    onClose: () -> Unit
) {
    val viewModel: WorkingSaturdaysViewModel by rememberGet()
    val viewState by viewModel.state.collectAsState()

    Column(
        attrs = {
            style {
                margin(20.px)
            }
        }
    ) {
        Text(
            text = AppTheme.stringResources.tablesShowWorkingSaturdaysTitle,
            fontSize = Typography.headlineSmall
        )

        Spacer(height = 16.px)

        Column(
            attrs = {
                style {
                    width(100.percent)
                }
            },
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally
        ) {
            if (viewState.errorLoading) {
                Column(
                    horizontalAlignment = Alignment.Horizontal.CenterHorizontally
                ) {
                    Text(text = AppTheme.stringResources.tablesShowWorkingSaturdaysErrorLoading)

                    Spacer(height = 5.px)

                    ButtonWithLoader(
                        enabled = !viewState.isLoading,
                        loader = viewState.isLoading,
                        onClick = {
                            viewModel.loadWorkingSaturdays()
                        }
                    ) {
                        Text(text = AppTheme.stringResources.tablesShowWorkingSaturdaysRetry)
                    }
                }
            } else {
                OutlinedMultilineTextField(
                    attrs = {
                        style {
                            width(100.percent)
                            maxWidth(100.percent)
                            height(250.px)
                        }
                    },
                    value = viewState.workingSaturdays.text,
                ) { text ->
                    viewModel.setWorkingSaturdays(WorkingSaturdays(text))
                }
            }
        }

        Spacer(height = 16.px)

        Row(
            attrs = {
                style {
                    width(100.percent)
                }
            },
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalArrangement = Arrangement.Horizontal.Center
        ) {
            OutlinedButton(
                attrs = {
                    style {
                        width(100.percent)
                    }
                },
                onClick = {
                    onClose()
                }
            ) {
                Text(text = AppTheme.stringResources.tablesShowWorkingSaturdaysCancel)
            }

            Spacer(width = 24.px)

            ButtonWithLoader(
                attrs = {
                    style {
                        width(100.percent)
                    }
                },
                enabled = !viewState.isLoading && !viewState.isSaving,
                loader = viewState.isSaving,
                onClick = {
                    viewModel.saveWorkingSaturdays()
                }
            ) {
                Text(text = AppTheme.stringResources.tablesShowWorkingSaturdaysSave)
            }
        }
    }
}