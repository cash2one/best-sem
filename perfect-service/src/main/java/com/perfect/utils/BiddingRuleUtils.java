package com.perfect.utils;

import com.perfect.commons.constants.BiddingStrategyConstants;
import com.perfect.dto.bidding.BiddingRuleDTO;
import com.perfect.dto.bidding.StrategyDTO;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.*;

/**
 * Created by vbzer_000 on 2014/8/31.
 */
public class BiddingRuleUtils {

    public static String getRule(BiddingRuleDTO biddingRuleDTO) {
        StringBuilder sb = new StringBuilder();

        StrategyDTO dto = biddingRuleDTO.getStrategyDTO();

        if (dto == null) {
            return null;
        }

        int positionStrategy = dto.getExpPosition();
        if (positionStrategy == BiddingStrategyConstants.POS_LEFT_1.value()) {
            sb.append("左:[1,");
        } else if (positionStrategy == BiddingStrategyConstants.POS_LEFT_2_3.value()) {
            sb.append("左:[2-3,");
        } else if (positionStrategy == BiddingStrategyConstants.POS_RIGHT_1_3.value()) {
            sb.append("右:[1-3,");
        } else if (positionStrategy == BiddingStrategyConstants.POS_RIGHT_OTHERS.value()) {
            sb.append("右:[").append(dto.getPosition()).append(",");
        }

        sb.append("上限").append(dto.getMaxPrice()).append("元,下限").append(dto.getMinPrice()).append("元]");
        sb.append("(").append((dto.getDevice() == BiddingStrategyConstants.TYPE_PC.value()) ? "计算机" : "移动").append(")");

        //竞价时段
        sb.append("时段:[");
        Integer times[] = dto.getTimes();
        for (int i = 0, l = times.length; i < l; i++) {
            if (i % 2 == 0)
                sb.append(times[i]);
            else {
                if (i + 1 == l)
                    sb.append("-").append(times[i]).append("]");
                else
                    sb.append("-").append(times[i]).append(";");
            }
        }

        return sb.toString();
    }


    public static Date getDateInvMinute(Integer[] times, int interval) {

        StringBuilder sb = new StringBuilder();
        sb.append("0 0");
        if (interval > 0) {
            sb.append("/").append(interval).append(" ");
        }

        for (int i = 0; i < times.length; i++) {
            sb.append(times[i]).append("-").append(times[++i] - 1).append(",");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(" * * ?");

        try {
            CronExpression cronExpression = new CronExpression(sb.toString());
            Date time = cronExpression.getNextValidTimeAfter(Calendar.getInstance().getTime());
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Date getDateInvHour(Integer[] times, int interval) {
        StringBuilder sb = new StringBuilder("0 0 ");

        for (int i = 0; i < times.length; i++) {
            sb.append(times[i]).append("-").append(times[++i] - 1).append(",");
        }

        sb.deleteCharAt(sb.length() - 1).append("/").append(interval / 60);
        sb.append(" * * ?");

        try {
            CronExpression cronExpression = new CronExpression(sb.toString());
            Date time = cronExpression.getNextValidTimeAfter(Calendar.getInstance().getTime());
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean runNow(Integer[] times) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        for (int i = 0; i < times.length; i++) {
            int start = times[i];
            int end = times[++i];

            if (start <= hour && hour <= end) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        boolean now = runNow(new Integer[]{1, 4, 12, 16});
        System.out.println("now = " + now);

        Date longTime = getNextHourTime(new Integer[]{1, 4, 12, 16});
        System.out.println("longTime = " + longTime);

        Date date = getDateInvMinute(new Integer[]{1, 4, 14, 16}, 40);
        System.out.println("date = " + date);

        date = getDateInvHour(new Integer[]{1, 4, 14, 17}, 60);
        System.out.println("date = " + date);
    }

    public static Integer[] clearEndTimes(Integer[] times) {
        List<Integer> arrays = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            arrays.add(times[i++]);
        }

        return arrays.toArray(new Integer[arrays.size()]);
    }

    public static Date getNextHourTime(Integer[] times) {
        try {
            List<Date> dates = new ArrayList<>();
            Date now = Calendar.getInstance().getTime();
            for (int i = 0; i < times.length; i++) {
                CronExpression expression = new CronExpression("0 0 " + times[i++] + " * * ?");
                dates.add(expression.getNextValidTimeAfter(now));
            }

            Collections.sort(dates);

            return dates.get(0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
