package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.FileInfo;
import ru.itis.models.Product;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    FileInfo saveFileToStorageProduct(Product product, InputStream file, String originalFileName, String contentType, Integer size);
    FileInfo saveFileToStorageProfile(UserDto userDto, InputStream file, String originalFileName, String contentType, Integer size);
    void readFileFromStorage(Integer fileId, OutputStream outputStream);
    FileInfo getFileInfo(Integer fileId);
}
