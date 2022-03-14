package models.response;

import implementation.Category;
import implementation.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.juneau.annotation.Bean;
import org.apache.juneau.annotation.BeanConfig;
import org.apache.juneau.annotation.BeanIgnore;
import org.apache.juneau.annotation.Beanc;

import java.util.List;

@Getter
@Setter
@ToString
public class CreatePetResponse {

    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag>  tags;
    private String status;

    @Beanc(properties = "category,id,name,photoUrls,status,tags")
    public CreatePetResponse(Category category, int id, String name, List<String> photoUrls, String status, List<Tag> tags) {


        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }
}
