package repositories;

import service.AuditService;

import java.io.*;
import java.util.Date;

public class AuditRepository {

    private static Integer writerAUD = 0;

    public AuditRepository() {
    }

    public static Integer getWriterAUD() {
        return writerAUD;
    }

    public static void setWriterAUD(Integer writerAUD) {
        AuditRepository.writerAUD = writerAUD;
    }

    public void writeAudit(String numeActiune, String numeThread) {
        String whereWrite = "./csvFiles/audit.csv";

        try {
            FileWriter fw = new FileWriter(whereWrite, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            if (writerAUD == 0) {
                pw.println("nume_actiune,data, nume_thread");
            }

            Date date = new Date();

            pw.println(numeActiune + "," + date.toString() + "," + numeThread);
            pw.flush();
            pw.close();

            writerAUD ++;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
