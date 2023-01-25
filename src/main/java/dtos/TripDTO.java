package dtos;

import entities.Guide;
import entities.Trip;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link Trip} entity
 */
public class TripDTO implements Serializable {
    private final Integer id;
    @Size(max = 45)
    private final String name;
    @Size(max = 45)
    private final String date;
    @Size(max = 45)
    private final String time;
    @Size(max = 45)
    private final String location;
    @Size(max = 45)
    private final String duration;
    @Size(max = 45)
    private final String packinglist;
    private final List<GuideInnerDTO> guides = new ArrayList<>();
    private final List<BrugerInnerDTO> brugers = new ArrayList<>();


    public TripDTO(Integer id, String name, String date, String time, String location, String duration, String packinglist) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packinglist = packinglist;
    }

    public TripDTO(Trip rm) {
        this.id = rm.getId();
        this.name = rm.getName();
        this.date = rm.getDate();
        this.time = rm.getTime();
        this.location = rm.getLocation();
        this.duration = rm.getDuration();
        this.packinglist = rm.getPackinglist();
        rm.getGuides().forEach(guide -> {
            guides.add(new GuideInnerDTO(guide));
        });

    }

    public static List<TripDTO> getDtos(List<Trip> rms) {
        List<TripDTO> rmdtos = new ArrayList();
        rms.forEach(rm -> rmdtos.add(new TripDTO(rm)));
        return rmdtos;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDuration() {
        return duration;
    }

    public String getPackinglist() {
        return packinglist;
    }

    public List<GuideInnerDTO> getGuides() {
        return guides;
    }

    public List<BrugerInnerDTO> getBrugers() {
        return brugers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDTO entity = (TripDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.time, entity.time) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.packinglist, entity.packinglist);
/*                Objects.equals(this.guides, entity.guides) &&
                Objects.equals(this.brugers, entity.brugers);*/
    }


    /**
     * A DTO for the {@link Guide} entity
     */
    public static class GuideInnerDTO implements Serializable {
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

        public GuideInnerDTO(Integer id, String name, String gender, String birthyear, String profile, String imageurl) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.birthyear = birthyear;
            this.profile = profile;
            this.imageurl = imageurl;
        }

        public GuideInnerDTO(Guide guide) {
            this.id = guide.getId();
            this.name = guide.getName();
            this.gender = guide.getGender();
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
            GuideInnerDTO entity = (GuideInnerDTO) o;
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

    /**
     * A DTO for the {@link Bruger} entity
     */
    public static class BrugerInnerDTO implements Serializable {
        private final Integer id;
        @Size(max = 45)
        private final String adress;
        @Size(max = 45)
        private final String phone;
        @Size(max = 45)
        private final String email;
        @Size(max = 45)
        private final String birthyear;
        @Size(max = 45)
        private final String gender;

        public BrugerInnerDTO(Integer id, String adress, String phone, String email, String birthyear, String gender) {
            this.id = id;
            this.adress = adress;
            this.phone = phone;
            this.email = email;
            this.birthyear = birthyear;
            this.gender = gender;
        }

        public Integer getId() {
            return id;
        }

        public String getAdress() {
            return adress;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getBirthyear() {
            return birthyear;
        }

        public String getGender() {
            return gender;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BrugerInnerDTO entity = (BrugerInnerDTO) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.adress, entity.adress) &&
                    Objects.equals(this.phone, entity.phone) &&
                    Objects.equals(this.email, entity.email) &&
                    Objects.equals(this.birthyear, entity.birthyear) &&
                    Objects.equals(this.gender, entity.gender);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, adress, phone, email, birthyear, gender);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "adress = " + adress + ", " +
                    "phone = " + phone + ", " +
                    "email = " + email + ", " +
                    "birthyear = " + birthyear + ", " +
                    "gender = " + gender + ")";
        }
    }
}