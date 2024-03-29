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
- [ ] Analisis performa aplikasi dengan mengacu kepada daftar pertanyaan di bawah dan taruhlah hasil analisis kamu di file README ini.
  - Data apa saja yang dapat diamati melalui grafana?
  - Berapa rata-rata response time dari setiap endpoint?
  - Endpoint mana yang memiliki response time rata-rata paling cepat?
  - Endpoint mana yang memiliki response time rata-rata paling lambat? Berapa response time rata-ratanya?
  - Apa yang menyebabkan response time endpoint tersebut lambat?
  - Berapa response time rata-rata endpoint tersebut setelah dioptimasi?
  - Bagaimana pengaruh endpoint yang lambat tersebut terhadap JVM memory?
- [ ] Berdasarkan hasil profiling dan analisis yang kamu lakukan, periksa fitur yang paling lambat dan lakukan optimasi pada implementasinya.

## Jawaban
1. Data apa saja yang dapat diamati melalui grafana? 
   > Terdapat beberapa data yang dapat diamati melalui Grafana, yaitu:
   > - Quick Facts berupa Uptime, Start time, Heap used, dan Non-Heap used
   > - I/O Overview berupa Rate, Errors, Duration, dan Utilization
   > - JVM Memory berupa JVM Heap, JVM Non-Heap, JVM Total, dan JVM Process Memory
   > - JVM Misc berupa CPU Usage, Load, Threads, Thread States, GC Pressure, Log Events, dan File Descriptors
   > - JVM Memory Pools (Heap) berupa G1 Eden Space, G1 Old Gen, G1 Survivor Space, Metaspace, Compressed Class Space, dan Code Cache
   > - Garbage Collection berupa Collections, Pause Durations, dan Allocated/Promoted
   > - Classloading berupa Classes loaded, Class delta, direct, mapped, dan mapped-'non-volatile memory'
2. Berapa rata-rata response time dari setiap endpoint?
   > Rata-rata response time dari setiap endpoint adalah sebagai berikut: <br>
   > - `/`: 0.16815775 s <br>
   > > http_server_requests_seconds_sum{job="spring_boot_app",uri="/"}/http_server_requests_seconds_count{job="spring_boot_app",uri="/"}
   > - `/api/article`: 0.016582416 s <br>
   > > http_server_requests_seconds_sum{job="spring_boot_app",uri="/api/article"}/http_server_requests_seconds_count{job="spring_boot_app",uri="/api/article"}
   > - `/api/category`: 13.542589166900001 s <br>
   > > http_server_requests_seconds_sum{job="spring_boot_app",uri="/api/category"}/http_server_requests_seconds_count{job="spring_boot_app",uri="/api/category"}
   > - `/category`: 0.0107966625 s <br>
   > > http_server_requests_seconds_sum{job="spring_boot_app",uri="/category"}/http_server_requests_seconds_count{job="spring_boot_app",uri="/category"}
   > - `/article/{id}`: 0.1156326045 s <br>
   > > http_server_requests_seconds_sum{job="spring_boot_app",uri="/article/{id}"}/http_server_requests_seconds_count{job="spring_boot_app",uri="/article/{id}"}
3. Endpoint mana yang memiliki response time rata-rata paling cepat?
   > ```/category``` memiliki response time rata-rata paling cepat, yaitu 0.0107966625 s
4. Endpoint mana yang memiliki response time rata-rata paling lambat? Berapa response time rata-ratanya?
   > ```/api/category``` memiliki response time rata-rata paling lambat, yaitu 13.542589166900001 s
5. Apa yang menyebabkan response time endpoint tersebut lambat?
   > Response time endpoint tersebut lambat karena terdapat operasi setingkat O(N^2) pada bagian pengambilan informasi terhadap suatu category. Informasi yang dibutuhkan sebenarnya hanya yang most recent, tetapi setiap operasi dilakukan pencarian menggunakan pembandingan (linear search) maka dari situ sebaiknya sudah diurutkan saat masuk ke dalam database termasuk querynya yaitu dengan kata kunci pengurutan secara DESCENDING yang disusun di dalam articleRepository.
6. Berapa response time rata-rata endpoint tersebut setelah dioptimasi?
   > Response time rata-rata endpoint ```/api/category``` tersebut setelah dioptimasi adalah 0.1201463125 s
7. Bagaimana pengaruh endpoint yang lambat tersebut terhadap JVM memory?
   > Leak Memory : Objek-objek yang tidak lagi digunakan tetapi tetap ada dalam memori, maka hal ini dapat menyebabkan peningkatan penggunaan memori oleh JVM. Jika memory leak terjadi secara terus-menerus, penggunaan memori akan terus meningkat seiring waktu, yang dapat menyebabkan JVM kehabisan memori dan mengakibatkan kinerja yang buruk atau bahkan kegagalan aplikasi.
   > Object Accumulation : Penggunaan memori dapat terus meningkat seiring bertambahnya jumlah permintaan. Jika objek-objek ini tidak dilepaskan dengan benar setelah digunakan, hal ini dapat menyebabkan peningkatan penggunaan memori dan memori yang tidak terbebaskan. Akumulasi objek yang terus-menerus dapat menyebabkan JVM kehabisan memori dan menyebabkan kinerja yang buruk atau kegagalan aplikasi.