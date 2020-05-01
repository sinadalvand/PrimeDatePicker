package com.aminography.primedatepicker.picker.theme

import android.content.res.ColorStateList
import androidx.core.widget.ImageViewCompat
import com.aminography.primedatepicker.calendarview.PrimeCalendarView
import com.aminography.primedatepicker.picker.action.ActionBarView
import com.aminography.primedatepicker.picker.base.BaseLazyView
import com.aminography.primedatepicker.picker.component.ColoredNumberPicker
import com.aminography.primedatepicker.picker.go.GotoView
import com.aminography.primedatepicker.picker.selection.SelectionBarView
import com.aminography.primedatepicker.picker.selection.multiple.MultipleDaysSelectionBarView
import com.aminography.primedatepicker.picker.selection.range.RangeDaysSelectionBarView
import com.aminography.primedatepicker.picker.selection.single.SingleDaySelectionBarView
import kotlinx.android.synthetic.main.action_bar_container.view.*
import kotlinx.android.synthetic.main.goto_container.view.*
import kotlinx.android.synthetic.main.range_days_header.view.*
import kotlinx.android.synthetic.main.single_day_header.view.*

/**
 * @author aminography
 */

internal fun ActionBarView.applyTheme(theme: ThemeFactory) {
    with(rootView) {
        todayTwoLineTextView.topLabelTextColor = theme.actionBarTodayTextColor
        negativeTwoLineTextView.topLabelTextColor = theme.actionBarNegativeTextColor
        positiveTwoLineTextView.topLabelTextColor = theme.actionBarPositiveTextColor

        todayTwoLineTextView.topLabelTextSize = theme.actionBarTextSize
        negativeTwoLineTextView.topLabelTextSize = theme.actionBarTextSize
        positiveTwoLineTextView.topLabelTextSize = theme.actionBarTextSize
    }
}

internal fun SelectionBarView.applyTheme(theme: ThemeFactory) {
    with((this as BaseLazyView).rootView) {
        setBackgroundColor(theme.selectionBarBackgroundColor)
    }
    when (this) {
        is SingleDaySelectionBarView -> {
            labelFormatter = theme.selectionBarSingleDayLabelFormatter

            with((this as BaseLazyView).rootView) {
                pickedTextView.topLabelTextSize = theme.selectionBarSingleDayItemTopLabelTextSize
                pickedTextView.topLabelTextColor = theme.selectionBarSingleDayItemTopLabelTextColor
                pickedTextView.bottomLabelTextSize = theme.selectionBarSingleDayItemBottomLabelTextSize
                pickedTextView.bottomLabelTextColor = theme.selectionBarSingleDayItemBottomLabelTextColor
                pickedTextView.gapBetweenLines = theme.selectionBarSingleDayItemGapBetweenLines
            }
        }
        is RangeDaysSelectionBarView -> {
            labelFormatter = theme.selectionBarRangeDaysLabelFormatter
            itemBackgroundColor = theme.selectionBarRangeDaysItemBackgroundColor

            with((this as BaseLazyView).rootView) {
                rangeStartTextView.topLabelTextSize = theme.selectionBarRangeDaysItemTopLabelTextSize
                rangeStartTextView.topLabelTextColor = theme.selectionBarRangeDaysItemTopLabelTextColor
                rangeStartTextView.bottomLabelTextSize = theme.selectionBarRangeDaysItemBottomLabelTextSize
                rangeStartTextView.bottomLabelTextColor = theme.selectionBarRangeDaysItemBottomLabelTextColor
                rangeStartTextView.gapBetweenLines = theme.selectionBarRangeDaysItemGapBetweenLines

                rangeEndTextView.topLabelTextSize = theme.selectionBarRangeDaysItemTopLabelTextSize
                rangeEndTextView.topLabelTextColor = theme.selectionBarRangeDaysItemTopLabelTextColor
                rangeEndTextView.bottomLabelTextSize = theme.selectionBarRangeDaysItemBottomLabelTextSize
                rangeEndTextView.bottomLabelTextColor = theme.selectionBarRangeDaysItemBottomLabelTextColor
                rangeEndTextView.gapBetweenLines = theme.selectionBarRangeDaysItemGapBetweenLines
            }
        }
        is MultipleDaysSelectionBarView -> {
            topLabelFormatter = theme.selectionBarMultipleDaysItemTopLabelFormatter
            bottomLabelFormatter = theme.selectionBarMultipleDaysItemBottomLabelFormatter

            itemBackgroundColor = theme.selectionBarMultipleDaysItemBackgroundColor
            topLabelTextSize = theme.selectionBarMultipleDaysItemTopLabelTextSize
            topLabelTextColor = theme.selectionBarMultipleDaysItemTopLabelTextColor
            bottomLabelTextSize = theme.selectionBarMultipleDaysItemBottomLabelTextSize
            bottomLabelTextColor = theme.selectionBarMultipleDaysItemBottomLabelTextColor
            gapBetweenLines = theme.selectionBarMultipleDaysItemGapBetweenLines
        }
    }
}

