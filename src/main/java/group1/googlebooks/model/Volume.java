package group1.googlebooks.model;

import javax.persistence.*;

@Entity
@Table(name = "volumes")
public class Volume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "kind")
    private String kind;

    @Column(name = "googleId")
    private String googleId;

    public Volume() {
        super();
    }

    public Volume(String kind, String googleId) {
        super();
        this.kind = kind;
        this.googleId = googleId;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return this.kind;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getGoogleId() {
        return this.googleId;
    }
}
