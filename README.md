# Tutorial 9
Pada tutorial kali ini, kamu akan menerapkan materi yang sudah kamu pelajari di kelas mengenai `Profiling`. Tidak hanya itu, disini kamu akan mencoba menggunakan Prometheus dan Grafana untuk memonitor dan memvisualisasikan performa sebuah aplikasi.


## Profiling
Sampai saat ini, kamu telah melalui perjalanan cukup panjang. Kamu telah mendapatkan banyak pengetahuan serta pengalaman membangun sebuah aplikasi. Tentu kamu tidak ingin berhenti sampai disana. Kini kamu akan belajar mengidentifikasi dan menganalisis performa aplikasimu. Proses ini tentu sudah kamu kenal, yaitu dinamakan Profiling. Profiling tersebut dapat membantu kamu meningkatkan performa aplikasimu yang akan kamu terapkan pada tutorial ini.


## Spesifikasi Program
Fitur pada aplikasi ini, antara lain:
- Endpoint `/`: Menampilkan list artikel.
  ![](https://i.imgur.com/VzlgCGF.png) <br>

- Endpoint `/views`: Menampilkan list artikel berdasarkan tanggal tertentu.
  ![](https://i.imgur.com/X4gerwB.png) <br>

- Endpoint `/category`: Menampilkan list category.
  ![](https://i.imgur.com/fCEDrVw.png) <br>

- Endpoint `/article/{id}`: Menampilkan detail sebuah artikel.
  ![](https://i.imgur.com/9652SEj.png)

Tutorial ini juga memuat beberapa _guide_ lain yang bisa dibaca untuk memenuhi _requirements_:

- [prometheus.md](prometheus.md): berisi tutorial instalasi `prometheus`
- [grafana.md](grafana.md): berisi tutorial instalasi `grafana`


## Requirements
- [ ] Lakukan set up database seperti yang sudah pernah dilakukan di tutorial sebelumnya. Buat sebuah database baru yang bernama tutorial-9. Pastikan `application.properties` sudah di konfigurasi.
- [ ] Amati fitur pada aplikasi. Pada saat pertama kali dijalankan, akan ada sebuah mekanisme *seeding* untuk mengisi database dengan data-data yang banyak. Harap untuk tidak mengubah kode *seeding*.
- [ ] Lakukan set up Promotheus dan Grafana untuk menampilkan performa aplikasi.
- [ ] Analisis performa aplikasi dan taruhlah hasil analisis kamu di file README ini.
- [ ] Berdasarkan hasil profiling dan analisis yang kamu lakukan, periksa fitur yang paling lambat dan lakukan optimasi pada implementasinya.
