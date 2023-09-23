/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package coreui.compose

import androidx.compose.runtime.Composable
import coreui.compose.base.Box
import coreui.theme.AppStyleSheet
import coreui.theme.AppTheme
import coreui.theme.Shape
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.w3c.dom.HTMLDivElement

@Composable
fun CircularProgressIndicator(
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    color: CSSColorValue = AppTheme.colors.primary,
    size: CSSNumeric = 50.px,
    strokeWidth: CSSNumeric? = null
) {
    Box(
        attrs = {
            style {
                width(size)
                height(size)
                padding(strokeWidth ?: (size / 8))
                background("$color")
                borderRadius(Shape.round)
                property("-webkit-mask", "conic-gradient(#0000,#000), linear-gradient(#000 0 0) content-box")
                property("-webkit-mask-composite", "source-out")
                property("mask-composite", "subtract")
                property("box-sizing", "border-box")
                animation(AppStyleSheet.circularProgressKeyframes) {
                    duration(0.9.s)
                    timingFunction(AnimationTimingFunction.Linear)
                    iterationCount(null)
                }
            }

            applyAttrs(attrs)
        }
    )
}