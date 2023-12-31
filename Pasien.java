import java.util.ArrayList;
import java.util.List;
class Pasien implements Konsultasi, Pelanggan{
    private String nama;
    private int usia;
    private String keluhan;
    private Psikiater psikiater;

    private List<Pesan> obrolan;

    public Pasien(String nama, int usia, String keluhan, Psikiater psikiater) {
        this.nama = nama;
        this.usia = usia;
        this.keluhan = keluhan;
        this.psikiater = psikiater;
        this.obrolan = new ArrayList<>();
    }

    public Pasien(List<Pesan> obrolan) {
        this.obrolan = obrolan;
    }

    public void mulaiKonsultasi() {
        System.out.println("Selamat datang, " + nama + "! Anda akan berkonsultasi dengan Psikiater " + psikiater.nama);
        psikiater.perkenalan();
        psikiater.konsultasiUmum();
        psikiater.tambahkanRiwayat("Pasien " + nama + " memulai konsultasi.");
        if (psikiater instanceof Konsultasi) {
            Konsultasi konsultasi = (Konsultasi) psikiater;
            konsultasi.mulaiKonsultasi();
        }
        System.out.println("Terima kasih, " + nama + ". Konsultasi selesai.");
        psikiater.tambahkanRiwayat("Pasien " + nama + " selesai konsultasi.");
    }
    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public void kirimPesan(String pesan, Pelanggan pengirim) {
        Pesan chat = new Pesan(pesan, pengirim);
        obrolan.add(chat);
    }

    public void lihatObrolan() {
        System.out.println("Obrolan dengan " + getNama() + ":");
        for (Pesan pesan : obrolan) {
            System.out.println(pesan.getPengirim().getNama() + ": " + pesan.getIsi());
        }
    }
}