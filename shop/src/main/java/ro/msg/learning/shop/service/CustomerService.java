package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.User;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(s);
        if (customer == null) {
            throw new UsernameNotFoundException(String.format("Username[%s] not found"));
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        User springUser = new User(customer.getUsername(), customer.getPassword(), authorities);
        return springUser;
    }

    public Customer addUser(Customer customer) throws Exception{
        if (emailExist(customer.getEmailAddress())) {
            throw new Exception("There is an account with that email adress:" + customer.getEmailAddress());
        }
        Customer customer1 = new Customer(customer.getFirstName(), customer.getLastName(), customer.getUsername(), passwordEncoder.encode(customer.getPassword()), customer.getEmailAddress());
        return customerRepository.save(customer1);

    }

    public boolean emailExist(String email){
        if (customerRepository.findByEmailAddress(email) != null){
            return true;
        }
        return false;
    }


}
