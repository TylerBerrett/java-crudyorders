package local.tylerb.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordnum;

    private double ordamount;
    private double advanceamount;
    private String orderdescription;

    @Transient
    public boolean hasOrdamount = false;
    @Transient
    public boolean hasAdvanceamount = false;


    public Orders(){}

    public Orders(double ordamount, double advanceamount, Customers customer, String orderdescription) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customers = customer;
        this.orderdescription = orderdescription;
    }

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties({"orders", "agents"})
    private Customers customers;

    @ManyToMany
    @JoinTable(name = "orderspayments",
                joinColumns = @JoinColumn(name = "ordnum"),
                inverseJoinColumns = @JoinColumn(name = "paymentid")
                )
    @JsonIgnoreProperties("orders")
    List<Payments> payments = new ArrayList<>();



    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        hasOrdamount = true;
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        hasAdvanceamount = true;
        this.advanceamount = advanceamount;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public void addPayments(Payments payment){
        payments.add(payment);
        payment.getOrders().add(this);
    }


}
