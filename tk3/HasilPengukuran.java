package tk3;

public class HasilPengukuran {
    private final int jumlahLagu;
    private final double totalDurasi;
    private final Lagu laguTerpanjang;
    private final double waktuTotalDurasi;
    private final double waktuTampilkanMundur;
    private final double waktuCariTerpanjang;

    public HasilPengukuran(
            int jumlahLagu,
            double totalDurasi,
            Lagu laguTerpanjang,
            double waktuTotalDurasi,
            double waktuTampilkanMundur,
            double waktuCariTerpanjang) {
        this.jumlahLagu = jumlahLagu;
        this.totalDurasi = totalDurasi;
        this.laguTerpanjang = laguTerpanjang;
        this.waktuTotalDurasi = waktuTotalDurasi;
        this.waktuTampilkanMundur = waktuTampilkanMundur;
        this.waktuCariTerpanjang = waktuCariTerpanjang;
    }

    public int getJumlahLagu() {
        return jumlahLagu;
    }

    public double getTotalDurasi() {
        return totalDurasi;
    }

    public Lagu getLaguTerpanjang() {
        return laguTerpanjang;
    }

    public double getWaktuTotalDurasi() {
        return waktuTotalDurasi;
    }

    public double getWaktuTampilkanMundur() {
        return waktuTampilkanMundur;
    }

    public double getWaktuCariTerpanjang() {
        return waktuCariTerpanjang;
    }
}
