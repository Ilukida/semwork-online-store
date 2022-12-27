package ru.itis.services.impl;

import ru.itis.dao.FileRepository;
import ru.itis.dao.ProductRepository;
import ru.itis.dao.UserRepository;
import ru.itis.dto.UserDto;
import ru.itis.excptions.NotFoundException;
import ru.itis.models.FileInfo;
import ru.itis.models.Product;
import ru.itis.services.FileService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    private final String path;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public FileServiceImpl(String path, FileRepository fileRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.path = path;
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public FileInfo saveFileToStorageProduct(Product product, InputStream file, String originalFileName, String contentType, Integer size) {
        FileInfo fileInfo = saveFileToStorage(file, originalFileName, contentType, size);
        productRepository.addPhotoForProduct(product.getId(), fileInfo.getId());
        return fileInfo;
    }

    @Override
    public FileInfo saveFileToStorageProfile(UserDto userDto, InputStream file, String originalFileName, String contentType, Integer size) {
        FileInfo fileInfo = saveFileToStorage(file, originalFileName, contentType, size);
        userRepository.updateAvatarForUser(userDto.getId(), fileInfo.getId());
        return fileInfo;
    }

    @Override
    public void readFileFromStorage(Integer fileId, OutputStream outputStream) {
        Optional<FileInfo> optionalFileInfo = fileRepository.findById(fileId);
        FileInfo fileInfo = optionalFileInfo.orElseThrow(() -> new NotFoundException("File not found"));

        File file = new File(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Integer fileId) {
        return fileRepository.findById(fileId).orElseThrow(() -> new NotFoundException("File not found"));
    }


    public FileInfo saveFileToStorage(InputStream inputStream, String originalFileName, String contentType, Integer size) {
        FileInfo fileInfo = new FileInfo(
                null,
                originalFileName,
                UUID.randomUUID().toString(),
                size,
                contentType
        );

        try {
            Files.copy(inputStream, Paths.get(path + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileInfo = fileRepository.save(fileInfo);
            return fileInfo;

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
