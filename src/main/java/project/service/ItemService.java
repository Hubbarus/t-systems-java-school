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

    public void update(ItemDTO itemDTO) {
        Item item = mapper.map(itemDTO, Item.class);
        itemDao.update(item);
    }

    public ItemDTO findById(Long id) {
        Item item = itemDao.findById(id);
        return mapper.map(item, ItemDTO.class);
    }

    public List<ItemDTO> findAll() {
        List<Item> items = itemDao.findAll();
        return items
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemDTO> findByGroup(String group) {
        List<Item> byCategory = itemDao.getByCategory(group);
        return byCategory
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public List<ItemDTO> getItems(int from, int quantity) {
        List<Item> items = itemDao.getItems(from, quantity);
        return items
                .stream()
                .map(it -> mapper.map(it, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public Set<String> getGroupNames() {
        Set<String> result = new HashSet<>();
        List<ItemDTO> list = findAll();
        for (ItemDTO item : list) {
            result.add(item.getItemGroup());
        }
        return result;
    }

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

    public void addToCart(CartListWrapper wrapper, CartDTO cart) {
        List<CartDTO> cartDTOList = wrapper.getList();
        cartDTOList.add(cart);
        wrapper.setList(cartDTOList);
        log.log(Level.INFO, String.format("Item %s added to cart", cart.getItem().getItemName()));
    }

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
