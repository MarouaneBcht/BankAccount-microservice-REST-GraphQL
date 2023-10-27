package maro.bcht.bankaccountservice.services;

import maro.bcht.bankaccountservice.dto.BankAccountRequestDTO;
import maro.bcht.bankaccountservice.dto.BankAccountResponseDTO;

public interface IAccountService {

    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountRequestDTO);

}
