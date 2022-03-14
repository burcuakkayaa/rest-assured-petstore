package implementation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.juneau.annotation.Beanc;


@Getter
@Setter
@ToString
public class Tag {

    private int id;
    private String name;


    @Beanc(properties="id,name")
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
