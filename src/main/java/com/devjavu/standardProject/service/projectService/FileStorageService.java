package com.devjavu.standardProject.service.projectService;

import com.devjavu.standardProject.exception.AppException;
import com.devjavu.standardProject.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileStorageService {
    static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "webp");

    @Value("${app.upload.dir:uploads}")
    @NonFinal
    String uploadDir;

    public String storeBookCover(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = extractExtension(originalFilename);
        if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);

            String storedFilename = "cover-" + UUID.randomUUID() + "." + extension.toLowerCase();
            Path targetPath = uploadPath.resolve(storedFilename);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + storedFilename;
        } catch (IOException exception) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    private String extractExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index < 0 || index == filename.length() - 1) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        return filename.substring(index + 1);
    }
}
