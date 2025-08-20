package cz.kostka.rybyjstr.domain;

import jakarta.persistence.*;

@Entity
@Table
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int year;

    @Column
    private int totalFishCount;

    @Column
    private int totalFishSize;

    @Column
    private String fishTypeNames;

    @Column
    private String hunterNames;

    public History() {
    }

    public History(final int year, final int totalFishCount, final int totalFishSize,
                   final String fishTypeNames,final String hunterNames) {
        this.year = year;
        this.totalFishCount = totalFishCount;
        this.totalFishSize = totalFishSize;
        this.fishTypeNames = fishTypeNames;
        this.hunterNames = hunterNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public int getTotalFishCount() {
        return totalFishCount;
    }

    public void setTotalFishCount(final int totalFishCount) {
        this.totalFishCount = totalFishCount;
    }

    public int getTotalFishSize() {
        return totalFishSize;
    }

    public void setTotalFishSize(final int totalFishSize) {
        this.totalFishSize = totalFishSize;
    }

    public String getFishTypeNames() {
        return fishTypeNames;
    }

    public void setFishTypeNames(final String fishTypeNames) {
        this.fishTypeNames = fishTypeNames;
    }

    public String getHunterNames() {
        return hunterNames;
    }

    public void setHunterNames(final String hunterNames) {
        this.hunterNames = hunterNames;
    }
}
