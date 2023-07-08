package cz.kostka.rybyjstr.domain;

import jakarta.persistence.*;

import java.util.List;

@Table
@Entity
public class FishType {
    @Id
    private Long id;

    @Column
    private String type;

    public FishType() {
    }

    public FishType(final Long id, final String type) {
        this.id = id;
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
