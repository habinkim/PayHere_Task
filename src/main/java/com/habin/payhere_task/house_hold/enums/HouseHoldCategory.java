package com.habin.payhere_task.house_hold.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum HouseHoldCategory {

    SPENDING(HanNms.SPENDING),
    SALARY(HanNms.INCOME_SALARY),
    POCKET_MONEY(HanNms.INCOME_POCKET_MONEY),
    DEPOSIT(HanNms.TRANSFER_DEPOSIT),
    WITHDRAWAL(HanNms.TRANSFER_WITHDRAWAL);

    private String hanNm;

    public static class HanNms {
        public static final String SPENDING = "지출";
        public static final String INCOME_SALARY = "수입 - 급여";
        public static final String INCOME_POCKET_MONEY = "수입 - 용돈";
        public static final String TRANSFER_DEPOSIT = "이체 - 입금";
        public static final String TRANSFER_WITHDRAWAL = "이체 - 출금";
    }

}
