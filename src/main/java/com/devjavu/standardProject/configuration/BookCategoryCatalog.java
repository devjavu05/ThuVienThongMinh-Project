package com.devjavu.standardProject.configuration;

import java.util.List;
import java.util.Set;

public final class BookCategoryCatalog {
    public static final List<String> CATEGORIES = List.of(
            "Chính trị - Lịch sử",
            "Danh nhân - Tự truyện",
            "Dạy làm giàu",
            "Học ngoại ngữ",
            "Khoa học - Kỹ thuật",
            "Kiếm hiệp - Tiên hiệp",
            "Kinh tế - Quản lý",
            "Kỹ năng - Tư duy",
            "Ngôn tình - Đam mỹ",
            "Nuôi dạy con",
            "Ôn thi THPT",
            "Sách tham khảo",
            "Triết học",
            "Trinh thám - Kinh dị",
            "Truyện cười",
            "Truyện tranh - Manga",
            "Tử vi - Phong thủy",
            "Văn hóa - Tôn giáo",
            "Văn học",
            "Y học - Sức khỏe"
    );

    public static final Set<String> CATEGORY_SET = Set.copyOf(CATEGORIES);

    private BookCategoryCatalog() {
    }
}
