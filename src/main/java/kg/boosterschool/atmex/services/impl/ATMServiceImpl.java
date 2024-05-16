package kg.boosterschool.atmex.services.impl;

import kg.boosterschool.atmex.dto.requestDto.CardNumAndAmountDto;
import kg.boosterschool.atmex.dto.requestDto.CardNumAndPinDto;
import kg.boosterschool.atmex.dto.responseDto.BalanceAndBlockedBalanceDto;
import kg.boosterschool.atmex.dto.requestDto.GetMoneyDto;
import kg.boosterschool.atmex.dto.responseDto.NotDto;
import kg.boosterschool.atmex.dto.responseDto.OkDto;
import kg.boosterschool.atmex.dto.responseDto.PinCodeInterface;
import kg.boosterschool.atmex.models.AccountHistories;
import kg.boosterschool.atmex.models.Balance;
import kg.boosterschool.atmex.models.CardAccount;
import kg.boosterschool.atmex.models.EntryTrieCount;
import kg.boosterschool.atmex.repositoryes.AccountHistoriesRepo;
import kg.boosterschool.atmex.repositoryes.BalanceRepo;
import kg.boosterschool.atmex.repositoryes.CardAccountRepo;
import kg.boosterschool.atmex.repositoryes.EntryTrieCountRepo;
import kg.boosterschool.atmex.services.ATMService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ATMServiceImpl implements ATMService {
    private final CardAccountRepo cardAccountRepo;
    private final BalanceRepo balanceRepo;
    private final EntryTrieCountRepo entryTrieCountRepo;
    private final AccountHistoriesRepo accountHistoriesRepo;

    public ATMServiceImpl(CardAccountRepo cardAccountRepo, BalanceRepo balanceRepo, EntryTrieCountRepo entryTrieCountRepo, AccountHistoriesRepo accountHistoriesRepo) {
        this.cardAccountRepo = cardAccountRepo;
        this.balanceRepo = balanceRepo;
        this.entryTrieCountRepo = entryTrieCountRepo;
        this.accountHistoriesRepo = accountHistoriesRepo;
    }

    @Override
    public PinCodeInterface checkingPINCode(CardNumAndPinDto cardNumAndPin) {
        CardAccount cardAccount = cardAccountRepo.findCardAccountByAccountNumber(cardNumAndPin.cardNum());
        Balance balance = balanceRepo.findBalanceByCardAccountId(cardAccount.getId());
        EntryTrieCount entryTrieCount = entryTrieCountRepo.findEntryTrieCountByCardAccountId(cardAccount.getId());

        if (cardAccount.getPin().equals(cardNumAndPin.pin()) && entryTrieCount.getTrieCount()!=entryTrieCount.getMaxCount()){
            entryTrieCountRepo.changeToZero(entryTrieCount.getId());
            return new OkDto("OK",balance.getBalance(),cardAccount.isActive());
        }else {
            if (entryTrieCount.getTrieCount() == entryTrieCount.getMaxCount()){
                return new NotDto("КАРТА БЛОКИРОВАНО",entryTrieCount.getTrieCount(),false);
            }else {
                entryTrieCountRepo.changeTrieCount(entryTrieCount.getId());

                return new NotDto("Неправильный PIN-CODE",(entryTrieCountRepo.findEntryTrieCountByCardAccountId(
                        cardAccount.getId()).getTrieCount())+1,false);

            }
        }
    }

    @Override
    public BalanceAndBlockedBalanceDto getBalanceAndBlockedBalance(CardNumAndAmountDto cardNumAndAmountDto) {
        CardAccount cardAccount = cardAccountRepo.findCardAccountByAccountNumber(cardNumAndAmountDto.cardNum());
        Balance balance = balanceRepo.findBalanceByCardAccountId(cardAccount.getId());
        balanceRepo.changeBlockedBalance(cardNumAndAmountDto.amount(), balance.getId());
        if (balance.getBalance() >= cardNumAndAmountDto.amount()){
            return new BalanceAndBlockedBalanceDto(balance.getBalance(), cardNumAndAmountDto.amount());
        }else {
            return new BalanceAndBlockedBalanceDto(balance.getBalance(),0);
        }
    }

    @Override
    public String getMoney(GetMoneyDto getMoneyDto) {
        CardAccount cardAccount = cardAccountRepo.findCardAccountByAccountNumber(getMoneyDto.cardNum());
        Balance balance = balanceRepo.findBalanceByCardAccountId(cardAccount.getId());
        if (balance.getBalance() >= balance.getBlockedBalance()) {
            balanceRepo.changeBalance(balance.getBlockedBalance(), balance.getId());
            AccountHistories accountHistories = new AccountHistories();
            accountHistories.setAmount(balance.getBlockedBalance());
            LocalDateTime currentDateTime = LocalDateTime.now();
            accountHistories.setAddDate(currentDateTime);
            accountHistories.setCardAccount(cardAccount);
            accountHistoriesRepo.save(accountHistories);
            return "Commit !";
        }else {
            return "Rollback !";
        }
    }

}
