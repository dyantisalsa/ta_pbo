class PsikiaterDewasa extends Psikiater implements Konsultasi {
    public PsikiaterDewasa(String nama) {
        super(nama);
    }

    @Override
    void perkenalan() {
        System.out.println("Saya adalah Psikiater Dewasa dengan nama " + nama);
    }

    @Override
    public void mulaiKonsultasi() {
        System.out.println("Mulai sesi konsultasi dewasa.");
        tambahkanRiwayat("Konsultasi dewasa dimulai");
        // Logika konsultasi dewasa
    }
}
