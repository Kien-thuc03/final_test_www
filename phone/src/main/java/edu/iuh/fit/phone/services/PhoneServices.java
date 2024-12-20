package edu.iuh.fit.phone.services;

import edu.iuh.fit.phone.entities.Phone;
import edu.iuh.fit.phone.repositories.PhoneRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServices {
    @Autowired
    private PhoneRepositories phoneRepositories;

    public Phone save(Phone phone) {
        return phoneRepositories.save(phone);
    }

    public List<Phone> findAll() {
        return (List<Phone>) phoneRepositories.findAll();
    }

    public Phone delete(Phone phone) {
        phoneRepositories.delete(phone);
        return phone;
    }

    public Phone update(Phone phone) {
        return phoneRepositories.save(phone);
    }


    public Phone findById(Long id) {
        return phoneRepositories.findById(id).get();
    }
}
