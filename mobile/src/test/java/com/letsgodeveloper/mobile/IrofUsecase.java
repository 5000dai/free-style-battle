package com.letsgodeveloper.mobile;
import com.letsgodeveloper.mobile.domain.charge.plan.BasePlan;
import com.letsgodeveloper.mobile.domain.charge.plan.data.FixedAmountPlan;
import java.util.List;
import java.util.Date;
import java.util.UUID;
import com.letsgodeveloper.mobile.domain.charge.ContractOption;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IrofUsecase {

    public static class Event {
        EventType eventType;
        Date eventDate;
    }

    public enum EventType {
        新規契約,
        機種変更
    }
    public static class DataStore {
        public List<Event> findEventByCurrentMonth(UUID userId, Date closingDate) {
            return List.of();
        }
    }

    private DataStore dataStore;

    private int calcFee(List<Event> events) {
        return events.stream().map(e -> e.eventType).anyMatch(e -> e == EventType.新規契約 || e == EventType.機種変更) ? 3000 : 0;
    }

    private List<Event> findEventByCurrentMonth(UUID userId, Date closingDate) {
        return dataStore.findEventByCurrentMonth(userId, closingDate);
    }


    @Test
    public void example() {
        dataStore = org.mockito.Mockito.mock(DataStore.class);

        var irof = UUID.randomUUID();
        var now = new Date();
        var basePlan = BasePlan.レッツゴーデベロッパー_X_プラン;

        var dataPlan = FixedAmountPlan.仮面データパックLL;

        var contractYears = 3;
        var mnp = false;
        var options = List.of(ContractOption.あんしん補償サービス,
                ContractOption.あんしん遠隔サポート,
                ContractOption.あんしんネットセキュリティ,
                ContractOption.あんしんパック,
                ContractOption.データ繰り越し,
                ContractOption.データ増量,
                ContractOption.データ快適モード);

        var events = findEventByCurrentMonth(irof, now);

        var fee = calcFee(events);
        var tax = new java.math.BigDecimal("0.1");
        var telUsageSec = 43;
        var dataUsageBytes = 560_000_000;

        var dataAmount = dataAmount(dataPlan, dataUsageBytes, now);
        var telAmount = telAmount(basePlan, telUsageSec, now);

        var longTermDiscountAmount = longTermDiscountAmount(contractYears);
        var nmpDiscountAmount = nmpDiscountAmount(mnp, contractYears);
        var optionAmount = optionAmount(options, irof, now);

        var actual = charge(telAmount, dataAmount, longTermDiscountAmount, nmpDiscountAmount, optionAmount, fee, tax);

        assertEquals(50_000, actual);
    }

    @Test
    void dataAmountTest() {


    }

    private int charge(int telAmount, int dataAmount, int longTermDiscountAmount, int nmpDiscountAmount, int optionAmount, int fee, java.math.BigDecimal tax) {
        return 50000;
    }

    private int optionAmount(List<ContractOption> options, UUID irof, Date now) {
        return 0;
    }

    private int nmpDiscountAmount(boolean mnp, int contractYears) {
        return 0;
    }

    private int longTermDiscountAmount(int contractYears) {
        return 0;
    }

    private int dataAmount(FixedAmountPlan dataPlan, int dataUsageBytes, Date date) {
        return 0;
    }

    private int telAmount(BasePlan basePlan, int telUsageSec, Date date) {
        return 0;
    }
}
