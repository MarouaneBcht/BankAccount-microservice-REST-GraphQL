package maro.bcht.bankaccountservice.mappers;

import maro.bcht.bankaccountservice.dto.BankAccountResponseDTO;
import maro.bcht.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount account) {
        BankAccountResponseDTO responseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(account,responseDTO);
        return responseDTO;
    }
}
