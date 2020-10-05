//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mycpu.utils;

import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.TimeZone;


@SuppressWarnings("all")
public class StringUtils {
    private static final Log LOG = LogFactory.getLog(StringUtils.class);

    public StringUtils() {
    }

    public static Integer subStringToInteger(String src, String start, String to) {
        return stringToInteger(subString(src, start, to));
    }

    public static String subString(String src, String start, String to) {
        int indexFrom = start == null ? 0 : src.indexOf(start);
        int indexTo = to == null ? src.length() : src.indexOf(to);
        if (indexFrom >= 0 && indexTo >= 0 && indexFrom <= indexTo) {
            if (null != start) {
                indexFrom += start.length();
            }

            return src.substring(indexFrom, indexTo);
        } else {
            return null;
        }
    }

    public static String subString(String src, String start, String to, boolean toLast) {
        if (!toLast) {
            return subString(src, start, to);
        } else {
            int indexFrom = start == null ? 0 : src.indexOf(start);
            int indexTo = to == null ? src.length() : src.lastIndexOf(to);
            if (indexFrom >= 0 && indexTo >= 0 && indexFrom <= indexTo) {
                if (null != start) {
                    indexFrom += start.length();
                }

                return src.substring(indexFrom, indexTo);
            } else {
                return null;
            }
        }
    }

    public static Integer stringToInteger(String in) {
        if (in == null) {
            return null;
        } else {
            in = in.trim();
            if (in.length() == 0) {
                return null;
            } else {
                try {
                    return Integer.parseInt(in);
                } catch (NumberFormatException var2) {
                    LOG.warn("stringToInteger fail,string=" + in, var2);
                    return null;
                }
            }
        }
    }

    public static boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equalsIgnoreCase(b);
        }
    }

    public static boolean isEmpty(String value) {
        return isEmpty((CharSequence)value);
    }

    public static boolean isEmpty(CharSequence value) {
        return value == null || value.length() == 0;
    }

    public static int lowerHashCode(String text) {
        if (text == null) {
            return 0;
        } else {
            int h = 0;

            for(int i = 0; i < text.length(); ++i) {
                char ch = text.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    ch = (char)(ch + 32);
                }

                h = 31 * h + ch;
            }

            return h;
        }
    }

    public static boolean isNumber(String str) {
        if (str.length() == 0) {
            return false;
        } else {
            int sz = str.length();
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = str.charAt(0) == '-' ? 1 : 0;
            int i;
            char ch;
            if (sz > start + 1 && str.charAt(start) == '0' && str.charAt(start + 1) == 'x') {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while(i < str.length()) {
                        ch = str.charAt(i);
                        if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f') && (ch < 'A' || ch > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for(i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    ch = str.charAt(i);
                    if (ch >= '0' && ch <= '9') {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (ch == '.') {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (ch != '+' && ch != '-') {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                if (i < str.length()) {
                    ch = str.charAt(i);
                    if (ch >= '0' && ch <= '9') {
                        return true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (!allowSigns && (ch == 'd' || ch == 'D' || ch == 'f' || ch == 'F')) {
                            return foundDigit;
                        } else if (ch != 'l' && ch != 'L') {
                            return false;
                        } else {
                            return foundDigit && !hasExp;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return !allowSigns && foundDigit;
                }
            }
        }
    }

    public static boolean isNumber(char[] chars) {
        if (chars.length == 0) {
            return false;
        } else {
            int sz = chars.length;
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = chars[0] == '-' ? 1 : 0;
            int i;
            char ch;
            if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while(i < chars.length) {
                        ch = chars[i];
                        if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f') && (ch < 'A' || ch > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for(i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    ch = chars[i];
                    if (ch >= '0' && ch <= '9') {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (ch == '.') {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (ch != '+' && ch != '-') {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                if (i < chars.length) {
                    ch = chars[i];
                    if (ch >= '0' && ch <= '9') {
                        return true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (!allowSigns && (ch == 'd' || ch == 'D' || ch == 'f' || ch == 'F')) {
                            return foundDigit;
                        } else if (ch != 'l' && ch != 'L') {
                            return false;
                        } else {
                            return foundDigit && !hasExp;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return !allowSigns && foundDigit;
                }
            }
        }
    }

    public static String formatDateTime19(long millis, TimeZone timeZone) {
        Calendar cale = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        cale.setTimeInMillis(millis);
        int year = cale.get(1);
        int month = cale.get(2) + 1;
        int dayOfMonth = cale.get(5);
        int hour = cale.get(11);
        int minute = cale.get(12);
        int second = cale.get(13);
        char[] chars = new char[]{(char)(year / 1000 + 48), (char)(year / 100 % 10 + 48), (char)(year / 10 % 10 + 48), (char)(year % 10 + 48), '-', (char)(month / 10 + 48), (char)(month % 10 + 48), '-', (char)(dayOfMonth / 10 + 48), (char)(dayOfMonth % 10 + 48), ' ', (char)(hour / 10 + 48), (char)(hour % 10 + 48), ':', (char)(minute / 10 + 48), (char)(minute % 10 + 48), ':', (char)(second / 10 + 48), (char)(second % 10 + 48)};
        return new String(chars);
    }
}
