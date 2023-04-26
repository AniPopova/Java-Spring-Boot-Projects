--project PaymentPalns
--DROP TABLE INDIVIDUAL;
--TRUNCATE TABLE
CREATE TABLE individual (
    individ VARCHAR2(32),
    name    VARCHAR2(100) NOT NULL,
    address VARCHAR2(255) NOT NULL,
    CONSTRAINT individual_pk PRIMARY KEY ( individ )
);
/
--DROP TABLE PaymentPlans;
--TRUNCATE TABLE PaymentPlans;
CREATE TABLE paymentplans (
    planid        VARCHAR2(32),
    individ       VARCHAR2(32) NOT NULL,
    amounttopay NUMBER(7, 2) DEFAULT (0),
    CONSTRAINT paymentplans_pk PRIMARY KEY ( planid ),
    CONSTRAINT paymentplans_indiv_fk FOREIGN KEY ( individ )
        REFERENCES individual (individ)
            ON DELETE CASCADE
);
/
--DROP TABLE PAYMENTS;
--TRUNCATE TABLE PAYMENTS;
CREATE TABLE payments (
    paymentid   VARCHAR2(32),
    planid      VARCHAR2(32) NOT NULL,
    amountpaid NUMBER(7, 2) DEFAULT (0),
    paymentdate DATE,
    CONSTRAINT payments_pk PRIMARY KEY ( paymentid ),
    CONSTRAINT payment__pplan_fk FOREIGN KEY ( planid )
        REFERENCES paymentplans ( planid )
            ON DELETE CASCADE
);