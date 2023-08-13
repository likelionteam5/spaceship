package study.security.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.security.Entity.Delivery;
import study.security.Repository.DeliveryRepository;
import study.security.Service.DeliveryService;
import study.security.dto.DeliveryDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryRepository deliveryRepository;

    @PostMapping // 게시물 작성
    public ResponseEntity<Delivery> createDelivery(@RequestBody DeliveryDto deliveryDto) {
        Delivery delivery = new Delivery();
        deliveryDto.toDeliveryEntity(delivery);
        Delivery createdDelivery = deliveryService.createDelivery(delivery);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    @GetMapping("/list") // 전체 게시물 조회
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> allDeliveries = deliveryService.getAllDeliveries();
        return new ResponseEntity<>(allDeliveries, HttpStatus.OK);
    }


    @GetMapping("/list/{location}") // 지역으로 조회
    public ResponseEntity<Delivery> findByLocation(@PathVariable String location) {
        Optional<Delivery> delivery = deliveryService.findByLocation(location);
        return delivery.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @GetMapping("/{id}") // 게시물 상세 조회
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryService.getDeliveryById(id);
        return delivery.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PatchMapping("/{id}") // 게시물 일부 수정
    public ResponseEntity<Delivery> updateDelivery(@PathVariable Long id, @RequestBody DeliveryDto deliveryDto) {
        Optional<Delivery> existingDeliveryOptional = deliveryService.getDeliveryById(id);

        if (existingDeliveryOptional.isPresent()) {
            Delivery existingDelivery = existingDeliveryOptional.get();
            deliveryDto.toDeliveryEntity(existingDelivery);

            Delivery updatedDelivery = deliveryService.updateDelivery(id, existingDelivery);
            return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}") // 게시물 삭제
    public ResponseEntity<String> deleteDelivery(@PathVariable Long id) {
        if (deliveryRepository.existsById(id)) {
            deliveryRepository.deleteById(id);
            return new ResponseEntity<>("글 삭제 완료", HttpStatus.OK);
        }
        return new ResponseEntity<>("삭제할 게시물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }
}