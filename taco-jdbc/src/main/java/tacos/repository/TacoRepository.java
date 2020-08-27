package tacos.repository;

import tacos.entity.Taco;

/**
 * Created by StuGud on 2020/8/25.
 */

public interface TacoRepository {
    Taco save(Taco taco);
}
