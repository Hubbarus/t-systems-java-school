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

    public void save(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.openCurrentSessionwithTransaction();
        itemDao.save(item);
        itemDao.closeCurrentSessionwithTransaction();
    }

    public void update(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.openCurrentSessionwithTransaction();
        itemDao.update(item);
        itemDao.closeCurrentSessionwithTransaction();
    }

    @Transactional
    public ItemDTO findById(Long id) {
        itemDao.openCurrentSession();
        Item item = itemDao.findById(id);
        itemDao.closeCurrentSession();
        return itemConverter.convertToDTO(item);
    }

    public void delete(ItemDTO itemDTO) {
        Item item = itemConverter.convertToEntity(itemDTO);
        itemDao.closeCurrentSessionwithTransaction();
        itemDao.delete(item);
        itemDao.closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {
        itemDao.openCurrentSessionwithTransaction();
        itemDao.deleteAll();
        itemDao.closeCurrentSessionwithTransaction();
    }

    @Transactional
    public List<ItemDTO> findAll() {
        itemDao.openCurrentSession();
        List<Item> item = itemDao.findAll();
        itemDao.closeCurrentSession();
        return item
                .stream()
                .map(itemConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
