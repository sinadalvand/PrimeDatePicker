package com.aminography.primedatepicker.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import com.aminography.primecalendar.base.BaseCalendar
import com.aminography.primecalendar.common.CalendarFactory
import com.aminography.primecalendar.common.CalendarType
import com.aminography.primedatepicker.PickType
import com.aminography.primedatepicker.R
import com.aminography.primedatepicker.tools.DateUtils
import com.aminography.primedatepicker.tools.screenSize
import kotlinx.android.synthetic.main.fragment_date_picker_bottom_sheet.view.*

class DatePickerBottomSheetDialogFragment : BaseBottomSheetDialogFragment(R.layout.fragment_date_picker_bottom_sheet) {

    private var currentDateCalendar: BaseCalendar? = null
    private var calendarType = CalendarType.CIVIL
    private var minDateCalendar: BaseCalendar? = null
    private var maxDateCalendar: BaseCalendar? = null
    private var pickType: PickType = PickType.NOTHING

    private var onCancelListener: DialogInterface.OnCancelListener? = null
    private var onDismissListener: DialogInterface.OnDismissListener? = null
    private var onDateSetListener: OnDateSetListener? = null

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val parentView = rootView.parent as View
        parentView.setBackgroundColor(ContextCompat.getColor(activityContext, R.color.transparent))
        val params = parentView.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.peekHeight = activityContext.screenSize().y
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onInitViews(rootView: View) {
        with(rootView) {
            currentDateCalendar = DateUtils.restoreCalendar(arguments?.getString("currentDateCalendar"))
            minDateCalendar = DateUtils.restoreCalendar(arguments?.getString("minDateCalendar"))
            maxDateCalendar = DateUtils.restoreCalendar(arguments?.getString("maxDateCalendar"))
            pickType = PickType.valueOf(arguments?.getString("pickType") ?: PickType.NOTHING.name)
            calendarType = currentDateCalendar?.calendarType ?: CalendarType.CIVIL

            calendarView.calendarType = calendarType
            calendarView.pickType = pickType

            positiveButton.setOnClickListener {
                when (pickType) {
                    PickType.SINGLE -> {
                        calendarView.pickedSingleDayCalendar?.apply {
                            onDateSetListener?.onDateSet(this)
                        }
                    }
                    else -> {
                    }
                }
                dismiss()
            }

            negativeButton.setOnClickListener { dismiss() }

            todayButton.setOnClickListener {
                val calendar = CalendarFactory.newInstance(calendarType)
                calendarView.goto(calendar.year, calendar.month, true)
            }
        }
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        onCancelListener?.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss(dialog)
    }

    /**
     * Sets the minimal date supported by this DatePicker. Dates before (but not including) the
     * specified date will be disallowed from being selected.
     *
     * @param calendar a Calendar object set to the year, month, day desired as the mindate.
     */

    fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener) {
        this.onCancelListener = onCancelListener
    }

    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) {
        this.onDismissListener = onDismissListener
    }

    fun registerOnDateSetListener(onDateSetListener: OnDateSetListener): DatePickerBottomSheetDialogFragment {
        this.onDateSetListener = onDateSetListener
        return this
    }

    interface OnDateSetListener {

        fun onDateSet(calendar: BaseCalendar)
    }

    companion object {

        fun newInstance(
                currentDateCalendar: BaseCalendar,
                minDateCalendar: BaseCalendar?,
                maxDateCalendar: BaseCalendar?,
                pickType: PickType
        ): DatePickerBottomSheetDialogFragment {
            val fragment = DatePickerBottomSheetDialogFragment()
            val bundle = Bundle()
            bundle.putString("currentDateCalendar", DateUtils.storeCalendar(currentDateCalendar))
            bundle.putString("minDateCalendar", DateUtils.storeCalendar(minDateCalendar))
            bundle.putString("maxDateCalendar", DateUtils.storeCalendar(maxDateCalendar))
            bundle.putString("pickType", pickType.name)
            fragment.arguments = bundle
            return fragment
        }
    }

}