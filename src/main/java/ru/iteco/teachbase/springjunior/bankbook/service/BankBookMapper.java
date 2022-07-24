package ru.iteco.teachbase.springjunior.bankbook.service;

import org.springframework.stereotype.Service;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookDto;
import ru.iteco.teachbase.springjunior.bankbook.model.BankBookEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankBookMapper {
    public BankBookDto mapToDto(BankBookEntity entity) {
        if (entity == null) {
            return null;
        }
        return BankBookDto.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .number(entity.getNumber())
            .currency(entity.getCurrency())
            .amount(entity.getAmount())
            .build();
    }

    public BankBookEntity mapToEntity(BankBookDto dto) {
        if (dto == null) {
            return null;
        }
        return BankBookEntity.builder()
            .id(dto.getId())
            .userId(dto.getUserId())
            .number(dto.getNumber())
            .currency(dto.getCurrency())
            .amount(dto.getAmount())
            .build();
    }

    public List<BankBookDto> mapToDtos(Collection<BankBookEntity> entities) {
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<BankBookEntity> mapToEntities(Collection<BankBookDto> dtos) {
        return dtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}
