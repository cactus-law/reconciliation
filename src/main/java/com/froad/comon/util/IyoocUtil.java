package com.froad.comon.util;

public interface IyoocUtil {
    /**
     * 补足String长度
     *
     * @param str 需要补足长度的字符串
     * @return 补足 后字符串
     * @throws Exception
     */
    public String addStr(String str) throws Exception;

    /**
     * 补足String长度
     *
     * @param str    原字符串
     * @param length 总长度
     * @return
     * @throws Exception
     */
    public String addStr(String str, int length) throws Exception;

}
