package com.kakaopaycoding.eunbi.order;

import com.kakaopaycoding.eunbi.global.ResponseDto;
import com.kakaopaycoding.eunbi.vo.OrderVo;
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
            return ResponseDto.builder()
                    .code("error")
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping(value = "/refundOrder")
    @Description("메뉴환불")
    public ResponseDto<Object> refundOrder(@RequestBody OrderDto dto){
       // orderService.doOrder(dto);
        return ResponseDto.builder()
                .code("")
                .message("")
                .result("").build();
    }
}
