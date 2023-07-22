package com.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CollectionUtils
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public class CollectionUtils {
    public static <T, K, V> Map<K, V> listToMap(
            Collection<T> collection,
            Function<? super T, ? extends K> keyMapper,
            Function<? super T, ? extends V> valueMapper
    ) {
        return collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T, R> List<R> mapToList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        return collection.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

}
