/**
 * ============================================================
 *  TUGAS KELOMPOK KE-1 - COSC6025 Data Structures & Algorithm Analysis
 *  Week 3 - Introduction to OOP & Data Structures
 * ============================================================
 *  Kelompok:
 *  1. Irsyaan Wijaya Hartanto  - 2902735692
 *  2. Muhammad Reza            - 2902735414
 *  3. Srigita Janu Saputra     - 2702434364
 *  4. Elis Elisa               - 2902693380
 * ============================================================
 */

// ============================================================
// CLASS LAGU
// Merepresentasikan satu objek lagu dalam sistem playlist.
// Menerapkan enkapsulasi: semua atribut bersifat private,
// hanya bisa diakses melalui getter dan setter.
// ============================================================
class Lagu {
    private String judul;
    private String artis;
    private double durasi; // dalam satuan menit

    /**
     * Constructor: membuat objek Lagu baru dengan data lengkap.
     */
    public Lagu(String judul, String artis, double durasi) {
        this.judul  = judul;
        this.artis  = artis;
        this.durasi = durasi;
    }

    // --- Getter ---
    public String getJudul()  { return judul; }
    public String getArtis()  { return artis; }
    public double getDurasi() { return durasi; }

    // --- Setter ---
    public void setJudul(String judul)    { this.judul  = judul; }
    public void setArtis(String artis)    { this.artis  = artis; }
    public void setDurasi(double durasi)  { this.durasi = durasi; }

    /**
     * tampilkanInfo(): menampilkan informasi lengkap sebuah lagu
     * ke konsol, termasuk judul, artis, dan durasi.
     */
    public void tampilkanInfo() {
        System.out.println("  Judul  : " + judul);
        System.out.println("  Artis  : " + artis);
        System.out.printf ("  Durasi : %.2f menit%n", durasi);
    }
}


// ============================================================
// CLASS USER (Parent Class)
// Merepresentasikan pengguna sistem secara umum.
// Menyediakan atribut dasar (nama, role) dan method
// tampilkanAkses() yang akan di-override oleh class turunan
// sebagai bentuk penerapan polymorphism.
// ============================================================
class User {
    protected String nama;
    protected String role;

    /**
     * Constructor: membuat objek User dengan nama dan role tertentu.
     */
    public User(String nama, String role) {
        this.nama = nama;
        this.role = role;
    }

    public String getNama() { return nama; }
    public String getRole() { return role; }

    /**
     * tampilkanAkses(): method dasar yang menampilkan info akses user.
     * Akan di-override oleh Admin dan Member (polymorphism).
     */
    public void tampilkanAkses() {
        System.out.println("[User] Nama: " + nama + " | Role: " + role);
    }
}


// ============================================================
// CLASS ADMIN (Child Class dari User)
// Mewarisi class User (inheritance).
// Bertanggung jawab mengelola data playlist:
// menambah lagu dan menampilkan semua lagu yang tersimpan.
// Array Lagu[] digunakan sebagai struktur data penyimpanan.
// ============================================================
class Admin extends User {
    private Lagu[] playlist;
    private int jumlahLagu;

    /**
     * Constructor: membuat Admin dengan nama tertentu.
     * Inisialisasi array playlist dengan kapasitas 100 lagu.
     */
    public Admin(String nama) {
        super(nama, "Admin");
        this.playlist  = new Lagu[100];
        this.jumlahLagu = 0;
    }

    /**
     * tambahLagu(): menambahkan objek Lagu baru ke dalam array playlist.
     * Jika kapasitas penuh, menampilkan peringatan tanpa menambah lagu.
     */
    public void tambahLagu(String judul, String artis, double durasi) {
        if (jumlahLagu >= playlist.length) {
            System.out.println("Playlist penuh! Tidak dapat menambah lagu.");
            return;
        }
        playlist[jumlahLagu] = new Lagu(judul, artis, durasi);
        jumlahLagu++;
        System.out.println("[Admin] Lagu \"" + judul + "\" berhasil ditambahkan.");
    }

