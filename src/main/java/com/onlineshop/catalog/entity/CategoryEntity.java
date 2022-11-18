package com.onlineshop.catalog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Table(name = "category")
@NoArgsConstructor
@Builder
@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue()
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String typeName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<ItemEntity> items;
}
