package guru.springframework.creditcard.repositories;

import guru.springframework.creditcard.domain.CreditCard;
import guru.springframework.creditcard.services.EncryptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = {"local"})
@SpringBootTest
class CreditCardRepositoryTest {

    public final static String CREDIT_CARD_NR = "12345678900000";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    EncryptionService encryptionServ;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testSaveAndStoreCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NR);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCC = creditCardRepository.saveAndFlush(creditCard);

        System.out.println("######## Getting CC-Nr. from database ########");
        System.out.println(savedCC.getCreditCardNumber());
        System.out.println("######## Getting CC-Nr. at Rest ########");
        System.out.println(encryptionServ.encrypt(CREDIT_CARD_NR));

        Optional<CreditCard> fetchedCCOpt = creditCardRepository.findById(savedCC.getId());

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("SELECT * FROM credit_card " +
                "WHERE id="+savedCC.getId());

        String dbCardNr = (String) dbRow.get("credit_card_number");

        assertThat(dbCardNr)
                .isNotEqualTo(CREDIT_CARD_NR)
                .isEqualTo(encryptionServ.encrypt(CREDIT_CARD_NR));


        assertThat(fetchedCCOpt).isNotEmpty();
        assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCCOpt.get().getCreditCardNumber());

    }
}