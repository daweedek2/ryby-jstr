package cz.kostka.rybyjstr.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

@Entity
@Table
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @JdbcType(VarbinaryJdbcType.class)
    @Column
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name="catch_id", nullable = false)
    private Catch theCatch;

    public Image(Long id, byte[] imageData) {
        this.id = id;
        this.imageData = imageData;
    }

    public Image() {
    }

    public Image(final byte[] imageData, final Catch theCatch) {
        this.imageData = imageData;
        this.theCatch = theCatch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Catch getTheCatch() {
        return theCatch;
    }

    public void setTheCatch(Catch theCatch) {
        this.theCatch = theCatch;
    }
}
