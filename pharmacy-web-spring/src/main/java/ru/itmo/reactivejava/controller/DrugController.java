package ru.itmo.reactivejava.controller;

import jakarta.transaction.Transactional;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.model.PharmacyDrug;
import ru.itmo.reactivejava.payload.request.DrugRequest;
import ru.itmo.reactivejava.payload.request.OrderRequest;
import ru.itmo.reactivejava.payload.request.PharmacyDrugRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.service.DrugService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class DrugController {
    private final DrugService drugService;


    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @PostMapping("/drugs/add")
    public Mono<ResponseEntity<MessageResponse>> addDrug(@RequestBody DrugRequest drugRequest) {
        return drugService.addDrug(drugRequest)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .onErrorResume(ex -> {
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(new MessageResponse("Error: " + ex.getMessage())));
                });
    }

    @GetMapping("/getPrice/{pharmacyId}/{drugId}")
    public Mono<ResponseEntity<Float>> findPrice(@PathVariable("pharmacyId") long pharmacyId,
                                                 @PathVariable("drugId") long drugId) {
        return drugService.findPrice(pharmacyId, drugId)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PostMapping("/drugs/shipment/{pharmacyId}/{drugId}")
    public Mono<ResponseEntity<MessageResponse>> addDrugInPharmacy(@RequestBody PharmacyDrugRequest pharmacyDrugRequest) {
        return drugService.addDrugToPharmacy(pharmacyDrugRequest)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .onErrorResume(ex -> {
                    return Mono.just(ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(new MessageResponse("Error: " + ex.getMessage())));
                });
    }

    @PostMapping("/order/createOrder")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Mono<MessageResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        Map<Pair<Long, Long>, Integer> drugMap = new HashMap<>();

        for (PharmacyDrug drug : orderRequest.getDrugs()) {
            Pair<Long, Long> key = Pair.of(drug.getPharmacyId(), drug.getDrugId());
            drugMap.put(key, drugMap.getOrDefault(key, 0) + drug.getQuantity());
        }

        return Flux.fromIterable(drugMap.entrySet())
                .flatMapSequential(entry -> drugService.reduceQuantity(entry.getKey().getLeft(), entry.getKey().getRight(), entry.getValue()))
                .then(Mono.just(new MessageResponse("Заказ успешно обработан")))
                .onErrorResume(e -> {
                    System.err.println("Ошибка при создании заказа: " + e.getMessage());
                    if (e instanceof ResponseStatusException) {
                        return Mono.error(e);
                    } else {
                        return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера"));
                    }
                });
    }
}
