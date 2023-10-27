package maro.bcht.bankaccountservice.web;

import maro.bcht.bankaccountservice.dto.BankAccountRequestDTO;
import maro.bcht.bankaccountservice.dto.BankAccountResponseDTO;
import maro.bcht.bankaccountservice.entities.BankAccount;
import maro.bcht.bankaccountservice.mappers.AccountMapper;
import maro.bcht.bankaccountservice.repositories.BankAccountRepository;
import maro.bcht.bankaccountservice.services.IAccountService;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountRestController {
    private IAccountService accountService;
    private BankAccountRepository bankAccountRepository;
    private AccountMapper mapper;

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO createAccount(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount updateAccount(@PathVariable String id, @RequestBody BankAccount account) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        if (account.getBalance()!=null) bankAccount.setBalance(account.getBalance());
        if (account.getCreateAt()!=null) bankAccount.setCreateAt(account.getCreateAt());
        if (account.getCurrency()!=null) bankAccount.setCurrency(account.getCurrency());
        if (account.getAccountType()!=null) bankAccount.setAccountType(account.getAccountType());
        return bankAccountRepository.save(bankAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAcccountById(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
