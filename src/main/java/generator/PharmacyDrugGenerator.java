package generator;

import model.Drug;
import model.Pharmacy;
import model.PharmacyDrug;
import org.instancio.Instancio;

import java.math.BigDecimal;

import static org.instancio.Select.field;

public class PharmacyDrugGenerator {
    public static PharmacyDrug generatePharmacyDrug(Pharmacy pharmacy, Drug drug) {
        return Instancio.of(PharmacyDrug.class)
                .set(field(PharmacyDrug.class, "pharmacy"), pharmacy)
                .set(field(PharmacyDrug.class, "drug"), drug)
                .generate(field(PharmacyDrug.class, "price"), gen -> gen.math().bigDecimal().range(BigDecimal.valueOf(10), BigDecimal.valueOf(200)))
                .create();
    }
}
