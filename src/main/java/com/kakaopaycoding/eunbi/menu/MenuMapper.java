package com.kakaopaycoding.eunbi.menu;

import com.kakaopaycoding.eunbi.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * Created by eunbi on 2022/05/31
 */
@Mapper
public interface MenuMapper {
    public List<MenuVo> selectMenuAll();

    public List<MenuVo> selectMenuTopMonthly();
    public List<MenuVo> selectMenuTopYearly();

    public List<MenuVo> selectMenuTopWeekly();

    public List<MenuVo> selectMenuTopDaily();
}
