package com.example.springbootdemo.test;

import com.example.springbootdemo.entity.User;

import java.util.Objects;

public class Aa {
    public static void main(String[] args) {
        User user = User.builder().build();
        ageProcess(user);
        System.out.println(user.getAge());
        user.setAge(-1);
        ageProcess(user);
        System.out.println(user.getAge());
        user.setAge(0);
        ageProcess(user);
        System.out.println(user.getAge());


//        List<String> allDcCodes = Arrays.asList("BSDC001","CKQY0607002(作废)1654683870788","CKQY0608001","CSCKQY22(作废)1654590527804","CSTPC2021","CSXNC2021","DD99","DDCF000001","HBBJ02","HBBJ03","HBBJ04","HBBJ90","HBBJ91","HBBJ92","HBJN01","HBQD01","HBSY01","HBSY02","HDHZ01","HDHZ02","HDSH02","HDSH03","HDSH90","HDSH91","HDSH92","HDSH93","HDSH95","HNDG01","HNGF01","HNSZ01","HNSZ02","HNSZ03","HNSZ04","HNSZ05","HNSZ09","HNSZ10","HNSZ11","HNSZ91","HNSZ92","HNXM01","HY20220225","HYA20220225","HZWH01","HZWH02","HZWH03","HZWH91","HZWH92","HaiYan0315","LF042701","LWZPC","NXCEWJK001(作废)1644822675759","NXHDWX0006","NXXNCD0008","PDBHC01","PDCK01","PDGYS99","PDSF01","PDSF02","TDC001-L","VDC001(作废)1645779471400","WANWEI01","WWJY98","WWSZ98","WWYL99","XBXA01","XBXA02","XBXA03","XBXA90","XBXA91","XBXA92","XNCD01","XNCD03","XNCQ01","XNCQ02","XNCQ93","XNCQ94","ceshi001","ceshi220428","zyl_1819_test_123");
//        List<String> userDcCodes = Arrays.asList("HBBJ02","HBBJ91","HBBJ92","HDSH02","HDSH91","HDSH92","HNSZ01","HNSZ02","HNSZ03","HNSZ04","HNSZ05","HNSZ91","HNSZ92","HZWH01","HZWH02","HZWH91","HZWH92","XBXA01","XBXA02","XBXA91","XBXA92","HDSH90","HBBJ90","HDSH93","HNDG01","WWYL99","WWJY98","WWSZ98","HNSZ09","PDBHC01","HBBJ03","XNCD01","HBJN01","HBQD01","XNCQ93","XNCQ94","PDGYS99","HBSY01","HNSZ10","HBSY02","DD99","HNGF01","LWZPC","HNXM01","CSTPC2021","CSXNC2021","HDSH95","HBBJ04","HNSZ11","HDSH03","XBXA03","XNCD03","HDHZ01","HDHZ02","HZWH03","NXHDWX0006","PDSF02","PDSF01","NXXNCD0008","PDCK01","XBXA90","XNCQ02","DDCF000001","NXCEWJK001(作废)1644822675759","VDC001(作废)1645779471400","BSDC001","HY20220225","HYA20220225","TDC001-L","HaiYan0315","ceshi001","zyl_1819_test_123","LF042701","ceshi220428","WANWEI01","XNCQ01","CSCKQY22(作废)1654590527804","CKQY0608001");
//        Sets.SetView<String> difference = Sets.difference(new HashSet<>(allDcCodes), new HashSet<>(userDcCodes));
//        difference.forEach(v -> System.out.println(v));
//        System.out.println(difference);

//        Sets.SetView<Integer> difference1 = Sets.difference(new HashSet<>(Arrays.asList(1, 2, 3)), new HashSet<>(Arrays.asList(2, 3, 4)));
//        System.out.println(difference1)
//        ;

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

    public static void ageProcess(User user) {
        boolean b = Objects.nonNull(user.getAge()) && user.getAge().equals(-1);
        user.setAge(b ? null : user.getAge());
    }
}
