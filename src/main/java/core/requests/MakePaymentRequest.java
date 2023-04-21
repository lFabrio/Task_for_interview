package main.java.core.requests;

import java.math.BigDecimal;

public class MakePaymentRequest {

    private final Long withdrawAccountId;
    private final Long depositAccountId;
    private final BigDecimal moneyAmount;

    public MakePaymentRequest(Long withdrawAccountId, Long depositAccountId, BigDecimal moneyAmount) {
        this.withdrawAccountId = withdrawAccountId;
        this.depositAccountId = depositAccountId;
        this.moneyAmount = moneyAmount;
    }

    public Long getWithdrawAccountId() {
        return withdrawAccountId;
    }

    public Long getDepositAccountId() {
        return depositAccountId;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }
}
