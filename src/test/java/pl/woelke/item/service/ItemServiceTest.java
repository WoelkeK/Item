package pl.woelke.item.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import pl.woelke.item.exception.ItemNotFoundException;
import pl.woelke.item.model.Item;
import pl.woelke.item.repository.ItemEntity;
import pl.woelke.item.repository.ItemRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    private ItemService itemService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        itemService = new ItemService((itemRepository));
    }

    @Test
    void addItem() {
// given
        Item item = new Item();
        ItemEntity itemEntity = new ItemEntity();
        Mockito.when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

// when
        Item createdItem = itemService.addItem(item);
        Item expectedItem = Mapper.mapItemEntityToItem(itemEntity);
// then
        assertEquals(expectedItem, createdItem, "New item added properly");
        Mockito.verify(itemRepository, Mockito.times(1)).save(any(ItemEntity.class));
    }

    @Test
    void getItemById() throws ItemNotFoundException {
        Long id = 1L;
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(id);

        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(itemEntity));

        Item item = itemService.getItemById(id);

        assertEquals(Mapper.mapItemEntityToItem(itemEntity), item, "Item retrieved by id");
        Mockito.verify(itemRepository, Mockito.times(1)).findById(id);
    }

    @Test
    void updateItem() throws ItemNotFoundException {

        Long id = 1L;
        Item item = new Item();
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(id);

        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.of(itemEntity));
        Mockito.when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

        Item updatedItem = itemService.updateItem(item, id);

        assertEquals(Mapper.mapItemEntityToItem(itemEntity), updatedItem, "Item updated properly");
        Mockito.verify(itemRepository, Mockito.times(1)).findById(id);
        Mockito.verify(itemRepository, Mockito.times(1)).save(any(ItemEntity.class));

    }

    @Test
    void deleteItem() {

        Long id = 1L;
        Mockito.doNothing().when(itemRepository).deleteById(id);
        itemService.deleteItem(id);
        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(id);

    }

    @Test
    void getAllItems() {
        //given
        ItemEntity itemEntity1 = new ItemEntity();
        itemEntity1.setName("item1");
        ItemEntity itemEntity2 = new ItemEntity();
        itemEntity2.setName("item2");
        List<ItemEntity> itemEntities = Arrays.asList(itemEntity1, itemEntity2);

        Item item1 = new Item();
        item1.setName("item1");
        Item item2 = new Item();
        item2.setName("item2");
        List<Item> expectedItems = Arrays.asList(item1, item2);

        Mockito.when(itemRepository.findAll()).thenReturn(itemEntities);
        //when

        List<Item> actualItems = itemService.getAllItems();

        //then
        assertEquals(expectedItems, actualItems, "All items retrieved");

    }
}