    /**
     * lihatSemuaLagu(): menampilkan seluruh lagu dalam array playlist
     * beserta nomor urut dan info lengkap tiap lagu.
     */
    public void lihatSemuaLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }
        System.out.println("\n===== DAFTAR SEMUA LAGU (Admin View) =====");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println("\n[" + (i + 1) + "]");
            playlist[i].tampilkanInfo();
        }
        System.out.println("==========================================");
    }

    // Getter untuk diakses oleh Member
    public Lagu[] getPlaylist()   { return playlist; }
    public int getJumlahLagu()    { return jumlahLagu; }

    /**
     * tampilkanAkses(): override dari User.
     * Polymorphism - perilaku berbeda untuk Admin:
     * menampilkan hak akses khusus Admin ke sistem.
     */
    @Override
    public void tampilkanAkses() {
        System.out.println("========================================");
        System.out.println("  [ADMIN]  Selamat datang, " + nama + "!");
        System.out.println("  Hak Akses: Tambah Lagu | Lihat Semua Lagu");
        System.out.println("========================================");
    }
}


// ============================================================
// CLASS MEMBER (Child Class dari User)
// Mewarisi class User (inheritance).
// Berperan sebagai pengguna playlist yang dapat:
// menelusuri lagu, mencari berdasarkan judul,
// dan menghitung rata-rata durasi lagu.
// ============================================================
class Member extends User {

    /**
     * Constructor: membuat Member dengan nama tertentu.
     */
    public Member(String nama) {
        super(nama, "Member");
    }

    /**
     * lihatDaftarLagu(): menampilkan daftar ringkas semua lagu
     * (judul dan artis) yang tersimpan di playlist Admin.
     */
    public void lihatDaftarLagu(Admin admin) {
        int jumlah = admin.getJumlahLagu();
        if (jumlah == 0) {
            System.out.println("Belum ada lagu di playlist.");
            return;
        }
        System.out.println("\n===== DAFTAR LAGU =====");
        for (int i = 0; i < jumlah; i++) {
            System.out.println((i + 1) + ". "
                    + admin.getPlaylist()[i].getJudul()
                    + " - " + admin.getPlaylist()[i].getArtis());
        }
        System.out.println("=======================");
    }

    /**
     * cariLagu(): mencari lagu berdasarkan keyword judul.
     * Pencarian bersifat case-insensitive dan menampilkan
     * info lengkap semua lagu yang cocok dengan keyword.
     */
    public void cariLagu(Admin admin, String keyword) {
        System.out.println("\n>> Hasil pencarian: \"" + keyword + "\"");
        boolean ditemukan = false;
        for (int i = 0; i < admin.getJumlahLagu(); i++) {
            Lagu lagu = admin.getPlaylist()[i];
            if (lagu.getJudul().toLowerCase().contains(keyword.toLowerCase())) {
                lagu.tampilkanInfo();
                System.out.println("  -----");
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("  Lagu \"" + keyword + "\" tidak ditemukan.");
        }
    }

    /**
     * hitungRataRataDurasi(): menghitung rata-rata durasi seluruh lagu
     * dalam playlist menggunakan iterasi array, lalu menampilkan hasilnya.
     */
    public void hitungRataRataDurasi(Admin admin) {
        int jumlah = admin.getJumlahLagu();
        if (jumlah == 0) {
            System.out.println("Playlist kosong, tidak bisa menghitung rata-rata.");
            return;
        }
        double total = 0;
        for (int i = 0; i < jumlah; i++) {
            total += admin.getPlaylist()[i].getDurasi();
        }
        System.out.printf("%nRata-rata durasi dari %d lagu: %.2f menit%n", jumlah, total / jumlah);
    }

    /**
     * tampilkanAkses(): override dari User.
     * Polymorphism - perilaku berbeda untuk Member:
     * menampilkan hak akses terbatas milik Member.
     */
    @Override
    public void tampilkanAkses() {
        System.out.println("========================================");
        System.out.println("  [MEMBER] Selamat datang, " + nama + "!");
        System.out.println("  Hak Akses: Lihat Daftar | Cari Lagu | Rata-rata Durasi");
        System.out.println("========================================");
    }
}


// ============================================================
// CLASS MAIN
// Entry point program. Menjalankan menu interaktif berbasis
// Scanner untuk dua peran: Admin dan Member.
// Admin dapat menambah lagu melalui input keyboard.
// Member dapat menelusuri, mencari, dan menghitung rata-rata.
// ============================================================
public class PlaylistOOP {

