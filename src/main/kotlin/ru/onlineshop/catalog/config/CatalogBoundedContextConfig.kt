package ru.onlineshop.catalog.config

import ru.onlineshop.catalog.api.CatalogAggregate
import ru.onlineshop.catalog.logic.CatalogAggregateState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.quipy.core.EventSourcingService
import ru.quipy.core.EventSourcingServiceFactory
import java.util.*

@Configuration
class CatalogBoundedContextConfig {

    @Autowired
    private lateinit var eventSourcingServiceFactory : EventSourcingServiceFactory

    @Bean
    fun catalogEsService(): EventSourcingService<String, CatalogAggregate, CatalogAggregateState> =
        eventSourcingServiceFactory.create()
}