internal fun GotoView.applyTheme(theme: ThemeFactory) {
    with(rootView) {
        setBackgroundColor(theme.gotoViewBackgroundColor)
        ImageViewCompat.setImageTintList(goIconImageView, ColorStateList.valueOf(theme.gotoViewBackgroundColor))
        ImageViewCompat.setImageTintList(closeIconImageView, ColorStateList.valueOf(theme.gotoViewBackgroundColor))

        ColoredNumberPicker.labelTextSize = theme.gotoViewTextSize
        ColoredNumberPicker.labelTextColor = theme.gotoViewTextColor
        ColoredNumberPicker.dividerColor = theme.gotoViewDividerColor
    }
}

internal fun PrimeCalendarView.applyTheme(theme: ThemeFactory) {
    // ------- Color Customizations
    setBackgroundColor(theme.elementBackgroundColor)
    monthLabelTextColor = theme.monthLabelTextColor
    weekLabelTextColor = theme.weekLabelTextColor
    dayLabelTextColor = theme.dayLabelTextColor
    todayLabelTextColor = theme.todayLabelTextColor
    pickedDayLabelTextColor = theme.pickedDayLabelTextColor
    pickedDayCircleColor = theme.pickedDayCircleColor
    disabledDayLabelTextColor = theme.disabledDayLabelTextColor
    dividerColor = theme.dividerColor

    // ------- Size & Behaviour Customizations
    monthLabelTextSize = theme.monthLabelTextSize
    weekLabelTextSize = theme.weekLabelTextSize
    dayLabelTextSize = theme.dayLabelTextSize
    monthLabelTopPadding = theme.monthLabelTopPadding
    monthLabelBottomPadding = theme.monthLabelBottomPadding
    weekLabelTopPadding = theme.weekLabelTopPadding
    weekLabelBottomPadding = theme.weekLabelBottomPadding
    dayLabelVerticalPadding = theme.dayLabelVerticalPadding
    showTwoWeeksInLandscape = theme.showTwoWeeksInLandscape
    animateSelection = theme.animateSelection
    animationDuration = theme.animationDuration
    animationInterpolator = theme.animationInterpolator
    dividerThickness = theme.dividerThickness
    dividerInsetLeft = theme.dividerInsetLeft
    dividerInsetRight = theme.dividerInsetRight
    dividerInsetTop = theme.dividerInsetTop
    dividerInsetBottom = theme.dividerInsetBottom

    elementPaddingLeft = theme.elementPaddingLeft
    elementPaddingRight = theme.elementPaddingRight
    elementPaddingTop = theme.elementPaddingTop
    elementPaddingBottom = theme.elementPaddingBottom

    loadFactor = theme.loadFactor
    maxTransitionLength = theme.maxTransitionLength
    transitionSpeedFactor = theme.transitionSpeedFactor
    flingOrientation = theme.flingOrientation
    monthLabelFormatter = theme.monthLabelFormatter
    weekLabelFormatter = theme.weekLabelFormatter
    developerOptionsShowGuideLines = theme.developerOptionsShowGuideLines
}
