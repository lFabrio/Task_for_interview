package main.java.core.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {

    private Long id;

    private final Long withdrawAccountId;
    private final Long depositAccountId;

    private final BigDecimal moneyAmount;
    private final Date paymentDate;

    public Payment(Long withdrawAccountId, Long depositAccountId, BigDecimal moneyAmount, Date paymentDate) {
        this.withdrawAccountId = withdrawAccountId;
        this.depositAccountId = depositAccountId;
        this.moneyAmount = moneyAmount;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", withdrawAccountId=" + withdrawAccountId +
                ", depositAccountId=" + depositAccountId +
                ", moneyAmount=" + moneyAmount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
