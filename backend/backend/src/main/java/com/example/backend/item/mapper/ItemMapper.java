package com.example.backend.item.mapper;

import com.example.backend.item.dto.ItemDTO;
import com.example.backend.item.dto.ItemResponseDTO;
import com.example.backend.item.model.Item;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "image", source = "image", qualifiedByName = "multipartFileToBinary")
    Item fromItemDTOtoItem(ItemDTO itemDTO);

    @Mapping(target = "imageUrl", source = "image", qualifiedByName = "binaryToImageUrl")
    ItemResponseDTO fromItemtoItemResponseDTO(Item item);

    @Named("multipartFileToBinary")
    default Binary multipartFileToBinary(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            return new Binary(BsonBinarySubType.BINARY, file.getBytes());
        }
        return null;
    }

    @Named("binaryToImageUrl")
    default String binaryToImageUrl(Binary image) {
        if (image != null) {
            return Base64.getEncoder().encodeToString(image.getData());
        }
        return null;
    }
}