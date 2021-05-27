package group1.googlebooks;

public class GetBook {
    private final long id;
    private final String content;

    public GetBook(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
