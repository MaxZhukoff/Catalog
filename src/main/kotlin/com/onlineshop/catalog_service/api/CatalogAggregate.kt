package com.onlineshop.catalog_service.api

import ru.quipy.core.annotations.AggregateType
import ru.quipy.domain.Aggregate

@AggregateType(aggregateEventsTableName = "catalog_events")
class CatalogAggregate: Aggregate