package tk3;

import java.util.Scanner;

public class PlaylistInput {
    public static Lagu[] bacaPlaylist(Scanner scanner) {
        int jumlahLagu = bacaJumlahLagu(scanner);
        Lagu[] playlist = new Lagu[jumlahLagu];

        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println();
            System.out.println("Input lagu ke-" + (i + 1));
            System.out.print("Judul  : ");
            String judul = bacaTeksWajib(scanner);

            System.out.print("Artis  : ");
            String artis = bacaTeksWajib(scanner);

            double durasi = bacaDurasi(scanner);
            playlist[i] = new Lagu(judul, artis, durasi);
        }

        return playlist;
    }

    private static int bacaJumlahLagu(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan jumlah lagu: ");
            String input = scanner.nextLine().trim();
            try {
                int jumlah = Integer.parseInt(input);
                if (jumlah > 0) {
                    return jumlah;
                }
                System.out.println("[!] Jumlah lagu harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("[!] Masukkan angka bulat.");
            }
        }
    }

    private static String bacaTeksWajib(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.print("[!] Input tidak boleh kosong. Ulangi: ");
        }
    }

    private static double bacaDurasi(Scanner scanner) {
        while (true) {
            System.out.print("Durasi (menit): ");
            String input = scanner.nextLine().trim();
            try {
                double durasi = Double.parseDouble(input);
                if (durasi > 0) {
                    return durasi;
                }
                System.out.println("[!] Durasi harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("[!] Masukkan angka desimal, contoh: 4.52");
            }
        }
    }
}
