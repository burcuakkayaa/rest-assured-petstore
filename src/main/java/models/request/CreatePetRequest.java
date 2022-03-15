package models.request;

import implementation.Category;
import implementation.Tag;
import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
public class CreatePetRequest {

    public static CreatePetRequest.Builder Builder;
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag>  tags;
    private String status;



    public CreatePetRequest(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.name = builder.name;
        this.photoUrls = builder.photoUrls;
        this.tags = builder.tags;
        this.status = builder.status;
    }


    public static class Builder {


        private long id;
        private  Category category;
        private  String name;
        private List<String> photoUrls;
        private List<Tag> tags;
        private  String status;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPhotoUrls(List<String> photoUrls) {
            this.photoUrls =photoUrls;
            return  this;
        }

        public Builder withTags(List<Tag>  tags) {
            this.tags=tags;
            return this;
        }

        public Builder withStatus(String status) {
            this.status=status;
            return this;
        }

        public CreatePetRequest build() {
            return new CreatePetRequest(this);
        }

    }

}
