package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import ro.msg.learning.shop.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
        Customer findByUsername(String username);
        Customer findByEmailAddress(String email);
}
