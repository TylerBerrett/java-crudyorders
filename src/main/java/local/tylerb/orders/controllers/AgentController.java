package local.tylerb.orders.controllers;

import local.tylerb.orders.models.Agents;
import local.tylerb.orders.models.Customers;
import local.tylerb.orders.services.AgentService;
import local.tylerb.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

// is this like inflating a view?
    @Autowired
    private AgentService agentService;

    // http://localhost:8080/agents/agent/2
    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> getAgentById(@PathVariable long id) {
        Agents myAgent = agentService.findById(id);
        return new ResponseEntity<>(myAgent, HttpStatus.OK);
    }


}
