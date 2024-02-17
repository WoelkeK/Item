package pl.woelke.item.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.woelke.item.exception.ItemNotFoundException;
import pl.woelke.item.model.Item;
import pl.woelke.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/item/")
@Slf4j
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Item>> getAllItems() {
        log.debug("getAllItems()");
        List<Item> allItems = itemService.getAllItems();
        log.debug("getAllItems(...)");
        return ResponseEntity.status(HttpStatus.OK).body(allItems);
    }

    @GetMapping("/findById")
    public ResponseEntity<Item> findItemById(@RequestParam Long id) throws ItemNotFoundException {
        log.debug("findItemById()");
        Item itemById = itemService.getItemById(id);
        log.debug("findItemById(...)");
        return ResponseEntity.ok(itemById);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        log.debug("addItem()");
        Item createdItem = itemService.addItem(item);
        log.debug("addItem(...)");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PatchMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item,
                                           @RequestParam(name = "id") Long id) {

        log.debug("updateItem()");
        try {
            Item updatedItem = itemService.updateItem(item, id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteItem(@RequestParam(name = "id") Long id) {
        log.debug("delete()");
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
