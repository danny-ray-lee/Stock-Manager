package io.github.danny.ray.stockmanager.model.fee;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.danny.ray.stockmanager.model.enums.EnumCurrencySymbol;
import io.github.danny.ray.stockmanager.model.enums.EnumProductType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "fee_plan")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StockFeePlan.class, name = "STOCK"),
        @JsonSubTypes.Type(value = FutureFeePlan.class, name = "FUTURE")
})
public abstract class FeePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private EnumProductType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private EnumCurrencySymbol currency;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public FeePlan setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FeePlan setName(String name) {
        this.name = name;
        return this;
    }

    public EnumProductType getType() {
        return type;
    }

    public FeePlan setType(EnumProductType type) {
        this.type = type;
        return this;
    }

    public EnumCurrencySymbol getCurrency() {
        return currency;
    }

    public FeePlan setCurrency(EnumCurrencySymbol currency) {
        this.currency = currency;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public FeePlan setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public FeePlan setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
