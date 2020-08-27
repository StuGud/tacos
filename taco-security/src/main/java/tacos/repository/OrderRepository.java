package tacos.repository;

import tacos.entity.Order;

/**
 * Created by StuGud on 2020/8/25.
 */

public interface OrderRepository {
    Order save(Order order);
}
