# Design Pattern

<details>
  <summary>Memento Pattern</summary>
  Untuk fitur riwayat belanja, saya menggunakan Memento Pattern karena pola ini cocok untuk menyimpan dan mengelola riwayat objek, memungkinkan penyimpanan snapshot dari objek untuk melacak perubahan. Dalam konteks ini, saya akan menyimpan riwayat belanja pengguna.
</details>

<details>
  <summary>Observer Pattern</summary>
  Untuk fitur 'Cart' atau keranjang belanja, saya menggunakan Observer Pattern. Pattern ini cocok untuk fitur keranjang belanja karena memungkinkan saya untuk memperbarui dan memantau perubahan pada keranjang belanja secara real-time. Misalnya, saat produk ditambahkan atau dihapus dari keranjang, total harga dapat diperbarui secara otomatis.
</details>

# Alasan mengaplikasikan microservice

<details>
  <summary>Kelebihan</summary>
  1. Pembagian tugas yang jelas. Kami berlima adalah mahasiswa yang masing-masing ingin mendapat nilai maksimal, dengan pembagian tugas yang jelas, kita akan lebih menilai dan dinilai berdasarkan performa kita di kelompok.
  2. Pengembangan yang cepat. Setelah saya research dan merasakan sendiri pengaplikasian metode ini, jelas sekali bahwa kita bisa update suatu fitur secara independen. Jadi, jika nanti kita akan perbaharui suatu fitur, kita tidak perlu untuk mengupdate semuanya. Hal itu juga membuat pemeliharaan lebih mudah.
  3. Fleksibel. Dengan menggunakan arsitektur microservices, aplikasi dapat dibangun sebagai serangkaian layanan kecil yang independen satu sama lain. Ini memungkinkan pengembang untuk mengembangkan, menyebarkan, dan memperbarui setiap layanan secara terpisah tanpa mempengaruhi bagian lain dari aplikasi.
  4. Skalabilitas. Arsitektur microservices memungkinkan skalabilitas horizontal, yang berarti Anda dapat meningkatkan kapasitas aplikasi dengan menambah atau mengurangi instance layanan secara independen.
</details>
