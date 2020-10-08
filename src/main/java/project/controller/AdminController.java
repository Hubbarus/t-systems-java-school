package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.dto.CartDTO;
import project.dto.ClientDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.service.ItemService;
import project.service.OrderService;
import project.utils.PagingUtil;
import project.utils.StatByDateHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
@SessionAttributes({"itemGroups", "categories"})
public class AdminController {

//    private static final String PATH = "admin/";
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PagingUtil pagingUtil;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAdminPage() {
        return "admin/adminPage";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
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

    @RequestMapping(value = "/orders/edit", method = RequestMethod.POST)
    public String editOrder(@ModelAttribute OrderDTO order) {
        orderService.updateOrderInformation(order);
        return "redirect:/manage/orders?page=1";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String getStatisticsPage(Model model, StatByDateHolder holder, HttpServletRequest request) {
        List<CartDTO> topTenItems = orderService.getTopTenItems();
        List<Map.Entry<ClientDTO, Integer>> topTenClients = orderService.getTopTenClients();

        request.getSession().setAttribute("topItems", topTenItems);
        request.getSession().setAttribute("topClients", topTenClients);
        model.addAttribute("statDateForm", new StatByDateHolder());
        return "admin/statistics";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public String getDatesStats(@ModelAttribute StatByDateHolder holder, Model model) {
        holder = orderService.getSalesBetweenDates(holder);
        if (holder.getOrders().size() == 0) {
            model.addAttribute("err", "No sales in this period");
        }
        model.addAttribute("statDateForm", holder);
        return "admin/statistics";
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String getItemsEditPage(@RequestParam(value = "page") Integer page, Model model) {
        int numOfPages = pagingUtil.getNumOfPages(itemService.findAll().size());
        List<ItemDTO> allItems = pagingUtil.getItemsForPage(page);

        model.addAttribute("currentPage", page);
        model.addAttribute("numOfPages", numOfPages);
        model.addAttribute("allItems", allItems);
        model.addAttribute("itemToEdit", new ItemDTO());
        return "admin/items";
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.GET)
    public String editOrAddItem(@ModelAttribute ItemDTO item, Model model) {
        ItemDTO itemToEdit = new ItemDTO();
        if (item.getItemName() != null) {
            itemToEdit = itemService.findById(item.getId());
        }
        model.addAttribute("itemToEdit", itemToEdit);
        return "admin/editItem";
    }

    @RequestMapping(value = "/editItem", method = RequestMethod.POST)
    public String editItem(@ModelAttribute ItemDTO item) {
        itemService.saveOrUpdate(item);
        return "redirect:/manage/items?page=1";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String getAllCategories() {
        return "/admin/categories";
    }

    @RequestMapping(value = "/categories/{category}", method = RequestMethod.GET)
    public String getCategoryName(@PathVariable("category") String category, Model model) {
        model.addAttribute("cat", category);
        return "/admin/categories";
    }

    @RequestMapping(value = "/categoriesEdit", method = RequestMethod.GET)
    public String editCat(@RequestParam("new") String newName,
                          @RequestParam("old") String oldName,
                          HttpServletRequest request) {
        itemService.renameGroup(oldName, newName);
        request.getSession().setAttribute("categories", itemService.getGroupNames());
        return "redirect:/manage/categories";
    }

    @RequestMapping(value = "/categoriesAdd", method = RequestMethod.GET)
    public String addCat(@RequestParam("c") String cat,
                         @SessionAttribute("categories") List<String> categories,
                         HttpServletRequest request) {
        categories.add(cat);
        request.getSession().setAttribute("categories", categories);
        return "redirect:/manage/categories";
    }

    @RequestMapping(value = "/categoriesDel", method = RequestMethod.GET)
    public String delCat(@RequestParam("c") String cat,
                         @SessionAttribute("categories") List<String> categories,
                         HttpServletRequest request) {
        itemService.deleteGroup(cat);
        categories.remove(cat);
        request.getSession().setAttribute("categories", categories);
        return "redirect:/manage/orders?page=1";
    }
}
