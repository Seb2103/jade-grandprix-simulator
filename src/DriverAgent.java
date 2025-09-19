import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class DriverAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " starting (DriverAgent).");

        // 1. Registrar en DF
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("driver");
        sd.setName("driver-" + getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        // 2. Imprimir las vueltas
        addBehaviour(new TickerBehaviour(this, 3000) {
            int lap = 0;
            @Override
            protected void onTick() {
                lap++;
                System.out.println(getLocalName() + " en vuelta " + lap);
            }
        });
    }
}
