package pojo;

import java.util.List;
import java.util.Map;

public class PetPojo {

    private int id;
    private Map<String, Object> category;
    private String name;
    private List<Object> photoUrls;
    private List<Object> tags;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Object> getCategory() {
        return category;
    }

    public void setCategory(Map<String, Object> category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrl(List<Object> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
