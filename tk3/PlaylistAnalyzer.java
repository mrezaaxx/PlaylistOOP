package tk3;

public class PlaylistAnalyzer {
    /*
     * Tujuan: menghitung total durasi semua lagu memakai rekursi.
     * Base case: n == 0, tidak ada lagu tersisa sehingga hasil 0.
     * Recursive case: durasi lagu ke-(n - 1) ditambah totalDurasi(list, n - 1).
     * Kompleksitas waktu: O(n), karena setiap lagu diproses satu kali.
     */
    public static double totalDurasi(Lagu[] list, int n) {
        if (n == 0) {
            return 0;
        }
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /*
     * Tujuan: menampilkan playlist dari indeks terakhir menuju indeks pertama.
     * Base case: index < 0, semua lagu sudah ditampilkan.
     * Recursive case: tampilkan lagu pada index, lalu panggil index - 1.
     * Kompleksitas waktu: O(n), karena setiap lagu ditampilkan satu kali.
     */
    public static void tampilkanMundur(Lagu[] list, int index) {
        if (index < 0) {
            return;
        }
        list[index].tampilkanRingkas(index + 1);
        tampilkanMundur(list, index - 1);
    }

    /*
     * Tujuan: mencari objek lagu dengan durasi paling panjang memakai rekursi.
     * Base case: index == 0, hanya lagu pertama yang dibandingkan.
     * Recursive case: bandingkan lagu pada index dengan hasil dari index - 1.
     * Kompleksitas waktu: O(n), karena setiap lagu dibandingkan satu kali.
     */
    public static Lagu cariLaguTerpanjang(Lagu[] list, int index) {
        if (index == 0) {
            return list[0];
        }

        Lagu kandidat = cariLaguTerpanjang(list, index - 1);
        if (list[index].getDurasi() > kandidat.getDurasi()) {
            return list[index];
        }
        return kandidat;
    }

    /*
     * Tujuan: mencari nilai durasi terpanjang memakai rekursi.
     * Base case: index == 0, durasi lagu pertama menjadi hasil awal.
     * Recursive case: bandingkan durasi pada index dengan hasil dari index - 1.
     * Kompleksitas waktu: O(n), karena setiap durasi dibandingkan satu kali.
     */
    public static double cariDurasiTerpanjang(Lagu[] list, int index) {
        if (index == 0) {
            return list[0].getDurasi();
        }

        double kandidat = cariDurasiTerpanjang(list, index - 1);
        if (list[index].getDurasi() > kandidat) {
            return list[index].getDurasi();
        }
        return kandidat;
    }
}
