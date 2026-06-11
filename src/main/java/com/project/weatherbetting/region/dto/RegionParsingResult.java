package com.project.weatherbetting.region.dto;

import java.math.BigDecimal;

public record RegionParsingResult(

        /**
         * 구분,행정구역코드,1단계,2단계,3단계,격자 X,격자 Y,경도(시),경도(분),경도(초),위도(시),위도(분),위도(초),경도(초/100),위도(초/100),위치업데이트,
         * kor,1100000000,서울특별시,,,60,127,126,58,48.03,37,33,48.85,126.980008333333,37.5635694444444,,
         * kor,1111000000,서울특별시,종로구,,60,127,126,58,53.91,37,34,13.36,126.981641666666,37.5703777777777,,
         */

        String regionCode,
        String city,
        String district,
        String dong,
        int nx,
        int ny,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
