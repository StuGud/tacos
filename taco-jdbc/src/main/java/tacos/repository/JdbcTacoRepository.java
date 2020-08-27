package tacos.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.entity.Ingredient;
import tacos.entity.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by StuGud on 2020/8/25.
 */

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId=saveTacoInfo(taco);
        taco.setId(tacoId);
        for(Ingredient ingredient:taco.getIngredients()){
            saveIngredientInfo(ingredient,tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco){
        taco.setCreateDate(new Date());
        PreparedStatementCreator psc=
                new PreparedStatementCreatorFactory(
                        "insert into Taco(name,CreateDate) values(?,?)",
                        Types.VARCHAR,Types.TIMESTAMP
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreateDate().getTime())
                        ));
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbc.update(psc,keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientInfo(Ingredient ingredient,long tacoId){
        jdbc.update("insert into Taco_Ingredients (taco,ingredient) values (?,?)",
                tacoId,ingredient.getId());
    }
}
