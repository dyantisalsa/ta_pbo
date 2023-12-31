class PsikiaterAnak extends Psikiater implements Konsultasi {
    public PsikiaterAnak(String nama) {
        super(nama);
    }

    @Override
    void perkenalan() {
        System.out.println("Saya adalah Psikiater Anak dengan nama " + nama);
    }

    @Override
    public void mulaiKonsultasi() {
        System.out.println("Mulai sesi konsultasi anak.");
        tambahkanRiwayat("Konsultasi anak dimulai");
        // Logika konsultasi anak
    }
}
