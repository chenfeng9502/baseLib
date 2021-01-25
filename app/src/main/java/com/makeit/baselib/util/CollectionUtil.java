/*
 * Copyright ©  2017-present  美契.  All Rights Reserved.
 *
 * 美契信息公司 版权所有
 */

package com.makeit.baselib.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by chenfeng on 2017/5/26.
 *
 * 用途：集合工具类
 */

public class CollectionUtil {


    private CollectionUtil() {

    }

    public static void clear(Collection<?> collection) {
        if (collection != null) {
            collection.clear();
        }
    }

    /**
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * @param array
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * @param collection
     * @param dest
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Collection<R>, R> T clone(T collection, T dest) {
        try {
            if (dest == null) {
                dest = (T) collection.getClass().newInstance();
            }
            if (!isEmpty(collection)) {
                for (R item : collection) {
                    dest.add(item);
                }
            }
            return dest;
        } catch (Exception e) {
            return dest;
        }
    }


    /**
     * @param collection
     * @return
     */
    public static int getSize(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * @param dest
     * @param src
     * @return
     */
    public static <T> Collection<T> addAll(Collection<T> dest, Collection<? extends T> src) {
        if (dest == null || isEmpty(src)) {
            return dest;
        } else {
            dest.addAll(src);
            return dest;
        }
    }

    /**
     * @param dest
     * @param src
     * @return
     */
    public static <T> Collection<T> fill(Collection<T> dest, Collection<? extends T> src) {
        if (dest == null) {
            return null;
        } else {
            dest.clear();
            addAll(dest, src);
            return dest;
        }
    }
}
