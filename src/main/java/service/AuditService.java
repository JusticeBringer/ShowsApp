package service;

import repositories.AuditRepository;

import java.io.*;
import java.util.Date;

public class AuditService {
    private AuditRepository auditRepository;

    public AuditService(){
        auditRepository = new AuditRepository();
    }

    public void writeInAuditFile(String numeActiune, String numeThread) {
        auditRepository.writeAudit(numeActiune, numeThread);
    }
}
