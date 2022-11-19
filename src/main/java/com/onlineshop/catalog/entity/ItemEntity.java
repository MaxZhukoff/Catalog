package com.onlineshop.catalog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private CategoryEntity category;
}
