package maro.bcht.bankaccountservice;

import maro.bcht.bankaccountservice.entities.BankAccount;
import maro.bcht.bankaccountservice.entities.Customer;
import maro.bcht.bankaccountservice.enums.AccountType;
import maro.bcht.bankaccountservice.repositories.BankAccountRepository;
import maro.bcht.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository,
                                        CustomerRepository customerRepository) {
        return args -> {
            Stream.of("Marouane","Hamza","Oussama","Mohamed").forEach(c ->{
                Customer customer = Customer.builder()
                        .name(c)
                        .build();
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(customer -> {
                for (int i = 0; i<10; i++) {
                    BankAccount account = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .accountType(Math.random()>0.5 ? AccountType.SAVING_ACCOUNT : AccountType.CURRENT_ACCOUNT)
                            .balance(777 + Math.random() * 999)
                            .currency("MAD")
                            .createAt(new Date())
                            .customer(customer)
                            .build();
                    bankAccountRepository.save(account);
                }
            });
        };
    }
}
