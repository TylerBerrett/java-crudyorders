package local.tylerb.orders.repos;

import local.tylerb.orders.models.Agents;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepo extends CrudRepository<Agents, Long> {
}
