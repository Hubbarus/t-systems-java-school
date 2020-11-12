package project.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.dao.ItemDao;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.entity.Item;
import project.exception.IMGUploadException;
import project.utils.CartListWrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Service class for {@link ItemDTO} objects.
 */
@Service
@AllArgsConstructor
@Log
public class ItemService {

    @Autowired private final ItemDao itemDao;
    @Autowired private final ModelMapper mapper;

    /**
     * Updates item information in database
     * @param itemDTO to be updated
     */
    public void update(ItemDTO itemDTO) {
        Item item = mapper.map(itemDTO, Item.class);
        itemDao.update(item);
    }

    /**
     * Finds {@link Item} entity in database and converts it to DTO
     * @param id of entity in database
     * @return {@link ItemDTO} object
     */
    public ItemDTO findById(Long id) {
        Item item = itemDao.findById(id);
        return mapper.map(item, ItemDTO.class);
    }

    /**
     * Finds all entities in database
     * @return list of {@link ItemDTO} objects
     */
    public List<ItemDTO> findAll() {
        List<Item> items = itemDao.findAll();
        return items
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Finds all entities in category from database
     * @param group name to find
     * @return list of {@link ItemDTO} objects
     */
    public List<ItemDTO> findByGroup(String group) {
        List<Item> byCategory = itemDao.getByCategory(group);
        return byCategory
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Finds items for paging util
     * @param from number of page
     * @param quantity in one page
     * @return list of {@link ItemDTO} objects
     */
    public List<ItemDTO> getItems(int from, int quantity) {
        List<Item> items = itemDao.getItems(from, quantity);
        return items
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Finds all category names of items
     * @return set of names
     */
    public Set<String> getGroupNames() {
        Set<String> result = new HashSet<>();
        List<ItemDTO> list = findAll();
        for (ItemDTO item : list) {
            result.add(item.getItemGroup());
        }
        return result;
    }

    /**
     * Gets item in category by id
     * @param id of item
     * @param category name of item
     * @return {@link CartDTO} object with filled item field
     */
    public CartDTO getItemInCategoryById(Long id, String category) {
        List<ItemDTO> itemByGroup = findByGroup(category);
        CartDTO cart = new CartDTO();
        for (ItemDTO item : itemByGroup) {
            if (item.getId() == id) {
                cart.setItem(item);
                break;
            }
        }
        return cart;
    }

    /**
     * Saves new item or updates old one
     * @param item to be saved or updated
     * @param file image to be uploaded
     * @param filePath of image
     * @throws IMGUploadException in case of errors during file uploading
     */
    public void saveOrUpdate(ItemDTO item, MultipartFile file, String filePath) throws IMGUploadException {
        if (item.getPathToIMG().equals("")
                || !file.getOriginalFilename().equals("")) {
            setPathToIMGAndUploadToServer(item, file, filePath);
        }

        try {
            findById(item.getId());
            update(item);
        } catch (Exception e) {
            Item itemToSave = mapper.map(item, Item.class);
            itemDao.save(itemToSave);
        }
    }

    /**
     * Uploads image to servers
     * @param item connected to image
     * @param file image
     * @param filePath of image to be saved
     * @throws IMGUploadException in case of errors during file uploading
     */
    private void setPathToIMGAndUploadToServer(ItemDTO item,
                                               MultipartFile file,
                                               String filePath) throws IMGUploadException {
        if (file.getOriginalFilename().equals("")) {
            item.setPathToIMG("/img/default_no_img.png");
            return;
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error while uploading file");
            throw new IMGUploadException("Error while uploading file");
        }
        item.setPathToIMG("/img/" + file.getOriginalFilename());
    }

    /**
     * Renames item category name
     * @param oldName of category
     * @param newName of category
     * @return set of categories
     */
    public Set<String> renameGroup(String oldName, String newName) {
        List<ItemDTO> all = findAll();
        for (ItemDTO item : all) {
            if (item.getItemGroup().equals(oldName)) {
                item.setItemGroup(newName);
                update(item);
            }
        }
        log.log(Level.INFO, String.format("Category with name %s renamed to %s", oldName, newName));
        return getGroupNames();
    }

    /**
     * Deletes category
     * @param cat name to be deleted
     */
    public void deleteGroup(String cat) {
        List<ItemDTO> all = findAll();
        for (ItemDTO item : all) {
            if (item.getItemGroup().equalsIgnoreCase(cat)) {
                item.setItemGroup("Other");
                update(item);
            }
        }
        log.log(Level.INFO, String.format("Category with name %s deleted. All items moved to DEFAULT.", cat));
    }

    /**
     * Adds item to cart
     * @param wrapper object with items
     * @param cart cart
     */
    public void addToCart(CartListWrapper wrapper, CartDTO cart) {
        List<CartDTO> cartDTOList = wrapper.getList();
        cartDTOList.add(cart);
        wrapper.setList(cartDTOList);
        log.log(Level.INFO, String.format("Item %s added to cart", cart.getItem().getItemName()));
    }

    /**
     * Adds exactly 1 item to cart
     * @param wrapper object with items
     * @param itemId to be added to cart
     */
    public void buyInOneClick(CartListWrapper wrapper, Long itemId) {
        List<CartDTO> cartDTOList = wrapper.getList();
        ItemDTO item = findById(itemId);
        CartDTO cart = new CartDTO();
        cart.setItem(item);
        cart.setQuantity(1);
        cartDTOList.add(cart);
        wrapper.setList(cartDTOList);
        log.log(Level.INFO, String.format("Item %s added to cart", cart.getItem().getItemName()));
    }

    /**
     * Removes items from cart
     * @param wrapper object with items
     * @param itemId to be removed from cart
     */
    public void removeItemFromCart(CartListWrapper wrapper, long itemId) {
        List<CartDTO> items = wrapper.getList();
        for (CartDTO cartDTO : items) {
            if (cartDTO.getItem().getId() == itemId) {
                items.remove(cartDTO);
                log.log(Level.INFO, String.format("Item %s removed from cart", cartDTO.getItem().getItemName()));
                break;
            }
        }
        wrapper.setList(items);
    }

    public ItemDTO editOrAddItem(ItemDTO item) {
        if (item.getItemName() != null) {
            return findById(item.getId());
        }
        return new ItemDTO();
    }

    /**
     * Finds top ten items in database for all time
     * @return list of {@link CartDTO} objects with filled item field
     */
    public List<CartDTO> getTopTenItems() {
        List<Object[]> topTenItems = itemDao.getTopTenItems();

        List<CartDTO> resultList = new ArrayList<>();
        for (Object[] obj : topTenItems) {
            CartDTO cart = new CartDTO();
            cart.setItem(findById((Long) obj[0]));
            Long quan = (Long) obj[1];
            cart.setQuantity(quan.intValue());
            resultList.add(cart);
        }

        return resultList;
    }
}
