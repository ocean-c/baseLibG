package com.luck.luckcloud.baselibrary.view.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.luckcloud.baselibrary.R;
import com.luck.luckcloud.baselibrary.R2;
import com.luck.luckcloud.baselibrary.utils.DisplayUtil;
import com.luck.luckcloud.baselibrary.utils.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义日期时间选择器
 * Created by fa on 2020/4/18.
 */
public class DatePickerView extends LinearLayout implements PickerView.OnSelectListener {
    // 年
    @BindView(R2.id.view_picker_year)
    public PickerView mYearPickerView;
    @BindView(R2.id.tv_year_unit)
    public TextView mYearUnitTv;
    // 月
    @BindView(R2.id.view_picker_month)
    public PickerView mMonthPickerView;
    @BindView(R2.id.tv_month_unit)
    public TextView mMonthUnitTv;
    // 日
    @BindView(R2.id.view_picker_day)
    public PickerView mDayPickerView;
    @BindView(R2.id.tv_day_unit)
    public TextView mDayUnitTv;
    // 时
    @BindView(R2.id.view_picker_hour)
    public PickerView mHourPickerView;
    @BindView(R2.id.tv_hour_unit)
    public TextView mHourUnitTv;
    // 分
    @BindView(R2.id.view_picker_minute)
    public PickerView mMinutePickerView;
    @BindView(R2.id.tv_minute_unit)
    public TextView mMinuteUnitTv;

    private Context mContext;
    private Calendar mBeginTime, mEndTime, mSelectedTime;

    private int mBeginYear, mBeginMonth, mBeginDay, mBeginHour, mBeginMinute,
            mEndYear, mEndMonth, mEndDay, mEndHour, mEndMinute;
    private List<String> mYearList = new ArrayList<>(), mMonthList = new ArrayList<>(), mDayList = new ArrayList<>(),
            mHourList = new ArrayList<>(), mMinuteList = new ArrayList<>();
    private DecimalFormat mDecimalFormat = new DecimalFormat("00");

    private String mYearUnit = "年", mMonthUnit = "月", mDayUnit = "日", mHourUnit = "时", mMinuteUnit = "分";

    private boolean mCanShowPreciseTime;
    private int mScrollUnits = SCROLL_UNIT_HOUR + SCROLL_UNIT_MINUTE;

    /**
     * 时间单位：时、分
     */
    private static final int SCROLL_UNIT_HOUR = 0b1;
    private static final int SCROLL_UNIT_MINUTE = 0b10;

    /**
     * 时间单位的最大显示值
     */
    private static final int MAX_MINUTE_UNIT = 59;
    private static final int MAX_HOUR_UNIT = 23;
    private static final int MAX_MONTH_UNIT = 12;

    /**
     * 级联滚动延迟时间
     */
    private static final long LINKAGE_DELAY_DEFAULT = 100L;

    public DatePickerView(Context context) {
        this(context, null);
    }

