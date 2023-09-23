/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.bellschedule

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
import tables.domain.model.BellSchedule

@Composable
fun ShowBellScheduleDialog(
    onClose: () -> Unit
) {
    val viewModel: BellScheduleViewModel by rememberGet()
    val viewState by viewModel.state.collectAsState()

    Column(
        attrs = {
            style {
                margin(20.px)
            }
        }
    ) {
        Text(
            text = AppTheme.stringResources.tablesShowBellScheduleTitle,
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
                    Text(text = AppTheme.stringResources.tablesShowBellScheduleErrorLoading)

                    Spacer(height = 5.px)

                    ButtonWithLoader(
                        enabled = !viewState.isLoading,
                        loader = viewState.isLoading,
                        onClick = {
                            viewModel.loadBellSchedule()
                        }
                    ) {
                        Text(text = AppTheme.stringResources.tablesShowBellScheduleRetry)
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
                    value = viewState.bellSchedule.text,
                ) { text ->
                    viewModel.setBellSchedule(BellSchedule(text))
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
                Text(text = AppTheme.stringResources.tablesShowBellScheduleCancel)
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
                    viewModel.saveBellSchedule()
                }
            ) {
                Text(text = AppTheme.stringResources.tablesShowBellScheduleSave)
            }
        }
    }
}