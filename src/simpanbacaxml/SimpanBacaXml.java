/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simpanbacaxml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author ROG
 */
public class SimpanBacaXml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        larik2xml();
        xml2larik();
    }
    
    public static void larik2xml(){
        Mahasiswa[] m = new Mahasiswa[3];
        m[0] = new Mahasiswa("Angga", "21523235");
        m[1] = new Mahasiswa("Vahri", "45367272");
        m[2] = new Mahasiswa("Lukman", "34526278");
        
        XStream xstream = new XStream(new StaxDriver());
        
        String xml = xstream.toXML(m);
        FileOutputStream berkasKeluar;
        
        try{
            byte[] data = xml.getBytes("UTF-8");
            berkasKeluar = new FileOutputStream("berkas2.xml");
            berkasKeluar.write(data);
            berkasKeluar.close();
        }catch (Exception io) {
            System.err.println("Terjadi kesalahan: " + io.getMessage());
        }
    }
    
    public static void xml2larik(){
        Mahasiswa[] m = new Mahasiswa[3];
        
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream berkasMasuk;
        try{
            berkasMasuk = new FileInputStream("berkas2.xml");
            int isi; char c; String s = "";
            while ((isi = berkasMasuk.read()) != -1){
                c = (char) isi;
                s = s + c;
            }
            m = (Mahasiswa[]) xstream.fromXML(s);
            berkasMasuk.close();
        }catch (Exception e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        }
        for (Mahasiswa mhs : m){
            System.out.println(mhs + " ");
        }System.out.println("");
    }
}
