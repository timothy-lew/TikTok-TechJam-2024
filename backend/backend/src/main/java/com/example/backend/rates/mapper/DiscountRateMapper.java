package com.example.backend.rates.mapper;

import com.example.backend.rates.dto.DiscountRateDTO;
import com.example.backend.rates.dto.DiscountRateResponseDTO;
import com.example.backend.rates.model.DiscountRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscountRateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "applyTo", source = "applyTo", qualifiedByName = "stringToEnum")
    DiscountRate fromDiscountRateDTOtoDiscountRate(DiscountRateDTO discountRateDTO);

    @Mapping(target = "applyTo", source = "applyTo", qualifiedByName = "enumToString")
    DiscountRateResponseDTO fromDiscountRatetoDiscountRateResponseDTO(DiscountRate discountRate);

    List<DiscountRateResponseDTO> fromDiscountRateListtoDiscountRateResponseDTOList(List<DiscountRate> discountRates);

    @Named("enumToString")
    default List<String> enumToString(List<DiscountRate.ApplyTo> applyTo) {
        if (applyTo == null) {
            return null;
        }
        return applyTo.stream()
                .map(Enum::name)
                .toList();
    }

    @Named("stringToEnum")
    default List<DiscountRate.ApplyTo> stringToEnum(List<String> applyTo) {
        if (applyTo == null) {
            return null;
        }
        return applyTo.stream()
                .map(DiscountRate.ApplyTo::valueOf)
                .toList();
    }
}