    /**
     * menuAdmin(): menampilkan dan menjalankan menu interaktif Admin.
     * Admin bisa menambah lagu (input via Scanner), lihat semua lagu,
     * atau kembali ke menu utama.
     */
    static void menuAdmin(Admin admin, java.util.Scanner sc) {
        admin.tampilkanAkses();
        boolean aktif = true;
        while (aktif) {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1. Tambah Lagu");
            System.out.println("2. Lihat Semua Lagu");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1":
                    // Input data lagu dari keyboard menggunakan Scanner
                    System.out.println("\n>> Tambah Lagu Baru");
                    System.out.print("   Judul  : ");
                    String judul = sc.nextLine();

                    System.out.print("   Artis  : ");
                    String artis = sc.nextLine();

                    // Loop sampai durasi yang dimasukkan valid (angka positif)
                    double durasi = 0;
                    while (true) {
                        System.out.print("   Durasi (menit, contoh: 3.45) : ");
                        String inputDurasi = sc.nextLine().trim();
                        try {
                            durasi = Double.parseDouble(inputDurasi);
                            if (durasi <= 0) {
                                System.out.println("   [!] Durasi harus lebih dari 0. Coba lagi.");
                            } else {
                                break; // input valid, keluar dari loop
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("   [!] Input tidak valid. Masukkan angka, contoh: 3.45");
                        }
                    }

                    admin.tambahLagu(judul, artis, durasi);
                    break;

                case "2":
                    admin.lihatSemuaLagu();
                    break;

                case "0":
                    aktif = false;
                    break;

                default:
                    System.out.println("[!] Pilihan tidak dikenal. Silakan pilih 0, 1, atau 2.");
            }
        }
    }

    /**
     * menuMember(): menampilkan dan menjalankan menu interaktif Member.
     * Member bisa melihat daftar lagu, mencari lagu berdasarkan judul,
     * menghitung rata-rata durasi, atau kembali ke menu utama.
     */
    static void menuMember(Member member, Admin admin, java.util.Scanner sc) {
        member.tampilkanAkses();
        boolean aktif = true;
        while (aktif) {
            System.out.println("\n--- MENU MEMBER ---");
            System.out.println("1. Lihat Daftar Lagu");
            System.out.println("2. Cari Lagu");
            System.out.println("3. Hitung Rata-rata Durasi");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1":
                    member.lihatDaftarLagu(admin);
                    break;

                case "2":
                    System.out.print("\n>> Masukkan judul yang ingin dicari: ");
                    String keyword = sc.nextLine();
                    member.cariLagu(admin, keyword);
                    break;

                case "3":
                    member.hitungRataRataDurasi(admin);
                    break;

                case "0":
                    aktif = false;
                    break;

                default:
                    System.out.println("[!] Pilihan tidak dikenal. Silakan pilih 0–3.");
            }
        }
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        // Buat objek Admin dan Member
        // (polymorphism: keduanya disimpan sebagai tipe User)
        Admin  admin  = new Admin("Irsyaan");
        Member member = new Member("Elis");

        System.out.println("============================================");
        System.out.println("  Selamat datang di Sistem Playlist Musik  ");
        System.out.println("  COSC6025 - Tugas Kelompok 1              ");
        System.out.println("============================================");

        boolean jalan = true;
        while (jalan) {
            System.out.println("\n===== MENU UTAMA =====");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Member");
            System.out.println("0. Keluar");
            System.out.print("Pilih peran: ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1":
                    menuAdmin(admin, sc);
                    break;

                case "2":
                    menuMember(member, admin, sc);
                    break;

                case "0":
                    System.out.println("\nTerima kasih! Program selesai.");
                    jalan = false;
                    break;

                default:
                    System.out.println("[!] Pilihan tidak valid. Masukkan 0, 1, atau 2.");
            }
        }

        sc.close();
    }
}