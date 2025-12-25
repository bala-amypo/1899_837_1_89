@Entity
@Table(
  name = "invoices",
  uniqueConstraints = @UniqueConstraint(
    columnNames = {"vendor_id", "invoiceNumber"}
  )
)
public class Invoice {

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User uploadedBy;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }
}
