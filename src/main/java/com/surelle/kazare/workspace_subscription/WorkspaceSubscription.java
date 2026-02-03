package com.surelle.kazare.workspace_subscription;

import com.surelle.kazare.shared.constants;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "workspace_subscriptions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "workspace_subscriptions_workspace_unique",
                        columnNames = "workspace_id"
                )
        }
)
public class WorkspaceSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Owning workspace
     */
    @Column(name = "workspace_id", nullable = false)
    private Long workspaceId;

    /**
     * Subscription plan
     */
    @Column(name = "subscription_id", nullable = false)
    private Long subscriptionId;

    @Column(name = "billing_cycle", nullable = false)
    private String billingCycle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private constants.WorkspaceSubscriptionStatus status = constants.WorkspaceSubscriptionStatus.active;

    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;

    @Column(name = "canceled_at")
    private OffsetDateTime canceledAt;

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
        this.startedAt = this.startedAt == null ? now : this.startedAt;
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
