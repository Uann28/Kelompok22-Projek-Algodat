import java.util.Scanner;

class Traversal {
    MoonKnight moonKnight;
    Kota startingCity;
    nodeHistory top;
    nodeFight front, rear;
    Scanner scanner;

    Traversal(MoonKnight moonKnight, Kota startingCity) {
        this.moonKnight = moonKnight;
        this.startingCity = startingCity;
        this.top = null;
        this.front = null;
        this.rear = null;
        this.scanner = new Scanner(System.in);
    }

    void displayAllCities() {
        System.out.println("Daftar kota di jalur perjalanan:");
        Kota current = startingCity;
        while (current != null) {
            System.out.println("- " + current.nama + " (Fase Bulan: " + current.faseBulan + ")");
            current = current.next;
        }
    }

    Villain linearSearchVillain(String targetVillain) {
        Kota currentCity = startingCity;

        while (currentCity != null) {
            Villain currentVillain = currentCity.headVillain;
            while (currentVillain != null) {
                if (currentVillain.nama.equals(targetVillain)) {
                    System.out.println(targetVillain + " ditemukan di kota " + currentCity.nama + ".");
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
                System.out.print(current.kota + " => ");
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
        while (moonKnight.hp > 0 && villain.HP > 0) {
            System.out.println("\nHP Moon Knight: " + moonKnight.hp);
            System.out.println("HP " + villain.nama + " : " + villain.HP);
            System.out.print("Tekan 'f' untuk menyerang atau 'd' untuk bertahan: ");
            String input = scanner.nextLine().toLowerCase();
            
            if (input.equals("f")) {
                villain.HP -= moonKnight.attackPower;
                System.out.println("Moon Knight menyerang " + villain.nama + " sebesar " + moonKnight.attackPower + " damage!");
                if (villain.HP > 0) {
                    moonKnight.hp -= villain.kekuatan;
                    System.out.println(villain.nama + " menyerang balik sebesar " + villain.kekuatan + " damage!");
                    System.out.println("HP Moon Knight berkurang sebesar " + villain.kekuatan + ". Sisa HP: " + moonKnight.hp);
                } else {
                    System.out.println(villain.nama + " telah dikalahkan!");
                }
            } else if (input.equals("d")) {
                System.out.println(villain.nama + " menyerang tapi tidak berpengaruh karena " + moonKnight.nama + " menangkis serangannya!!!");
            } else {
                System.out.println("Input tidak valid! Silakan tekan 'f' atau 'd' !");
            }

            if (moonKnight.hp <= 0) {
                System.out.println("\n" + moonKnight.nama + " telah dikalahkan! Game over!!!");
                return;
            }
        }
    }
}