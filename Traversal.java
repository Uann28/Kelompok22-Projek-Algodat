class Traversal {
    MoonKnight moonKnight;
    Kota startingCity;
    nodeHistory top;
    nodeFight front, rear;

    Traversal(MoonKnight moonKnight, Kota startingCity) {
        this.moonKnight = moonKnight;
        this.startingCity = startingCity;
        this.top = null;
        this.front = null;
        this.rear = null;
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
}
