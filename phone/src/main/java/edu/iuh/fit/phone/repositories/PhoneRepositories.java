package edu.iuh.fit.phone.repositories;

import edu.iuh.fit.phone.entities.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepositories extends CrudRepository<Phone, Long> {
}
