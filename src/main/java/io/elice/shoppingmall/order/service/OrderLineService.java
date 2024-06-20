package io.elice.shoppingmall.order.service;

import io.elice.shoppingmall.order.DTO.OrderLineDTO;
import io.elice.shoppingmall.order.entity.OrderLine;
import io.elice.shoppingmall.order.entity.Orders;
import io.elice.shoppingmall.order.mapper.OrderMapper;
import io.elice.shoppingmall.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderLine createOrderLine(OrderLineDTO orderLineDTO){
        OrderLine orderLine = OrderMapper.INSTANCE.toOrderLineEntity(orderLineDTO);
        return orderLineRepository.save(orderLine);
    }

    public List<OrderLineDTO> getOrderLineByOrderId(Long orderId) { //주문 상세 조회
        List<OrderLine> orderLines = orderLineRepository.findByOrders_Id(orderId);
        return orderLines.stream()
                .map(orderMapper::toOrderLineDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderLine updateOrderLine(Long orderId, OrderLineDTO orderLineDTO) {
        OrderLine orderLine = orderLineRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("OrderLine not found"));
        orderMapper.updateOrderLineFromDTO(orderLineDTO, orderLine);
        return orderLineRepository.save(orderLine);
    }
}
