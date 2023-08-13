package study.security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.security.Entity.Delivery;



@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}