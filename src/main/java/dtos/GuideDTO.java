package dtos;

import entities.Guide;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Guide} entity
 */
public class GuideDTO implements Serializable {
    private final Integer id;
    @Size(max = 45)
    private final String name;
    @Size(max = 45)
    private final String gender;
    @Size(max = 45)
    private final String birthyear;
    @Size(max = 45)
    private final String profile;
    @Size(max = 45)
    private final String imageurl;

    public GuideDTO(Integer id, String name, String gender, String birthyear, String profile, String imageurl) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthyear = birthyear;
        this.profile = profile;
        this.imageurl = imageurl;
    }

    public GuideDTO(Guide guide) {
        this.id = guide.getId();
        this.name = guide.getName();
        this.gender = guide.getName();
        this.birthyear = guide.getBirthyear();
        this.profile = guide.getProfile();
        this.imageurl = guide.getImageurl();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public String getProfile() {
        return profile;
    }

    public String getImageurl() {
        return imageurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuideDTO entity = (GuideDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.birthyear, entity.birthyear) &&
                Objects.equals(this.profile, entity.profile) &&
                Objects.equals(this.imageurl, entity.imageurl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, birthyear, profile, imageurl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "gender = " + gender + ", " +
                "birthyear = " + birthyear + ", " +
                "profile = " + profile + ", " +
                "imageurl = " + imageurl + ")";
    }
}