package ru.itmo.reactivejava.generator;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.controller.DrugController;
import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.payload.request.OrderRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.repository.PharmacyDrugRepository;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class OrderGenerator {
    private final DrugController drugController;

    private final PharmacyDrugRepository pharmacyDrugRepository;

    public OrderGenerator(DrugController drugController, PharmacyDrugRepository pharmacyDrugRepository) {
        this.drugController = drugController;
        this.pharmacyDrugRepository = pharmacyDrugRepository;
    }

    public void startGeneratingOrders() {
        Flux.interval(Duration.ofMillis(10))
                .flatMap(tick -> generateRandomOrder()
                        .onErrorResume(error -> {
                            System.err.println("Ошибка при создании заказа: " + error.getMessage());
                            return Mono.empty();
                        })
                )
                .subscribe(
                        orderResponse -> System.out.println("Заказ успешно создан: " + orderResponse),
                        error -> System.err.println("Неожиданная ошибка: " + error.getMessage())
                );
    }

    private Mono<MessageResponse> generateRandomOrder() {
        return pharmacyDrugRepository.findAll()
                .filter(drug -> drug.getQuantity() > 0)
                .collectList()
                .flatMap(drugs -> {
                    if (drugs.isEmpty()) {
                        return Mono.error(new IllegalStateException("Нет доступных лекарств для заказа!"));
                    }

                    Random random = new Random();
                    int numDrugs = random.nextInt(3) + 1;

                    Collections.shuffle(drugs);

                    List<PharmacyDrug> selectedDrugs = drugs.subList(0, Math.min(drugs.size(), numDrugs));

                    OrderRequest orderRequest = new OrderRequest();
                    orderRequest.setDrugs(selectedDrugs);

                    for (int i = 0; i < numDrugs; i++) {
                        orderRequest.getDrugs().get(i).setQuantity(random.nextInt(3) + 1);
                    }

                    return sendCreateOrderRequest(orderRequest)
                            .flatMap(response ->
                                    checkAndRestockDrugs(selectedDrugs)
                                            .then(Mono.just(response))
                            )
                            .onErrorResume(error -> {
                                System.err.println("Ошибка при создании заказа или пополнении: " + error.getMessage());
                                return Mono.empty();
                            });
                });
    }


    private Mono<MessageResponse> sendCreateOrderRequest(OrderRequest orderRequest) {
        System.out.println(orderRequest.getDrugs());
        return drugController.createOrder(orderRequest);
    }

    private Mono<Void> checkAndRestockDrugs(List<PharmacyDrug> drugs) {
        return Flux.fromIterable(drugs)
                .flatMap(pharmacyDrug ->
                        pharmacyDrugRepository.findByDrugId(pharmacyDrug.getDrugId())
                                .filter(drug -> drug.getQuantity() < 50)
                                .flatMap(this::restockDrug)
                )
                .onErrorContinue((error, drug) -> {
                    System.err.println("Ошибка при пополнении лекарства: " + error.getMessage());
                })
                .then();
    }

    private Mono<Integer> restockDrug(PharmacyDrug pharmacyDrug) {
        return pharmacyDrugRepository.findByPharmacyIdAndDrugId(pharmacyDrug.getPharmacyId(), pharmacyDrug.getDrugId())
                .flatMap(drug -> pharmacyDrugRepository.updateQuantity(pharmacyDrug.getPharmacyId(), pharmacyDrug.getDrugId(), drug.getQuantity() + 100)
                )
                .onErrorResume(error -> {
                    System.err.println("Ошибка при пополнении: " + error.getMessage());
                    return Mono.empty();
                });

    }
}