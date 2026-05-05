package com.devjavu.standardProject.configuration;

import java.text.Normalizer;
import java.util.List;

public final class ShelfLocationCatalog {
    private ShelfLocationCatalog() {
    }

    public static final List<Integer> FLOORS = List.of(1, 2, 3);

    public static String shelfCodeForCategory(String category) {
        String normalized = Normalizer.normalize(category, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replace('đ', 'd')
                .replace('Đ', 'D');
        String[] tokens = normalized.toUpperCase().split("[^A-Z0-9]+");
        StringBuilder builder = new StringBuilder();
        for (String token : tokens) {
            if (!token.isBlank()) {
                builder.append(token.charAt(0));
            }
        }
        return builder.isEmpty() ? "GEN" : builder.toString();
    }

    public static String shelfNameForCategory(String category) {
        return "Kệ " + shelfCodeForCategory(category);
    }

    public static boolean isValidFloor(Integer floorNumber) {
        return floorNumber != null && FLOORS.contains(floorNumber);
    }

    public static boolean isValidShelfCode(String category, String shelfCode) {
        if (category == null || shelfCode == null) {
            return false;
        }
        return shelfCodeForCategory(category).equalsIgnoreCase(shelfCode.trim());
    }

    public static String buildBookLocation(Integer floorNumber, String category) {
        if (!isValidFloor(floorNumber) || category == null || category.isBlank()) {
            return null;
        }
        return String.format("Tầng %d - %s - %s", floorNumber, shelfNameForCategory(category), category);
    }
}
