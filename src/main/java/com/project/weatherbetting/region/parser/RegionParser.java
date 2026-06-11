package com.project.weatherbetting.region.parser;

import com.project.weatherbetting.region.dto.RegionParsingResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RegionParser {

    public List<RegionParsingResult> parse(File file) throws FileNotFoundException {

        List<RegionParsingResult> regionParsingResults = new ArrayList<>();
        // -> LinkedList보다 어지간하면 더 빠르다고 하는데 이유는 잘 모르겠음

        /**
         * 구분,행정구역코드,1단계,2단계,3단계,격자 X,격자 Y,경도(시),경도(분),경도(초),위도(시),위도(분),위도(초),경도(초/100),위도(초/100),위치업데이트,
         * kor,1100000000,서울특별시,,,60,127,126,58,48.03,37,33,48.85,126.980008333333,37.5635694444444,,
         * kor,1111000000,서울특별시,종로구,,60,127,126,58,53.91,37,34,13.36,126.981641666666,37.5703777777777,,
         */


        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {

            reader.readLine(); // 첫줄 넘기기
            while((line = reader.readLine()) != null) {
                String[] token = line.split(",");

                String regionCode = token[1].trim();
                String city = token[2].trim();
                String district = token[3].trim();
                String dong = token[4].trim();
                int nx = Integer.parseInt(token[5].trim());
                int ny = Integer.parseInt(token[6].trim());
                BigDecimal latitude = new BigDecimal(token[13].trim());
                BigDecimal longitude = new BigDecimal(token[14].trim());

                regionParsingResults.add(new RegionParsingResult(
                        regionCode,
                        city,
                        district,
                        dong,
                        nx,
                        ny,
                        latitude,
                        longitude
                ));
            }
        } catch(IOException e) {
            throw new FileNotFoundException("File Not Found : " + file.getName());
        }
        log.info("=== Region 데이터 파싱 완료 ===");
        return regionParsingResults;
    }

}
