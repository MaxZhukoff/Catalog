package com.onlineshop.catalog.api;

import ru.quipy.core.annotations.AggregateType;
import ru.quipy.domain.Aggregate;

@AggregateType(aggregateEventsTableName = "catalog")
public
class CatalogAggregate implements Aggregate{}
