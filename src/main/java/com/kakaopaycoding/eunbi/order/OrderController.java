package com.kakaopaycoding.eunbi.order;

import com.kakaopaycoding.eunbi.global.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eunbi on 2022/05/31
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    /**
     * 메뉴 주문
     * @param dto
     * @return 주문정
     */
    @PostMapping(value = "/doOrder")
    @Description("메뉴주문")
    public ResponseDto<Object> doOrder(@RequestBody OrderDto dto){
        try{
            OrderVo vo = orderService.doOrder(dto);
            return ResponseDto.builder()
                    .code("ok")
                    .message("주문이 완료되었습니다.")
                    .result(vo).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.builder()
                    .code("error")
                    .message(e.getMessage())
                    .build();
        }
    }

    /**
     * 메뉴 환불
     * @param dto
     * @return 환불한 주문정보
     */
    @PostMapping(value = "/refundOrder")
    @Description("메뉴환불")
    public ResponseDto<Object> refundOrder(@RequestBody OrderDto dto){
        try {
            OrderVo vo = orderService.refundOrder(dto);
            return ResponseDto.builder()
                    .code("ok")
                    .message("환불이 완료되었습니다.")
                    .result(vo).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.builder()
                    .code("error")
                    .message(e.getMessage())
                    .build();
        }
    }
}
