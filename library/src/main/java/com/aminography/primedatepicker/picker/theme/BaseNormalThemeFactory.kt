package com.aminography.primedatepicker.picker.theme

import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator
import com.aminography.primedatepicker.LabelFormatter
import com.aminography.primedatepicker.R
import com.aminography.primedatepicker.calendarview.PrimeCalendarView

/**
 * @author aminography
 */
abstract class BaseNormalThemeFactory : ThemeFactory() {

    // ------------------------------------------ General ------------------------------------------

    override val typefacePath: String?
        get() = null

    // --------------------------------------- Calendar View ---------------------------------------

    override val showTwoWeeksInLandscape: Boolean
        get() = getBoolean(R.bool.defaultShowTwoWeeksInLandscape)

    override val elementPaddingLeft: Int
        get() = getDimension(R.dimen.defaultElementPaddingLeft)

    override val elementPaddingRight: Int
        get() = getDimension(R.dimen.defaultElementPaddingRight)

    override val elementPaddingTop: Int
        get() = getDimension(R.dimen.defaultElementPaddingTop)

    override val elementPaddingBottom: Int
        get() = getDimension(R.dimen.defaultElementPaddingBottom)

    // ------- Month Label

    override val monthLabelTextSize: Int
        get() = getDimension(R.dimen.defaultMonthLabelTextSize)

    override val monthLabelTopPadding: Int
        get() = getDimension(R.dimen.defaultMonthLabelTopPadding)

    override val monthLabelBottomPadding: Int
        get() = getDimension(R.dimen.defaultMonthLabelBottomPadding)

    override val monthLabelFormatter: LabelFormatter
        get() = { primeCalendar -> "${primeCalendar.monthName} ${primeCalendar.year}" }

    // ------- Week Label

    override val weekLabelTextSize: Int
        get() = getDimension(R.dimen.defaultWeekLabelTextSize)

    override val weekLabelTopPadding: Int
        get() = getDimension(R.dimen.defaultWeekLabelTopPadding)

    override val weekLabelBottomPadding: Int
        get() = getDimension(R.dimen.defaultWeekLabelBottomPadding)

    override val weekLabelFormatter: LabelFormatter
        get() = { primeCalendar -> primeCalendar.weekDayNameShort }

    // ------- Day Label

    override val dayLabelTextSize: Int
        get() = getDimension(R.dimen.defaultDayLabelTextSize)

    override val dayLabelVerticalPadding: Int
        get() = getDimension(R.dimen.defaultDayLabelVerticalPadding)

    // ------- Divider

    override val dividerThickness: Int
        get() = getDimension(R.dimen.defaultDividerThickness)

    override val dividerInsetLeft: Int
        get() = getDimension(R.dimen.defaultDividerInsetLeft)

    override val dividerInsetRight: Int
        get() = getDimension(R.dimen.defaultDividerInsetRight)

    override val dividerInsetTop: Int
        get() = getDimension(R.dimen.defaultDividerInsetTop)

    override val dividerInsetBottom: Int
        get() = getDimension(R.dimen.defaultDividerInsetBottom)

    // ------- Animation

    override val animateSelection: Boolean
        get() = getBoolean(R.bool.defaultAnimateSelection)

    override val animationDuration: Int
        get() = getInteger(R.integer.defaultAnimationDuration)

    override val animationInterpolator: Interpolator
        get() = OvershootInterpolator()

    // ------- Transition

    override val loadFactor: Int
        get() = getInteger(R.integer.defaultLoadFactor)

    override val maxTransitionLength: Int
        get() = getInteger(R.integer.defaultMaxTransitionLength)

    override val transitionSpeedFactor: Float
        get() = getFloat(R.string.defaultTransitionSpeedFactor)

    override val flingOrientation: PrimeCalendarView.FlingOrientation
        get() = PrimeCalendarView.FlingOrientation.VERTICAL

    // ------- Developer Options

    override val developerOptionsShowGuideLines: Boolean
        get() = false

    // ------------------------------------ Picker Bottom Sheet ------------------------------------

    // ------- Button Bar

    override val actionBarTextSize: Int
        get() = getDimension(R.dimen.defaultButtonTextSize)

    // ------- Selection Bar - Single Day

    override val selectionBarSingleDayItemTopLabelTextSize: Int
        get() = getDimension(R.dimen.text_size_small)

    override val selectionBarSingleDayItemBottomLabelTextSize: Int
        get() = getDimension(R.dimen.text_size_normal)

    override val selectionBarSingleDayItemGapBetweenLines: Int
        get() = getDimension(R.dimen.defaultGapBetweenLines)

    override val selectionBarSingleDayLabelFormatter: LabelFormatter
        get() = { primeCalendar -> primeCalendar.shortDateString }

    // ------- Selection Bar - Range Days

    override val selectionBarRangeDaysItemTopLabelTextSize: Int
        get() = getDimension(R.dimen.text_size_small)

    override val selectionBarRangeDaysItemBottomLabelTextSize: Int
        get() = getDimension(R.dimen.text_size_normal)

    override val selectionBarRangeDaysItemGapBetweenLines: Int
        get() = getDimension(R.dimen.defaultGapBetweenLines)

    override val selectionBarRangeDaysLabelFormatter: LabelFormatter
        get() = { primeCalendar -> primeCalendar.shortDateString }

    // ------- Selection Bar - Multiple Days

    override val selectionBarMultipleDaysItemTopLabelTextSize: Int
        get() = getDimension(R.dimen.text_size_header_multi_large)

    override val selectionBarMultipleDaysItemBottomLabelTextSize: Int
        get() = getDimension(R.dimen.text_size_header_multi_small)

    override val selectionBarMultipleDaysItemGapBetweenLines: Int
        get() = 0

    override val selectionBarMultipleDaysItemTopLabelFormatter: LabelFormatter
        get() = { primeCalendar ->
            String.format("%02d", primeCalendar.dayOfMonth)
        }

    override val selectionBarMultipleDaysItemBottomLabelFormatter: LabelFormatter
        get() = { primeCalendar ->
            String.format(
                "%s '%s",
                primeCalendar.monthNameShort,
                "${primeCalendar.year}".substring(2)
            )
        }

    // ------- Goto View

    override val gotoViewTextSize: Int
        get() = getDimension(R.dimen.defaultGotoTextSize)

}