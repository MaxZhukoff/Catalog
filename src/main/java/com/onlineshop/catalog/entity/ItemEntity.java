package com.onlineshop.catalog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
public class ItemEntity {

    private UUID id;

    private String name;

    private String description;

    private long price;

    private long amount;
}
