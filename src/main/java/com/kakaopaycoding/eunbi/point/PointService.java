package com.kakaopaycoding.eunbi.point;

import com.kakaopaycoding.eunbi.global.ValidationUtil;
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
    private final ValidationUtil validationUtil;

    /**
     * 사용자포인트적립
     * @param dto
     */
    public void pointAdd(PointDto dto) {
        PointVo vo = PointVo.builder()
                .userId(dto.getUserId())
                .pointTot(dto.getPointTot())
                .description(dto.getDescription()).build();
        vo.setSystemId(dto.getSystemId());

        pointAdd(vo);
    }
    @Transactional(readOnly = true)
    public void pointAdd(PointVo vo) {
        pointMapper.insertPointAdd(vo);
        pointMapper.updateUserPointAdd(vo);
    }
    /**
     * 사용자포인트사용
     * @param dto
     * @throws Exception
     */
    public void pointUse(PointDto dto)  throws Exception  {
        PointVo vo = PointVo.builder()
                .userId(dto.getUserId())
                .orderId(dto.getOrderId())
                .pointTot(dto.getPointTot())
                .description(dto.getDescription()).build();
        vo.setSystemId(dto.getSystemId());
        validationUtil.validateUserPoint(vo.getUserId(), vo.getPointTot());

        pointUse(vo);
    }
    @Transactional(readOnly = true)
    public void pointUse(PointVo vo)  throws Exception {
        pointMapper.insertPointUse(vo);
        pointMapper.updateUserPointUse(vo);
    }

}
