import java.util.Scanner;

public class AplikasiKonsultasi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di Aplikasi Konsultasi Psikiater");
        System.out.print("Masukkan nama Anda: ");
        String namaPasien = scanner.nextLine();

        System.out.print("Masukkan usia Anda: ");
        int usiaPasien = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        System.out.print("Apa keluhan Anda? ");
        String keluhanPasien = scanner.nextLine();

        System.out.print("Masukkan jenis konsultasi (anak/dewasa): ");
        String jenisKonsultasi = scanner.nextLine();

        Psikiater psikiater;

        if ("anak".equalsIgnoreCase(jenisKonsultasi)) {
            psikiater = new PsikiaterAnak("Dr. Anak");
        } else {
            psikiater = new PsikiaterDewasa("Dr. Dewasa");
        }

        Pasien pasien = new Pasien(namaPasien, usiaPasien, keluhanPasien, psikiater);
        pasien.mulaiKonsultasi();

        System.out.println("\nChat antara Pasien dan Psikiater:");
        pasien.kirimPesan("Halo, saya memiliki masalah dengan kecemasan.", pasien);
        psikiater.kirimPesan("Halo " + pasien.getNama() + ", saya akan membantu Anda.", psikiater);
        pasien.kirimPesan("Terima kasih, dok. Bagaimana cara mengatasi kecemasan?", pasien);
        psikiater.kirimPesan("Kita dapat mencoba beberapa teknik relaksasi dan terapi bicara.", psikiater);

        // Menampilkan obrolan
        pasien.lihatObrolan();
        psikiater.lihatObrolan();

        System.out.print("Apakah Anda ingin melanjutkan konsultasi? (ya/tidak): ");
        String lanjut = scanner.next();

        while ("ya".equalsIgnoreCase(lanjut)) {
            pasien.mulaiKonsultasi();
            System.out.print("Apakah Anda ingin melanjutkan konsultasi? (ya/tidak): ");
            lanjut = scanner.next();
        }

        System.out.println("Terima kasih telah menggunakan Aplikasi Konsultasi Psikiater.");
        scanner.close();
    }
}