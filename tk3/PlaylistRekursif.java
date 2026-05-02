package tk3;

import java.util.Scanner;

/**
 * ============================================================
 *  TUGAS KELOMPOK KE-3 - COSC6025 Data Structures & Algorithm Analysis
 *  Week 8 - Algorithmic Analysis, Mathematical Induction, and Recursive Functions
 * ============================================================
 *  Kelompok:
 *  1. Irsyaan Wijaya Hartanto  - 2902735692
 *  2. Muhammad Reza            - 2902735414
 *  3. Srigita Janu Saputra     - 2702434364
 *  4. Elis Elisa               - 2902693380
 * ============================================================
 */
public class PlaylistRekursif {
    /*
     * Tujuan: mengubah selisih waktu nanosecond menjadi millisecond.
     * Base case: tidak memakai rekursi.
     * Recursive case: tidak memakai rekursi.
     * Kompleksitas waktu: O(1), karena hanya melakukan satu operasi aritmetika.
     */
    private static double millis(long startNano, long endNano) {
        return (endNano - startNano) / 1_000_000.0;
    }

    /*
     * Tujuan: menjalankan analisis rekursif playlist dan mencatat waktu eksekusi.
     * Base case: mengikuti fungsi rekursif pada PlaylistAnalyzer.
     * Recursive case: mengikuti fungsi rekursif pada PlaylistAnalyzer.
     * Kompleksitas waktu: O(n), karena setiap analisis memproses seluruh lagu.
     */
    private static HasilPengukuran analisisPlaylist(Lagu[] playlist) {
        long mulaiTotal = System.nanoTime();
        double total = PlaylistAnalyzer.totalDurasi(playlist, playlist.length);
        long selesaiTotal = System.nanoTime();

        System.out.println("Daftar lagu :");
        long mulaiMundur = System.nanoTime();
        PlaylistAnalyzer.tampilkanMundur(playlist, playlist.length - 1);
        long selesaiMundur = System.nanoTime();

        long mulaiTerpanjang = System.nanoTime();
        PlaylistAnalyzer.cariDurasiTerpanjang(playlist, playlist.length - 1);
        Lagu terpanjang = PlaylistAnalyzer.cariLaguTerpanjang(playlist, playlist.length - 1);
        long selesaiTerpanjang = System.nanoTime();

        return new HasilPengukuran(
                playlist.length,
                total,
                terpanjang,
                millis(mulaiTotal, selesaiTotal),
                millis(mulaiMundur, selesaiMundur),
                millis(mulaiTerpanjang, selesaiTerpanjang));
    }

    /*
     * Tujuan: menampilkan hasil total durasi, lagu terpanjang, dan waktu eksekusi.
     * Base case: tidak memakai rekursi.
     * Recursive case: tidak memakai rekursi.
     * Kompleksitas waktu: O(1), karena jumlah data yang ditampilkan tetap.
     */
    private static void tampilkanHasil(HasilPengukuran hasil) {
        System.out.println();
        System.out.printf("Jumlah lagu      : %d%n", hasil.getJumlahLagu());
        System.out.printf("Total durasi     : %.2f menit%n", hasil.getTotalDurasi());
        System.out.printf(
                "Lagu terpanjang  : \"%s\" - %s (%.2f menit)%n",
                hasil.getLaguTerpanjang().getJudul(),
                hasil.getLaguTerpanjang().getArtis(),
                hasil.getLaguTerpanjang().getDurasi());
        System.out.printf("Execution Time (totalDurasi)         : %.4f ms%n", hasil.getWaktuTotalDurasi());
        System.out.printf("Execution Time (tampilkanMundur)     : %.4f ms%n", hasil.getWaktuTampilkanMundur());
        System.out.printf("Execution Time (cariDurasiTerpanjang): %.4f ms%n", hasil.getWaktuCariTerpanjang());
    }

    /*
     * Tujuan: menampilkan tabel waktu eksekusi dari semua hasil pengukuran.
     * Base case: tidak memakai rekursi.
     * Recursive case: tidak memakai rekursi.
     * Kompleksitas waktu: O(m), dengan m sebagai jumlah hasil pengukuran.
     */
    private static void tampilkanTabelPengukuran(HasilPengukuran[] hasil) {
        System.out.println();
        System.out.println("=== TABEL PENGUKURAN WAKTU EKSEKUSI ===");
        System.out.println("+----------------+----------------+-------------------+-------------------------+");
        System.out.println("| Jumlah Lagu (n)| totalDurasi ms | tampilkanMundur ms| cariTerpanjang ms       |");
        System.out.println("+----------------+----------------+-------------------+-------------------------+");
        for (HasilPengukuran item : hasil) {
            System.out.printf(
                    "| %-14d | %-14.4f | %-17.4f | %-23.4f |%n",
                    item.getJumlahLagu(),
                    item.getWaktuTotalDurasi(),
                    item.getWaktuTampilkanMundur(),
                    item.getWaktuCariTerpanjang());
        }
        System.out.println("+----------------+----------------+-------------------+-------------------------+");
    }

    /*
     * Tujuan: menampilkan ringkasan base case, growth rate, dan kompleksitas.
     * Base case: tidak memakai rekursi langsung.
     * Recursive case: tidak memakai rekursi langsung.
     * Kompleksitas waktu: O(1), karena jumlah baris analisis tetap.
     */
    private static void tampilkanAnalisisKompleksitas() {
        System.out.println();
        System.out.println("=== ANALISIS TIME AND GROWTH RATE ===");
        System.out.println("totalDurasi()          : base case n == 0, growth rate linear, kompleksitas O(n)");
        System.out.println("tampilkanMundur()      : base case index < 0, growth rate linear, kompleksitas O(n)");
        System.out.println("cariDurasiTerpanjang() : base case index == 0, growth rate linear, kompleksitas O(n)");
    }

    /*
     * Tujuan: menjadi titik masuk program dan mengatur alur analisis playlist.
     * Base case: mengikuti fungsi rekursif yang dipanggil melalui analisisPlaylist().
     * Recursive case: mengikuti fungsi rekursif yang dipanggil melalui analisisPlaylist().
     * Kompleksitas waktu: O(n), karena input dan analisis memproses n lagu.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===");
        Lagu[] playlist = PlaylistInput.bacaPlaylist(scanner);
        System.out.println();
        System.out.println("--- Hasil Analisis Playlist Input ---");
        HasilPengukuran hasil = analisisPlaylist(playlist);
        tampilkanHasil(hasil);

        tampilkanTabelPengukuran(new HasilPengukuran[] {hasil});
        tampilkanAnalisisKompleksitas();
        scanner.close();
    }
}
