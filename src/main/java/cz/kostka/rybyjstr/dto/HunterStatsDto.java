package cz.kostka.rybyjstr.dto;

public class HunterStatsDto {
    private Long hunterId;
    private String hunterName;
    private Long totalPoints;
    private Double avgPoints;
    private Long totalCatches;
    private Integer maxSize;

    public HunterStatsDto(Long hunterId, String hunterName, Long totalPoints, Double avgPoints, Long totalCatches, Integer maxSize) {
        this.hunterId = hunterId;
        this.hunterName = hunterName;
        this.totalPoints = totalPoints != null ? totalPoints : 0L;
        // Zaokrouhlení průměru bodů na 1 desetinné místo
        this.avgPoints = avgPoints != null ? Math.round(avgPoints * 10.0) / 10.0 : 0.0;
        this.totalCatches = totalCatches != null ? totalCatches : 0L;
        this.maxSize = maxSize != null ? maxSize : 0;
    }

    public Long getHunterId() { return hunterId; }
    public String getHunterName() { return hunterName; }
    public Long getTotalPoints() { return totalPoints; }
    public Double getAvgPoints() { return avgPoints; }
    public Long getTotalCatches() { return totalCatches; }
    public Integer getMaxSize() { return maxSize; }
}