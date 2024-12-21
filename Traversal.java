import java.util.Scanner;

class Traversal {
    MoonKnight moonKnight;
    Kota startingCity;
    nodeHistory top;
    nodeFight front, rear;
    Scanner scanner;

    Traversal(MoonKnight moonKnight) {
        this.moonKnight = moonKnight;
        this.startingCity = null;
        this.top = null;
        this.front = null;
        this.rear = null;
        this.scanner = new Scanner(System.in);
    }

    void printHeading(String title) {
        System.out.println("=========================================");
        System.out.println("          " + title.toUpperCase());
        System.out.println("=========================================");
    }

    void tambahKota(Kota kota) {
        if (startingCity == null) {
            startingCity = kota;
        } else {
            Kota current = startingCity;
            while (current.next != null) {
                current = current.next;
            }
            current.next = kota;
        }
    }

    void tambahEdge(Kota sumber, Kota tujuan, int bobot) {
        sumber.tambahTetangga(tujuan, bobot);
        tujuan.tambahTetangga(sumber, bobot);
    }

    void displayAllCities() {
        printHeading("Daftar Kota");
        Kota current = startingCity;
        while (current != null) {
            System.out.print(current.nama + " -> ");
            Tetangga currentTetangga = current.headTetangga;
            while (currentTetangga != null) {
                System.out.print(currentTetangga.tujuan.nama + " (" + currentTetangga.bobot + ") ");
                currentTetangga = currentTetangga.next;
            }
            System.out.println();
            current = current.next;
        }
        System.out.println("-----------------------------------------");
    }

    void searchAndDisplayVillain() {
        printHeading("Pencarian Villain");
        System.out.print("Masukkan nama villain yang ingin dicari: ");
        String targetVillain = scanner.nextLine(); // Input dari user untuk cari villain yang diinginkan user

        Villain foundVillain = linearSearchVillain(targetVillain);
        if (foundVillain != null) {
            System.out.println("\n!!! " + targetVillain + " ditemukan dengan kekuatan sebesar: " + foundVillain.kekuatan + " !!!");
        } else {
            System.out.println("\n✗ Villain " + targetVillain + " tidak ditemukan !");
        }
    }

    Villain linearSearchVillain(String targetVillain) {
        Kota currentCity = startingCity;

        while (currentCity != null) {
            Villain currentVillain = currentCity.headVillain;
            while (currentVillain != null) {
                if (currentVillain.nama.equals(targetVillain)) {
                    return currentVillain;
                }
                currentVillain = currentVillain.next;
            }
            currentCity = currentCity.next;
        }
        System.out.println("Villain " + targetVillain + " tidak ditemukan di jalur perjalanan.");
        return null;
    }

