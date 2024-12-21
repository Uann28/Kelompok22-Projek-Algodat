public class Tetangga {
    Kota tujuan; 
    int bobot;       
    Tetangga next;    

    Tetangga(Kota tujuan, int bobot) {
        this.tujuan = tujuan;
        this.bobot = bobot;
        this.next = null;
    }
}