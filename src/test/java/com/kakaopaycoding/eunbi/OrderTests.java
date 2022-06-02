package com.kakaopaycoding.eunbi;

import com.kakaopaycoding.eunbi.order.OrderDtlVo;
import com.kakaopaycoding.eunbi.order.OrderDto;
import com.kakaopaycoding.eunbi.order.OrderMapper;
import com.kakaopaycoding.eunbi.order.OrderService;
import com.kakaopaycoding.eunbi.point.PointMapper;
import com.kakaopaycoding.eunbi.point.PointService;
import com.kakaopaycoding.eunbi.point.PointVo;
import com.kakaopaycoding.eunbi.point.UserVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by eunbi on 2022/06/03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  PointMapper pointMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    /**
     * 	사용자 식별값, 메뉴ID를 입력 받아 주문을 하고 결제를 진행
     * 	결제는 포인트로만 가능하며, 충전한 포인트에서 주문금액을 차감
     * */
    @Test
    void doOrderTest() throws Exception {
        List<UserVo> voList = pointMapper.selectUserPoint("eunbi");
        UserVo beforeUser = voList.get(0);

        OrderDto dto = new OrderDto();
        dto.setUserId("eunbi");
        dto.setOrderPrice(1000);
        dto.setSystemId("UNIT TEST");

        List<OrderDtlVo> dtlList = new ArrayList<>();
        dtlList.add(OrderDtlVo.builder().menuId("1").menuPrice(500).orderCnt(1).build());
        dtlList.add(OrderDtlVo.builder().menuId("3").menuPrice(500).orderCnt(1).build());
        dto.setOrderDtlList(dtlList);

        orderService.doOrder(dto);
        List<UserVo> voList2 = pointMapper.selectUserPoint("eunbi");
        UserVo afterUser = voList2.get(0);

        assertEquals(beforeUser.getPointTot() - 1000, afterUser.getPointTot());
    }
}