    void enqueue(Villain villain) {
        nodeFight newNode = new nodeFight(villain);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    Villain dequeue() {
        if (isEmptyFight()) {
            return null;
        }
        Villain villain = front.villain;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return villain;
    }

    boolean isEmptyFight() {
        return front == null;
    }

    void push(String data) {
        nodeHistory newNode = new nodeHistory(data);
        newNode.next = top;
        top = newNode;
    }

    String pop() {
        if (isEmptyHistory()) {
            return null;
        }
        String kota = top.kota;
        top = top.next;
        return kota;
    }

    boolean isEmptyHistory() {
        return top == null;
    }

    void displayHistory() {
        nodeHistory current = top;
        while (current != null) {
            if (current.next == null){
                System.out.print(current.kota);
            } else {
                System.out.print(current.kota + " <= ");
            } 
            current = current.next;
        }
        System.out.println();
    }

    void bubbleSortVillainsInCity(Kota kota) {
        if (kota.headVillain == null || kota.headVillain.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Villain current = kota.headVillain;
            Villain prev = null;
            while (current != null && current.next != null) {
                Villain next = current.next;
                if (current.kekuatan > next.kekuatan) {
                    swapped = true;
                    if (prev == null) {
                        kota.headVillain = next;
                    } else {
                        prev.next = next;
                    }
                    current.next = next.next;
                    next.next = current;
                    prev = next;
                } else {
                    prev = current;
                    current = next;
                }
            }
        } while (swapped);
    }
    
    void battle(Villain villain) {
        printHeading("Battle Start!!!");

        System.out.println("      MOON KNIGHT VS " + villain.nama.toUpperCase());
        System.out.println("------------------------------------");

        while (moonKnight.hp > 0 && villain.HP > 0) {
            System.out.println("\n[STATUS]");
            System.out.println("Moon Knight: HP = " + moonKnight.hp);
            System.out.println(villain.nama + ": HP = " + villain.HP);
            System.out.println("------------------------------------");
            System.out.print("Pilihan: [F] Serang | [D] Bertahan: ");
            String input = scanner.nextLine().toLowerCase();
            
            if (input.equals("f")) {
                villain.HP -= moonKnight.attackPower;
                System.out.println("\n> Moon Knight menyerang " + villain.nama + " sebesar " + moonKnight.attackPower + " damage! <");
                if (villain.HP > 0) {
                    moonKnight.hp -= villain.kekuatan;
                    System.out.println("> " + villain.nama + " menyerang balik sebesar " + villain.kekuatan + " damage! <");
                    System.out.println("> HP Moon Knight berkurang sebesar " + villain.kekuatan + ". Sisa HP: " + moonKnight.hp + " <");
                } else {
                    System.out.println("\n>>> " + villain.nama + " telah dikalahkan! <<<");
                }
            } else if (input.equals("d")) {
                System.out.println(villain.nama + " menyerang tapi tidak berpengaruh karena " + moonKnight.nama + " menangkis serangannya!!!");
            } else {
                System.out.println("\nPilihan tidak valid! Silakan pilih [F] atau [D]");
            }

            if (moonKnight.hp <= 0) {
                System.out.println("\n>>> Moon Knight telah kalah! Pertarungan selesai. <<<");
                return;
            }
        }
    }

    void startTraversal() {
        printHeading("Perjalanan Dimulai!!!");
        
        Kota currentKota = startingCity;

        while (currentKota != null) {
            if (currentKota.headVillain != null && currentKota.headVillain.next != null) {
                bubbleSortVillainsInCity(currentKota);
            }
            currentKota = currentKota.next;
        }

        currentKota = startingCity;
        int totalBobot = 0;
        while (currentKota != null) {
            System.out.println("Moon Knight telah tiba di kota: " + currentKota.nama);
            push(currentKota.nama);

            moonKnight.bonusFaseBulan(currentKota.faseBulan);

            if (currentKota.headVillain != null) {
                Villain currentVillain = currentKota.headVillain;

                while (currentVillain != null) {
                    enqueue(currentVillain);
                    System.out.println("\nVillain ditemukan: " + currentVillain.nama + " (Kekuatan: " + currentVillain.kekuatan + ")");
                    battle(currentVillain);

                    if (moonKnight.hp <= 0) {
                        System.out.println("Moon Knight tidak dapat melanjutkan perjalanan. Game over.");
                        return;
                    } 
                    currentVillain = currentVillain.next;
                }

                while (front != null) {
                    dequeue();
                }
                System.out.println("\nSemua villain di kota " + currentKota.nama + " telah berhasil dikalahkan!\n");

                currentKota.headVillain = null;
            } else {
                System.out.println("Tidak ada villain di kota ini.");
            }
            
            boolean foundUnvisitedCity = false;
            if (currentKota.headTetangga != null) {
                Tetangga minEdge = currentKota.headTetangga;
                Tetangga currentTetangga = currentKota.headTetangga;

                while (currentTetangga != null) {
                    if (!isCityVisited(currentTetangga.tujuan)) {
                        if (currentTetangga.bobot < minEdge.bobot || !foundUnvisitedCity) {
                            minEdge = currentTetangga;
                            foundUnvisitedCity = true;
                        }
                    }
                    currentTetangga = currentTetangga.next;
                }

                if (foundUnvisitedCity) {
                    System.out.println("Moon Knight melanjutkan perjalanan ke kota: " + minEdge.tujuan.nama + " dengan bobot " + minEdge.bobot + " !\n");
                    currentKota = minEdge.tujuan;
                    totalBobot += minEdge.bobot;
                } else {
                    System.out.println("Semua kota telah dikunjungi dengan total bobot sebanyak: " + totalBobot);
                    break;
                }
            } else {
                System.out.println("Tidak ada kota tetangga yang tersedia.");
                break;
            }
        }

        System.out.println("\nRiwayat Perjalanan:");
        displayHistory();

        System.out.println("\nMoon Knight memulai perjalanan mundur untuk memeriksa villain...");
        while (top != null) {
            System.out.println("Moon Knight kembali ke kota: " + pop());
        }
        System.out.println("\nMoon Knight telah selesai memeriksa semua kota dan tidak ada lagi villain yang tersisa di setiap kota!");
    }

    boolean isCityVisited(Kota kota) {
        nodeHistory current = top;
        while (current != null) {
            if (current.kota.equals(kota.nama)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}