import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

// Class untuk menyimpan pasangan Nilai dan Mata Pelajaran
class NilaiPelajaran {
    private String mataPelajaran;
    private int nilai;

    public NilaiPelajaran(String mataPelajaran, int nilai) {
        this.mataPelajaran = mataPelajaran;
        this.nilai = nilai;
    }

    public String getMataPelajaran() {
        return mataPelajaran;
    }

    public int getNilai() {
        return nilai;
    }

    @Override
    public String toString() {
        return mataPelajaran + " (" + nilai + ")";
    }
}

public class App6 {

    private static final int KKM = 78;

    private static ArrayList<NilaiPelajaran> dataNilai = new ArrayList<>();

    // Insertion Sort ArrayList
    public static void insertionSortNilai(ArrayList<NilaiPelajaran> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            NilaiPelajaran key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getNilai() > key.getNilai()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Binary Search untuk mencari nilai di bawah KKM
    public static ArrayList<String> cariMataPelajaranDiBawahKKM(ArrayList<NilaiPelajaran> list, int targetKKM) {

        int low = 0;
        int high = list.size() - 1;
        int indexLulusPertama = list.size();

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid).getNilai() >= targetKKM) {
                indexLulusPertama = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        ArrayList<String> mapelGagal = new ArrayList<>();
        for (int i = 0; i < indexLulusPertama; i++) {
            mapelGagal.add(list.get(i).getMataPelajaran());
        }
        return mapelGagal;
    }

    // Rekursi Total Nilai
    public static int hitungTotalRekursif(ArrayList<NilaiPelajaran> list, int n) {
        if (n <= 0) return 0;
        return list.get(n - 1).getNilai() + hitungTotalRekursif(list, n - 1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== INPUT NILAI UNTUK SNBP ===");
        System.out.print("Masukkan jumlah mata pelajaran: ");
        int jumlah = sc.nextInt();
        sc.nextLine(); // membersihkan newline

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nMata Pelajaran ke-" + (i + 1));

            System.out.print("Nama mata pelajaran: ");
            String mapel = sc.nextLine();

            System.out.print("Nilai: ");
            int nilai = sc.nextInt();
            sc.nextLine();

            dataNilai.add(new NilaiPelajaran(mapel, nilai));
        }

        System.out.println("\nData Awal Nilai: " + dataNilai);

        // LANGKAH 1: Sorting
        insertionSortNilai(dataNilai);
        System.out.println("\n[Langkah 1: Sorting]");
        System.out.println("Nilai Terurut: " + dataNilai);

        // LANGKAH 2: Searching KKM
        System.out.println("\n[Langkah 2: Searching KKM]");
        ArrayList<String> gagalKKM = cariMataPelajaranDiBawahKKM(dataNilai, KKM);

        if (gagalKKM.isEmpty()) {
            System.out.println("Semua mata pelajaran LULUS KKM!");
        } else {
            System.out.println(gagalKKM.size() + " Mata Pelajaran di bawah KKM:");
            System.out.println(gagalKKM);
        }

        // LANGKAH 3: Total Nilai Rekursif
        int total = hitungTotalRekursif(dataNilai, dataNilai.size());
        System.out.println("\n[Langkah 3: Rekursi]");
        System.out.println("Total Nilai: " + total);

        double rataRata = (double) total / dataNilai.size();
        System.out.println("Rata-Rata Nilai: "  + rataRata + "\n");

        System.out.println("Silahkan cek di Google Universitas dan Jurusan yang cocok dengan rata rata anda \n");

    }

   
    }

