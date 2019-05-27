package com.aminography.primedatepicker.calendarview

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.FrameLayout
import com.aminography.primeadapter.PrimeAdapter
import com.aminography.primeadapter.PrimeDataHolder
import com.aminography.primedatepicker.calendarview.adapter.MonthListAdapter
import com.aminography.primedatepicker.calendarview.dataholder.MonthDataHolder
import com.aminography.primedatepicker.tools.Utils


/**
 * @author aminography
 */
@Suppress("PrivatePropertyName")
class CalendarView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        @AttrRes defStyleAttr: Int = 0,
        @StyleRes defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val DEFAULT_LOAD_FACTOR = 24
    private val DEFAULT_TRANSITION_FACTOR = 2

    private var adapter: MonthListAdapter
    private var recyclerView = TouchControllableRecyclerView(context)
    private var layoutManager = LinearLayoutManager(context)
    private var centeredData: ArrayList<PrimeDataHolder>? = null
    private var isInTransition = false
    private var isInLoading = false

    init {
        addView(recyclerView)
        recyclerView.addOnScrollListener(OnScrollListener())

        adapter = PrimeAdapter.with(recyclerView)
                .setLayoutManager(layoutManager)
                .setSnapHelper(StartSnapHelper())
                .setHasFixedSize(true)
                .setDivider()
                .set()
                .build(MonthListAdapter::class.java)

        val calendar = Utils.newCalendar()
        goto(calendar.year, calendar.month, false)
    }

    fun goto(year: Int, month: Int, animate: Boolean = false) {
        centeredData = CalendarViewUtils.centeredData(year, month, DEFAULT_LOAD_FACTOR)
        if (animate) {
            val position = layoutManager.findFirstVisibleItemPosition()
            val dataHolder = adapter.getItem(position) as MonthDataHolder

            val transitionData = CalendarViewUtils.transitionData(dataHolder.year, dataHolder.month, year, month, DEFAULT_TRANSITION_FACTOR)
            val isForward = CalendarViewUtils.isForward(dataHolder.year, dataHolder.month, year, month)
            transitionData?.apply {
                adapter.replaceDataList(this)
                isInTransition = true
                recyclerView.touchEnabled = false
                if (isForward) {
                    recyclerView.fastScrollTo(1)
                    recyclerView.smoothScrollTo(size - 2)
                } else {
                    recyclerView.fastScrollTo(size - 2)
                    recyclerView.smoothScrollTo(1)
                }
            }
        } else {
            centeredData?.apply {
                adapter.replaceDataList(this)
                recyclerView.fastScrollTo(DEFAULT_LOAD_FACTOR)
            }
        }
    }

    private inner class OnScrollListener : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(view: RecyclerView, newState: Int) {
            super.onScrollStateChanged(view, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_DRAGGING -> {
                }
                RecyclerView.SCROLL_STATE_SETTLING -> {
                }
                RecyclerView.SCROLL_STATE_IDLE -> {
                    if (isInTransition) {
                        postDelayed({
                            centeredData?.apply {
                                adapter.replaceDataList(this)
                                recyclerView.fastScrollTo(DEFAULT_LOAD_FACTOR)
                                isInTransition = false
                                recyclerView.touchEnabled = true
                            }
                        }, 10)
                    }
                }
            }
        }

        override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(view, dx, dy)
            if (!isInTransition) {
                if (dy > 0) { // scroll down
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (!isInLoading && (visibleItemCount + firstVisibleItemPosition >= totalItemCount)) {
                        isInLoading = true
                        val dataHolder = adapter.getItem(totalItemCount - 1) as MonthDataHolder
                        val moreData = CalendarViewUtils.moreData(dataHolder.year, dataHolder.month, DEFAULT_LOAD_FACTOR, true)
                        centeredData?.apply {
                            addAll(size, moreData)
                            adapter.replaceDataList(this)
                        }
                        isInLoading = false
                    }
                } else if (dy < 0) {
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (!isInLoading && firstVisibleItemPosition == 0) {
                        isInLoading = true
                        val dataHolder = adapter.getItem(0) as MonthDataHolder
                        val moreData = CalendarViewUtils.moreData(dataHolder.year, dataHolder.month, DEFAULT_LOAD_FACTOR, false)
                        centeredData?.apply {
                            addAll(0, moreData)
                            adapter.replaceDataList(this)
                            recyclerView.fastScrollTo(DEFAULT_LOAD_FACTOR + 1)
                        }
                        isInLoading = false
                    }
                }
            }
        }
    }

}