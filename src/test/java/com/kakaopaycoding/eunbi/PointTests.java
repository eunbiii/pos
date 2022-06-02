package com.kakaopaycoding.eunbi;

import com.kakaopaycoding.eunbi.point.PointMapper;
import com.kakaopaycoding.eunbi.point.PointService;
import com.kakaopaycoding.eunbi.point.PointVo;
import com.kakaopaycoding.eunbi.point.UserVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by eunbi on 2022/06/03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PointTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  PointMapper pointMapper;

    @Autowired
    private PointService pointService;

    /**
     * 포인트 충전
     * 사용자 식별값, 충전금액을 입력 받아 포인트를 충전. (1원=1P)
     */
    @Test
    void pointAddTest() throws Exception {
        mockMvc.perform(post("/point/pointAdd"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void pointAddTest2(){
        List<UserVo> voList = pointMapper.selectUserPoint("eunbi");
        UserVo beforeUser = voList.get(0);
        PointVo vo = PointVo.builder()
                .userId("eunbi")
                .pointTot(1000)
                .description("UnitTest").build();
        vo.setSystemId("UNIT TEST");

        pointService.pointAdd(vo);

        List<UserVo> voList2 = pointMapper.selectUserPoint("eunbi");
        UserVo afterUser = voList2.get(0);

        assertEquals(beforeUser.getPointTot() + 1000, afterUser.getPointTot());
    }

}
