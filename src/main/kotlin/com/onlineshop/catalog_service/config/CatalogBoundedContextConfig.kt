package com.onlineshop.catalog_service.config

import com.onlineshop.catalog_service.api.CatalogAggregate
import com.onlineshop.catalog_service.logic.Catalog
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
    fun catalogEsService(): EventSourcingService<UUID, CatalogAggregate, Catalog> =
        eventSourcingServiceFactory.create()
    //TODO хз что за ошибка
}