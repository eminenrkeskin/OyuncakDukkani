package guzvizejava.HR220059;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;

import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping
public class MyController {

    @GetMapping("/")
    public String Yazdir() {
        return "HR220059, Emine Nur Keskin";
    }

    @GetMapping("/cevap1")
    public String siraliKelimeler(@RequestParam String metin) {
        String[] kelimeler = metin.split("\\s+");
        Arrays.sort(kelimeler);
        return String.join(" ", kelimeler);
    }

    @GetMapping("/cevap2")
    public Integer cevap2(@RequestParam String op, @RequestParam Integer sayi1, @RequestParam Integer sayi2) {
        return hesapla(op, sayi1, sayi2);
    }

    private Integer hesapla(String op, Integer sayi1, Integer sayi2) {
        int result;

        switch (op) {
            case "+":
                result = sayi1 + sayi2;
                break;
            case "-":
                result = sayi1 - sayi2;
                break;
            case "*":
                result = sayi1 * sayi2;
                break;
            case "/":
                if (sayi2 != 0) {
                    result = sayi1 / sayi2;
                } else {
                    return -1;
                }
                break;
            default:
                return -1;
        }

        return result;
    }

    @GetMapping("/cevap3")
    public String tarihSaat() {
        String format = "dd-MM-yyyy HH:mm";
        LocalDateTime tarihSaat = LocalDateTime.now();
        return tarihSaat.format(DateTimeFormatter.ofPattern(format));
    }

    @PostMapping("/cevap4")
        public boolean checkIP (@RequestBody String ip){
            try {
                InetAddress.getByName(ip);
                return true;
            } catch (UnknownHostException e) {
                return false;
            }
        }


    @PostMapping("/cevap5")
    public ResponseEntity<String> processFiles(@RequestBody DosyaOlusturma fileRequest) {
        try {
            String folderPath = fileRequest.getKlasorAdresi();
            String fileName = fileRequest.getSonucDosyasiAdi();
            long sizeThreshold = fileRequest.getSize();

            File resultFile = new File(folderPath, fileName);

            if (!resultFile.exists()) {
                resultFile.createNewFile();
            }

            List<File> largerFiles = findLargerFiles(folderPath, sizeThreshold);

            try (FileWriter writer = new FileWriter(resultFile, true)) {
                for (File file : largerFiles) {
                    writer.write( file.getAbsolutePath() + " " + file.length() + "\n");
                }
            }
            String resultFilePath = resultFile.getAbsolutePath();
            return ResponseEntity.status(HttpStatus.CREATED).body(resultFilePath);
        } catch (IOException e) {
            return null;
        }
    }

    private List<File> findLargerFiles(String folderPath, long sizeThreshold) throws IOException {
        return Files.walk(Path.of(folderPath))
                .filter(Files::isRegularFile)
                .filter(file -> file.toFile().length() > sizeThreshold)
                .map(Path::toFile)
                .toList();
    }

    public static class DosyaOlusturma {
        private String klasorAdresi;
        private long size;
        private String sonucDosyasiAdi;

        public String getKlasorAdresi() {
            return klasorAdresi;
        }

        public void setKlasorAdresi(String klasorAdresi) {
            this.klasorAdresi = klasorAdresi;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getSonucDosyasiAdi() {
            return sonucDosyasiAdi;
        }

        public void setSonucDosyasiAdi(String sonucDosyasiAdi) {
            this.sonucDosyasiAdi = sonucDosyasiAdi;
        }
    }

}
