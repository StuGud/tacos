package tacos.repository;

import tacos.entity.Ingredient;

/**
 * Created by StuGud on 2020/8/25.
 */

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
