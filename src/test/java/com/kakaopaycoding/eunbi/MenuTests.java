package com.kakaopaycoding.eunbi;

import com.kakaopaycoding.eunbi.menu.MenuService;
import com.kakaopaycoding.eunbi.menu.MenuVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by eunbi on 2022/06/03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MenuTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MenuService menuService;

    /**
     * 커피 정보(메뉴ID, 이름, 가격)을 조회
     */
    @Test
    void getMenuAllListTest() throws Exception {
        mockMvc.perform(get("/menu/getMenuAllList"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 인기메뉴 목록 조회 API
     * 최근 7일간 인기있는 메뉴 3개를 조회하는 API 작성
     * 메뉴별 주문 횟수가 정확해야 함
     */
    @Test
    void getMenuTopListTest() throws Exception  {
        mockMvc.perform(get("/menu/getMenuTopList"))
                .andExpect(status().isOk())

                .andDo(print());
    }

    @Test
    void getMenuTopListTest2(){
        List<MenuVo> list = menuService.getMenuTopList();
        assertEquals(list.size(), 3);
    }

}
