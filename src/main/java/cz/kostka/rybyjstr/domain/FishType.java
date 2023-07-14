package cz.kostka.rybyjstr.domain;

import jakarta.persistence.*;

@Table
@Entity
public class FishType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    public FishType() {
    }

    public FishType(final Long id, final String type) {
        this.id = id;
        this.type = type;
    }

    public FishType(final String type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
