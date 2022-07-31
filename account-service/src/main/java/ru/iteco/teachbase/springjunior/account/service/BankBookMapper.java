package ru.iteco.teachbase.springjunior.account.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.iteco.teachbase.springjunior.account.model.dto.BankBookDto;
import ru.iteco.teachbase.springjunior.account.model.entity.BankBookEntity;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BankBookMapper {
    @Mapping(source = "currency.name", target = "currency")
    BankBookDto mapToDto(BankBookEntity entity);

    @Mapping(target = "currency", ignore = true)
    BankBookEntity mapToEntity(BankBookDto dto);

    List<BankBookDto> mapToDtos(Collection<BankBookEntity> entities);

    List<BankBookEntity> mapToEntities(Collection<BankBookDto> dtos);
}
