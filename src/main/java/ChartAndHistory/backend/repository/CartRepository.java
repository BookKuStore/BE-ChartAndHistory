package ChartAndHistory.backend.repository;

import ChartAndHistory.backend.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}