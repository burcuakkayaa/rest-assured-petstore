package implementation;

import lombok.*;
import org.apache.juneau.annotation.Beanc;


@Setter
@Getter
@ToString
public class Category {

    private int id;
    private String name;


    @Beanc(properties="id,name")
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
