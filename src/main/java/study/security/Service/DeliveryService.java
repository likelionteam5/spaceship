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



    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> findByLocation(String location) {
        return deliveryRepository.findByLocation(location);
    }


    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }


    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        if (deliveryRepository.existsById(id)) {
            updatedDelivery.setId(id);
            return deliveryRepository.save(updatedDelivery);
        }
        return null;
    }
}