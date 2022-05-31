package com.kakaopaycoding.eunbi.point;

import com.kakaopaycoding.eunbi.vo.PointVo;
import com.kakaopaycoding.eunbi.vo.UserVo;
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
public class PointService {
    private final PointMapper pointMapper;

    /**
     * 사용자포인트적립
     */
    @Transactional(readOnly = true)
    public void pointAdd(PointDto dto) {
        PointVo vo = PointVo.builder()
                .userId(dto.getUserId())
                .pointTot(dto.getPointTot())
                .description(dto.getDescription()).build();
        vo.setSystemId(dto.getSystemId());

        pointMapper.insertPointAdd(vo);
        pointMapper.updateUserPointAdd(vo);
    }

    /**
     * 사용자포인트사용
     */
    @Transactional(readOnly = true)
    public void pointUse(PointDto dto)  throws Exception  {
        PointVo vo = PointVo.builder()
                .userId(dto.getUserId())
                .orderId(dto.getOrderId())
                .pointTot(dto.getPointTot())
                .description(dto.getDescription()).build();
        vo.setSystemId(dto.getSystemId());

        pointUse(vo);
    }
    public void pointUse(PointVo vo)  throws Exception {
        checkUserPoint(vo.getUserId(), vo.getPointTot());
        pointMapper.insertPointUse(vo);
        pointMapper.updateUserPointUse(vo);
    }
    // 2. 고객 정보확인 (포인트)
    public boolean checkUserPoint(String userId, int updatePrice)  throws Exception {
        List<UserVo> userList = pointMapper.selectUserPoint(userId);
        if(userList ==null || userList.size()<1){
            //사용자가 존재하지 않습니다.
            throw new Exception("사용자가 존재하지 않습니다.");
        }
        UserVo userVo = userList.get(0);
        if(userVo.getPointTot() < updatePrice){
            //사용가능한 포인트가 부족합니다.
            throw new Exception("사용가능한 포인트가 부족합니다.");
        }
        return true;
    }

}
