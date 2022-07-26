package com.framework.cloud.common.utils;

import com.framework.cloud.common.weight.WeightMeta;

import java.util.Map;

/**
 * 权重工具类
 *
 * @author wusiwei
 */
public class WeightUtil {

    public static <T> WeightMeta<T> randomWeightMeta(final Map<T, Integer> weightMap) {
        if (weightMap.isEmpty()) {
            return null;
        }
        final int size = weightMap.size();
        Object[] nodes = new Object[size];
        int[] weights = new int[size];
        int index = 0;
        int weightAdder = 0;
        for (Map.Entry<T, Integer> each : weightMap.entrySet()) {
            nodes[index] = each.getKey();
            weights[index++] = (weightAdder = weightAdder + each.getValue());
        }
        return new WeightMeta<T>((T[]) nodes, weights);
    }

}
