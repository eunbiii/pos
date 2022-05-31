package com.kakaopaycoding.eunbi.menu;

import com.kakaopaycoding.eunbi.global.ResponseDto;
import com.kakaopaycoding.eunbi.vo.MenuVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eunbi on 2022/05/31
 */
@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping(value = "/getMenuAllList")
    @Description("전체메뉴조회")
    public ResponseDto<Object> getMenuAllList(MenuVo dto){
        return ResponseDto.builder()
                .code("ok")
                .result(menuService.getMenuAllList()).build();
    }

    @GetMapping(value = "/getMenuTopList")
    @Description("전체메뉴조회")
    public ResponseDto<Object> getMenuTopList(MenuVo dto){
        return ResponseDto.builder()
                .code("ok")
                .result(menuService.getMenuAllList()).build();
    }

}
