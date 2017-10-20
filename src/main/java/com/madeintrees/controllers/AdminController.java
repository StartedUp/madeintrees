package com.madeintrees.controllers;

import com.madeintrees.dao.RoleDao;
import com.madeintrees.model.Product;
import com.madeintrees.model.User;
import com.madeintrees.service.ProductManager;
import com.madeintrees.service.RoleManager;
import com.madeintrees.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Prithu on 21-03-2017.
 */
@Controller
public class AdminController {
    @Autowired
    private UserManager userManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private ProductManager productManager;
    List<Product> products;
    @Value("${LOCAL_UPLOAD_PATH}")
    private String LOCAL_UPLOAD_PATH;
    //private String LOCAL_UPLOAD_PATH="/home/gokulraj/Repository/MyRepo/madeintrees/src/main/resources/static/images/products/";
    @RequestMapping(value = "/")
    public String adminRoot(@ModelAttribute("user")User user){
        System.out.println(userManager.findByEmail("admin@madeintrees.com"));
        return "admin";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "userLogin";
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String signupPage(){
        return "userSignup";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user")User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userManager.save(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "/admin")
    public String showAdminPage(){
        return "adminHome";
    }
    @GetMapping("/addProduct")
    public String addProduct(){
        return "addProductPage";
    }

    @PostMapping(value = "/addThisProduct")
    public String register(@ModelAttribute("product")Product product, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("addProductError",true);
            return "addProductPage";
        }
        productManager.save(product);
        model.addAttribute("addProductSuccess",true);
        return "redirect:/listProducts";
    }
    @GetMapping("/listUsers")
    public String listUsers(){
        return "redirect:/listProducts";
    }
    @GetMapping("/accounts")
    public String showAccounts(){
        return "accounts";
    }
    @GetMapping(value = "/listProducts")
    public String showProducts(Model model){
        products = productManager.findAll();
        model.addAttribute("products", products);
        return "listProducts";
    }
    @PostMapping("/removeProduct")
    public String removeProduct(@RequestParam("productId") int productId, Model model){
        productManager.deleteById(productId);
        model.addAttribute("removeProductSuccess",true);
        return "redirect:/listProducts";
    }
    @RequestMapping("/uploadImages")
    public String showUploadImagePage(Model model){
        products = productManager.findAll();
        model.addAttribute("products", products);
        return "uploadProductImages";
    }
    @PostMapping("/addProductImages")
    public String uploadAlbumToGallery(@RequestParam("productName") String productName,
                                       @RequestParam("productImgSize") String productImgSize,
                                       @RequestParam("image")MultipartFile[] files,
                                       RedirectAttributes redirectAttributes,
                                        Model model)
    {
        System.out.println(productName+"...."+productImgSize+"..."+files.length+" files");
        //Creating a new directory with Product Name
        new File(LOCAL_UPLOAD_PATH + productName).mkdir();
        new File(LOCAL_UPLOAD_PATH + productName +"/"+ productImgSize).mkdir();
        //Save the uploaded file to this folder
        String UPLOADED_FOLDER = LOCAL_UPLOAD_PATH+productName+"/"+ productImgSize+"/";
        System.out.println(UPLOADED_FOLDER);
        for (MultipartFile file:files){
            if (files.length==0){
                redirectAttributes.
                        addFlashAttribute("message","please upload a picture and submit");
            }
            try {
                byte[] bytes=file.getBytes();
                Path path= Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path,bytes);
            }catch (Exception e){
                e.printStackTrace();
                model.addAttribute("uploadStatus","Upload Failed. An Exception..!!!");
                return "uploadProductImages";
            }}
        redirectAttributes.
                addFlashAttribute("message","   You have successfully uploaded "+files.length+" files");
        model.addAttribute("totalFiles",files.length);
        model.addAttribute("uploadStatus", true);
        return "redirect:/listProducts";
    }
    @RequestMapping("/photoGallery")
    public String showphotoGalleryHome(Model model){
        try {
            File file = new File(LOCAL_UPLOAD_PATH);
            String[] names = file.list();
            List albumNames =new ArrayList();
            for(String name : names) {
                if (new File(LOCAL_UPLOAD_PATH+ name).isDirectory())
                    albumNames.add(name);
            }
            model.addAttribute("albumNames",albumNames);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "photoGalleryHome";
    }
    @RequestMapping("/showAlbum/{albumName}")
    public String showAlbum(Model model, @PathVariable("albumName") String albumName){
        String[] imageFiles=null;
        try {
            File file = new File(LOCAL_UPLOAD_PATH + albumName+"/");
            imageFiles = file.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("path","/img/gallery/");
        model.addAttribute("heading",albumName);
        model.addAttribute("albumName",albumName);
        model.addAttribute("imageFiles",imageFiles);
        return "photoGallery";
    }
}
