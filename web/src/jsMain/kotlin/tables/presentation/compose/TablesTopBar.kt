/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.compose

import androidx.compose.runtime.*
import core.domain.model.ColorSchemeMode
import coreui.compose.*
import coreui.compose.base.Alignment
import coreui.compose.base.Arrangement
import coreui.compose.base.Box
import coreui.compose.base.Row
import coreui.theme.AppIconClass
import coreui.theme.AppTheme
import coreui.theme.Typography
import coreui.util.alpha
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.w3c.dom.HTMLDivElement
import tables.presentation.navigation.TablesScreen
import tables.presentation.navigation.getName

@Composable
fun TablesTopBar(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    selectedScreen: TablesScreen,
    colorSchemeMode: ColorSchemeMode,
    onScreenSelected: (screen: TablesScreen) -> Unit,
    onColorSchemeModeChanged: (colorSchemeMode: ColorSchemeMode) -> Unit,
    onBellSchedule: () -> Unit,
    onWorkingSaturdays: () -> Unit,
    onGenerateAccessKey: () -> Unit,
    onLogOut: () -> Unit
) {
    Surface(
        attrs = {
            style {
                borderTopLeftRadius(0.percent)
                borderTopRightRadius(0.percent)
                overflow(Overflow.Hidden)
            }

            applyAttrs(attrs)
        },
        shadowElevation = ShadowElevation.Level3,
        tonalElevation = TonalElevation.Level3
    ) {
        Row(
            attrs = {
                style {
                    width(100.percent)
                    height(100.percent)
                    overflowY(Overflow.Auto)
                }
            },
            verticalAlignment = Alignment.Vertical.CenterVertically,
            horizontalArrangement = Arrangement.Horizontal.SpaceBetween
        ) {
            Row(
                attrs = {
                    style {
                        height(100.percent)
                    }
                }
            ) {
                TablesScreen.entries.forEach { screen ->
                    TableTopBarScreenItem(
                        isSelected = selectedScreen == screen,
                        title = screen.getName()
                    ) {
                        onScreenSelected(screen)
                    }
                }
            }

            Row(
                attrs = {
                    style {
                        height(100.percent)
                    }
                }
            ) {
                IconButton(
                    attrs = {
                        style {
                            margin(5.px)
                        }
                    },
                    icon = if (colorSchemeMode == ColorSchemeMode.LIGHT) {
                        AppIconClass.DarkColorScheme
                    } else {
                        AppIconClass.LightColorScheme
                    }
                ) {
                    val newColorSchemeMode = if (colorSchemeMode == ColorSchemeMode.LIGHT) {
                        ColorSchemeMode.DARK
                    } else {
                        ColorSchemeMode.LIGHT
                    }
                    onColorSchemeModeChanged(newColorSchemeMode)
                }

                IconButton(
                    attrs = {
                        style {
                            margin(5.px)
                        }
                    },
                    icon = AppIconClass.Bell
                ) {
                    onBellSchedule()
                }

                IconButton(
                    attrs = {
                        style {
                            margin(5.px)
                        }
                    },
                    icon = AppIconClass.CalendarWeek
                ) {
                    onWorkingSaturdays()
                }

                IconButton(
                    attrs = {
                        style {
                            margin(5.px)
                        }
                    },
                    icon = AppIconClass.Key
                ) {
                    onGenerateAccessKey()
                }

                IconButton(
                    attrs = {
                        style {
                            margin(5.px)
                        }
                    },
                    icon = AppIconClass.LogOut
                ) {
                    onLogOut()
                }
            }
        }
    }
}

@Composable
private fun TableTopBarScreenItem(
    isSelected: Boolean,
    title: String,
    onSelect: () -> Unit
) {
    var isHover by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }

    Box(
        attrs = {
            style {
                height(100.percent)
                paddingLeft(10.px)
                paddingRight(10.px)

                if (isSelected) {
                    backgroundColor(AppTheme.colors.primary)
                    color(AppTheme.colors.onPrimary)
                } else {
                    backgroundColor(
                        when {
                            isPressed -> AppTheme.colors.primary.alpha(0.1f)
                            isHover -> AppTheme.colors.primary.alpha(0.05f)
                            else -> Color.transparent
                        }
                    )
                    color(AppTheme.colors.primary)
                }
            }

            onMouseDown {
                isPressed = true
            }

            onMouseUp {
                isPressed = false
            }

            onMouseOver {
                isHover = true
            }

            onMouseOut {
                isHover = false
                isPressed = false
            }

            onClick {
                if (!isSelected) {
                    onSelect()
                }
            }
        },
        contentAlignment = Alignment.Box.Center
    ) {
        Text(
            text = title,
            fontSize = Typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}