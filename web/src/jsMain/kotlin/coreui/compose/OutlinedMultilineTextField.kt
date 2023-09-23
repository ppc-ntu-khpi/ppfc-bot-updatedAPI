/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package coreui.compose

import androidx.compose.runtime.*
import coreui.compose.base.*
import coreui.theme.AppTheme
import coreui.theme.Shape
import coreui.theme.Typography
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.readOnly
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.TextArea
import org.w3c.dom.HTMLDivElement

private val outlinedTextFieldWidth = 240.px
private val outlinedTextFieldHeight = 200.px

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun OutlinedMultilineTextField(
    value: String,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    editable: Boolean = true,
    enabled: Boolean = true,
    error: String? = null,
    onValueChange: (text: String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val isLabelElevated = isFocused || value.isNotEmpty()

    Column(
        attrs = {
            style {
                width(outlinedTextFieldWidth)
                height(outlinedTextFieldHeight)
                backgroundColor(Color.transparent)
            }

            applyAttrs(attrs)
        },
        verticalArrangement = Arrangement.Vertical.Center
    ) {
        Box(
            attrs = {
                style {
                    width(100.percent)
                    height(100.percent)
                    backgroundColor(Color.transparent)
                }
            },
            contentAlignment = Alignment.Box.CenterStart
        ) {
            Box(
                attrs = {
                    style {
                        width(100.percent)
                        height(100.percent)
                        borderRadius(Shape.small)
                        border {
                            style = LineStyle.Solid
                            width = 2.px
                            color = if (error != null) {
                                AppTheme.colors.error
                            } else if (isFocused) {
                                AppTheme.colors.primary
                            } else {
                                AppTheme.colors.outline
                            }
                        }
                        transitions {
                            all {
                                duration = 0.15.s
                                timingFunction = AnimationTimingFunction.EaseOut
                            }
                        }
                        if (!enabled) {
                            opacity(0.7)
                            pointerEvents(PointerEvents.None)
                        }
                    }
                },
                contentAlignment = Alignment.Box.CenterStart
            ) textFieldBox@{
                TextArea(
                    attrs = {
                        style {
                            width(100.percent)
                            height(100.percent)
                            paddingLeft(16.px)
                            paddingRight(16.px)
                            paddingTop(8.px)
                            paddingBottom(8.px)
                            backgroundColor(Color.transparent)
                            outline("0")
                            border(width = 0.px)
                            boxSizing(BoxSizing.BorderBox)
                            fontSize(Typography.bodyLarge)
                            color(AppTheme.colors.onSurface)
                            resize(Resize.None)
                            fontFamily("Sans-Serif")
                        }

                        onFocusIn {
                            isFocused = true
                        }

                        onFocusOut {
                            isFocused = false
                        }

                        onInput {
                            onValueChange(it.value)
                        }

                        if (!editable || !enabled) {
                            readOnly()
                        }
                    },
                    value = value
                )
            }
        }
    }

    if (error != null) {
        Spacer(height = 5.px)

        Text(
            attrs = {
                style {
                    paddingLeft(16.px)
                    paddingRight(16.px)
                    overflowWrap(OverflowWrap.BreakWord)
                    fontSize(Typography.bodyMedium)
                    color(AppTheme.colors.error)
                }
            },
            text = error
        )
    }
}