package spring.microservices.model;

/**
 * Created by wonwoo on 2016. 2. 11..
 */
public class Board {
    private Long id;
    private String title;

    public Board(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Board() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
