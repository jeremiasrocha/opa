package br.com.opa.util;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class UtilNullEmpty {

	public static boolean isNull(Object value) {
		return value == null;
    }

    public static boolean isNullOrEmpty(String value) {
        return (value == null) || (value.trim().length() == 0);
    }

    public static boolean isNullOrEmpty(Object value) {
        return (value == null || value.equals(""));
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return (collection == null) || (collection.isEmpty());
    }

    public static boolean isNullOrEmpty(Number number) {
        return (number == null) || (!(number.doubleValue() > 0));
    }

    public static boolean isNullOrEmpty(Date data) {
        return (data == null) || (!(data.getTime() > 0));
    }

    public static <T> boolean isNullOrEmpty(Map<T, T> map) {
        return (map == null) || (map.isEmpty());
    }

    public static boolean isNullOrEmpty(File file) {
        return isNull(file) || file.length() == 0;
    }

    public static boolean isNullOrEmpty(Object[] array) {
        return (array == null) || (array.length == 0);
    }

	public static String getEmptyValue() {
		return "";
	}

}