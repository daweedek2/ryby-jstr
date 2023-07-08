package cz.kostka.rybyjstr.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Catch {
    @Id
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @Column
    private int size;

    @Column
    private long weight;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "hunter_id")
    private Hunter hunter;


    @ManyToOne
    @JoinColumn(name = "fishType_id")
    private FishType fishType;

    public Catch() {
    }

    public Catch(
            final Long id,
            final LocalDateTime timestamp,
            final int size,
            final long weight,
            final String note,
            final Hunter hunter,
            final FishType fishType) {
        this.id = id;
        this.timestamp = timestamp;
        this.size = size;
        this.weight = weight;
        this.note = note;
        this.hunter = hunter;
        this.fishType = fishType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    public FishType getFishType() {
        return fishType;
    }

    public void setFishType(FishType fishType) {
        this.fishType = fishType;
    }
}
