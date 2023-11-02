package maro.bcht.bankaccountservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import maro.bcht.bankaccountservice.dto.BankAccountRequestDTO;
import maro.bcht.bankaccountservice.dto.BankAccountResponseDTO;
import maro.bcht.bankaccountservice.entities.BankAccount;
import maro.bcht.bankaccountservice.mappers.AccountMapper;
import maro.bcht.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{
    private BankAccountRepository bankAccountRepository;
    private AccountMapper mapper;


    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount account = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .currency(bankAccountRequestDTO.getCurrency())
                .accountType(bankAccountRequestDTO.getAccountType())
                .build();
        BankAccount bankAccount = bankAccountRepository.save(account);
        BankAccountResponseDTO responseDTO = mapper.fromBankAccount(bankAccount);
        return responseDTO;
    }


    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount account = BankAccount.builder()
                .id(id)
                .createAt(new Date())
                .balance(bankAccountRequestDTO.getBalance())
                .currency(bankAccountRequestDTO.getCurrency())
                .accountType(bankAccountRequestDTO.getAccountType())
                .build();
        BankAccount bankAccount = bankAccountRepository.save(account);
        BankAccountResponseDTO responseDTO = mapper.fromBankAccount(bankAccount);
        return responseDTO;
    }

}
