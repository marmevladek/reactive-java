package ru.itmo.reactivejava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.itmo.reactivejava.payload.request.DrugRequest;
import ru.itmo.reactivejava.payload.request.OrderRequest;
import ru.itmo.reactivejava.payload.response.MessageResponse;
import ru.itmo.reactivejava.service.DrugService;


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

    @GetMapping("/drugs/getPrice/{pharmacyId}/{drugId}")
    public Mono<ResponseEntity<Float>> findPrice(@PathVariable("pharmacyId") long pharmacyId, @PathVariable("drugId") long drugId) {
        return drugService.findPrice(pharmacyId, drugId)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @PostMapping("/order/createOrder")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MessageResponse> createOrder(@RequestBody OrderRequest orderRequest) {

        // юзается генератор заказов

        /*
        Пример использования reduceQuantity, чтобы убавить количество каждого купленного товара

        for (PharmacyDrug drug : drugs) {
            drugService.reduceQuantity(drug.getPharmacyId(), drug.getPharmacyId(), drugs.size());
        }

         */

        return null;
    }




//    метод изменения количества юзается внутри создания заказа
//
//    @PutMapping("/drugs/reduceQuantity/{pharmacyId}/{drugId}/quantity")
//    @ResponseStatus(HttpStatus.OK)
//    public Mono<Void> reduceQuantity(@PathVariable("pharmacyId") long pharmacyId, @PathVariable("drugId") long drugId, @RequestParam int quantity) {
//        return drugService.reduceQuantity(pharmacyId, drugId, quantity);
//    }
}
