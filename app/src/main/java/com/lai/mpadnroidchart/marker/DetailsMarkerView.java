package com.lai.mpadnroidchart.marker;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.lai.mpadnroidchart.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lai
 * @time 2018/5/13 17:32
 * @describe describe
 */

public class DetailsMarkerView extends MarkerView {

    private TextView mTvMonth;
    private TextView mTvChart1;


    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public DetailsMarkerView(Context context) {
        super(context, R.layout.item_chart_des_marker_item_3);
        mTvMonth = findViewById(R.id.tv_chart_month);
        mTvChart1 = findViewById(R.id.tv_chart_1);
    }


    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        try {
            //收入
            if (e.getY() == 0) {
                mTvChart1.setText("暂无数据");
            } else {
//                mTvChart1.setText(concat(e.getY(), "支出："));
                mTvChart1.setText(String.valueOf(e.getY()));
            }
//            mTvMonth.setText(String.valueOf((int) e.getX() + 1).concat("月"));

            List<String> list = new ArrayList<>();
            list.add("周一");
            list.add("周二");
            list.add("周三");
            list.add("周四");
            list.add("周五");
            list.add("周六");
            list.add("周日");
//            mTvMonth.setText(list.get((int)e.getX()));
            mTvMonth.setText(format(e.getX()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        super.refreshContent(e, highlight);
    }


    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }


    public String concat(float money, String values) {
        return values + new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "元";
    }



    //时间格式化（显示今日往前7天的每一天日期）
    public String  format(float x)
    {
        CharSequence format = DateFormat.format("MM-dd",
                System.currentTimeMillis()-(long) (6-(int)x)*24*60*60*1000);
        return format.toString();
    }

}
