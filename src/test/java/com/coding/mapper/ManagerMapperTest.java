package com.coding.mapper;


import com.coding.domain.Airdetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ManagerMapperTest {
    @Autowired
    private AirdetailMapper airdetailMapper;

    @Test
    public void selectTest() {
        /*模糊查询*/

        String key="一架";
        Example example = Example.builder(Airdetail.class)
                .where(WeekendSqls.<Airdetail>custom()
                        .andLike(Airdetail::getAirhow, "%" + key + "%")
                )
                .build();

        List<Airdetail> resultList = airdetailMapper.selectByExample(example);

        for (Airdetail airdetail : resultList) {
            System.out.println(airdetail);

        }

    }

}