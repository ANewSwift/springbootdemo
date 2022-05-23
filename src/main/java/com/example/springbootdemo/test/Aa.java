package com.example.springbootdemo.test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;
import java.util.stream.Collectors;

public class Aa {
    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("a","b"));
        lists.add(Arrays.asList("c","d"));
//        lists.stream().flatMap(list -> list.addAll());
        List<String> collect = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);


//    public static void main(String[] args) {
////        List<BigDecimal> bigDecimals = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(3), BigDecimal.valueOf(5));
////        BigDecimal reduce = bigDecimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
////        System.out.println(BigDecimal.ZERO.compareTo(reduce));
//        List<Integer> list1 = Arrays.asList(1, 2, 3);
//        List<Integer> list2 = Arrays.asList(1, 2, 3);
//        List<Integer> list3 = Arrays.asList(2, 1, 3);
//
//        ;
//        System.out.println(CollectionUtils.isEqualCollection(list1, list2));
//        System.out.println(CollectionUtils.isEqualCollection(list1, list3));
    }
}
