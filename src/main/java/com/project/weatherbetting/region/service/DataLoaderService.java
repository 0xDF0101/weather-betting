package com.project.weatherbetting.region.service;

import com.project.weatherbetting.region.dto.RegionParsingResult;
import com.project.weatherbetting.region.parser.RegionParser;
import com.project.weatherbetting.region.repository.DataLoaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataLoaderService {

    private final DataLoaderRepository dataLoaderRepository;
    private final RegionParser parser;
    private final String REGION_DATA_FILE_PATH = "data/region_coordinate.csv";
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void loadIfEmpty() {

        // 데이터가 이미 들어간 경우 파싱 안함
        if(dataLoaderRepository.count()>0) return;

        List<RegionParsingResult> regions;
        try {
            regions = parse();
            batchInsert(regions);
        } catch(IOException e) {
            throw new RuntimeException("Region 데이터 로딩 실패 : " + e);
        }

    }
    // parsing
    private List<RegionParsingResult> parse() throws IOException {
        ClassPathResource resource = new ClassPathResource(REGION_DATA_FILE_PATH);
        File file = resource.getFile();  // 여기서 IOException 발생 가능
        return parser.parse(file);
    }

    /**
     *  Region은 id가 Auto Increment이기 때문에 DB에 한번 저장되어야 id가 발급됨
     *  고로 saveAll()하면 성능이 떨어짐
     *  그래서 jdbcTemplate 사용해서 100개씩 꽂아넣음
     */

    private void batchInsert(List<RegionParsingResult> results) {
        String sql = """
                   INSERT INTO regions (region_code, city, district, dong, nx, ny, latitude, longitude)
                   VALUES(?, ?, ?, ?, ?, ?, ?, ?) 
                """;
        jdbcTemplate.batchUpdate(sql, results, 100, (ps, r) -> {
            ps.setString(1, r.regionCode());
            ps.setString(2, r.city());
            ps.setString(3, r.district());
            ps.setString(4, r.dong());
            ps.setInt(5, r.nx());
            ps.setInt(6, r.ny());
            ps.setBigDecimal(7, r.latitude());
            ps.setBigDecimal(8, r.longitude());
        });

    }
}
