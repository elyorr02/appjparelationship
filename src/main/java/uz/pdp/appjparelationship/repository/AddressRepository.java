package uz.pdp.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationship.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
