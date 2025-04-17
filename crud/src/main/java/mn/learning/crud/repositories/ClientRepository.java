package mn.learning.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mn.learning.crud.modules.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	public Client findByEmail(String email);

}
