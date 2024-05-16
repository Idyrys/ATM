package kg.boosterschool.atmex.services;

import kg.boosterschool.atmex.dto.requestDto.CardNumAndAmountDto;
import kg.boosterschool.atmex.dto.requestDto.CardNumAndPinDto;
import kg.boosterschool.atmex.dto.responseDto.BalanceAndBlockedBalanceDto;
import kg.boosterschool.atmex.dto.requestDto.GetMoneyDto;
import kg.boosterschool.atmex.dto.responseDto.PinCodeInterface;

public interface ATMService {
    PinCodeInterface checkingPINCode(CardNumAndPinDto cardNumAndPin);
    BalanceAndBlockedBalanceDto getBalanceAndBlockedBalance(CardNumAndAmountDto cardNumAndAmountDto);
    String getMoney(GetMoneyDto getMoneyDto);
}
