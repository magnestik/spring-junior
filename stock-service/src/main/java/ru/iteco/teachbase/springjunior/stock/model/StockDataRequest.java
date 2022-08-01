package ru.iteco.teachbase.springjunior.stock.model;

import lombok.Data;

import java.util.List;

@Data
public abstract class StockDataRequest<T> {
    List<T> data;
}
