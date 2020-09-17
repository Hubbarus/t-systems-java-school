package project.service;

import org.springframework.transaction.annotation.Transactional;
import project.converter.ItemConverter;
import project.dao.ItemDao;
import project.dto.ItemDTO;
import project.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemDao itemDao;
    private final ItemConverter itemConverter;

    @Autowired
    public ItemService(ItemDao itemDao, ItemConverter itemConverter) {
        this.itemDao = itemDao;
        this.itemConverter = itemConverter;
    }
    @Transactional
    public void save(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.save(item);
    }
    @Transactional
    public void update(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.update(item);
    }

    public ItemDTO findById(Long id) {
        Item item = itemDao.findById(id);
        return itemConverter.convertToDTO(item);
    }
    @Transactional
    public void delete(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.delete(item);
    }
    @Transactional
    public void deleteAll() {
        itemDao.deleteAll();
    }

    public List<ItemDTO> findAll() {
        List<Item> item = itemDao.findAll();
        return item
                .stream()
                .map(itemConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
