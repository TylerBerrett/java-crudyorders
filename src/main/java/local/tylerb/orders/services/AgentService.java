package local.tylerb.orders.services;

import local.tylerb.orders.models.Agents;

public interface AgentService {

    Agents findById(long id);

}
