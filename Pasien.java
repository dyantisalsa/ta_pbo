import java.util.ArrayList;
import java.util.List;

public class Pasien implements Konsultasi, Pelanggan {
    private String nama;
    private int usia;
    private String keluhan;
    private Psikiater psikiater;
    private List<Pesan> obrolan;
    private StringBuilder riwayatKonsultasi;

    public Pasien(String nama, int usia, String keluhan, Psikiater psikiater) {
        this.nama = nama;
        this.usia = usia;
        this.keluhan = keluhan;
        this.psikiater = psikiater;
        this.obrolan = new ArrayList<>();
        this.riwayatKonsultasi = new StringBuilder();
    }
    public Psikiater getPsikiater() {
        return psikiater;
    }
    public void tambahRiwayatKonsultasi(String pesan) {
        riwayatKonsultasi.append(pesan).append("\n");
    }
    public String getRiwayatKonsultasi() {
        return riwayatKonsultasi.toString();
    }
    @Override
    public void mulaiKonsultasi() {
        System.out.println("Selamat datang, " + nama + "! Anda akan berkonsultasi dengan Psikiater " + psikiater.getNama());
        psikiater.perkenalan();
        psikiater.konsultasiUmum();
        psikiater.tambahkanRiwayat("Pasien " + nama + " memulai konsultasi.");
        if (psikiater instanceof Konsultasi) {
            Konsultasi konsultasi = (Konsultasi) psikiater;
            konsultasi.mulaiKonsultasi();
        }
        System.out.println("Terima kasih, " + nama + ". Konsultasi selesai.");
        psikiater.tambahkanRiwayat("Pasien " + nama + " selesai konsultasi.");
        obrolan.clear();
    }
    @Override
    public void kirimPesan(String pesan, Pelanggan pengirim) {
        Pesan chat = new Pesan(pesan, pengirim);
        obrolan.add(chat);
    }
    public void lihatObrolan(Output output) {
        boolean firstMessage = true;
        for (Pesan pesan : obrolan) {
            if (firstMessage) {
                firstMessage = false;
                continue;
            }
            output.append(pesan.getPengirim().getNama() + ": " + pesan.getIsi() + "\n");
        }
    }
    public void lihatRiwayatKonsultasi(Output output) {
        output.append("Riwayat Konsultasi untuk " + getNama() + " dengan " + psikiater.getNama() + ":\n");
        output.append("Keluhan Pasien: " + keluhan + "\n");
        output.append("Obrolan dengan " + getNama() + ":\n");
        boolean firstMessage = true;
        for (Pesan pesan : obrolan) {
            if (firstMessage) {
                firstMessage = false;
                continue;
            }
            output.append(pesan.getPengirim().getNama() + ": " + pesan.getIsi() + "\n");
        }
        output.append(getRiwayatKonsultasi());
    }
    @Override
    public String getNama() {
        return nama;
    }
}
