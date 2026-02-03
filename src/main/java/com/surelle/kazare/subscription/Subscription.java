package com.surelle.kazare.subscription;

import com.surelle.kazare.shared.constants;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "subscriptions",
        uniqueConstraints = {
                @UniqueConstraint(name = "subscriptions_sku_unique", columnNames = "sku")
        }
)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(name = "billing_cycle", nullable = false)
    private String billingCycle;

    @Column(name = "trial_days", nullable = false)
    @Builder.Default
    private Integer trialDays = 0;

    @Column(name = "user_capacity", nullable = false)
    @Builder.Default
    private Integer userCapacity = 1;

    @Column(name = "price_display", nullable = false, precision = 12, scale = 2)
    private BigDecimal priceDisplay;

    @Column(name = "price_actual", nullable = false, precision = 12, scale = 2)
    private BigDecimal priceActual;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    @Builder.Default
    private constants.ApplicationCurrency currency = constants.ApplicationCurrency.USD;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    /* =========================
       JPA lifecycle hooks
       ========================= */

    @PrePersist
    protected void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
