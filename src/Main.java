import java.util.Scanner;

public class Main {

    // Array-i ekrana çap edən köməkçi method
    static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] arr = {5, 12, 7, 12, 34, 8, 5, 9, 40, 3};

        System.out.println("Başlanğıc array:");
        printArray(arr);

        // ---------------------------------------------------
        // 1) Artan və azalan qaydada sıralama (Bubble Sort)
        // ---------------------------------------------------
        System.out.println("\n=== 1) Sıralama ===");

        // Orijinal array pozulmasın deyə kopya yaradırıq
        int[] asc = new int[arr.length];
        int[] desc = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            asc[i] = arr[i];
            desc[i] = arr[i];
        }

        // Artan (ascending)
        for (int i = 0; i < asc.length - 1; i++) {
            for (int j = 0; j < asc.length - 1 - i; j++) {
                if (asc[j] > asc[j + 1]) {
                    int temp = asc[j];
                    asc[j] = asc[j + 1];
                    asc[j + 1] = temp;
                }
            }
        }

        // Azalan (descending)
        for (int i = 0; i < desc.length - 1; i++) {
            for (int j = 0; j < desc.length - 1 - i; j++) {
                if (desc[j] < desc[j + 1]) {
                    int temp = desc[j];
                    desc[j] = desc[j + 1];
                    desc[j + 1] = temp;
                }
            }
        }

        System.out.print("Artan:  ");
        printArray(asc);
        System.out.print("Azalan: ");
        printArray(desc);

        // ---------------------------------------------------
        // 2) İki indeksdəki elementlərin yerini dəyişmək (Swap)
        // ---------------------------------------------------
        System.out.println("\n=== 2) Swap ===");
        System.out.println("Array: ");
        printArray(arr);

        System.out.print("Birinci indeksi daxil edin (0-" + (arr.length - 1) + "): ");
        int index1 = scanner.nextInt();
        System.out.print("İkinci indeksi daxil edin (0-" + (arr.length - 1) + "): ");
        int index2 = scanner.nextInt();

        if (index1 < 0 || index1 >= arr.length || index2 < 0 || index2 >= arr.length) {
            System.out.println("Səhv indeks daxil etdiniz!");
        } else {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;

            System.out.println("Swap-dan sonra:");
            printArray(arr);
        }

        // ---------------------------------------------------
        // 3) Yalnız cüt ədədləri saxla, tək ədədləri sil
        // ---------------------------------------------------
        System.out.println("\n=== 3) Yalnız cüt ədədlər ===");

        // Array-in ölçüsü sabit olduğu üçün əvvəlcə cüt ədədlərin sayını tapırıq
        int evenCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evenCount++;
            }
        }

        // Həmin ölçüdə yeni array yaradıb cütləri köçürürük
        int[] evens = new int[evenCount];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                evens[k] = arr[i];
                k++;
            }
        }

        printArray(evens);

        // ---------------------------------------------------
        // 4) Təkrarlanan elementləri sil (hər element 1 dəfə qalsın)
        // ---------------------------------------------------
        System.out.println("\n=== 4) Təkrarlananları sil ===");

        // Müvəqqəti array (maksimum ölçü = orijinal ölçü)
        int[] temp2 = new int[arr.length];
        int uniqueCount = 0;

        for (int i = 0; i < arr.length; i++) {
            boolean exists = false;

            // Bu element artıq müvəqqəti array-də varmı?
            for (int j = 0; j < uniqueCount; j++) {
                if (temp2[j] == arr[i]) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                temp2[uniqueCount] = arr[i];
                uniqueCount++;
            }
        }

        // Dəqiq ölçülü yeni array yaradırıq
        int[] unique = new int[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            unique[i] = temp2[i];
        }

        System.out.println("Unikal elementlər:");
        printArray(unique);

        // ---------------------------------------------------
        // 5) İkinci ən böyük və ikinci ən kiçik ədəd
        // ---------------------------------------------------
        System.out.println("\n=== 5) İkinci böyük / İkinci kiçik ===");

        // Əvvəlcə ən böyük və ən kiçiyi tapırıq
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        // İkinci böyük = max-dan kiçik olanların ən böyüyü
        // İkinci kiçik = min-dən böyük olanların ən kiçiyi
        boolean foundSecondMax = false;
        boolean foundSecondMin = false;
        int secondMax = 0;
        int secondMin = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < max) {
                if (!foundSecondMax || arr[i] > secondMax) {
                    secondMax = arr[i];
                    foundSecondMax = true;
                }
            }
            if (arr[i] > min) {
                if (!foundSecondMin || arr[i] < secondMin) {
                    secondMin = arr[i];
                    foundSecondMin = true;
                }
            }
        }

        if (foundSecondMax) {
            System.out.println("İkinci ən böyük: " + secondMax);
        } else {
            System.out.println("İkinci ən böyük element yoxdur (bütün elementlər eynidir).");
        }

        if (foundSecondMin) {
            System.out.println("İkinci ən kiçik: " + secondMin);
        } else {
            System.out.println("İkinci ən kiçik element yoxdur (bütün elementlər eynidir).");
        }

        scanner.close();
    }
}