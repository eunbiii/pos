package com.kakaopaycoding.eunbi.point;

import com.kakaopaycoding.eunbi.global.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

/**
 * Created by eunbi on 2022/05/31
 */
@Slf4j
@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping(value = "/getUserPoint")
    @Description("사용자포인트조회")
    public ResponseDto<Object> getMenuAllList(){
        return ResponseDto.builder()
                .code("ok").build();
    }

    @PostMapping (value = "/pointAdd")
    @Description("사용자포인트적립")
    public ResponseDto<Object> addPoint(@RequestBody PointDto dto){
        pointService.pointAdd(dto);
        return ResponseDto.builder()
                .code("ok").build();
    }

    @PostMapping (value = "/pointUse")
    @Description("사용자포인트사용")
    public ResponseDto<Object> usePoint(@RequestBody PointDto dto){
        try{
            PointVo vo = PointVo.builder()
                    .userId(dto.getUserId())
                    .orderId(dto.getOrderId())
                    .pointTot(dto.getPointTot())
                    .description(dto.getDescription()).build();
            vo.setSystemId(dto.getSystemId());

            pointService.pointUse(vo);
        }catch (Exception e){
            return ResponseDto.builder()
                    .code("error")
                    .message(e.getMessage())
                    .build();
        }
        return ResponseDto.builder()
                .code("ok").build();
    }


}
