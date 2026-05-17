package org.example.hotelbooking.common.response;

import java.util.List;

public record ListResponse<T>(
        int total,
        List<T> items
) {
    public static <T> ListResponse<T> of(List<T> items) {
        return new ListResponse<>(items.size(), items);
    }
}