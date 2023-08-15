package study.security.Service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.Entity.Delivery;
import study.security.Repository.DeliveryRepository;


import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class DeliveryService {

    private DeliveryRepository deliveryRepository;


    public Delivery createDelivery(Delivery delivery) { // 작성
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() { // 전체 조회
        return deliveryRepository.findAll();
    }

    public List<Delivery> findByLocation(String location) { // 지역으로 조회
        return deliveryRepository.findByLocation(location);
    }


    public Optional<Delivery> getDeliveryById(Long id) { // id로 조회
        return deliveryRepository.findById(id);
    }


    public Delivery updateDelivery(Long id, Delivery updatedDelivery) { // 수정
        if (deliveryRepository.existsById(id)) {
            updatedDelivery.setId(id);
            return deliveryRepository.save(updatedDelivery);
        }
        return null;
    }
}