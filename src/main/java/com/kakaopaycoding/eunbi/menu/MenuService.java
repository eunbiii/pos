package com.kakaopaycoding.eunbi.menu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by eunbi on 2022/05/31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuMapper menuMapper;

    /**
     * 모든 메뉴 목록 조회
     * @return List<MenuVo> 메 목록
     */
    @Transactional(readOnly = true)
    public List<MenuVo> getMenuAllList(){
        return menuMapper.selectMenuAll();
    }

    /**
     * 모든 메뉴 목록 조회
     * @return List<MenuVo> 카테고리 목록
     */
    @Transactional(readOnly = true)
    public List<MenuVo> getMenuTopList(){
        return menuMapper.selectMenuTopWeekly();
    }
}
