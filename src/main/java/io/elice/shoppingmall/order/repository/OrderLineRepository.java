package io.elice.shoppingmall.order.repository;

import io.elice.shoppingmall.order.entity.OrderLine;
import io.elice.shoppingmall.order.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    // 주문 ID에 따라 주문 라인을 찾는 메소드
    List<OrderLine> findByOrdersId(Long orderId);

}

