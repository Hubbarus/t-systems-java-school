package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.exception.IMGUploadException;
import project.exception.NoSuchClientException;
import project.service.ClientService;
import project.service.ItemService;
import project.service.OrderService;
import project.utils.PagingUtil;
import project.utils.StatByDateHolder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controller class for "/manage" mapping.
 */
@Controller
@RequestMapping("/manage")
@SessionAttributes({"itemGroups", "categories"})
public class AdminController {

//    private static final String PATH = "admin/";
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PagingUtil pagingUtil;

    @GetMapping(value = "/")
    public String showAdminPage() {
        return "admin/adminPage";
    }

    @GetMapping(value = "/orders")
    public String getAllOrders(@RequestParam(value = "page") Integer page,
                               Model model) {
        int numOfPages = pagingUtil.getNumOfPages(orderService.findAll().size());
        List<OrderDTO> all = pagingUtil.getOrdersForPage(page);

        model.addAttribute("currentPage", page);
        model.addAttribute("numOfPages", numOfPages);
        model.addAttribute("orders", all);
        model.addAttribute("thisOrder", new OrderDTO());
        return "admin/orders";
    }

    @PostMapping(value = "/orders/edit")
    public String editOrder(@ModelAttribute OrderDTO order) {
        orderService.updateOrderInformation(order);
        return "redirect:/manage/orders?page=1";
    }

    @GetMapping(value = "/statistics")
    public String getStatisticsPage(Model model, HttpServletRequest request) throws NoSuchClientException {
        List<CartDTO> topTenItems = itemService.getTopTenItems();
        List<Map.Entry<ClientDTO, Integer>> topTenClients = clientService.getTopTenClients();

        request.getSession().setAttribute("topItems", topTenItems);
        request.getSession().setAttribute("topClients", topTenClients);
        model.addAttribute("statDateForm", new StatByDateHolder());
        return "admin/statistics";
    }

    @PostMapping(value = "/statistics")
    public String getDatesStats(@ModelAttribute StatByDateHolder holder, Model model) {
        holder = orderService.getSalesBetweenDates(holder);
        if (holder.getOrders().size() == 0) {
            model.addAttribute("err", "No sales in this period");
        }
        model.addAttribute("statDateForm", holder);
        return "admin/statistics";
    }

    @GetMapping(value = "/items")
    public String getItemsEditPage(@RequestParam(value = "page") Integer page, Model model) {
        int numOfPages = pagingUtil.getNumOfPages(itemService.findAll().size());
        List<ItemDTO> allItems = pagingUtil.getItemsForPage(page);

        model.addAttribute("currentPage", page);
        model.addAttribute("numOfPages", numOfPages);
        model.addAttribute("allItems", allItems);
        model.addAttribute("itemToEdit", new ItemDTO());
        return "admin/items";
    }

    @GetMapping(value = "/editItem")
    public String editOrAddItem(@ModelAttribute ItemDTO item, Model model) {
        ItemDTO itemToEdit = itemService.editOrAddItem(item);
        model.addAttribute("itemToEdit", itemToEdit);
        return "admin/editItem";
    }

    @PostMapping(value = "/editItem")
    public String editItem(@Valid @ModelAttribute ItemDTO item, BindingResult result,
                           @RequestParam("file") MultipartFile file, HttpServletRequest request,
                           Model model)
            throws IMGUploadException {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("itemToEdit", item);
            return "admin/editItem";
        }

        String filePath = request.getServletContext().getRealPath("/") + "img/";
        itemService.saveOrUpdate(item, file, filePath);
        return "redirect:/manage/items?page=1";
    }

    @GetMapping(value = "/categories")
    public String getAllCategories() {
        return "/admin/categories";
    }

    @GetMapping(value = "/categories/{category}")
    public String getCategoryName(@PathVariable("category") String category, Model model) {
        model.addAttribute("cat", category);
        return "/admin/categories";
    }

    @GetMapping(value = "/categoriesEdit")
    public String editCat(@RequestParam("new") String newName,
                          @RequestParam("old") String oldName,
                          Model model) {

        model.addAttribute("categories", itemService.renameGroup(oldName, newName));
        return "redirect:/manage/categories";
    }

    @GetMapping(value = "/categoriesAdd")
    public String addCat(@RequestParam("c") String cat,
                         @SessionAttribute("categories") List<String> categories,
                         Model model) {
        categories.add(cat);
        model.addAttribute("categories", categories);
        return "redirect:/manage/categories";
    }

    @GetMapping(value = "/categoriesDel")
    public String delCat(@RequestParam("c") String cat,
                         @SessionAttribute("categories") List<String> categories,
                         Model model) {
        itemService.deleteGroup(cat);
        categories.remove(cat);
        model.addAttribute("categories", categories);
        return "redirect:/manage/items?page=1";
    }
}
