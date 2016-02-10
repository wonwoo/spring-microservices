package spring.microservices.board.model;

/**
 * Created by wonwoo on 2016. 2. 10..
 */
public class Board {

    private Long id;
    private String title;
    private String content;

    public Board(Long id, String title,String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Board() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
