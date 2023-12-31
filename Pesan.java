public class Pesan {
    private String isi;
    private Pelanggan pengirim;

    public Pesan(String isi, Pelanggan pengirim) {
        this.isi = isi;
        this.pengirim = pengirim;
    }
    public String getIsi() {
        return isi;
    }
    public Pelanggan getPengirim() {
        return pengirim;
    }
}

