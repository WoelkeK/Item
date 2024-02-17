package pl.woelke.item.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.woelke.item.exception.ItemNotFoundException;
import pl.woelke.item.model.Item;
import pl.woelke.item.repository.ItemEntity;
import pl.woelke.item.repository.ItemRepository;

import java.util.List;

/**
 * The ItemService class is responsible for managing items in the system.
 * Author: Krzysztof Woelke initial version 17.02.2024
 */
@Service
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        log.debug("getAllItems()");
        return Mapper.mapItemEntitiesToItems(itemRepository.findAll());
    }

    public Item addItem(Item item) {
        log.debug("addItem()");
        return Mapper.mapItemEntityToItem(itemRepository.save(Mapper.mapItemToItemEntity(item)));
    }

    public Item getItemById(Long id) throws ItemNotFoundException {
        log.debug("getItemById()");
        return Mapper.mapItemEntityToItem(itemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Item not found with id: " + id)));
    }

    public Item updateItem(Item item, Long id) throws ItemNotFoundException {
        log.debug("updateItem()");
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Item not found with id: " + id));

        ItemEntity editedItemEntity = Mapper.mapItemToItemEntity(item);
        editedItemEntity.setId(itemEntity.getId());
        return Mapper.mapItemEntityToItem(itemRepository.save(editedItemEntity));
    }

    public void deleteItem(Long id) {
        log.debug("deleteItem()");
        itemRepository.deleteById(id);
    }
}
