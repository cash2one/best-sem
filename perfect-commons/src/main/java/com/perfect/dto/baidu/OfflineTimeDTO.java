package com.perfect.dto.baidu;

import java.util.Date;

/**
 * Created by yousheng on 14/11/20.
 */
public class OfflineTimeDTO {
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //OfflineTimeType Attributes
    private Integer flag;
    private Date time;

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setFlag(Integer aFlag)
    {
        boolean wasSet = false;
        flag = aFlag;
        wasSet = true;
        return wasSet;
    }

    public boolean setTime(Date aTime)
    {
        boolean wasSet = false;
        time = aTime;
        wasSet = true;
        return wasSet;
    }

    public Integer getFlag()
    {
        return flag;
    }

    public Date getTime()
    {
        return time;
    }

    public void delete()
    {}


    public String toString()
    {
        String outputString = "";
        return super.toString() + "["+
                "flag" + ":" + getFlag()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "time" + "=" + (getTime() != null ? !getTime().equals(this)  ? getTime().toString().replaceAll("  ","    ") : "this" : "null")
                + outputString;
    }
}
