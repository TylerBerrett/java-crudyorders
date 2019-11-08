package local.tylerb.orders.services;

import local.tylerb.orders.models.Agents;
import local.tylerb.orders.repos.AgentsRepo;
import local.tylerb.orders.repos.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

// what does this do?
@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {

    // does this inject the agent repo dependency?
    @Autowired
    private AgentsRepo agentsRepo;

    @Override
    public Agents findById(long id) {
        return agentsRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }
}
