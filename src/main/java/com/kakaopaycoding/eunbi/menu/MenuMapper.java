package com.kakaopaycoding.eunbi.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * Created by eunbi on 2022/05/31
 */
@Mapper
public interface MenuMapper {
    List<MenuVo> selectMenuAll();
    List<MenuVo> selectMenuTopWeekly();
}
