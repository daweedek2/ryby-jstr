package cz.kostka.rybyjstr.dto;

import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object for statistics graph.
 * @param names List of names
 * @param nameWithCounts Map where the key is the fish type name and the value is a list of counts for each hunter
 */
public record GraphDTO(List<String> names, Map<String, List<Integer>> nameWithCounts) {
}
