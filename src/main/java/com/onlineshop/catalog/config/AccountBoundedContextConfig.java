package com.onlineshop.catalog.config;

import com.onlineshop.catalog.api.ItemAggregate;
import com.onlineshop.catalog.logic.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.quipy.core.EventSourcingService;
import ru.quipy.core.EventSourcingServiceFactory;

import java.util.UUID;

@Configuration
public class AccountBoundedContextConfig {

    private EventSourcingServiceFactory eventSourcingServiceFactory;

    @Bean
    public EventSourcingService<UUID, ItemAggregate, Item> accountEsService() {
        return eventSourcingServiceFactory.create();
    }
}
