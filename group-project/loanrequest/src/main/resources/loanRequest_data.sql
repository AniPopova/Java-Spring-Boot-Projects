SELECT * FROM REGISTERED_CLIENTS LEFT JOIN PERSONAL_INFORMATION S1PI on REGISTERED_CLIENTS.ID_CLIENT = S1PI.ID_CLIENT
                                   LEFT JOIN FINANCIAL_STATUS S2FS on REGISTERED_CLIENTS.ID_CLIENT = S2FS.ID_CLIENT
                                   LEFT JOIN BANK_OFFER BO on REGISTERED_CLIENTS.ID_CLIENT = BO.ID_CLIENT
                                   LEFT JOIN CLIENT_RESPONSE BOR on REGISTERED_CLIENTS.ID_CLIENT = BOR.ID_CLIENT
                                   LEFT JOIN REGISTRATION_TOKEN RT on REGISTERED_CLIENTS.ID_CLIENT = RT.ID_CLIENT
                                   LEFT JOIN REGISTRATION_FORM RF on REGISTERED_CLIENTS.ID_CLIENT = RF.ID_CLIENT
                                   LEFT JOIN FILE_DATA FD on REGISTERED_CLIENTS.ID_CLIENT = FD.ID_CLIENT WHERE REGISTERED_CLIENTS.ID_CLIENT=62;``