    public DatePickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(DisplayUtil.dp2px(mContext, 20), DisplayUtil.dp2px(mContext, 15),
                DisplayUtil.dp2px(mContext, 20), DisplayUtil.dp2px(mContext, 20));
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_date_picker, this, true);
        ButterKnife.bind(this, view);
        mBeginTime = Calendar.getInstance();
        mBeginTime.setTimeInMillis(0);
        mEndTime = Calendar.getInstance();
        mEndTime.add(Calendar.YEAR, 10);
        mSelectedTime = Calendar.getInstance();
        // 设置单位
        mYearPickerView.setUnit(mYearUnit);
        mYearUnitTv.setVisibility(StringUtils.isEmpty(mYearUnit) ? VISIBLE : GONE);
        mMonthPickerView.setUnit(mMonthUnit);
        mMonthUnitTv.setVisibility(StringUtils.isEmpty(mMonthUnit) ? VISIBLE : GONE);
        mDayPickerView.setUnit(mDayUnit);
        mDayUnitTv.setVisibility(StringUtils.isEmpty(mDayUnit) ? VISIBLE : GONE);
        mHourPickerView.setUnit(mHourUnit);
        mHourUnitTv.setVisibility(StringUtils.isEmpty(mHourUnit) ? VISIBLE : GONE);
        mMinutePickerView.setUnit(mMinuteUnit);
        mMinuteUnitTv.setVisibility(StringUtils.isEmpty(mMinuteUnit) ? VISIBLE : GONE);
        // 设置文字大小和间距
        mYearPickerView.setTextSize(DisplayUtil.dp2px(mContext, 16));
        mYearPickerView.setTextSpace(DisplayUtil.dp2px(mContext, 15));
        mMonthPickerView.setTextSize(DisplayUtil.dp2px(mContext, 16));
        mMonthPickerView.setTextSpace(DisplayUtil.dp2px(mContext, 15));
        mDayPickerView.setTextSize(DisplayUtil.dp2px(mContext, 16));
        mDayPickerView.setTextSpace(DisplayUtil.dp2px(mContext, 15));
        mHourPickerView.setTextSize(DisplayUtil.dp2px(mContext, 16));
        mHourPickerView.setTextSpace(DisplayUtil.dp2px(mContext, 15));
        mMinutePickerView.setTextSize(DisplayUtil.dp2px(mContext, 16));
        mMinutePickerView.setTextSpace(DisplayUtil.dp2px(mContext, 15));
        // 设置选择监听
        mYearPickerView.setOnSelectListener(this);
        mMonthPickerView.setOnSelectListener(this);
        mDayPickerView.setOnSelectListener(this);
        mHourPickerView.setOnSelectListener(this);
        mMinutePickerView.setOnSelectListener(this);
        // 数据初始化
        initData();
    }

    private void initData() {
        mBeginYear = mBeginTime.get(Calendar.YEAR);
        mBeginMonth = mBeginTime.get(Calendar.MONTH) + 1;// Calendar.MONTH 值为 0-11
        mBeginDay = mBeginTime.get(Calendar.DAY_OF_MONTH);
        mBeginHour = mBeginTime.get(Calendar.HOUR_OF_DAY);
        mBeginMinute = mBeginTime.get(Calendar.MINUTE);

        mEndYear = mEndTime.get(Calendar.YEAR);
        mEndMonth = mEndTime.get(Calendar.MONTH) + 1;// Calendar.MONTH 值为 0-11
        mEndDay = mEndTime.get(Calendar.DAY_OF_MONTH);
        mEndHour = mEndTime.get(Calendar.HOUR_OF_DAY);
        mEndMinute = mEndTime.get(Calendar.MINUTE);

        boolean canSpanYear = mBeginYear != mEndYear;
        boolean canSpanMon = !canSpanYear && mBeginMonth != mEndMonth;
        boolean canSpanDay = !canSpanMon && mBeginDay != mEndDay;
        boolean canSpanHour = !canSpanDay && mBeginHour != mEndHour;
        boolean canSpanMinute = !canSpanHour && mBeginMinute != mEndMinute;
        if (canSpanYear) {
            initDateList(MAX_MONTH_UNIT, mBeginTime.getActualMaximum(Calendar.DAY_OF_MONTH), MAX_HOUR_UNIT, MAX_MINUTE_UNIT);
        } else if (canSpanMon) {
            initDateList(mEndMonth, mBeginTime.getActualMaximum(Calendar.DAY_OF_MONTH), MAX_HOUR_UNIT, MAX_MINUTE_UNIT);
        } else if (canSpanDay) {
            initDateList(mEndMonth, mEndDay, MAX_HOUR_UNIT, MAX_MINUTE_UNIT);
        } else if (canSpanHour) {
            initDateList(mEndMonth, mEndDay, mEndHour, MAX_MINUTE_UNIT);
        } else if (canSpanMinute) {
            initDateList(mEndMonth, mEndDay, mEndHour, mEndMinute);
        }
    }

    private void initDateList(int endMonth, int endDay, int endHour, int endMinute) {
        for (int i = mBeginYear; i <= mEndYear; i++) {
            mYearList.add(String.valueOf(i));
        }

        for (int i = mBeginMonth; i <= endMonth; i++) {
            mMonthList.add(mDecimalFormat.format(i));
        }

        for (int i = mBeginDay; i <= endDay; i++) {
            mDayList.add(mDecimalFormat.format(i));
        }

        if ((mScrollUnits & SCROLL_UNIT_HOUR) != SCROLL_UNIT_HOUR) {
            mHourList.add(mDecimalFormat.format(mBeginHour));
        } else {
            for (int i = mBeginHour; i <= endHour; i++) {
                mHourList.add(mDecimalFormat.format(i));
            }
        }

        if ((mScrollUnits & SCROLL_UNIT_MINUTE) != SCROLL_UNIT_MINUTE) {
            mMinuteList.add(mDecimalFormat.format(mBeginMinute));
        } else {
            for (int i = mBeginMinute; i <= endMinute; i++) {
                mMinuteList.add(mDecimalFormat.format(i));
            }
        }

        mYearPickerView.setDataList(mYearList);
        mYearPickerView.setSelected(0);
        mMonthPickerView.setDataList(mMonthList);
        mMonthPickerView.setSelected(0);
        mDayPickerView.setDataList(mDayList);
        mDayPickerView.setSelected(0);
        mHourPickerView.setDataList(mHourList);
        mHourPickerView.setSelected(0);
        mMinutePickerView.setDataList(mMinuteList);
        mMinutePickerView.setSelected(0);

        setCanScroll();
    }

    @Override
    public void onSelect(View view, String selected) {
        if (view == null || StringUtils.isEmpty(selected)) return;

        int timeUnit;
        try {
            timeUnit = Integer.parseInt(selected);
        } catch (Throwable ignored) {
            return;
        }
        if (view == mYearPickerView) {// 年
            mSelectedTime.set(Calendar.YEAR, timeUnit);
            linkageMonthList(true, LINKAGE_DELAY_DEFAULT);
        } else if (view == mMonthPickerView) {// 月
            // 防止类似 2018/12/31 滚动到11月时因溢出变成 2018/12/01
            int lastSelectedMonth = mSelectedTime.get(Calendar.MONTH) + 1;
            mSelectedTime.add(Calendar.MONTH, timeUnit - lastSelectedMonth);
            linkageDayList(true, LINKAGE_DELAY_DEFAULT);
        } else if (view == mDayPickerView) {// 日
            mSelectedTime.set(Calendar.DAY_OF_MONTH, timeUnit);
            linkageHourList(true, LINKAGE_DELAY_DEFAULT);
        } else if (view == mHourPickerView) {// 时
            mSelectedTime.set(Calendar.HOUR_OF_DAY, timeUnit);
            linkageMinuteList(true);
        } else if (view == mMinutePickerView) {// 分
            mSelectedTime.set(Calendar.MINUTE, timeUnit);
        }
    }


    /**
     * 联动“月”变化
     *
     * @param showAnim 是否展示滚动动画
     * @param delay    联动下一级延迟时间
     */
    private void linkageMonthList(final boolean showAnim, final long delay) {
        int minMonth;
        int maxMonth;
        int selectedYear = mSelectedTime.get(Calendar.YEAR);
        if (mBeginYear == mEndYear) {
            minMonth = mBeginMonth;
            maxMonth = mEndMonth;
        } else if (selectedYear == mBeginYear) {
            minMonth = mBeginMonth;
            maxMonth = MAX_MONTH_UNIT;
        } else if (selectedYear == mEndYear) {
            minMonth = 1;
            maxMonth = mEndMonth;
        } else {
            minMonth = 1;
            maxMonth = MAX_MONTH_UNIT;
        }

        // 重新初始化时间单元容器
        mMonthList.clear();
        for (int i = minMonth; i <= maxMonth; i++) {
            mMonthList.add(mDecimalFormat.format(i));
        }
        mMonthPickerView.setDataList(mMonthList);

        // 确保联动时不会溢出或改变关联选中值
        int selectedMonth = getValueInRange(mSelectedTime.get(Calendar.MONTH) + 1, minMonth, maxMonth);
        mSelectedTime.set(Calendar.MONTH, selectedMonth - 1);
        mMonthPickerView.setSelected(selectedMonth - minMonth);
        if (showAnim) {
            mMonthPickerView.startAnim();
        }

        // 联动“日”变化
        mMonthPickerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                linkageDayList(showAnim, delay);
            }
        }, delay);
    }

    /**
     * 联动“日”变化
     *
     * @param showAnim 是否展示滚动动画
     * @param delay    联动下一级延迟时间
     */
    private void linkageDayList(final boolean showAnim, final long delay) {
        int minDay;
        int maxDay;
        int selectedYear = mSelectedTime.get(Calendar.YEAR);
        int selectedMonth = mSelectedTime.get(Calendar.MONTH) + 1;
        if (mBeginYear == mEndYear && mBeginMonth == mEndMonth) {
            minDay = mBeginDay;
            maxDay = mEndDay;
        } else if (selectedYear == mBeginYear && selectedMonth == mBeginMonth) {
            minDay = mBeginDay;
            maxDay = mSelectedTime.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else if (selectedYear == mEndYear && selectedMonth == mEndMonth) {
            minDay = 1;
            maxDay = mEndDay;
        } else {
            minDay = 1;
            maxDay = mSelectedTime.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        mDayList.clear();
        for (int i = minDay; i <= maxDay; i++) {
            mDayList.add(mDecimalFormat.format(i));
        }
        mDayPickerView.setDataList(mDayList);

        int selectedDay = getValueInRange(mSelectedTime.get(Calendar.DAY_OF_MONTH), minDay, maxDay);
        mSelectedTime.set(Calendar.DAY_OF_MONTH, selectedDay);
        mDayPickerView.setSelected(selectedDay - minDay);
        if (showAnim) {
            mDayPickerView.startAnim();
        }

        mDayPickerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                linkageHourList(showAnim, delay);
            }
        }, delay);
    }

    /**
     * 联动“时”变化
     *
     * @param showAnim 是否展示滚动动画
     * @param delay    联动下一级延迟时间
     */
    private void linkageHourList(final boolean showAnim, final long delay) {
        if ((mScrollUnits & SCROLL_UNIT_HOUR) == SCROLL_UNIT_HOUR) {
            int minHour;
            int maxHour;
            int selectedYear = mSelectedTime.get(Calendar.YEAR);
            int selectedMonth = mSelectedTime.get(Calendar.MONTH) + 1;
            int selectedDay = mSelectedTime.get(Calendar.DAY_OF_MONTH);
            if (mBeginYear == mEndYear && mBeginMonth == mEndMonth && mBeginDay == mEndDay) {
                minHour = mBeginHour;
                maxHour = mEndHour;
            } else if (selectedYear == mBeginYear && selectedMonth == mBeginMonth && selectedDay == mBeginDay) {
                minHour = mBeginHour;
                maxHour = MAX_HOUR_UNIT;
            } else if (selectedYear == mEndYear && selectedMonth == mEndMonth && selectedDay == mEndDay) {
                minHour = 0;
                maxHour = mEndHour;
            } else {
                minHour = 0;
                maxHour = MAX_HOUR_UNIT;
            }

            mHourList.clear();
            for (int i = minHour; i <= maxHour; i++) {
                mHourList.add(mDecimalFormat.format(i));
            }
            mHourPickerView.setDataList(mHourList);

            int selectedHour = getValueInRange(mSelectedTime.get(Calendar.HOUR_OF_DAY), minHour, maxHour);
            mSelectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
            mHourPickerView.setSelected(selectedHour - minHour);
            if (showAnim) {
                mHourPickerView.startAnim();
            }
        }

        mHourPickerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                linkageMinuteList(showAnim);
            }
        }, delay);
    }

    /**
     * 联动“分”变化
     *
     * @param showAnim 是否展示滚动动画
     */
    private void linkageMinuteList(final boolean showAnim) {
        if ((mScrollUnits & SCROLL_UNIT_MINUTE) == SCROLL_UNIT_MINUTE) {
            int minMinute;
            int maxMinute;
            int selectedYear = mSelectedTime.get(Calendar.YEAR);
            int selectedMonth = mSelectedTime.get(Calendar.MONTH) + 1;
            int selectedDay = mSelectedTime.get(Calendar.DAY_OF_MONTH);
            int selectedHour = mSelectedTime.get(Calendar.HOUR_OF_DAY);
            if (mBeginYear == mEndYear && mBeginMonth == mEndMonth && mBeginDay == mEndDay && mBeginHour == mEndHour) {
                minMinute = mBeginMinute;
                maxMinute = mEndMinute;
            } else if (selectedYear == mBeginYear && selectedMonth == mBeginMonth && selectedDay == mBeginDay && selectedHour == mBeginHour) {
                minMinute = mBeginMinute;
                maxMinute = MAX_MINUTE_UNIT;
            } else if (selectedYear == mEndYear && selectedMonth == mEndMonth && selectedDay == mEndDay && selectedHour == mEndHour) {
                minMinute = 0;
                maxMinute = mEndMinute;
            } else {
                minMinute = 0;
                maxMinute = MAX_MINUTE_UNIT;
            }

            mMinuteList.clear();
            for (int i = minMinute; i <= maxMinute; i++) {
                mMinuteList.add(mDecimalFormat.format(i));
            }
            mMinutePickerView.setDataList(mMinuteList);

            int selectedMinute = getValueInRange(mSelectedTime.get(Calendar.MINUTE), minMinute, maxMinute);
            mSelectedTime.set(Calendar.MINUTE, selectedMinute);
            mMinutePickerView.setSelected(selectedMinute - minMinute);
            if (showAnim) {
                mMinutePickerView.startAnim();
            }
        }

        setCanScroll();
    }

    private int getValueInRange(int value, int minValue, int maxValue) {
        if (value < minValue) {
            return minValue;
        } else if (value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }

    private void setCanScroll() {
        mYearPickerView.setCanScroll(mYearList.size() > 1);
        mMonthPickerView.setCanScroll(mMonthList.size() > 1);
        mDayPickerView.setCanScroll(mDayList.size() > 1);
        mHourPickerView.setCanScroll(mHourList.size() > 1 && (mScrollUnits & SCROLL_UNIT_HOUR) == SCROLL_UNIT_HOUR);
        mMinutePickerView.setCanScroll(mMinuteList.size() > 1 && (mScrollUnits & SCROLL_UNIT_MINUTE) == SCROLL_UNIT_MINUTE);
    }

    /**
     * 获取选择的时间毫秒值
     */
    public long getSelectTime() {
        return mSelectedTime.getTimeInMillis();
    }

    /**
     * 设置日期选择器的选中时间
     *
     * @param dateStr  日期字符串 格式为 yyyy-MM-dd 或 yyyy-MM-dd HH:mm
     * @param showAnim 是否展示动画
     * @return 是否设置成功
     */
    public boolean setSelectedTime(String dateStr, boolean showAnim) {
        return !StringUtils.isEmpty(dateStr)
                && setSelectedTime(com.luck.luckcloud.baselibrary.view.picker.DateFormatUtils.str2Long(dateStr, mCanShowPreciseTime), showAnim);
    }

    /**
     * 设置日期选择器的选中时间
     *
     * @param timestamp 毫秒级时间戳
     * @param showAnim  是否展示动画
     * @return 是否设置成功
     */
    public boolean setSelectedTime(long timestamp, boolean showAnim) {
        if (timestamp < mBeginTime.getTimeInMillis()) {
            timestamp = mBeginTime.getTimeInMillis();
        } else if (timestamp > mEndTime.getTimeInMillis()) {
            timestamp = mEndTime.getTimeInMillis();
        }
        mSelectedTime.setTimeInMillis(timestamp);

        mYearList.clear();
        for (int i = mBeginYear; i <= mEndYear; i++) {
            mYearList.add(String.valueOf(i));
        }
        mYearPickerView.setDataList(mYearList);
        mYearPickerView.setSelected(mSelectedTime.get(Calendar.YEAR) - mBeginYear);
        linkageMonthList(showAnim, showAnim ? LINKAGE_DELAY_DEFAULT : 0);
        return true;
    }

    /**
     * 设置日期控件是否显示 日 、时、分
     */
    public void setCanShowPreciseDay(boolean canShowPreciseTime) {
        if (canShowPreciseTime) {
            initScrollUnit();
            mDayPickerView.setVisibility(View.VISIBLE);
            mDayUnitTv.setVisibility(View.VISIBLE);
            mHourPickerView.setVisibility(View.VISIBLE);
            mHourUnitTv.setVisibility(View.VISIBLE);
            mMinutePickerView.setVisibility(View.VISIBLE);
            mMinuteUnitTv.setVisibility(View.VISIBLE);
        } else {
            initScrollUnit(SCROLL_UNIT_HOUR, SCROLL_UNIT_MINUTE);
            mDayPickerView.setVisibility(View.GONE);
            mDayUnitTv.setVisibility(View.GONE);
            mHourPickerView.setVisibility(View.GONE);
            mHourUnitTv.setVisibility(View.GONE);
            mMinutePickerView.setVisibility(View.GONE);
            mMinuteUnitTv.setVisibility(View.GONE);
        }
        mCanShowPreciseTime = canShowPreciseTime;
    }

    /**
     * 设置日期控件是否显示时和分
     */
    public void setCanShowPreciseTime(boolean canShowPreciseTime) {
        if (canShowPreciseTime) {
            initScrollUnit();
            mHourPickerView.setVisibility(View.VISIBLE);
            mHourUnitTv.setVisibility(View.VISIBLE);
            mMinutePickerView.setVisibility(View.VISIBLE);
            mMinuteUnitTv.setVisibility(View.VISIBLE);
        } else {
            initScrollUnit(SCROLL_UNIT_HOUR, SCROLL_UNIT_MINUTE);
            mHourPickerView.setVisibility(View.GONE);
            mHourUnitTv.setVisibility(View.GONE);
            mMinutePickerView.setVisibility(View.GONE);
            mMinuteUnitTv.setVisibility(View.GONE);
        }
        mCanShowPreciseTime = canShowPreciseTime;
    }

    private void initScrollUnit(Integer... units) {
        if (units == null || units.length == 0) {
            mScrollUnits = SCROLL_UNIT_HOUR + SCROLL_UNIT_MINUTE;
        } else {
            for (int unit : units) {
                mScrollUnits ^= unit;
            }
        }
    }

    /**
     * 设置日期控件是否可以循环滚动
     */
    public void setScrollLoop(boolean canLoop) {
        mYearPickerView.setCanScrollLoop(canLoop);
        mMonthPickerView.setCanScrollLoop(canLoop);
        mDayPickerView.setCanScrollLoop(canLoop);
        mHourPickerView.setCanScrollLoop(canLoop);
        mMinutePickerView.setCanScrollLoop(canLoop);
    }

    /**
     * 设置日期控件是否展示滚动动画
     */
    public void setCanShowAnim(boolean canShowAnim) {
        mYearPickerView.setCanShowAnim(canShowAnim);
        mMonthPickerView.setCanShowAnim(canShowAnim);
        mDayPickerView.setCanShowAnim(canShowAnim);
        mHourPickerView.setCanShowAnim(canShowAnim);
        mMinutePickerView.setCanShowAnim(canShowAnim);
    }

    public void setYearUnit(String yearUnit) {
        this.mYearUnit = yearUnit;
        mYearUnitTv.setText(yearUnit);
        mYearUnitTv.setVisibility(StringUtils.isEmpty(yearUnit) ? VISIBLE : GONE);
    }

    public void setMonthUnit(String monthUnit) {
        this.mMonthUnit = monthUnit;
        mMonthUnitTv.setText(monthUnit);
        mMonthUnitTv.setVisibility(StringUtils.isEmpty(monthUnit) ? VISIBLE : GONE);
    }

    public void setDayUnit(String dayUnit) {
        this.mDayUnit = dayUnit;
        mDayUnitTv.setText(dayUnit);
        mDayUnitTv.setVisibility(StringUtils.isEmpty(dayUnit) ? VISIBLE : GONE);
    }

    public void setHourUnit(String hourUnit) {
        this.mHourUnit = hourUnit;
        mHourUnitTv.setText(hourUnit);
        mHourUnitTv.setVisibility(StringUtils.isEmpty(hourUnit) ? VISIBLE : GONE);
    }

    public void setMinuteUnit(String minuteUnit) {
        this.mMinuteUnit = minuteUnit;
        mMinuteUnitTv.setText(minuteUnit);
        mMinuteUnitTv.setVisibility(StringUtils.isEmpty(minuteUnit) ? VISIBLE : GONE);
    }

    /**
     * 销毁弹窗
     */
    public void onDestroy() {
        mYearPickerView.onDestroy();
        mMonthPickerView.onDestroy();
        mDayPickerView.onDestroy();
        mHourPickerView.onDestroy();
        mMinutePickerView.onDestroy();
    }
}
