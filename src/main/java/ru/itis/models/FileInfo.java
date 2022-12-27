package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FileInfo {
    private Integer id;
    private String originalFileName;
    private String storageFileName;
    private Integer size;
    private String type;
}
