package guru.springframework.creditcard.repositories;

import guru.springframework.creditcard.domain.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = {"local"})
@SpringBootTest
class CreditCardRepositoryTest {

    public final static String CREDIT_CARD_NR = "12345678900000";

    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    void testSaveAndStoreCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditCardNumber(CREDIT_CARD_NR);
        creditCard.setCvv("123");
        creditCard.setExpirationDate("12/2028");

        CreditCard savedCC = creditCardRepository.saveAndFlush(creditCard);

        System.out.println("######## Getting CC-Nr. from database ########");

        CreditCard fetchedCC = creditCardRepository.findById(savedCC.getId()).orElse(null);

        assertThat(savedCC.getCreditCardNumber()).isEqualTo(fetchedCC.getCreditCardNumber());

    }
}