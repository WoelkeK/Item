package pl.woelke.item.service;

import lombok.extern.slf4j.Slf4j;
import pl.woelke.item.model.Item;
import pl.woelke.item.repository.ItemEntity;

import java.util.List;

@Slf4j
public class Mapper {

    public static ItemEntity mapItemToItemEntity(Item item) {
        log.debug("mapItemToItemEntity()");
        return ItemEntity.builder()
                .index(item.getIndex())
                .name(item.getName())
                .category(item.getCategory())
                .description(item.getDescription())
                .unit(item.getUnit())
                .weight(item.getWeight())
                .quantity(item.getQuantity())
                .netPrice(item.getNetPrice())
                .grossPrice(item.getGrossPrice())
                .vat(item.getVat())
                .currency(item.getCurrency())
                .availableStock(item.getAvailableStock())
                .maxStockLevel(item.getMaxStockLevel())
                .minStockLevel(item.getMinStockLevel())
                .build();


    }

    public static Item mapItemEntityToItem(ItemEntity itemEntity) {
        log.debug("mapItemEntityToItem()");
        return Item.builder()
                .index(itemEntity.getIndex())
                .name(itemEntity.getName())
                .category(itemEntity.getCategory())
                .description(itemEntity.getDescription())
                .unit(itemEntity.getUnit())
                .weight(itemEntity.getWeight())
                .quantity(itemEntity.getQuantity())
                .netPrice(itemEntity.getNetPrice())
                .grossPrice(itemEntity.getGrossPrice())
                .vat(itemEntity.getVat())
                .currency(itemEntity.getCurrency())
                .availableStock(itemEntity.getAvailableStock())
                .maxStockLevel(itemEntity.getMaxStockLevel())
                .minStockLevel(itemEntity.getMaxStockLevel())
                .build();
    }

    public static List<Item> mapItemEntitiesToItems(List<ItemEntity> itemEntities) {
        log.debug("mapItemEntitiesToItems()");
        return itemEntities.stream().map(Mapper::mapItemEntityToItem).toList();
    }

    public static List<ItemEntity> mapItemsToItemEntities(List<Item> items) {
        log.debug("mapItemsToItemsEntities()");
        return items.stream().map(Mapper::mapItemToItemEntity).toList();
    }
}
