import java.util.ArrayList;
import java.util.List;

abstract class Psikiater implements Konsultasi, Pelanggan{
    protected String nama;
    protected List<String> riwayatKonsultasi;

    private List<Pesan> obrolan;

    public Psikiater(String nama) {
        this.nama = nama;
        this.riwayatKonsultasi = new ArrayList<>();
        this.obrolan = new ArrayList<>();
    }
    abstract void perkenalan();

    void konsultasiUmum() {
        System.out.println("Psikiater " + nama + " memberikan konsultasi umum.");
        tambahkanRiwayat("Konsultasi umum");
    }

    void tambahkanRiwayat(String catatan) {
        riwayatKonsultasi.add(catatan);
    }

    @Override
    public void kirimPesan(String pesan, Pelanggan pengirim) {
        Pesan chat = new Pesan(pesan, pengirim);
        obrolan.add(chat);
    }

    @Override
    public String getNama() {
        return nama;
    }

    public void lihatObrolan() {
        System.out.println("Obrolan dengan " + getNama() + ":");
        for (Pesan pesan : obrolan) {
            System.out.println(pesan.getPengirim().getNama() + ": " + pesan.getIsi());
        }
    }
}
