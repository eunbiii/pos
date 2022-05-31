package com.kakaopaycoding.eunbi.point;

import com.kakaopaycoding.eunbi.vo.PointVo;
import com.kakaopaycoding.eunbi.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * Created by eunbi on 2022/05/31
 */
@Mapper
public interface PointMapper {
    void insertPointAdd(PointVo vo);
    void insertPointUse(PointVo vo);
    void updateUserPointAdd(PointVo vo);
    void updateUserPointUse(PointVo vo);


    List<UserVo> selectUserPoint(String userId);

}
