package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    public ModelAndView test() {
        System.out.println(productService.findById(1L));
        return null;
    }
}
