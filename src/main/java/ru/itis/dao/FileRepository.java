package ru.itis.dao;

import ru.itis.models.FileInfo;

import java.util.Optional;

public interface FileRepository {
    FileInfo save(FileInfo fileInfo);
    Optional<FileInfo> findById(Integer id);
}
