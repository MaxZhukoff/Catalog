package com.onlineshop.catalog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
@Builder
@Entity
public class ItemEntity {

    @Id
    @GeneratedValue()
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    private String description;

    private int price;

    private int amount;
}
