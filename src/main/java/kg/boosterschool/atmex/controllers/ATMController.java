package kg.boosterschool.atmex.controllers;

import kg.boosterschool.atmex.dto.requestDto.CardNumAndAmountDto;
import kg.boosterschool.atmex.dto.requestDto.CardNumAndPinDto;
import kg.boosterschool.atmex.dto.responseDto.BalanceAndBlockedBalanceDto;
import kg.boosterschool.atmex.dto.requestDto.GetMoneyDto;
import kg.boosterschool.atmex.dto.responseDto.OkDto;
import kg.boosterschool.atmex.dto.responseDto.PinCodeInterface;
import kg.boosterschool.atmex.services.impl.ATMServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ATMController {
    private final ATMServiceImpl atmService;

    public ATMController(ATMServiceImpl atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/pin/check")
    public ResponseEntity<PinCodeInterface> checkPinCode(@RequestBody CardNumAndPinDto cardNumAndPin){
        return ResponseEntity.ok(atmService.checkingPINCode(cardNumAndPin));
    }

    @PutMapping("/account/withdraw")
    public ResponseEntity<BalanceAndBlockedBalanceDto> getBalanceAndBlockedBalance(@RequestBody CardNumAndAmountDto cardNumAndAmountDto){
        return ResponseEntity.ok(atmService.getBalanceAndBlockedBalance(cardNumAndAmountDto));
    }

    @PutMapping("/account/withdraw/close")
    public ResponseEntity<String> getMoney(@RequestBody GetMoneyDto getMoneyDto){
            return ResponseEntity.ok( atmService.getMoney(getMoneyDto) );
    }
}
