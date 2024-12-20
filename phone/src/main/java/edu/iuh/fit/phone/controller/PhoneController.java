package edu.iuh.fit.phone.controller;

import edu.iuh.fit.phone.entities.Phone;
import edu.iuh.fit.phone.services.PhoneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PhoneController {
    @Autowired
    private PhoneServices phoneServices;

    @RequestMapping("/phones")
    public String showPhoneList(Model model) {
        model.addAttribute("phones", phoneServices.findAll());
        return "phones/list-phone";
    }

    @RequestMapping(value = "/phones/add", method = RequestMethod.GET)
    public String addPhone(Model model) {
        model.addAttribute("phone", new Phone());
        return "phones/add-phone";
    }
    @RequestMapping(value = "/phones/add", method = RequestMethod.POST)
    public String savePhone(Phone phone) {
        phoneServices.save(phone);
        return "redirect:/phones";
    }

    @RequestMapping(value = "phones/edit/{maDienThoai}", method = RequestMethod.GET)
    public String editPhone(Model model, @PathVariable("maDienThoai") Long maDienThoai) {
        model.addAttribute("phone", phoneServices.findById(maDienThoai));
        return "phones/edit-phone";
    }
    @RequestMapping(value = "phones/edit/{maDienThoai}", method = RequestMethod.POST)
    public String updatePhone(@PathVariable("maDienThoai") Long maDienThoai, Phone phone) {
        phone.setMaDienThoai(maDienThoai);
        phoneServices.save(phone);
        return "redirect:/phones";
    }

    @RequestMapping(value = "phones/delete/{maDienThoai}", method = RequestMethod.GET)
    public String deletePhone(@PathVariable("maDienThoai") Long maDienThoai) {
        phoneServices.delete(phoneServices.findById(maDienThoai));
        return "redirect:/phones";
    }

}
