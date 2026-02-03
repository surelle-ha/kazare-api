package com.surelle.kazare.shared;

public class constants {

    //
    public enum ApplicationCurrency {
        USD,
        EUR,
        PHP
    }

    //
    public enum WorkspaceUserRole {
        owner,
        admin,
        member
    }

    //
    public enum WorkspaceStatus {
        active,
        suspended,
        archived
    }

    //
    public enum WorkspaceSubscriptionStatus {
        active,
        trial,
        expired,
        canceled
    }
}
