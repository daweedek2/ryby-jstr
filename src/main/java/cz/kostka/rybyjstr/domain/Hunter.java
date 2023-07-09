package cz.kostka.rybyjstr.domain;

import jakarta.persistence.*;

@Entity
@Table
public class Hunter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;

    public Hunter() {
    }

    public Hunter(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
