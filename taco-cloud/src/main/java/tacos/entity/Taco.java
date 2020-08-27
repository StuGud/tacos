package tacos.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Created by StuGud on 2020/8/24.
 */
@Data
public class Taco {

    //id,createDate以适应持久化
    private long id;
    private Date createDate;

    @NotNull
    @Size(min=5,message = "Name must be at least 5 characters long")
    private String name;

    @Size(min=1,message = "You must choose at least 1 ingredient")
    private List<String> ingredients;
}

