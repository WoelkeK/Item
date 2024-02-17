package pl.woelke.item.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryTest {

    private static final Long ITEM_ID = 1L;
    private static final String ITEM_INDEX = "INDEX1";
    private static final String ITEM_NAME = "NAME";
    private static final String ITEM_CURRENCY = "PLN";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Order(1)
    void addItem() {
        // given
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(ITEM_NAME);
        // when
        ItemEntity savedEntity = itemRepository.save(itemEntity);
        // then
        assertAll(
                () -> assertNotNull(savedEntity, "savedEntity is NULL"),
                () -> assertNotNull(savedEntity.getId(), "savedEntity ID is NULL")
        );
    }

    @Test
    @Order(2)
    void findById() {
        // given
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(ITEM_ID);
        itemEntity.setName(ITEM_NAME);
        ItemEntity savedEntity = itemRepository.save(itemEntity);
        // when
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(ITEM_ID);
        // then
        assertAll(
                () -> assertTrue(optionalItemEntity.isPresent(), "itemEntity is not present"),
                () -> assertNotNull(optionalItemEntity.get().getId(), "savedItemEntity ID is NULL"),
                () -> assertEquals(optionalItemEntity.get().getId(), ITEM_ID)
        );
    }

    @Test
    @Order(3)
    void updateItem() {

        // given
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(ITEM_ID);
        itemEntity.setName(ITEM_NAME);
        ItemEntity savedItemEntity = itemRepository.save(itemEntity);
        assertTrue(itemRepository.findById(ITEM_ID).isPresent());
        ItemEntity editedItemEntity = itemRepository.findById(ITEM_ID).get();
        // when
        editedItemEntity.setCurrency(ITEM_CURRENCY);
        editedItemEntity.setSalesIndex(ITEM_INDEX);
        ItemEntity updatedItemEntity = itemRepository.save(editedItemEntity);
        // then
        assertAll(
                () -> assertNotNull(updatedItemEntity, "updatedItemEntity is NULL"),
                () -> assertNotNull(updatedItemEntity.getId(), "updatedItemEntity ID is NULL"),
                () -> assertEquals(updatedItemEntity.getId(), ITEM_ID),
                () -> assertEquals(updatedItemEntity.getName(), ITEM_NAME),
                () -> assertEquals(updatedItemEntity.getSalesIndex(), ITEM_INDEX),
                () -> assertEquals(updatedItemEntity.getCurrency(), ITEM_CURRENCY)
        );
    }

    @Test
    @Order(4)
    void deleteItem() {
        // given
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(ITEM_ID);
        itemEntity.setName(ITEM_NAME);
        ItemEntity savedItemEntity = itemRepository.save(itemEntity);
        assertTrue(itemRepository.findById(ITEM_ID).isPresent());
        // when
        itemRepository.deleteById(savedItemEntity.getId());
        // then
        assertFalse(itemRepository.findById(ITEM_ID).isPresent());
    }
}